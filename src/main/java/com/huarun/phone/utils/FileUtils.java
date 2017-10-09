package com.huarun.phone.utils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileUtils {
	/**
	 * 追加文件：使用FileWriter
	 * 
	 * @param fileName
	 * @param content
	 */
	public static void write(String fileName, String content) {
		FileWriter writer = null;
		BufferedWriter bw = null;
		try {
			// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			writer = new FileWriter(fileName, true);
			bw = new BufferedWriter(writer);
			writer.write(content);
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			

		}
	}

	/**
	 * 读取手机文件内容
	 * 
	 * @param filePath
	 * @return
	 */
	public static Map<String, Object> getPhoneNoListFromFile(String filePath) {
		Map<String, Object> map = new HashMap<String, Object>();
		BufferedReader br = null;
		String line = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
			while ((line = br.readLine()) != null) {
				map.put(line, line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	
}
