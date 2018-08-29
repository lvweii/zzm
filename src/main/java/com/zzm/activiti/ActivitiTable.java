package com.zzm.activiti;


import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.jupiter.api.Test;

/**
 * ActivitiTable ：从数据源和流程图中，生成一个数据库表（这个就是Activiti流程控制的关键的数据表）
 *
 * @author Lv
 * @since 2018/8/9 11:46
 */
public class ActivitiTable {
  /**
   * 创建Activiti流的相关的数据库表
   */
  @Test
  public void creatTable(){
    ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource(
        "activiti.cfg.xml")
        .buildProcessEngine();
  }
}

