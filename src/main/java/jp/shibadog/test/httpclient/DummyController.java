package jp.shibadog.test.httpclient;

import java.util.concurrent.TimeUnit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DummyController {

    private final DummyService service;

    public DummyController(DummyService service) {
        this.service = service;
    }

    @RequestMapping("sleep")
    public String run() throws InterruptedException {
        log.info("着信！");
        TimeUnit.SECONDS.sleep(5L);
        return "OK";
    }

    @RequestMapping("go")
    public String go() {
        log.info("発信！");
        return service.exchangeWebClient("http://localhost:8080/sleep/");
    }
}
