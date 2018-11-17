package com.secure.peaas.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by grahul on 17-11-2018.
 */
@Component
public class InitData implements InitializingBean, BeanPostProcessor, DisposableBean {

  @PostConstruct
  public void initMethod() {
    System.out.println("1.  PostConstruct");
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println("2.  afterPropertiesSet");
  }

  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    System.out.println("3.  BPP : postProcessBeforeInitialization " + beanName);
    return bean;
  }

  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    System.out.println("4.  BPP : postProcessAfterInitialization " + beanName);
    return bean;
  }

  @PreDestroy
  public void preDestroy() {
    System.out.println("5.  PreDestroy");
  }

  @Override
  public void destroy() throws Exception {
    System.out.println("6.  destroy");
  }

}
