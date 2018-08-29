package com.zzm.service.testService.impl;

import com.zzm.domain.User;
import com.zzm.service.BaseServiceImpl;
import com.zzm.service.testService.TestService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * TestServiceImpl ：
 *
 * @author Lv
 * @since 2018/8/10 17:23
 */
public class TestServiceImpl extends BaseServiceImpl implements TestService {

  @Autowired
  JdbcTemplate baseJdbcTemplateDao;

  public JdbcTemplate getBaseJdbcTemplateDao() {
    return baseJdbcTemplateDao;
  }

  @Override
  public List<User> getUserList() {
    String sql = "select id,username name,password,status,descn from user";
    return this.getBaseJdbcTemplateDao().query(sql, new BeanPropertyRowMapper<User>(User.class));
  }
}
