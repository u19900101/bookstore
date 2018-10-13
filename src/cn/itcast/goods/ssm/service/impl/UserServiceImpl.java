package cn.itcast.goods.ssm.service.impl;

import cn.itcast.goods.ssm.mapper.UserMapper;
import cn.itcast.goods.ssm.po.User;
import cn.itcast.goods.ssm.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by liupanbangbangda on 2018/9/19.
 */
public class UserServiceImpl implements UserService {

    private ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:applicationContext-dao.xml");
    UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");

    @Override
    public User selectByUser(User user) {
        return userMapper.selectByUser(user);
    }
}
