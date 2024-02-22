package com.bookmyshow;

import ch.qos.logback.classic.encoder.JsonEncoder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class BookMyShowApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BookMyShowApplication.class, args);
	}


	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

//	@Bean
//	public PasswordEncoder passwordEncoder(){
//		return new BCryptPasswordEncoder();
//	}



	@Override
	public void run(String... args) throws Exception {
//		System.out.println("hiiiiiiiiiiiiiiii");
//		System.out.println(Arrays.toString(new String[]{this.passwordEncoder.encode("123")}));
	}


	//this is for without using database
//	@Override
//	public void run(String... args) throws Exception {
//		//System.out.println(passwordEncoder.encode("Ayura"));
//		try {
//			Role role =new Role();
//			role.setId(AppConstants.NORMAL_USER);
//			role.setName("ROLE_NORMAL");
//
//
//			Role role1 =new Role();
//			role1.setId(AppConstants.ADMIN_USER);
//			role1.setName("ROLE_ADMIN");
//
//			List<Role> roleList = List.of(role, role1);
//
//			List<Role> result = roleRepo.saveAll(roleList);
//			result.forEach(r -> System.out.println(r.getName()));
//		}
//		catch (Exception e){
//			e.printStackTrace();
//		}
//		User user = new User(22,"Pavan","Pavanpetkar48@gmail.com","554d5","Programmer", Collections.emptyList(),Collections.emptySet());
//		System.out.println(user);
//	}


}
