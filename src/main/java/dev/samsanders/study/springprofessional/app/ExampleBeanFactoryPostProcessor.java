package dev.samsanders.study.springprofessional.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class ExampleBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(ExampleBeanFactoryPostProcessor.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        logger.debug(String.format("Total BeanPostProcessor count: %d", configurableListableBeanFactory.getBeanPostProcessorCount()));
    }
}
