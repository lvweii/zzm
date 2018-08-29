package com.zzm.activiti;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.junit.jupiter.api.Test;

/**
 * ActivitiQueryApi ：关于流程部署相关的其他API的示例
 *
 * @author Lv
 * @since 2018/8/9 17:08
 */
public class ActivitiQueryApi {


  /**
   * 根据名称查询流程部署
   */
  @Test
  public void testQueryDeploymentByName(){
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    List<Deployment> deployments = processEngine.getRepositoryService()
        .createDeploymentQuery()
        .orderByDeploymenTime()//按照部署时间排序
        .desc()//按照降序排序
        .deploymentName("leaveApply")
        .list();
    for (Deployment deployment : deployments) {
      System.out.println(deployment.getId());
    }
  }

  /**
   * 查询所有的部署流程
   */
  @Test
  public void queryAllDeplyoment(){
    //得到流程引擎
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    List<Deployment> lists = processEngine.getRepositoryService()
        .createDeploymentQuery()
        .orderByDeploymenTime()//按照部署时间排序
        .desc()//按照降序排序
        .list();
    for (Deployment deployment:lists) {
      System.out.println(deployment.getId() +"    部署名称" + deployment.getName());
    }
  }


  /**
   * 查询所有的流程定义
   */
  @Test
  public void testQueryAllPD(){
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    List<ProcessDefinition> pdList = processEngine.getRepositoryService()
        .createProcessDefinitionQuery()
        .orderByProcessDefinitionVersion()
        .desc()
        .list();
    for (ProcessDefinition pd : pdList) {
      System.out.println(pd.getName());
    }
  }

  /**
   * 查看流程图
   * 根据deploymentId和name(在act_ge_bytearray数据表中)
   */
  @Test
  public void testShowImage() throws Exception{
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    InputStream inputStream = processEngine.getRepositoryService()
        /**
         * deploymentID
         * 文件的名称和路径
         */
        .getResourceAsStream("1","leaveApply.png");
    OutputStream outputStream3 = new FileOutputStream("d:/processimg.png");
    int b = -1 ;
    while ((b=inputStream.read())!=-1){
      outputStream3.write(b);
    }
    inputStream.close();
    outputStream3.close();
  }

  /**
   * 根据pdid查看图片(在act_re_procdef数据表中)
   * @throws Exception
   */
  @Test
  public void testShowImage2() throws Exception{
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    InputStream inputStream = processEngine.getRepositoryService()
        .getProcessDiagram("leaveApply:1:4");
    OutputStream outputStream = new FileOutputStream("d:/processimg.png");
    int b = -1 ;
    while ((b=inputStream.read())!=-1){
      outputStream.write(b);
    }
    inputStream.close();
    outputStream.close();
  }

  /**
   * 查看bpmn文件(在act_re_procdef数据表中)
   */
  @Test
  public void testShowBpmn() throws Exception{
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    InputStream inputStream = processEngine.getRepositoryService()
        .getProcessModel("leaveApply:1:4");
    OutputStream outputStream = new FileOutputStream("d:/processimg.bpmn");
    int b = -1 ;
    while ((b=inputStream.read())!=-1){
      outputStream.write(b);
    }
    inputStream.close();
    outputStream.close();
  }






}
