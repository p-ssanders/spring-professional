package dev.samsanders.study.springprofessional.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleLifecycleBean {

    private static final Logger logger = LoggerFactory.getLogger(ExampleLifecycleBean.class);

    void init() {
        logger.info("initializing");
    }

    void destroy() {
        logger.info("destroying");
    }

}
