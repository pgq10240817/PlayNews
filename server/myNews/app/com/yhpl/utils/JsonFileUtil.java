package com.yhpl.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonFileUtil {

	public static Object getGetUrlContentAsObject(String url, Class clazz) {
		Object result = null;

		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url.toString());
		InputStream inStrem = null;
		try {
			HttpResponse response = client.execute(get);
			int status = response.getStatusLine().getStatusCode();
			if (status == HttpStatus.SC_OK) {
				inStrem = response.getEntity().getContent();
				// InputStreamReader input = new InputStreamReader(inStrem);
				// BufferedReader reader = new BufferedReader(input);
				try {
					InputStreamReader isr = new InputStreamReader(inStrem, "UTF-8");
					result = JsonUtils.getObjectMapper().readValue(isr, clazz);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {

			}
		} catch (IOException e) {
			Logger.errorWithFmt("IOException", "%s", e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (inStrem != null) {
					inStrem.close();
				}
			} catch (Exception e) {
				// skip
			}
		}
		return result;
	}

	public static JsonNode getGetUrlContentAsJsonNode(String url) {
		JsonNode result = null;

		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url.toString());
		InputStream inStrem = null;
		try {
			HttpResponse response = client.execute(get);
			int status = response.getStatusLine().getStatusCode();
			if (status == HttpStatus.SC_OK) {
				inStrem = response.getEntity().getContent();
				try {
					InputStreamReader isr = new InputStreamReader(inStrem, "UTF-8");
					result = JsonUtils.getObjectMapper().readTree(isr);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {

			}
		} catch (IOException e) {
			Logger.errorWithFmt("IOException", "%s", e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (inStrem != null) {
					inStrem.close();
				}
			} catch (Exception e) {
				// skip
			}
		}
		return result;
	}

}
