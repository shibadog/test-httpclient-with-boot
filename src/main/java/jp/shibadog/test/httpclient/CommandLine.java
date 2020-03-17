package jp.shibadog.test.httpclient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CommandLine implements CommandLineRunner {

    private final DummyService service;
    public CommandLine(DummyService service) {
        this.service = service;
    }
    
    @Override
    @NewSpan("aaa")
    public void run(String... args) throws Exception {
        log.info("リクエスト！！");
        String requestUrl = "http://localhost:8080/sleep/";
        
        String aa = service.exchangeWebClient(requestUrl);
        log.info(aa);

        log.info("******* ここから RestTemplate ******");

        String bb = service.exchangeRestTemplate(requestUrl);
        log.info(bb);
    }
}
