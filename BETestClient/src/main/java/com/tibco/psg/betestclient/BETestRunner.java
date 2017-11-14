package com.tibco.psg.betestclient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BETestRunner {
	private static OkHttpClient httpClient = null;
	private static String testUrl = null;

	/**
	 * initialize HTTP connection of the BE engine that opened to receive test requests
	 * 
	 * @param host hostname of the BE engine to be tested
	 * @param port HTTP listening port of the BE engine for test requests
	 */
	public static void initTestConnection(String host, int port) {
		if (null == httpClient) {
			httpClient = new OkHttpClient();
		}
		testUrl = String.format("http://%s:%s/testservice/test/function", host, port);
	}

	/**
	 * Send a test request to BE engine; the request is to invoke a BE rule-function on the BE engine, which returns a "true" or an error message.
	 * 
	 * @param functionName BE rule-function to be invoked
	 * @param isPreproc true to execute the rule-funciton in preproc context; false to execute rules
	 * @return "true" if test passed, or an error message if test failed
	 * @throws Exception Exception if failed to send HTTP request.
	 */
	private static String sendTestRequest(String functionName, boolean isPreproc) throws Exception {
		HttpUrl.Builder urlBuilder = HttpUrl.parse(testUrl).newBuilder();
		urlBuilder.addQueryParameter("name", functionName);
		urlBuilder.addQueryParameter("preproc", String.valueOf(isPreproc));
		Request request = new Request.Builder().url(urlBuilder.build().toString()).build();
		Response response = httpClient.newCall(request).execute();
		if (response.isSuccessful()) {
			return response.body().string();
		} else {
			throw new Exception("Unexpected status " + response.code() + " - " + response.message());
		}
	}

	/**
	 * JUnit test wrapper. jUnit tests should use this method to execute a test that is implemented by a BE rule-function.
	 * 
	 * @param functionName name of the BE rule-function for a test
	 * @param isPreproc true if execute the test in preprocessor context
	 */
	public static void assertRuleFunction(String functionName, boolean isPreproc) {
		try {
			String msg = sendTestRequest(functionName, isPreproc);
			if (!"true".equals(msg)) {
				org.junit.Assert.fail(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			org.junit.Assert.fail("Caught exception: " + e.getLocalizedMessage());
		}
	}

	/**
	 * Start a BE engine. It is typically invoked before starting the tests. Command and args for starting the engine is configured in a file.
	 * The config file contains be-engine command and its arguments in separate lines, and the last line is the name of the working directory of the engine.
	 * 
	 * @param commandConfig name of the config file
	 * @param waitSeconds seconds to wait after the command is executed
	 * @return the Process instance of the BE engine
	 */
	public static Process startBEEngine(String commandConfig, long waitSeconds) {
		try {
			ArrayList<String> cmd = new ArrayList<String>();
			BufferedReader reader = new BufferedReader(new FileReader(commandConfig));
			String arg = "";
			while ((arg = reader.readLine()) != null) {
				arg = arg.trim();
				if (!arg.isEmpty() && !arg.startsWith("#")) {
					cmd.add(arg);
				}
				System.out.println("BE engine arg: " + arg);
			}
			reader.close();

			String workDir = cmd.remove(cmd.size() - 1);
			System.out.println("workDir: " + workDir);

			ProcessBuilder pb = new ProcessBuilder(cmd);
			pb.directory(new File(workDir));
			Process p = pb.start();
			System.out.println("Starting BE engine, wait " + waitSeconds + " seconds ...");
			p.waitFor(waitSeconds, TimeUnit.SECONDS);
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Stop a process, e.g., the BE engine started for the test.  It is typically invoked after tests are done.
	 * 
	 * @param process the Process object returned by the startBEEngine()
	 * @param waitMillis milliseconds to wait after the shutdown command is executed
	 * @return exit status code of the process, or -1 if exception is thrown.
	 */
	public static int stopProcess(Process process, long waitMillis) {
		if (null == process) {
			return 0;
		}
		try {
			if (process.isAlive()) {
				System.out.println("Shutting down BE engine ...");
				process.destroyForcibly();
				Thread.sleep(waitMillis);
			}
			return process.exitValue();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
}
