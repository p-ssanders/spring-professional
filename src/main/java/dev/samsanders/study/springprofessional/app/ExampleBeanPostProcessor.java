package dev.samsanders.study.springprofessional.app;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

import java.util.concurrent.atomic.AtomicInteger;

public class ExampleBeanPostProcessor implements BeanPostProcessor, Ordered {

    private final AtomicInteger beanPostProcessorInvocationCounter;

    public ExampleBeanPostProcessor(AtomicInteger beanPostProcessorInvocationCounter) {
        this.beanPostProcessorInvocationCounter = beanPostProcessorInvocationCounter;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        beanPostProcessorInvocationCounter.incrementAndGet();
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        beanPostProcessorInvocationCounter.incrementAndGet();
        return bean;
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }
}
