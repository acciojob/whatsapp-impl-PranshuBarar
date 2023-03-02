package com.driver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//@SpringBootApplication
public class Application {

	public static void main(String[] args) throws Exception {
//		SpringApplication.run(Application.class, args);
		WhatsappController whatsappController = new WhatsappController();
		List<User> userList = new ArrayList<>();
		userList.add(new User("abc", "1"));
		userList.add(new User("def", "2"));
		userList.add(new User("ghi", "3"));
		userList.add(new User("jkl", "4"));
		userList.add(new User("mno", "5"));
		userList.add(new User("pqr", "6"));
		userList.add(new User("stu", "7"));

		Group group = whatsappController.createGroup(userList);


		whatsappController.sendMessage(new Message(2, "Are you all enjoying studies", new Date(5)), new User("mno", "5"), group);
		whatsappController.sendMessage(new Message(3, "We are fine", new Date(7)), new User("jkl", "4"), group);
		whatsappController.sendMessage(new Message(4, "Yes we are happily enjoying", new Date(1)), new User("pqr", "6"), group);
		whatsappController.sendMessage(new Message(5, "Hi, Everyone", new Date(2)), new User("abc", "1"), group);
		whatsappController.sendMessage(new Message(6, "Hello abc", new Date(11)), new User("mno", "5"), group);
		whatsappController.sendMessage(new Message(7, "How are you abc ?", new Date(9)), new User("mno", "5"), group);
		whatsappController.sendMessage(new Message(1, "Hi, How are You ?", new Date(8)), new User("mno", "5"), group);

//		System.out.println(whatsappController.findMessage(new Date(), new Date(), 1));
//		System.out.println(whatsappController.removeUser(new User("mno", "5")));

//
//		System.out.println(new Date(10));
//
//		System.out.println(new Date(2020, Calendar.JANUARY,2));
//
//		System.out.println(new Date(2019,Calendar.MAY,20)); //+1900
	}


}