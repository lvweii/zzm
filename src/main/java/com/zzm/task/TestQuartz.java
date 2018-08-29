package com.zzm.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * TestQuartz ：
 *
 * @author Lv
 * @since 2018/8/4 16:14
 */
@Component
public class TestQuartz {

  //每隔五秒执行一次
  @Scheduled(cron="*/5 * * * * ?")
  public void test(){
    System.out.println("定时任务，每5秒执行一次！");
  }

}
