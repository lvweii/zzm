package com.zzm.service.testService;

import com.zzm.domain.User;
import com.zzm.service.IBaseService;
import java.util.List;

/**
 * TestService ：
 *
 * @author Lv
 * @since 2018/8/10 17:21
 */
public interface TestService extends IBaseService {

  List<User> getUserList();
}
