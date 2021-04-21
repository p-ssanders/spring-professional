package dev.samsanders.study.springprofessional.app;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class ExampleBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        BeanDefinition exampleSingletonBean = configurableListableBeanFactory.getBeanDefinition("exampleSingletonBean");
        exampleSingletonBean.setAttribute("some-attribute", "some-value");
    }
}
