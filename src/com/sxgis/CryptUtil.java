package com.sxgis;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * <ul>
 * <li>BASE64的加密解密是双向的，可以求反解。</li>
 * <li>MD5、SHA以及HMAC是单向加密，任何数据加密后只会产生唯一的一个加密串，通常用来校验数据在传输过程中是否被修改。</li>
 * <li>HMAC算法有一个密钥，增强了数据传输过程中的安全性，强化了算法外的不可控因素。</li>
 * <li>DES DES-Data Encryption Standard,即数据加密算法。 DES算法的入口参数有三个:Key、Data、Mode。
 * <ul>
 * <li>Key:8个字节共64位,是DES算法的工作密钥;</li>
 * <li>Data:8个字节64位,是要被加密或被解密的数据;</li>
 * <li>Mode:DES的工作方式,有两种:加密或解密。</li>
 * </ul>
 * </li>
 * <ul>
 * 
 * @author Ice_Liu
 * 
 */
public class CryptUtil {
	public static void main(String[] args) throws Exception {
		try {
			// Generate a key for the HMAC-MD5 keyed-hashing algorithm; see RFC
			// 2104
			// In practice, you would save this key.

			// KeyGenerator keyGen = KeyGenerator.getInstance("HmacMD5");
			// SecretKey key = keyGen.generateKey();
			SecretKeySpec key = new SecretKeySpec("sxgis".getBytes(), "HmacMD5");

			// Create a MAC object using HMAC-MD5 and initialize with key
			Mac mac = Mac.getInstance(key.getAlgorithm());
			mac.init(key);

			String str = "Passw0rd";

			// Encode the string into bytes using utf-8 and digest it
			byte[] utf8 = str.getBytes("utf-8");
			byte[] digest = mac.doFinal(utf8);

			// If desired, convert the digest into a string
			String digestB64 = new sun.misc.BASE64Encoder().encode(digest);
			System.out.println("加密后:" + digestB64);
		} catch (InvalidKeyException e) {
		} catch (NoSuchAlgorithmException e) {
		}
	}
}