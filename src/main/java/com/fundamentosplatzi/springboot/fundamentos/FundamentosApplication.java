package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWitchPropietes;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;

import org.apache.commons.logging.Log;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.commons.logging.LogFactory;

import java.awt.*;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {
	Log LOGGER= LogFactory.getLog(FundamentosApplication.class);
	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWitchPropietes myBeanWitchPropietes;


	public FundamentosApplication(@Qualifier("componentTowImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWitchPropietes myBeanWitchPropietes) {
		this.componentDependency = componentDependency;
		this.myBean=myBean;
		this.myBeanWithDependency=myBeanWithDependency;
		this.myBeanWitchPropietes=myBeanWitchPropietes;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args)  {
		ejemplosAnteriores();
	}
	public void ejemplosAnteriores(){
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDpendency();
		myBeanWitchPropietes.imprimirValues();
		try {
			int value=10/0;
			LOGGER.debug("Esto parece ser un error en la aplicación");
		}catch (Exception e ){
			LOGGER.error("Esto parece ser un error en la aplicación al dividir por 0");
		}
	}
}
