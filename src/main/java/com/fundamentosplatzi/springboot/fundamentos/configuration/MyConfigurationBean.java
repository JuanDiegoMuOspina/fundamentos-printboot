package com.fundamentosplatzi.springboot.fundamentos.configuration;

import com.fundamentosplatzi.springboot.fundamentos.bean.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfigurationBean {

    @Bean
    public MyBean beanOperation(){
        return new MyBeanImplement2();
    }
    @Bean
    public MyOperation beanOperationImplement(){
        return new MyOperationImplement();
    }

    @Bean
    public MyBeanWithDependency myBeanWithDpendency(MyOperation myOperation,MyImplementForMe myImplementForMe){
        return new MyBeanWithDependecyImplement(myOperation,myImplementForMe);
    }
    @Bean
    public MyImplementForMe myImplementForMe(){
        return new MyOperationForMeImpelement();
    }
}
