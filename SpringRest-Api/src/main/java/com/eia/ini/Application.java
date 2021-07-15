package com.eia.ini;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.eia.model.entity.User;
import com.eia.repository.IUserRepository;
import com.github.javafaker.Faker;

@SpringBootApplication
@ComponentScan(basePackages = "com.eia.*")
@EntityScan(basePackages = "com.eia.model.entity")
@EnableJpaRepositories(basePackages = "com.eia.repository")
public class Application implements ApplicationRunner {

	@Autowired
	private Faker faker;
	
	@Autowired
	private IUserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		/*	
		for(int i=0; i<50;i++) {
		
			userRepository.save(new User(
				 				faker.name().username(),
				 				faker.dragonBall().character()));
		
		}*/
		
		/**Poblando BD - H2*/
		for(int i=0; i<200;i++) {
			
			User user = new User();
			
			user.setUserName(faker.name().username());
			user.setPassword(faker.dragonBall().character());
	 		
	 		
	 		userRepository.save(user);
		}
		
	}
	
	

}
