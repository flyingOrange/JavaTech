package common.apache.commons.StringUtils;
import org.apache.commons.lang3.StringUtils;

public class StringUtilsUsage {

	public static void main(String[] args) {
		String str1 = "" ;
        String str2 = "" ;
        String str3 = "\t" ;
        String str4 = null ;
        String str5 = "123" ;
        String str6 = "ABCDEFG" ;
        String str7 = "Itfeels good to use apache commons.\r\n" ;
        String str8 = "     ABCDEFG     " ;
        

        // check for blank strings
        System.out.println( "==============================" );
        System.out.println( "Is str1 blank? " +StringUtils.isBlank(str1));
        System.out.println( "Is str2 blank? " +StringUtils.isBlank(str2));
        System.out.println( "Is str3 blank? " +StringUtils.isBlank(str3));
        System.out.println( "Is str4 blank? " +StringUtils.isBlank(str4));

        // check for empty strings      判断字符串是否为""或者null
        System.out.println( "==============================" );
        System.out.println( "Is str1 empty? " +StringUtils.isEmpty(str1));
        System.out.println( "Is str2 empty? " +StringUtils.isEmpty(str2));
        System.out.println( "Is str3 empty? " +StringUtils.isEmpty(str3));
        System.out.println( "Is str4 empty? " +StringUtils.isEmpty(str4));
        
        // 任意一个参数为空的话，返回true
        System.out.println( "==============================" );
        System.out.println( "Is str3 str4 any empty? " +StringUtils.isAnyEmpty(str3,str4));
        System.out.println( "Is str3 str4 any blank? " +StringUtils.isAnyBlank(str3,str4));

        //移除字符串两端的空字符串
        System.out.println( "==============================" );
        System.out.println( "str8: "+StringUtils.trim(str8));
        
        // check for numerics
        System.out.println( "==============================" );
        System.out.println( "Is str5 numeric? " +StringUtils.isNumeric(str5));
        System.out.println( "Is str6 numeric? " +StringUtils.isNumeric(str6));

        // reverse strings / whole words
        System.out.println( "==============================" );
        System.out.println( "str6: " + str6);
        System.out.println( "str6reversed: " + StringUtils.reverse(str6));
        System.out.println( "str7: " + str7);
        String temp = StringUtils.chomp(str7);
        temp =StringUtils.reverseDelimited(temp, ' ' );
        System.out.println( "str7 reversed whole words:" + temp);

        // build header (useful to print logmessages that are easy to locate)
        System.out.println( "==============================" );
        System.out.println( "print header:" );
        String padding = StringUtils.repeat( "=" , 50);
        String msg = StringUtils.center( " Customised Header " , 50, "%" );
        Object[] raw = new Object[]{padding, msg,padding};
        String header = StringUtils.join(raw, "\r\n" );
        System.out.println(header);

	}

}
