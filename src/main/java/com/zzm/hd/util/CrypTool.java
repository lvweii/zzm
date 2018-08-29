package com.zzm.hd.util;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class CrypTool {	

	/**
	 * DES算法用到的向量
	 */
    private static final byte[] DESIV = { (byte)0x12,(byte) 0x34, (byte)0x56, (byte)0x78, (byte)0x90,   
            (byte)0xAB, (byte)0xCD,(byte) 0xEF };//设置向量，略去   
    
    /**
     * 十六进制字符数组
     */
    private final static byte[] hex = "0123456789ABCDEF".getBytes();
    
    /**
     * 十六进制字符串到字节数组转换用到的中间函数
     * @param c
     * @return
     */
    private static int parse(char c) { 
    	  if (c >= 'a') 
    	   return (c - 'a' + 10) & 0x0f; 
    	  if (c >= 'A') 
    	   return (c - 'A' + 10) & 0x0f; 
    	  return (c - '0') & 0x0f; 
    }
    
    /**
     * 从字节数组到十六进制字符串转换
     * @param bcd
     * @throws Exception
     */
    public static final String bytesToHex(byte[] bcd){
    	byte[] buff = new byte[2 * bcd.length]; 
    	  for (int i = 0; i < bcd.length; i++) { 
    	   buff[2 * i] = hex[(bcd[i] >> 4) & 0x0f]; 
    	   buff[2 * i + 1] = hex[bcd[i] & 0x0f]; 
    	  } 
    	return new String(buff);
    }
    
    /**
     * 从十六进制字符串到字节数组转换
     * @param hexstr
     * @return
     */
    public static byte[] HexToBytes(String hexstr) { 
    	  byte[] b = new byte[hexstr.length() / 2]; 
    	  int j = 0; 
    	  for (int i = 0; i < b.length; i++) { 
    	   char c0 = hexstr.charAt(j++); 
    	   char c1 = hexstr.charAt(j++); 
    	   b[i] = (byte) ((parse(c0) << 4) | parse(c1)); 
    	  } 
    	  return b; 
    }
    
    /**
     * 通用加密函数
     * @param data
     * @param param
     * @return
     * @throws Exception
     */
    public static  String encode(String data, String param) throws Exception {
    	if (data==null || param==null) {
			return null;
		}
    	byte [] DESkey = param.getBytes();//设置密钥，略去   
    	DESKeySpec keySpec = new DESKeySpec(DESkey);//设置密钥参数   
    	AlgorithmParameterSpec iv = new IvParameterSpec(DESIV);//设置向量 ,加密算法的参数接口，IvParameterSpec是它的一个实现  
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");//获得密钥工厂   
        Key key = keyFactory.generateSecret(keySpec);//得到密钥对象   
        Cipher enCipher  =  Cipher.getInstance("DES/CBC/PKCS5Padding");//得到加密对象Cipher   
        enCipher.init(Cipher.ENCRYPT_MODE,key,iv);//设置工作模式为加密模式，给出密钥和向量   
        byte[] pasByte = enCipher.doFinal(data.getBytes("utf-8"));   
        return bytesToHex(pasByte);
        
//        System.out.println(pasByte.toString());
//        BASE64Encoder base64Encoder = new BASE64Encoder();   
//        return base64Encoder.encode(pasByte);   
    }   
   
    /**
     * 通用解密函数
     * @param data
     * @param param
     * @return
     * @throws Exception
     */
    public static String decode(String data, String param) throws Exception{   
    	if (data==null || param==null) {
			return null;
		}
    	byte [] DESkey = param.getBytes();//设置密钥，略去   
    	DESKeySpec keySpec = new DESKeySpec(DESkey);//设置密钥参数   
    	AlgorithmParameterSpec iv = new IvParameterSpec(DESIV);//设置向量 ,加密算法的参数接口，IvParameterSpec是它的一个实现 
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");//获得密钥工厂
    	Key key = keyFactory.generateSecret(keySpec);//得到密钥对象       	
        Cipher deCipher   =  Cipher.getInstance("DES/CBC/PKCS5Padding");   
        deCipher.init(Cipher.DECRYPT_MODE,key,iv);   
//        BASE64Decoder base64Decoder = new BASE64Decoder();          
//        byte[] pasByte=deCipher.doFinal(base64Decoder.decodeBuffer(data));
        byte[] pasByte=deCipher.doFinal(HexToBytes(data));   //将输入的十六进制字符串转换为字节数值后解密
        return new String(pasByte,"UTF-8");   
    }   
    
//    public static void main(String[] args) throws Exception{   
//        String value = "hello";   
//        System.out.println(CrypTool.encode(value,"123"));   
//        System.out.println("解密"+CrypTool.decode(CrypTool.encode(value,"ising@ws"),"123"));   
//    }
}
