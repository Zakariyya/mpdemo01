package cn.anan.mpdemo01.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.IllegalSQLInterceptor;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: anan
 * @Date: 2020/4/12 17:49
 */
@Configuration
@MapperScan("cn.anan.mpdemo01.mapper")
@EnableTransactionManagement
public class MpConfig {


  /**
   *  乐观锁插件
   * @return
   */
  @Bean
  public OptimisticLockerInterceptor optimisticLockerInterceptor() {
    return new OptimisticLockerInterceptor();
  }

  /**
   * 分页
   *
   * 直接new page 对象，传入两个参数
   * - 当前页
   * - 每页显示记录数
   */
  @Bean
  public PaginationInterceptor paginationInterceptor() {
    PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
    // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
    // paginationInterceptor.setOverflow(false);
    // 设置最大单页限制数量，默认 500 条，-1 不受限制
    // paginationInterceptor.setLimit(500);
    // 开启 count 的 join 优化,只针对部分 left join
//    paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
    return paginationInterceptor;
  }


  /**
   * 逻辑删除插件
   */
  @Bean
  public ISqlInjector sqlInjector(){
    return new LogicSqlInjector();
  }



  /**
   * SQL执行效率插件
   */
  @Bean
  @Profile({"dev","test"})// 设置 dev test 环境开启
  public PerformanceInterceptor performanceInterceptor() {
//    return new PerformanceInterceptor();
    PerformanceInterceptor pIntercetor = new PerformanceInterceptor();
    pIntercetor.setMaxTime(500); // ms, 超过此处设置的ms则sql不执行
    pIntercetor.setFormat(true);
    return pIntercetor;

  }














}
