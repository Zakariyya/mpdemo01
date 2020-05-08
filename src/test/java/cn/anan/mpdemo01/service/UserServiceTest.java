package cn.anan.mpdemo01.service;

import cn.anan.mpdemo01.entity.User;
import cn.anan.mpdemo01.mapper.UserMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@SpringBootTest
public class UserServiceTest {

  @Autowired
  UserService userService;

  @Autowired
  UserMapper userMapper;

  @Test
  public void findAll(){
    System.out.println("findAll: "+userMapper.selectList(null));
  }

  @Test
  public void add(){
    User user = new User();
    user.setName("东方不败");
    user.setAge(11);
    user.setEmail("aaa@gmail.com");

    System.out.println("add: "+userMapper.insert(user));
  }

  @Test
  public void update(){
    User user = new User();
    user.setId(1248280269750743041L);
    user.setAge(44);

    System.out.println("update: "+userMapper.updateById(user));
  }

  //测试乐观锁
  @Test
  public void testOptimisticLocker() {
    //根据id查询数据
    User user = userMapper.selectById(1231115382920916994L);
    //进行修改
    user.setAge(200);
    int i = userMapper.updateById(user);
    System.out.println("testOptimisticLocker: " + i);

  }


  // 多个ID批量查询
  @Test
  public void testSelectDemo1(){

    List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));
    System.out.println("多个ID批量查询: " + users);

  }

  @Test
  public void testSelectByMap(){
    HashMap<String, Object> map = new HashMap<>();
    map.put("name", "Jone");
    map.put("age", 18);
    List<User> users = userMapper.selectByMap(map);

    users.forEach(System.out::println);
  }



  //分页
  @Test
  public void testPage(){

    Page<User> page = new Page<>(1,3);

    IPage<User> userIPage = userMapper.selectPage(page, null);

    System.out.println("testPage: 当前页："+page.getCurrent());
    System.out.println("testPage: 每页数据list集合："+page.getRecords());
    System.out.println("testPage: 每页显示记录数："+page.getSize());
    System.out.println("testPage: 总记录数："+page.getTotal());
    System.out.println("testPage: 总页数："+page.getPages());

    System.out.println("testPage: 是否有下一页："+page.hasNext());
    System.out.println("testPage: 是否有上一页："+page.hasPrevious());

    System.out.println("testPage: userIPage : "+userIPage.toString());

  }

  //批量删除（物理）
  @Test
  public void batchDelete(){
    int i = userMapper.deleteBatchIds(Arrays.asList(1248278820815794177L));
  }

  //简单的条件查询删除（物理）
  @Test
  public void deleteByMap(){
    HashMap<String, Object> map = new HashMap<>();
    map.put("name", "he");
    map.put("age",10);
    userMapper.deleteByMap(map);
  }


}
