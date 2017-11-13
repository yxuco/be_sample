package com.tibco.psg.bestats;

import com.tibco.be.functions.engine.EngineFunctions;
import com.tibco.be.model.functions.BEFunction;
import com.tibco.be.model.functions.BEPackage;
import com.tibco.be.model.functions.FunctionDomain;
import com.tibco.be.model.functions.FunctionParamDescriptor;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@BEPackage(catalog = "BEStats", category = "Stats")
public class InfluxDBHelper {

	private static OkHttpClient influxClient = null;
	private static String influxUrl = null;
	private static String defaultMeasurement = "stat";
	
	@BEFunction(name="initInfluxDB", 
		signature="void initInfluxDB(String host, String port, String database, String user, String password, String measurement)", 
		params={@FunctionParamDescriptor(name="host", type="String", desc="host name of influxDB for monitoring data"),
			@FunctionParamDescriptor(name="port", type="String", desc="HTTP service port for influxDB API"),
			@FunctionParamDescriptor(name="database", type="String", desc="database name for monitoring data in influxDB; or null to use default \"bemon\""),
			@FunctionParamDescriptor(name="user", type="String", desc="user name for accessing influxDB API; or null if no authentication."),
			@FunctionParamDescriptor(name="password", type="String", desc="password for accessing influxDB API; or null if no authentication."),
			@FunctionParamDescriptor(name="measurement", type="String", desc="default measurment (aka table) of influxDB to store monitoring stats; or null to use default \"stat\".")
		}, 
		freturn=@FunctionParamDescriptor(name="", type="void", desc=""), 
		version="5.4", see="", description="initialize HTTP client for influxDB API.", 
		cautions="", fndomain={FunctionDomain.ACTION}, example="")
	public static void initInfluxDB(String host, String port, String database, String user, String password, String measurement) {
		if (null == influxClient) {
			influxClient = new OkHttpClient();
		}
		HttpUrl.Builder urlBuilder = HttpUrl.parse(String.format("http://%s:%s/write", host, port)).newBuilder();
		urlBuilder.addQueryParameter("db", null == database ? "bemon" : database);
		if (user != null) {
			urlBuilder.addQueryParameter("u", user);
			urlBuilder.addQueryParameter("p", password);
		}
		influxUrl = urlBuilder.build().toString();
		if (measurement != null) {
			defaultMeasurement = measurement;
		}
	}
	
	public static OkHttpClient getInfluxClient() {
		return influxClient;
	}

	@BEFunction(name="writeStat", 
		signature="void writeStat(String category, long value, String measurement)", 
		params={@FunctionParamDescriptor(name="category", type="String", desc="Name of the stat catetory"),
			@FunctionParamDescriptor(name="value", type="long", desc="value to be added to the stat"),
			@FunctionParamDescriptor(name="measurement", type="String", desc="name of the measurement; or null to use the default name specified by initInfluxDB().")
		}, 
		freturn=@FunctionParamDescriptor(name="", type="String", desc="InfluxDB status starting with a log level WARN, DEBUG, or INFO. null if not written to InfluxDB."), 
		version="5.4", see="", description="update in-memory stat; and write total to influxDB if it has passed reset-interval since the last reset.", 
		cautions="", fndomain={FunctionDomain.ACTION}, example="")
	public static String writeStat(String category, long value, String measurement) {
		String session = "";
		String source = "";
		try {
			session = EngineFunctions.sessionName();
		
			// set source to rule or function name
			source = EngineFunctions.ruleFunctionName();
			if (null == source || source.isEmpty()) {
				// check ruleName only if it is not inside a function because rule may call function
				source = EngineFunctions.ruleName();
				if (source != null && !source.isEmpty()) {
					int index = source.indexOf("=");  // remove prefix "Rule=" from the returned value of ruleName()
					if (index > 0) {
						source = source.substring(index+1);
					}
				}
			}
		} catch (Exception e) {}
		session = session.replaceAll("\\.", "/");
		source = source.replaceAll("\\.", "/");

		String uniqueCategory = String.format("%s.%s.%s", session, source, category);
		long[] stat = StatsHelper.updateStat(uniqueCategory, value);
		if (stat != null) {
			String msg = influxCommand(uniqueCategory, stat, measurement);
			if (influxClient != null) {
				// write to influxDB
				MediaType BINARY = MediaType.parse("binary/octet-stream");
				Request request = new Request.Builder()
						.url(influxUrl)
						.post(RequestBody.create(BINARY, msg.getBytes()))
						.build();
				try {
					Response response = influxClient.newCall(request).execute();
					if (!response.isSuccessful()) {
						return String.format("WARN: Failed writing stat. InfluxDB returned status %d - %s. Request: %s", response.code(), response.message(), msg);
					} else {
						return String.format("DEBUG: Written stat to InfluxDB. Status %d - %s. Request: %s", response.code(), response.message(), msg);					
					}
				} catch (Exception e) {
					// reset influx client, so it won't try to write stats again
					influxClient = null;
					return String.format("ERROR: Exception caught while writing stat to InfluxDB: %s. %s", msg, e.getMessage());
				}
			} else if (stat.length == 3){
				return String.format("INFO: InfluxDB not configured. Ignore stat: %s", msg);					
			}
		}
		return null;
	}
	
