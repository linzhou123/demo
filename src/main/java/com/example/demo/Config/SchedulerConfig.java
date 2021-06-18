package com.example.demo.Config;

import com.example.demo.common.factory.MyJobFactory;
import org.quartz.Scheduler;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

/**
 * Qartz配置, 搭配quartz.properties文件自定义配置, 该处目的使定时任务持久化
 */
@Configuration
public class SchedulerConfig {

    @Autowired
    MyJobFactory myJobFactory;

    /**
     * 读取quartz.properties 文件
     * 将值初始化
     * @return
     */
    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    /**
     * 将配置文件的数据加载到SchedulerFactoryBean中
     * @return
     * @throws IOException
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        //启动时更新己存在的Job，这样就不用每次修改targetObject后删除qrtz_job_details表对应记录了
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        // 延时启动(秒)
        schedulerFactoryBean.setStartupDelay(20);
        //设置quartz的配置文件
        schedulerFactoryBean.setQuartzProperties(quartzProperties());
        //设置自定义Job Factory，用于Spring管理Job bean
        schedulerFactoryBean.setJobFactory(myJobFactory);
        return schedulerFactoryBean;
    }

    /**
     * 初始化监听器
     * @return
     */
    @Bean
    public QuartzInitializerListener executorListener(){
        return new QuartzInitializerListener();
    }

    /**
     * 获得Scheduler 对象
     * @return
     * @throws IOException
     */
    @Bean
    public Scheduler scheduler() throws IOException {
        return schedulerFactoryBean().getScheduler();
    }
}