package common.apache.commons.StringUtils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomStringUtilsUsage {

    public static void main(String[] args) {

        /**
         * count 创建一个随机字符串，其长度是指定的字符数,字符将从参数的字母数字字符集中选择，如参数所示。 letters
         * true,生成的字符串可以包括字母字符 numbers true,生成的字符串可以包含数字字符
         * public static String random(int count, boolean letters, boolean numbers)
         */
        String random = RandomStringUtils.random(15, true, false);
        System.out.println(random);

        /**
         * 创建一个随机字符串，其长度是指定的字符数。
         * 将从所有字符集中选择字符
         * public static String random(int count)
         */
        random = RandomStringUtils.random(15);
        System.out.println(random);
        
        /**
         * 创建一个随机字符串，其长度是指定的字符数。
         * 字符将从字符串指定的字符集中选择，不能为空。如果NULL，则使用所有字符集。
         * public static String random(int count, String chars)
         */
        random = RandomStringUtils.random(15, "abcdefgABCDEFG123456789");
        System.out.println(random);
        
        /**
         * 创建一个随机字符串，其长度是指定的字符数,字符将从参数的字母数字字符集中选择，如参数所示。
         * count:计算创建的随机字符长度
         * start:字符集在开始时的位置
         * end:字符集在结束前的位置，必须大于65
         * letters true,生成的字符串可以包括字母字符
         * numbers true,生成的字符串可以包含数字字符
         * public static String random(int count, int start,int end,boolean letters, boolean numbers)
         */
        random = RandomStringUtils.random(1009, 49, 66, true, true);
        System.out.println(random);
        
        /**
         * 产生一个长度为指定的随机字符串的字符数，字符将从拉丁字母（a-z、A-Z的选择）。
         * count:创建随机字符串的长度
         * public static String randomAlphabetic(int count)
         */
        random = RandomStringUtils.randomAlphabetic(15);
        System.out.println(random);
        
        /**
         * 创建一个随机字符串，其长度介于包含最小值和最大最大值之间,，字符将从拉丁字母（a-z、A-Z的选择）。
         * minLengthInclusive ：要生成的字符串的包含最小长度
         * maxLengthExclusive ：要生成的字符串的包含最大长度
         * public static String randomAlphabetic(int minLengthInclusive, int maxLengthExclusive)
         */
        random = RandomStringUtils.randomAlphabetic(2, 15);
        System.out.println(random);

        /**
         * 创建一个随机字符串，其长度是指定的字符数，字符将从拉丁字母（a-z、A-Z）和数字0-9中选择。
         * count ：创建的随机数长度
         * public static String randomAlphanumeric(int count)
         */
        random = RandomStringUtils.randomAlphanumeric(15);
        System.out.println(random);

        /**
         * 创建一个随机字符串，其长度介于包含最小值和最大最大值之间,字符将从拉丁字母（a-z、A-Z）和数字0-9中选择。
         * minLengthInclusive ：要生成的字符串的包含最小长度
         * maxLengthExclusive ：要生成的字符串的包含最大长度
         * public static String randomAlphanumeric(int minLengthInclusive,int maxLengthExclusive
         */
        random = RandomStringUtils.randomAlphanumeric(5, 68);
        System.out.println(random);
        
        /**
         * 创建一个随机字符串，其长度是指定的字符数，字符将从ASCII值介于32到126之间的字符集中选择（包括）
         * count:随机字符串的长度
         * public static String randomAscii(int count)
         */
        random = RandomStringUtils.randomAscii(15);
        System.out.println(random);
        
        /**
         * 创建一个随机字符串，其长度介于包含最小值和最大最大值之间,字符将从ASCII值介于32到126之间的字符集中选择（包括）
         * minLengthInclusive ：要生成的字符串的包含最小长度
         * maxLengthExclusive ：要生成的字符串的包含最大长度
         * public static String randomAscii(int minLengthInclusive, int maxLengthExclusive)
         */
        random = RandomStringUtils.randomAscii(15, 30);
        System.out.println(random);
        
        /**
         * 创建一个随机字符串，其长度是指定的字符数，将从数字字符集中选择字符。
         * count:生成随机数的长度
         * public static String randomNumeric(int count)
         */
        random = RandomStringUtils.randomNumeric(15);
        System.out.println(random);

        /**
         * 创建一个随机字符串，其长度介于包含最小值和最大最大值之间,将从数字字符集中选择字符.
         * minLengthInclusive, 要生成的字符串的包含最小长度
         * maxLengthExclusive 要生成的字符串的包含最大长度
         * public static String randomNumeric(int minLengthInclusive, int maxLengthExclusive)
         */
        random = RandomStringUtils.randomNumeric(15, 20);
        System.out.println(random);

    }

}
