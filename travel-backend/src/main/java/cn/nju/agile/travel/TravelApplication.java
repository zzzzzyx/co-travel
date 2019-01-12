package cn.nju.agile.travel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
public class TravelApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(TravelApplication.class, args);
    }
    
}

