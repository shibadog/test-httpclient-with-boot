package jp.shibadog.test.httpclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application { //implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // @Override
    // public void run(String... args) throws Exception {
    //     String aa = WebClient.create()
    //         .get()
    //         .uri("http://localhost:9411/zipkin/")
    //         .retrieve()
    //         .bodyToMono(String.class)
    //         .block();

    //     System.out.println(aa);
    // }
}
