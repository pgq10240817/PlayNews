package com.yhpl.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import play.libs.Json;

public class GsonUtil {

	public static final String PATH = "txt" + System.currentTimeMillis() + "/";
	public static final String SUFFIX = ".txt";

	public static final String CHARSET = "UTF-8";

	public static boolean write2File(List list, String fileName) {

		File path = new File(PATH);
		if (!path.exists()) {
			path.mkdirs();
		}
		boolean result = false;
		try {

			String s = Json.toJson(list).toString();

			File out = new File(PATH + fileName + SUFFIX);
			if (!out.exists()) {
				out.createNewFile();
			} else {
				out = new File(PATH + fileName + System.currentTimeMillis() + SUFFIX);
				if (out.exists()) {
					out.delete();
				}
				out.createNewFile();

			}
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(out));
			bos.write(s.getBytes(CHARSET));
			bos.flush();
			bos.close();
			result = true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public static boolean write2File(Object obj, String path, String fileName) {

		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		boolean result = false;
		try {
			String s = Json.toJson(obj).toString();

			File out = new File(path, fileName + SUFFIX);
			if (!out.exists()) {
				out.createNewFile();
			} else {
				out = new File(fileName + SUFFIX);
				if (out.exists()) {
					out.delete();
				}
				out.createNewFile();

			}
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(out));
			bos.write(s.getBytes(CHARSET));
			bos.flush();
			bos.close();
			result = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static boolean write2File(String content, String path, String fileName) {

		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		boolean result = false;
		try {
			File out = new File(path, fileName + SUFFIX);
			if (!out.exists()) {
				out.createNewFile();
			} else {
				out = new File(fileName + SUFFIX);
				if (out.exists()) {
					out.delete();
				}
				out.createNewFile();

			}
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(out));
			bos.write(content.getBytes(CHARSET));
			bos.flush();
			bos.close();
			result = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static <T> List<T> getListFromFile(Class<T> t, String fileName) {
		List<T> mList = null;
		File gsonFile = new File(fileName);
		if (gsonFile.exists()) {
			BufferedInputStream bis = null;
			try {

				bis = new BufferedInputStream(new FileInputStream(gsonFile));
				StringBuilder mRawString = new StringBuilder();
				byte[] data = new byte[1024];
				int length = 0;
				while (0 < (length = bis.read(data))) {

					mRawString.append(new String(data, 0, length, CHARSET));
				}
				ArrayNode arrayNodes = (ArrayNode) Json.parse(mRawString.toString());
				Iterator<JsonNode> iter = arrayNodes.iterator();
				mList = new ArrayList<T>();
				while (iter.hasNext()) {
					JsonNode childNode = iter.next();
					T child = Json.fromJson(childNode, t);
					mList.add(child);
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (bis != null) {
					try {
						bis.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

		return mList;
	}

	public static <T> T getObjctFromInputStream(Class<T> t, InputStream in) {
		T mResult = null;
		BufferedInputStream bis = null;
		try {

			bis = new BufferedInputStream(in);
			StringBuilder mRawString = new StringBuilder();
			byte[] data = new byte[1024];
			int length = 0;
			while (0 < (length = bis.read(data))) {

				mRawString.append(new String(data, 0, length, CHARSET));
			}
			JsonNode node = Json.parse(mRawString.toString());
			mResult = Json.fromJson(node, t);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return mResult;
	}
}
