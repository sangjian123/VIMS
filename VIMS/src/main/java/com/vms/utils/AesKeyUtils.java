package com.vms.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vms.constant.GeneralConstant;

/**
 * Aes加密工具类
 *
 * @author tfl
 * @date 2016年12月5日 下午3:10:42
 */
public class AesKeyUtils
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AesKeyUtils.class);
    
    /**
     * 加密算法是AES
     */
    private static final String ALGORITHM = "AES";
    
    private static final String SECURE_ALGORITHM = "SHA1PRNG";
    
    private static final int KEY_SIZE = 128;
    
    /**
     * cookie中url加密私钥
     */
    private static final String ENCRYPT_COOKIE_URL_KEY = "ami@&**@@@@";
    
    /**
     * 加密
     *
     * @param content
     *            待加密内容
     * @return
     */
    public static String encrypt(String content, String keyStr)
    {
        try
        {
            KeyGenerator kgen = KeyGenerator.getInstance(ALGORITHM);
            SecureRandom secureRandom = SecureRandom.getInstance(SECURE_ALGORITHM);
            secureRandom.setSeed(keyStr.getBytes(GeneralConstant.CHARACTER_CODING));
            kgen.init(KEY_SIZE, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);// 创建密码器
            byte[] byteContent = content.getBytes(GeneralConstant.CHARACTER_CODING);
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return bytes2Hex(result); // 加密
        }
        catch (Exception e)
        {
            LOGGER.error("AesKeyUtils.encrypt Error : ", e);
        }
        return null;
    }
    
    /**
     * 解密
     * 
     * @param content
     *            待解密内容
     * @return
     */
    public static String decrypt(String content, String keyStr)
    {
        try
        {
            KeyGenerator kgen = KeyGenerator.getInstance(ALGORITHM);
            SecureRandom secureRandom = SecureRandom.getInstance(SECURE_ALGORITHM);
            secureRandom.setSeed(keyStr.getBytes(GeneralConstant.CHARACTER_CODING));
            kgen.init(KEY_SIZE, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(hexStr2Byte(content));
            return new String(result, GeneralConstant.CHARACTER_CODING); // 加密
        }
        catch (Exception e)
        {
            LOGGER.error("AesKeyUtils.decrypt Error : " + e);
        }
        return null;
    }
    
    public static void main(String[] args)
    {
        String password =
            AesKeyUtils
                .decrypt(
                    "b3650746dbf3dd6cc8d4ed2f76e9fef53c0363fca2da6eac31868fc030cf45fed873e69f813cc7bac1e3c2a6610e06f0cbeaefb01f3f44cb50f2f6f2a21ec765ea6c046d05988f769405bb02adafcb5e5a6de33061d316973f29e3deb87efcfc3508b07cfdcebc3ed8b2ddccb19159caa9cf1a9a9d6ecdbac6c60fe3c2375dbf",
                    "FdMyKBIVvziQPBocuCbZv2sRSJA1pUCj06cX9DchoNLiNVVt99WvcudltPQNsh");
        System.out.println(password);
    }
    
    /**
     * 转为16进制
     *
     * @param bts
     *            加密后内容
     * @return
     */
    private static String bytes2Hex(byte[] bts)
    {
        StringBuilder des = new StringBuilder();
        String tmp = null;
        for(int i = 0; i < bts.length; i++)
        {
            tmp = Integer.toHexString(bts[i] & 0xFF);
            if(tmp.length() == 1)
            {
                des.append("0");
            }
            des.append(tmp);
        }
        return des.toString();
    }
    
    /**将16进制转换为二进制 
     * @param hexStr 
     * @return 
     */
    public static byte[] hexStr2Byte(String hexStr)
    {
        if(hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for(int i = 0; i < hexStr.length() / 2; i++)
        {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
    
    /**
     * 给保存在cookie中的url加密
     *
     * @param url
     *            需要加密的url
     * @return 加密后密文
     */
    public static String encryptCookieURL(String url)
    {
        if(StringUtils.isBlank(url))
        {
            return url;
        }
        try
        {
            byte[] urlBytes = url.getBytes(GeneralConstant.CHARACTER_CODING);
            SecureRandom secureRandom = SecureRandom.getInstance(SECURE_ALGORITHM);
            secureRandom.setSeed(ENCRYPT_COOKIE_URL_KEY.getBytes(GeneralConstant.CHARACTER_CODING));
            
            KeyGenerator kgen = KeyGenerator.getInstance(ALGORITHM);
            kgen.init(KEY_SIZE, secureRandom);
            
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, ALGORITHM);
            
            // 创建密码器
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(urlBytes);
            
            return new String(Base64.encodeBase64(result), GeneralConstant.CHARACTER_CODING);
        }
        catch (NoSuchAlgorithmException e)
        {
            LOGGER.error("AesKeyUtils.encryptCookieURL Error : ", e);
        }
        catch (NoSuchPaddingException e)
        {
            LOGGER.error("AesKeyUtils.encryptCookieURL Error : ", e);
        }
        catch (InvalidKeyException e)
        {
            LOGGER.error("AesKeyUtils.encryptCookieURL Error : ", e);
        }
        catch (BadPaddingException e)
        {
            LOGGER.error("AesKeyUtils.encryptCookieURL Error : ", e);
        }
        catch (IllegalBlockSizeException e)
        {
            LOGGER.error("AesKeyUtils.encryptCookieURL Error : ", e);
        }
        catch (UnsupportedEncodingException e)
        {
            LOGGER.error("AesKeyUtils.encryptCookieURL Error : ", e);
        }
        return null;
    }
    
    /**
     * 给加密后的cookie中的url解密
     *
     * @param code
     *            加密后的url
     * @return 解密后的url
     */
    public static String decryptCookieURL(String code)
    {
        if(StringUtils.isBlank(code))
        {
            return code;
        }
        try
        {
            
            // 进行Base64解密
            byte[] encryptBytes = Base64.decodeBase64(code.getBytes(GeneralConstant.CHARACTER_CODING));
            SecureRandom secureRandom = SecureRandom.getInstance(SECURE_ALGORITHM);
            secureRandom.setSeed(ENCRYPT_COOKIE_URL_KEY.getBytes(GeneralConstant.CHARACTER_CODING));
            
            KeyGenerator kgen = KeyGenerator.getInstance(ALGORITHM);
            kgen.init(KEY_SIZE, secureRandom);
            
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, ALGORITHM);
            
            // 创建密码器
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] result = cipher.doFinal(encryptBytes);
            
            return new String(result, GeneralConstant.CHARACTER_CODING);
        }
        catch (NoSuchAlgorithmException e)
        {
            LOGGER.error("AesKeyUtils.decryptCookieURL Error : ", e);
        }
        catch (NoSuchPaddingException e)
        {
            LOGGER.error("AesKeyUtils.decryptCookieURL Error : ", e);
        }
        catch (InvalidKeyException e)
        {
            LOGGER.error("AesKeyUtils.decryptCookieURL Error : ", e);
        }
        catch (IllegalBlockSizeException e)
        {
            LOGGER.error("AesKeyUtils.decryptCookieURL Error : ", e);
        }
        catch (BadPaddingException e)
        {
            LOGGER.error("AesKeyUtils.decryptCookieURL Error : ", e);
        }
        catch (UnsupportedEncodingException e)
        {
            LOGGER.error("AesKeyUtils.decryptCookieURL Error : ", e);
        }
        
        return null;
    }
    
}