	private static String influxCommand(String category, long[] stat, String measurement) {
		if (null == stat || stat.length < 3) {
			return null;
		}
		String engineName = "";
		String threadName = "";
		try {
			engineName = EngineFunctions.engineName();
			threadName = EngineFunctions.threadName();
		} catch (Exception e) {}
		StringBuffer buff = new StringBuffer(null == measurement ? defaultMeasurement : measurement);
		String[] catTokens = category.split("\\.");  // tokens: "session.source.category"
		if (catTokens.length > 0 && catTokens[0] != null && !catTokens[0].isEmpty()) {
			buff.append(",session=").append(catTokens[0]);			
		}
		if (catTokens.length > 1 && catTokens[1] != null && !catTokens[1].isEmpty()) {
			buff.append(",source=").append(catTokens[1]);			
		}
		if (catTokens.length > 2 && catTokens[2] != null && !catTokens[2].isEmpty()) {
			buff.append(",category=").append(catTokens[2]);
		}
		if (!engineName.isEmpty()) {
			buff.append(",engine=").append(engineName);
		}
		if (!threadName.isEmpty()) {
			buff.append(",thread=").append(threadName);
		}
		buff.append(String.format(" count=%d,value=%d,interval=%d", stat[0], stat[1], stat[2]));
		return buff.toString();
	}

	/**
	 * test writing stat to InfluxDB, assuming already set database=bemon and measurement=stat
	 * @param args
	 */
	public static void main(String[] args) {
		// initialize DB connection
		initInfluxDB("localhost","8086", "bemon", "admin", "admin", "stat");
		StatsHelper.initResetInterval(1000); // specify interval for automatic reset
		for (long i = 0; i < 100; i++) {
			long now = System.nanoTime();
			try {
				Thread.sleep(16);
			} catch (Exception e) {}
			String msg = writeStat("TEST", (System.nanoTime() - now)/1000000, null);
			if (msg != null) {
				System.out.println(msg);
				break;
			}
		}
		
		// construct request to store a point
		String msg = "stat,category=TEST,engine=SimpleHTTP_PU,session=default,source=BETest count=1,interval=1000,value=60000";
		MediaType BINARY = MediaType.parse("binary/octet-stream");
		Request request = new Request.Builder()
				.url(influxUrl)
				.post(RequestBody.create(BINARY, msg.getBytes()))
				.build();

		// verify client support for HTTP/2; although influxDB still uses HTTP/1.1
		for (Protocol prot : influxClient.protocols()) {
			System.out.println("Supports protocol: " + prot.name());
		}
		
		// synchronous call to store a measurement point
		try {
			Response response = influxClient.newCall(request).execute();
			System.out.println("response: " + response.toString());
			System.out.println("response body: " + response.body().string());
		} catch (Exception e) {
			e.printStackTrace();
		}

//		// async call to store a measurement point
//		influxClient.newCall(request).enqueue(new Callback() {
//			@Override
//			public void onFailure(Call call, IOException e) {
//				e.printStackTrace();
//			}
//
//			@Override
//			public void onResponse(Call call, Response response) {
//				try {
//					if (!response.isSuccessful()) {
//						System.out.println("Failed return code: " + response.code());
//					} else {
//						System.out.println("response: " + response.toString());
//					}
//					Buffer buff = new Buffer();
//					request.body().writeTo(buff);
//					System.out.println("Original request: " + buff.readUtf8());
//					buff.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}
}
