package common.security;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.zip.CRC32;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

/*
 * 循环冗余校验算法
 * CRC-32算法是各种压缩算法中最常用的数据完整性校验算法
 * */
public class CRCandFileCheck {
    
    @Test
    //CRC32校验库用法
    public void crc32() {
        String str = "测试CRC-32";
        CRC32 crc32 = new CRC32();
        crc32.update(str.getBytes());
        
        String hex = Long.toHexString(crc32.getValue());
        System.out.println("原文:\t"+str);
        System.out.println("crc-32:\t"+hex);
    }
    
    @Test
    /* 文件校验，验证文件的MD5值
     * 假设文件的MD5值为  26acafa23ee86c5aa2de5ce600fc68ad
     * 
     * */
    public void fileCheckSum() throws Exception {
        String path = "D:\\mysql-win32.msi";
        FileInputStream fis = new FileInputStream(new File(path));
        //初始化MessageDigest,并指定MD5算法
        DigestInputStream dis = new DigestInputStream(fis,MessageDigest.getInstance("MD5"));
        //缓冲字节数组
        int buf = 1024;
        byte[] buffer = new byte[buf]; 
        int read = dis.read(buffer, 0, buf);
        while(read > -1) {
            read = dis.read(buffer, 0, buf);
        }
        dis.close();
        //获得MessageDigest
        MessageDigest md = dis.getMessageDigest();
        //摘要处理
        byte[] b = md.digest();
        String md5hex = Hex.encodeHexString(b);
        //等价方法
        String md5hex2 = DigestUtils.md5Hex(fis);
        assertEquals(md5hex,md5hex2);
        
        //验证
        assertEquals(md5hex,"26acafa23ee86c5aa2de5ce600fc68ad");
        
    }
    

}
