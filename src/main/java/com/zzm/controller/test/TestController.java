package com.zzm.controller.test;

import com.zzm.domain.User;
import com.zzm.service.testService.TestService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
}
