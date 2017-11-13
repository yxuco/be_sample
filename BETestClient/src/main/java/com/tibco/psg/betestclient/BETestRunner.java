package com.tibco.psg.betestclient;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BETestRunner {
	private static OkHttpClient httpClient = null;
	private static String testUrl = null;
	
	public static void initTestConnection(String host, int port) {
		if (null == httpClient) {
			httpClient = new OkHttpClient();
		}
		testUrl = String.format("http://%s:%s/testservice/test/function", host, port);
	}

	public static String sendTestRequest(String functionName,
			boolean isPreproc) throws Exception {
		
		HttpUrl.Builder urlBuilder = HttpUrl.parse(testUrl).newBuilder();
		urlBuilder.addQueryParameter("name", functionName);
		urlBuilder.addQueryParameter("preproc", String.valueOf(isPreproc));
		Request request = new Request.Builder()
				.url(urlBuilder.build().toString())
				.build();
		Response response = httpClient.newCall(request).execute();
		if (response.isSuccessful()) {
			return response.body().string();
		} else {
			throw new Exception("Unexpected status " + response.code() + " - " + response.message());
		}
	}

	public static void assertRuleFunction(String functionName, boolean isPreproc) {
		try {
			String msg = sendTestRequest(functionName, isPreproc);
			if (!"true".equals(msg)) {
				org.junit.Assert.fail(msg);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
