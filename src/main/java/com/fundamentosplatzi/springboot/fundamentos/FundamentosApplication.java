package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWitchPropietes;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;

import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import com.fundamentosplatzi.springboot.fundamentos.service.UserService;
import org.apache.commons.logging.Log;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Sort;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {
	Log LOGGER= LogFactory.getLog(FundamentosApplication.class);
	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWitchPropietes myBeanWitchPropietes;
	private UserPojo userPojo;
	private UserRepository userRepository;
	private UserService userService;


	public FundamentosApplication(@Qualifier("componentTowImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWitchPropietes myBeanWitchPropietes,UserPojo userPojo,UserRepository userRepository, UserService userService) {
		this.componentDependency = componentDependency;
		this.myBean=myBean;
		this.myBeanWithDependency=myBeanWithDependency;
		this.myBeanWitchPropietes=myBeanWitchPropietes;
		this.userPojo=userPojo;
		this.userRepository=userRepository;
		this.userService=userService;

	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args)  {
		//ejemplosAnteriores();
		//saveUserInDataBase();
		//getinformationGetJpql();
		//saveWithErrorTransactional();
		//userService.getAllUsers().stream().forEach(user-> LOGGER.info("Rrevisando los usuario test"+user));

	}
	private  void saveUserInDataBase(){
		User user1=new User("Juan","jdmunozo_1@uqvirtual.edu.co", LocalDate.of(2021,02,02));
		User user2=new User("Susa","algunc@correo", LocalDate.of(2021,02,02));
		User user3=new User("daniela","algunCorreo@correo", LocalDate.of(2022,03,03));
		User user4=new User("danna","algunCorreoDanna@correo", LocalDate.of(2023,04,04));

		List<User> LisUserList= Arrays.asList(user1,user2,user3,user4);
		LisUserList.stream().forEach(userRepository::save);
		userService.saveTransactional(LisUserList);



	}
	private void getinformationGetJpql(){
		//LOGGER.info(userRepository.finfByUserEmail("jdmunozo_1@uqvirtual.edu.co").orElseThrow(()->new RuntimeException("No se encontro el usuario")));

		//userRepository.findAndSort("dann", Sort.by("id").descending()).stream().forEach(user->LOGGER.info("USUARIO: "+user));


/*Investiga de los queryMethods*/
		//userRepository.findByName("jdmunozo_1@uqvirtual.edu.co","Juan").orElseThrow(()-> new RuntimeException("Usuario No encontrado"));
		//userRepository.findByNameLike("%Juan%").stream().forEach(user -> LOGGER.info("Usuarios encontrados en like is"+user));
		//userRepository.findByNameOrEmail("Juan","jdmunozo_1@uqvirtual.edu.co").stream().forEach(user -> LOGGER.info("Usuarios encontrados en Or  name or email is"+user));
		userRepository.findByBirthdateBetween(LocalDate.of(2021, 02 , 02),LocalDate.of(2023,04,04)).stream().forEach(user-> LOGGER.info("Usuario con intervalo de fecha"+ user));
		userRepository.findByNameLikeOrderByIdDesc("%Juan%").stream().forEach(user -> LOGGER.info("Usuario encontrado con LIKE y ORDENADO"+user));
		//System.out.println(userRepository.getAllByBirthdateAndEmail(LocalDate.of(2021, 02 , 02),
		//		"algunc@correo").orElseThrow(()->new RuntimeException("No se encontro por medio del parametro")));
	}
	public void ejemplosAnteriores(){
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDpendency();

		myBeanWitchPropietes.imprimirValues();
		try {
			int value=10/0;
			LOGGER.info("Esto parece ser un error en la aplicación");
		}catch (Exception e ){
			LOGGER.error("Esto parece ser un error en la aplicación al dividir por 0");
		}

		System.out.println(userPojo.getEmail()+"-"+userPojo.getPassword());
	}

	private void saveWithErrorTransactional(){
		User test1=new User("test 1","mail@dominio",LocalDate.now());
		User test2=new User("test 2","mail@dominio",LocalDate.now());
		List<User> users=Arrays.asList(test1);
		try {
			userService.saveTransactional(users);
		}catch (Exception e){
			LOGGER.error("Esta un exception de JPA"+e);
		}


	}
}
