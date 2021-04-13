package com.example.TACS2021UTN;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@RestController
public class Tacs2021UtnApplication {

    @RequestMapping("/")
    public String home(){
        return "Hello World";
    }
	public static void main(String[] args) {
		SpringApplication.run(Tacs2021UtnApplication.class, args);
	}

}
