package dev.samsanders.study.springprofessional.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

public class ExampleBeanPostProcessor implements BeanPostProcessor, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(ExampleBeanPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        logger.debug(String.format("processing bean before initialization: %s", beanName));
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        logger.debug(String.format("processing bean after initialization: %s", beanName));
        return bean;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
