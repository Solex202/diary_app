package com.technophiles.diaryapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.technophiles.diaryapp")
public class DiaryAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiaryAppApplication.class, args);
	}
}
