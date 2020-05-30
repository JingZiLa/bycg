package com.bycg.conmmon.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
/**
 * @Description: MD5数据加密 工具类
 * @className: MD5Utils
 * @Author: Mirror
 * @CreateDate: 2020/5/20 18:05
 **/
public class MD5Utils {
	/**
	 * 使用md5的算法进行加密
	 */
	public static String md5(String plainText) {
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(
					plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("没有md5这个算法！");
		}
		String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
		// 如果生成数字未满32位，需要前面补0
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}

	public static void main(String[] args) {
//		System.out.println(md5("admin"));
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		String format = simpleDateFormat.format(new Date());
//		System.out.println(format);
//		format = "2019-09-12";
//		int parseInt = Integer.parseInt(format.substring(5,7));
//
//		System.out.println(parseInt);
//		int  random = new Random().nextInt(1000);
//		int  randoms = new Random().nextInt(10000);
//		System.out.println(format+random+randoms);

		 String parameter = "1, 2, 3, ";
		 String[] split = parameter.split(", ");
		 System.out.println(split.toString());
		 Long[] orderArr = new Long[split.length];
		 for (int i = 0; i < split.length; i++) {
			 orderArr[i] = Long.parseLong(split[i]);
			 System.out.println(orderArr[i]);
		}

	}

}
