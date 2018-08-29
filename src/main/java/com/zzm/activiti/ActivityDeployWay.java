package com.zzm.activiti;

import java.io.InputStream;
import java.util.zip.ZipInputStream;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.junit.jupiter.api.Test;

/**
 * ActivityDeployWay ：
 *
 * @author Lv
 * @since 2018/8/9 16:43
 */
public class ActivityDeployWay {

  /**
   * 1:通过bpmn和png资源进行部署
   */
  @Test
  public void testDeployFromClasspath(){
    //得到流程引擎
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    processEngine.getRepositoryService()
        .createDeployment()
        .addClasspathResource("leaveApply.bpmn")
        .addClasspathResource("leaveApply.png")
        .deploy();
  }

  /**
   * 2:通过 inputstream完成部署
   */
  @Test
  public void testDeployFromInputStream(){
    InputStream bpmnStream = this.getClass().getClassLoader().getResourceAsStream("leaveApply.bpmn");
    //得到流程引擎
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    processEngine.getRepositoryService()
        .createDeployment()
        .addInputStream("leaveApply.bpmn", bpmnStream)
        .deploy();
  }

  /**
   * 3:通过zipinputstream完成部署
   * 注意：这个的话，需要将bpmn和png文件进行压缩成zip文件，然后放在项目src目录下即可(当然其他目录也可以)
   */
  @Test
  public void testDeployFromZipinputStream(){
    InputStream in = this.getClass().getClassLoader().getResourceAsStream("leaveApply.zip");
    ZipInputStream zipInputStream = new ZipInputStream(in);
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    processEngine.getRepositoryService()
        .createDeployment()
        .addZipInputStream(zipInputStream)
        .deploy();
  }


  /**
   * 删除已经部署的Activiti流程
   */
  @Test
  public void testDelete(){
    //得到流程引擎
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    //第一个参数是部署的流程的ID，第二个true表示是进行级联删除
    processEngine.getRepositoryService()
        .deleteDeployment("17501",true);
  }




}
