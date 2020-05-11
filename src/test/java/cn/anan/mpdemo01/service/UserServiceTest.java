package cn.anan.mpdemo01.service;

import cn.anan.mpdemo01.entity.User;
import cn.anan.mpdemo01.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@SpringBootTest
@Slf4j
public class UserServiceTest {

  @Autowired
  UserService userService;

  @Autowired
  UserMapper userMapper;

  @Test
  public void findAll(){
    log.info("findAll: "+userMapper.selectList(null));
  }

  @Test
  public void add(){
    User user = new User();
    user.setName("东方不败121");
    user.setAge(11);
    user.setEmail("aaa@gmail.com");

    log.info("add: "+userMapper.insert(user));
  }

  @Test
  public void update(){
    User user = new User();
    user.setId(1248280269750743041L);
    user.setAge(44);

    log.info("update: "+userMapper.updateById(user));
  }

  //测试乐观锁
  @Test
  public void testOptimisticLocker() {
    //根据id查询数据
    User user = userMapper.selectById(1231115382920916994L);
    //进行修改
    user.setAge(200);
    int i = userMapper.updateById(user);
    log.info("testOptimisticLocker: " + i);

  }


  // 多个ID批量查询
  @Test
  public void testSelectDemo1(){

    List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));
    log.info("多个ID批量查询: " + users);

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

    log.info("testPage: 当前页："+page.getCurrent());
    log.info("testPage: 每页数据list集合："+page.getRecords());
    log.info("testPage: 每页显示记录数："+page.getSize());
    log.info("testPage: 总记录数："+page.getTotal());
    log.info("testPage: 总页数："+page.getPages());

    log.info("testPage: 是否有下一页："+page.hasNext());
    log.info("testPage: 是否有上一页："+page.hasPrevious());

    log.info("testPage: userIPage : "+userIPage.toString());

  }

  /**
   * 批量删除（物理）
   *
   * 批量删除（逻辑删除）加入插件
   * @Bean
   *   public ISqlInjector sqlInjector(){
   *     return new LogicSqlInjector();
   *   }
   */
  @Test
  public void batchDelete(){
    int i = userMapper.deleteBatchIds(Arrays.asList(1259662572083011586L));
  }

  //简单的条件查询删除（物理）
  @Test
  public void deleteByMap(){
    HashMap<String, Object> map = new HashMap<>();
    map.put("name", "he");
    map.put("age",10);
    userMapper.deleteByMap(map);
  }

  //mp实现复杂操作
  @Test
  public void testSelectQuery(){

    //创建QueryWrapper对象
    QueryWrapper<User> wrapper = new QueryWrapper<>();

    /**
     * 通过QueryWrapper设置条件
     * ge, gt, le, lt
     * 查询 age >= 30
     */
    wrapper.ge("age", 44);
    List<User> users = userMapper.selectList(wrapper);
    log.info("------------------testSelectQuery :: ge::users:"+users);

    /**
     * eq, ne(不等于)
     */
    wrapper = new QueryWrapper<>();
    wrapper.eq("age", "东方不败");
    users = userMapper.selectList(wrapper);
    log.info("------------------testSelectQuery :: eq::users:"+users);


    /**
     * between
     */
    wrapper = new QueryWrapper<>();
    wrapper.between("age", 44, 444);
    users = userMapper.selectList(wrapper);
    log.info("------------------testSelectQuery :: between::users:"+users);

    /**
     * like
     */
    wrapper = new QueryWrapper<>();
    wrapper.like("name", "东");
    users = userMapper.selectList(wrapper);
    log.info("------------------testSelectQuery :: like::users:"+users);

    /**
     * orderByDesc
     */
    wrapper = new QueryWrapper<>();
    wrapper.orderByDesc("id");
    users = userMapper.selectList(wrapper);
    log.info("------------------testSelectQuery :: orderByDesc::users:"+users);

    /**
     * last (在语句后面拼接sql)
     */
    wrapper = new QueryWrapper<>();
    wrapper.last("limit 1");
    users = userMapper.selectList(wrapper);
    log.info("------------------testSelectQuery :: orderByDesc::last:"+users);

    /**
     * 指定查询的列
     */
    wrapper = new QueryWrapper<>();
    wrapper.select("id", "name");
    users = userMapper.selectList(wrapper);
    log.info("------------------testSelectQuery :: orderByDesc::select clounm:"+users);

    


  }


}
