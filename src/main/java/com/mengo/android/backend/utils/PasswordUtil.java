package com.mengo.android.backend.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密密码
 */
public class PasswordUtil {
    public static String encryptionPassword(String pwd,String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");//获取MD5实例
        md.update((pwd+salt).getBytes());//传入要加密的byte类型值
        byte[] digest = md.digest();//得到md5加密后的byte类型值
        int i;
        StringBuilder sb = new StringBuilder();
        for (int offset = 0; offset<digest.length;offset++){
            i = digest[offset];
            if (i<0)
                i+=256;
            if (i<16)
                sb.append(0);
            sb.append(Integer.toHexString(i));//通过Integer.toHexString方法把值变为16进制

        }
        return sb.toString().substring(0,20);
    }


}
