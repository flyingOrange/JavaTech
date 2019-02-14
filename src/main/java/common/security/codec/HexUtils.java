package common.security.codec;

import org.junit.Test;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
public class HexUtils {

    @Test
    //16进制转化为字符串
    public void byte2string() throws DecoderException {
        byte[] data = {(byte)0x02,(byte)0x3f,(byte)0x19,(byte)0xba};
        
        String str1 = Hex.encodeHexString(data).toUpperCase();
        char[] c = Hex.encodeHex(data,false);
        String str2 = new String(c);
        //输出结果 E23F19BA
        System.out.println(str1);
        System.out.println(str2);
    }
    
    @Test
    //字符串转16进制
    public void string2byte() throws DecoderException {
        String str = "023F19BA";
        
        byte[] data = Hex.decodeHex(str.toCharArray());
        System.out.println(Hex.encodeHexString(data).toUpperCase());
    }
    
    @Test
    public void common() {
        byte binVal = 0b01010100;
        System.out.println(binVal);
        
        byte hexVal = 0x03e;
        System.out.println(hexVal);
        
        int dVal = 10000000;
        System.out.println(Long.toBinaryString(dVal));
        
        int i = 10000000;
        char c = (char)i;
        i = c;
        System.out.println(i);
        
    }
    
}
