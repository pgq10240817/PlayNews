package com.yhpl.utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

/**
 * Json工具类，实现JSON与Java Bean的互相转换 User: shijing <span style=
 * "font-family: Arial, Helvetica, sans-serif;">2015年4月3日上午10:42:19</span>
 */

public class JsonUtils {

	private static ObjectMapper objectMapper = new ObjectMapper();
	private static JsonFactory jsonFactory = new JsonFactory();

	/**
	 * 泛型返回，json字符串转对象 2015年4月3日上午10:42:19 auther:shijing
	 * 
	 * @param jsonAsString
	 * @param pojoClass
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @throws IOException
	 */
	public static <T> T fromJson(String jsonAsString, Class<T> pojoClass)
			throws JsonMappingException, JsonParseException, IOException {
		return objectMapper.readValue(jsonAsString, pojoClass);
	}

	public static <T> T fromJson(FileReader fr, Class<T> pojoClass) throws JsonParseException, IOException {
		return objectMapper.readValue(fr, pojoClass);
	}

	/**
	 * Object对象转json 2015年4月3日上午10:41:53 auther:shijing
	 * 
	 * @param pojo
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 * @throws IOException
	 */
	public static String toJson(Object pojo) {
		String result = "";
		try {
			result = toJson(pojo, false);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static String toJson(Object pojo, boolean prettyPrint)
			throws JsonMappingException, JsonGenerationException, IOException {
		StringWriter sw = new StringWriter();
		JsonGenerator jg = jsonFactory.createJsonGenerator(sw);
		if (prettyPrint) {
			jg.useDefaultPrettyPrinter();
		}
		objectMapper.writeValue(jg, pojo);
		return sw.toString();
	}

	public static void toJson(Object pojo, FileWriter fw, boolean prettyPrint)
			throws JsonMappingException, JsonGenerationException, IOException {
		JsonGenerator jg = jsonFactory.createJsonGenerator(fw);
		if (prettyPrint) {
			jg.useDefaultPrettyPrinter();
		}
		objectMapper.writeValue(jg, pojo);
	}

	/**
	 * json字符串转Map 2015年4月3日上午10:41:25 auther:shijing
	 * 
	 * @param jsonStr
	 * @return
	 * @throws IOException
	 */
	public static Map<String, Object> parseMap(String jsonStr) throws IOException {
		Map<String, Object> map = objectMapper.readValue(jsonStr, Map.class);
		return map;
	}

	public static JsonNode parse(String jsonStr) throws IOException {
		JsonNode node = null;
		node = objectMapper.readTree(jsonStr);
		return node;
	}

	public static ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public static JsonNode jsonToJsonNode(String json) {
		JsonNode result = null;
		try {
			result = objectMapper.readTree(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (result == null) {
			result = objectMapper.createObjectNode();
		}
		return result;
	}

	public static JsonNode objToJsonNode(Object obj) {
		JsonNode result = objectMapper.valueToTree(obj);
		return result;
	}
}
