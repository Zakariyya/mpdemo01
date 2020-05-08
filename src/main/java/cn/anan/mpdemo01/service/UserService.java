package cn.anan.mpdemo01.service;

import cn.anan.mpdemo01.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: anan
 * @Date: 2020/4/9 23:19
 */
@Service
public class UserService {

  @Autowired
  UserMapper userMapper;

  public List getAll(){
    return userMapper.selectList(null);
  }



}
