package com.ywl.study.config;

import com.ywl.study.job.CreateOrderJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail myJobDetail(){
        JobDetail jobDetail=JobBuilder.newJob(CreateOrderJob.class)
                .withIdentity("orderJob1","group1")
                .storeDurably()
                .build();
       return jobDetail;
    }

    @Bean
    public Trigger myTrigger1(){
        Trigger trigger= TriggerBuilder.newTrigger()
                .startNow()//多台部署的话，只会有一台机器去跑，这个开始跑的时间，要设定为具体时间，而且多台机器的时间要保持一致
                //如果启动时间是startNow(),由于每台机器的时间可能有差别，这样会导致多台部署的话，多台都会去跑任务
                //启动多台去跑任务的时候，多台机器时间要设定保持一致，而且开启时间不要设为为startNow（），要设为具体的时间点；
                //其中一台跑任务，其他的就不会跑同一个任务，当跑任务的服务停止后，任务会交给其他机器再进行跑
                .withIdentity("orderTrigger1","group1")
                .forJob(myJobDetail())
                .withSchedule(CronScheduleBuilder.cronSchedule("0/15 * * * * ?")
                ).build();
        return trigger;
    }


}
