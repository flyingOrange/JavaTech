package test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestSomething {

    @Test
    public void readFileNames() {
        // 通过LoggerFactory获取Logger实例
        final Logger logger = LoggerFactory.getLogger(TestSomething.class);
        logger.info("testlog: {}", "test"); 

        logger.debug("testlog: {}", "test");

        logger.error("testlog: {}", "test");

        logger.trace("testlog: {}", "test");

        logger.warn("testlog: {}", "test");

    }

}
