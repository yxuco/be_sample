package bestats.test;

import static org.junit.Assert.*;
import static com.tibco.psg.bestats.StatsHelper.*;

import org.junit.Test;

public class StatsHelperTest {

	@Test
	public void testRandomStatUpdate() {
		for (long i = 0; i < 100; i++) {
			updateStat("Random", (long) (Math.random() * 1000.0F));
		}
		long[] stat = getStat("Random", false);
		assertEquals("total count is 100", 100, stat[0]);
		assertTrue("total elapsed time < 20 ms", stat[2] < 20);
		assertTrue("total value between 20K and 100K", stat[1] < 100000 && stat[1] > 20000);
	}

	@Test
	public void testConstantStatUpdate() {
		for (long i = 0; i < 30; i++) {
			long now = System.nanoTime();
			try {
				Thread.sleep(16);
			} catch (Exception e) {}
			updateStat("Constant", (System.nanoTime() - now)/1000000);
		}
		long[] stat = getStat("Constant", false);
		assertEquals("total count is 30", 30, stat[0]);
		assertTrue("total elapsed time between 480 and 700 ms", stat[2] >= 480 && stat[2] <= 700);
		assertTrue("total value between 480 and 700", stat[1] >= 480 && stat[1] <= 700);
	}
	
	@Test
	public void testResetStat() {
		updateStat("Reset", (long) (Math.random() * 1000.0F));
		long[] stat = getStat("Reset", true); // get and reset stats
		assertTrue("total count > 0", stat[0] > 0);
		stat = getStat("Reset", false);
		assertEquals("stat is reset", 0, stat[0]);
	}

	@Test
	public void testResetInterval() {
		initResetInterval(1000); // specify interval for automatic reset
		updateStat("Interval", 16);
		for (long i = 0; i < 100; i++) {
			long now = System.nanoTime();
			try {
				Thread.sleep(16);
			} catch (Exception e) {}
			long[] stat = updateStat("Interval", (System.nanoTime() - now)/1000000);
			if (stat != null) {
				assertTrue("stat reset after specified time interval", stat[2] >= 1000);
				stat = getStat("Interval", false);
				assertEquals("stat is reset", 1, stat[0]);
				return;
			}
		}
		fail("did not reset stat after specified time interval");
	}
}
