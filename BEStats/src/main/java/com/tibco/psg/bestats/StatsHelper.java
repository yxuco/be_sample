package com.tibco.psg.bestats;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import com.tibco.be.model.functions.BEFunction;
import com.tibco.be.model.functions.BEPackage;
import com.tibco.be.model.functions.FunctionDomain;
import com.tibco.be.model.functions.FunctionParamDescriptor;

@BEPackage(catalog = "BEStats", category = "Stats")
public class StatsHelper {
	/**
	 * time interval in millis for stats reset
	 */
	private static long RESET_INTERVAL = -1;
	private static ConcurrentHashMap<String, StatValue> statMap = new ConcurrentHashMap<String, StatValue>();

	@BEFunction(name="initResetInterval", 
		signature="void initResetInterval(long resetMillis)", 
		params={@FunctionParamDescriptor(name="resetMillis", type="long", desc="time interval in millis for resetting stats")}, 
		freturn=@FunctionParamDescriptor(name="", type="void", desc=""), 
		version="5.4", see="", description="initialize time interval for stat reset, default -1 means never reset", 
		cautions="", fndomain={FunctionDomain.ACTION}, example="")
	public static void initResetInterval(long resetMillis) {
		RESET_INTERVAL = resetMillis;
	}

	@BEFunction(name="updateStat", 
		signature="long[] updateStat(String category, long value)", 
		params={@FunctionParamDescriptor(name="category", type="String", desc="Name of the stat catetory"),
			@FunctionParamDescriptor(name="value", type="long", desc="value to be added to the stat")
		}, 
		freturn=@FunctionParamDescriptor(name="", type="long[]", desc="array of [count, value, time_interval_millis] before the update if the stat is reset; null otherwise."), 
		version="5.4", see="", description="add a stat value of a catagory to in-memory cache. If reset-interval is set by initResetInterval(), it'll automatically reset the stat and return the previous value if last reset time is older than the specified reset-interval.", 
		cautions="", fndomain={FunctionDomain.ACTION}, example="")
	public static long[] updateStat(String category, long value) {
		StatValue stat = statMap.get(category);
		if (null == stat) {
			stat = new StatValue();
			statMap.put(category, stat);
		}
		return stat.addValue(value);
	}

	@BEFunction(name="getStat", 
		signature="long[] updateStat(String category, boolean reset)", 
		params={@FunctionParamDescriptor(name="category", type="String", desc="Name of the stat catetory"),
			@FunctionParamDescriptor(name="reset", type="boolean", desc="true to reset the stat after fetching the value")
		}, 
		freturn=@FunctionParamDescriptor(name="", type="long[]", desc="array of [count, value, time_interval_millis] before the stat is reset."), 
		version="5.4", see="", description="return stat value of a catagory, or null if it does not exist", 
		cautions="", fndomain={FunctionDomain.ACTION}, example="")
	public static long[] getStat(String category, boolean reset) {
		StatValue stat = statMap.get(category);
		if (null == stat) {
			return null;
		} else {
			return stat.getValue(reset);
		}
	}

	@BEFunction(name="allStats", 
		signature="String allStats(boolean reset)", 
		params={@FunctionParamDescriptor(name="reset", type="boolean", desc="true to reset the stat after fetching the value")}, 
		freturn=@FunctionParamDescriptor(name="", type="String", desc="comma-delimited value for category,total-count,average-value-per-count,average-count-per-second since last reset. categories are delimited by semi-colon."), 
		version="5.1", see="", description="Return total-count, value-per-event, and event-per-second since the last reset for all categories.", 
		cautions="", fndomain={FunctionDomain.ACTION}, example="")
	public static String allStats(boolean reset) {
		StringBuffer buff = new StringBuffer();
		boolean empty = true;
		for (String cat : statMap.keySet()) {
			long[] value = statMap.get(cat).getValue(reset);
			String stat = String.format("%s,%d,%.2f,%.2f", cat, value[0], 
					value[0] > 0 ? ((double) value[1])/value[0] : 0.0,
					value[2] > 0 ? value[0]*1000.0/value[2] : 0.0);
			if (!empty) {
				buff.append(';');
			}
			buff.append(stat);
			empty = false;
		}
		return buff.toString();
	}

	/**
	 * data structure to keep in-memory cache of event count and total value of a stat measurement.
	 */
	private static class StatValue {
		AtomicLong lastResetMillis;
		AtomicLong count;
		AtomicLong value;
		
		StatValue() {
			this.lastResetMillis = new AtomicLong(System.currentTimeMillis());
			this.count = new AtomicLong(0);
			this.value = new AtomicLong(0);
		}
		
		/**
		 * add count and value if the elapsed time since last reset is less than RESET_INTERVAL,
		 * otherwise, reset the stat, and return the old count and value.
		 * 
		 * @param value stat value to be added
		 * @return null if no value reset, otherwise, return array of [count, value, time_interval] before reset
		 */
		long[] addValue(long value) {
			if (RESET_INTERVAL > 0) {
				long lastReset = this.lastResetMillis.get();
				long newTimeMillis = System.currentTimeMillis();
				if (lastReset + RESET_INTERVAL <= newTimeMillis) {
					// reset and return old count and value
					boolean updated = this.lastResetMillis.compareAndSet(lastReset, newTimeMillis);
					if (updated) {
						return new long[]{this.count.getAndSet(1), this.value.getAndSet(value), newTimeMillis - lastReset};
					}
				}
			}
			// no reset, simply increase the count and value
			this.count.incrementAndGet();
			this.value.addAndGet(value);
			return null;
		}
		
		/**
		 * return the current value in array [count, value, time_interval_since_last_reset].
		 * reset stat if the arg reset=true.
		 * 
		 * @param reset true to reset stats after fetching the value
		 * @return return array of [count, value, time_interval] before reset
		 */
		long[] getValue(boolean reset) {
			long lastReset = this.lastResetMillis.longValue();
			long newTimeMillis = System.currentTimeMillis();
			if (reset) {
				// reset and return old count and value
				this.lastResetMillis.set(newTimeMillis);
				return new long[]{this.count.getAndSet(0), this.value.getAndSet(0), newTimeMillis - lastReset};
			} else {
				return new long[]{this.count.get(), this.value.get(), newTimeMillis - lastReset};
			}
		}
	}
	
	/**
	 * test stat collection
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String test1 = "Random";
		for (long i = 0; i < 100; i++) {
			updateStat(test1, (long) (Math.random() * 1000.0F));
		}
		long[] stat = getStat(test1, false);
		String statStr = String.format("%s:%d,%d,%d", test1, stat[0], stat[1], stat[2]);
		System.out.println(statStr);

		String test2 = "Constant";
		for (long i = 0; i < 100; i++) {
			long now = System.nanoTime();
			Thread.sleep(16);
			StatsHelper.updateStat(test2, (System.nanoTime() - now)/1000000);
		}
		stat = getStat(test2, false);
		statStr = String.format("%s:%d,%d,%d", test2, stat[0], stat[1], stat[2]);
		System.out.println(statStr);
		System.out.println("All Stats: " + allStats(false));

		for (long i = 0; i < 100; i++) {
			long now = System.nanoTime();
			Thread.sleep(32);
			StatsHelper.updateStat(test2, (System.nanoTime() - now)/1000000);
		}
		stat = getStat(test2, true);
		statStr = String.format("%s:%d,%d,%d", test2, stat[0], stat[1], stat[2]);
		System.out.println(statStr);
		System.out.println("All Stats: " + allStats(false));
	}
}
