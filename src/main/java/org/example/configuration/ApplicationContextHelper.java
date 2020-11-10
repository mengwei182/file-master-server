package org.example.configuration;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class ApplicationContextHelper implements ApplicationContextAware {
  private ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    try {
      this.applicationContext = applicationContext;
      DataSource dataSource = (DataSource) applicationContext.getBean("dataSource");
      dataSource.getConnection();
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(-1);
    }
  }

  public ApplicationContext getApplicationContext() {
    return applicationContext;
  }
}
