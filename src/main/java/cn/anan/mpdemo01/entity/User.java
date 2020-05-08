package cn.anan.mpdemo01.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @Author: anan
 * @Date: 2020/4/9 23:15
 */
@Data
public class User {

  /**
   * 4种策略
   *
   * AUTO
   * UUID
   * Redis 实现 原子操作，5台redis，每台初始值依次 1,2,3,4,5 ,步长都是 5
   * snowflake
   */
  @TableId(type= IdType.ID_WORKER) //mp 自带策略，生成19位值，数字类型食用这种策略，比如long
//  @TableId(type= IdType.ID_WORKER_STR) // mp 自带策略，生成19位值，字符串类型使用这种策略
  private Long id;

  private String name;
  private Integer age;
  private String email;

  //逻辑删除
  @TableLogic
  private Integer deleted; //删除标志位

  @Version
  @TableField(fill = FieldFill.INSERT)
  private Integer version; //版本号

  @TableField(fill = FieldFill.INSERT)
  private Date createTime;
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private Date updateTime;


}
