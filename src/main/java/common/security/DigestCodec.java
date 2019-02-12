package common.security;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

/*
 * 几种摘要算法(除base64)
 * */
public class DigestCodec {
    @Test
    // Base64编解码
    public void base64() throws UnsupportedEncodingException {
        String str = "zchaha";

        str = Base64.encodeBase64String(str.getBytes("UTF-8"));
        System.out.println("Base64编码后: " + str);

        str = new String(Base64.decodeBase64(str), "UTF-8");
        System.out.println("Base64解码后: " + str);
    }

    @Test
    // MD5加密(不可逆)
    public void MD5() throws UnsupportedEncodingException {
        String str = "zchaha";

        str = new String(DigestUtils.md5Hex(str.getBytes("UTF-8")));
        System.out.println("MD5编码后: " + str);
    }

    @Test
    //SHA加密(不可逆)
    public void SHA() throws UnsupportedEncodingException {
        String str = "zchaha";
        
        str = new String(DigestUtils.sha512Hex(str));
        System.out.println("SHA编码后: "+str);
    }

}
