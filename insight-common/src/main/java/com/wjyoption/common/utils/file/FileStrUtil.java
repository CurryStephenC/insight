package com.wjyoption.common.utils.file;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import sun.misc.BASE64Decoder;
 
@SuppressWarnings("restriction")
public class FileStrUtil {
	/**
	 * summary:将字符串存储为文件 采用Base64解码
	 * 
	 * @param fileStr
	 * @param outfile
	 * 
	 */
	public static File streamSaveAsFile(InputStream is, String outFileStr) {
		FileOutputStream fos = null;
		File file = new File(outFileStr);
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			fos = new FileOutputStream(file);
			byte[] buffer = decoder.decodeBuffer(is);
			fos.write(buffer, 0, buffer.length);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				is.close();
				fos.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				throw new RuntimeException(e2);
			}
		}
		return file;
	}
 
	/**
	 * 
	 * 
	 * summary:将字符串存储为文件
	 * 
	 * @param fileStr
	 * @param outfile
	 * 
	 */
	public static File stringSaveAsFile(String fileStr, String outFilePath) {
		InputStream out = new ByteArrayInputStream(fileStr.getBytes());
		return FileStrUtil.streamSaveAsFile(out, outFilePath);
	}
 
	/**
	 * 将流转换成字符串 使用Base64加密
	 * 
	 * @param in输入流
	 * @return
	 * @throws IOException
	 */
	public static String streamToString(InputStream inputStream) throws IOException {
		byte[] bt = toByteArray(inputStream);
		inputStream.close();
		String out = new sun.misc.BASE64Encoder().encodeBuffer(bt);
		return out;
	}
 
	/**
	 * 将流转换成字符串
	 * 
	 * @param in输入流
	 * @return
	 * @throws IOException
	 */
	public static String fileToString(String filePath) throws IOException {
		File file = new File(filePath);
		FileInputStream is = new FileInputStream(file);
		String fileStr = FileStrUtil.streamToString(is);
		return fileStr;
	}
 
	/**
	 * 
	 * summary:将流转化为字节数组
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 * 
	 */
	public static byte[] toByteArray(InputStream inputStream) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024 * 4];
		byte[] result = null;
		try {
			int n = 0;
			while ((n = inputStream.read(buffer)) != -1) {
				out.write(buffer, 0, n);
			}
			result = out.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			out.close();
		}
		return result;
	}
 
	public static void main(String[] args) throws Exception {
		String fromPath = "G:\\test\\1P306233S7-3.jpg";
		String toPath = "G:\\a\\a\\b.jpg";
		String fileStr = FileStrUtil.fileToString(fromPath);
		FileStrUtil.stringSaveAsFile(fileStr, toPath);
	}
}