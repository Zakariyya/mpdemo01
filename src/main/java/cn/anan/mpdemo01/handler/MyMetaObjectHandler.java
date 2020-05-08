package cn.anan.mpdemo01.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: anan
 * @Date: 2020/4/10 0:00
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

  // 自动填充
  @Override
  public void insertFill(MetaObject metaObject) {
    this.setFieldValByName("createTime", new Date(), metaObject);
    this.setFieldValByName("updateTime", new Date(), metaObject);
    this.setFieldValByName("version", 1, metaObject);

  }

  @Override
  public void updateFill(MetaObject metaObject) {
    this.setFieldValByName("updateTime", new Date(), metaObject);

  }
}
