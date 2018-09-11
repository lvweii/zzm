package com.zzm.controller.test;

import com.zzm.domain.User;
import com.zzm.service.testService.TestService;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * TestController ：
 *
 * @author Lv
 * @since 2018/8/3 17:55
 */
@Controller
//@ResponseBody  加上此注解就不会走视图解析器，不会返回页面，而是返回的json数据
public class TestController {

  @Resource
  TestService testService;
  
  @RequestMapping(value = "/testJpa.action",method = RequestMethod.GET)
  public String testJpa(HttpServletRequest req,HttpServletResponse resp) {
    HttpSession session = req.getSession();
    User user = (User)testService.getEntity(User.class,(long)1);
    System.out.println("name: "+user.getName());
    List<User> list = testService.getUserList();
    session.setAttribute("list",list);
    return "testJpa";
  }

  @RequestMapping(value = "/test.action",method = RequestMethod.GET)
  public String index(HttpServletRequest req,HttpServletResponse resp) {
    System.out.println("test");
    HttpSession session = req.getSession();
    String s = "";
    Connection conn = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("数据库驱动加载成功");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    try {
      Properties properties = new Properties();
      properties.setProperty("user", "root");
      properties.setProperty("password", "admin");
      properties.setProperty("useSSL", "false");
      properties.setProperty("autoReconnect", "true");

//      conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/zzm","root","admin");
      conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/zzm",properties);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      Statement smt = conn.createStatement();
      ResultSet res = smt.executeQuery("select * from user");
      while (res.next()){
        String name = res.getString("username");
        String pwd = res.getString("password");
        System.out.println(name+"  "+pwd);
        s=s+name+" "+pwd+"</br>";
      }
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    session.setAttribute("msg",s);
    return "test";
  }

  /**
   *  保存文档到服务器磁盘，返回值true，保存成功，返回值为false时，保存失败。
   */
  @RequestMapping(value = {"/testUpload.action"}, method = {RequestMethod.GET,RequestMethod.POST})
  @ResponseBody
  public boolean saveFileToDisk(HttpServletRequest request,HttpServletResponse response)
  {
    File officeFileUpload = null;
    FileItem officeFileItem = null;
    String officefileNameDisk = new Date().getTime()+".docx";
    String absoluteOfficeFileDir = "C:\\Users\\Lv\\Desktop\\upload\\office";
    boolean result=true ;

    DiskFileItemFactory factory = new DiskFileItemFactory();
    // 设置最多只允许在内存中存储的数据,单位:字节
    factory.setSizeThreshold(4096);
    // 设置一旦文件大小超过setSizeThreshold()的值时数据存放在硬盘的目录
//    factory.setRepository(new File(tempFileDir));
    ServletFileUpload upload = new ServletFileUpload(factory);
    //设置允许用户上传文件大小,单位:字节
    upload.setSizeMax(1024*1024*4);
    List fileItems = null;
//    Iterator<String> iterator = req.getFileNames();
//    while (iterator.hasNext()) {
//      MultipartFile file = req.getFile(iterator.next());
//      String fileNames = file.getOriginalFilename();
//      int split = fileNames.lastIndexOf(".");
//      //存储文件
//      //文件名
//      System.out.println(fileNames.substring(0,split));
//      //文件格式
//      System.out.println(fileNames.substring(split+1,fileNames.length()));
//      //文件内容
////      System.out.println(file.getBytes());
//    }
    try{fileItems=upload.parseRequest(request);}
    catch(FileUploadException e)
    {
      System.out.println("the max upload size is 4m,cheeck upload file size!");
      System.out.println(e.getMessage());
      e.printStackTrace();
      return false;
    }
    Iterator iter = fileItems.iterator();
    while (iter.hasNext())
    {
      FileItem item = (FileItem) iter.next();
      //打印提交的文本域和文件域名称
      System.out.println(item.getFieldName());
      if(item.isFormField())
      {
//普通文本表单字段
//        if(item.getFieldName().equalsIgnoreCase("fileType"))
//        {
//          fileType=item.getString("utf-8").trim();
//        }
      }else
      {
//        if(item.getFieldName().equalsIgnoreCase("upLoadFile"))
//        {officeFileItem=item;}
        officeFileItem=item;
      }
    }

    try
    {
      System.out.println("officefilepath:"+absoluteOfficeFileDir+officefileNameDisk);
      if(!officefileNameDisk.equalsIgnoreCase("")&&officeFileItem!=null)
      {
        officeFileUpload =  new File(absoluteOfficeFileDir+officefileNameDisk);
        officeFileItem.write(officeFileUpload);
      }
    }
    catch(FileNotFoundException e){}
    catch(Exception e)
    {
      System.out.println("error saveFileToDisk:"+e.getMessage());
      e.printStackTrace();
      result=false;
    }
    return result;
  }

}
