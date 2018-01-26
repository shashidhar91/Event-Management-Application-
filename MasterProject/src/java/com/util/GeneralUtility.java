package com.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import org.apache.commons.io.IOUtils;


public class GeneralUtility {
	
	
	public static byte[] TransformImageToByte(InputStream inputStream) {
		
		byte[] imageData = null;
		try {
			imageData = IOUtils.toByteArray(inputStream);
		} catch (IOException e2) {

			e2.printStackTrace();
		}
		return imageData;
	}
	
	public static void uploadImage(String path, String fileName, InputStream in) {
		try {
			File file = new File(path);
	            
	        if(!file.exists()) {
	          	 file.mkdirs();
	        }
	        OutputStream out = new FileOutputStream(new File(file,fileName));
	        int read = 0;
	        byte[] bytes = new byte[1024];
	        while ((read = in.read(bytes)) != -1) {
	        	out.write(bytes, 0, read);
	        }
	        in.close();
	        out.flush();
	        out.close();
	   } catch (IOException e) {
		   e.printStackTrace();
	   }
	}
	
	public static byte[] readImage(String path) {
		File file = new File(path);
		byte[] photoBytes = null;
		if(file.exists()) {
			Path photoPath =  Paths.get(path);
			try {
				photoBytes = Files.readAllBytes(photoPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return photoBytes;
	}
	// ############################### End Employee Read Image  ##############################################################
	public static String generateBarcode() {
		char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		sb.append("-");
		char[] numbers = "1234567890".toCharArray();
		for (int i = 0; i < 3; i++) {
			char c = numbers[random.nextInt(numbers.length)];
			sb.append(c);
		}
		return sb.toString();
	}
}
