package cn.lxsir.uniapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("cn.lxsir.uniapp.mapper")
@EnableAsync
public class UniappApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniappApiApplication.class, args);
	}

}
