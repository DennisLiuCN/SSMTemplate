package org.bluesky.ssm.spring;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;

/**
 * 使用jasypt对资源文件加密测试
 * @author: liuyuefeng
 * @date: 2015年10月29日 下午4:37:11
 * @version: V1.0
 *
 */
public class EncryptUtilsTest {
	public static void main(String[] args) {
		encrypt();
		decrypt();
	}

	/**
	 * 加密测试
	 * 
	 * @return: void
	 * @throws:
	 */
	private static void encrypt() {
		// 加密配置
		EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
		// 加密算法
		config.setAlgorithm("PBEWithMD5AndDES");
		// 自己在用的时候更改此密码
		config.setPassword("PBEWithMD5AndDES");

		// 加密工具
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		// 应用配置
		encryptor.setConfig(config);

		// 加密
		String plaintext = "bzSysWin@14";
		String ciphertext = encryptor.encrypt(plaintext);
		System.out.println(plaintext + " : " + ciphertext);
	}

	/**
	 * 解密测试
	 * 
	 * @return: void
	 * @throws:
	 */
	private static void decrypt() {
		// 加密配置
		EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
		config.setAlgorithm("PBEWithMD5AndDES");
		// 自己在用的时候更改此密码
		config.setPassword("PBEWithMD5AndDES");

		// 加密工具
		StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
		// 应用配置
		decryptor.setConfig(config);

		// 解密
		String ciphertext = "LNHXUSBr6jlA5Uqy3V7forofx7DiRRiu";
		String plaintext = decryptor.decrypt(ciphertext);
		System.out.println(ciphertext + " : " + plaintext);
	}
}
