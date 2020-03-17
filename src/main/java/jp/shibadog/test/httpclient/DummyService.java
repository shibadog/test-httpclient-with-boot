package jp.shibadog.test.httpclient;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class DummyService {

    private final WebClient webClient;
    private final RestTemplate restTemplate;

    public DummyService(WebClient webClient, RestTemplate restTemplate) {
        this.webClient = webClient;
        this.restTemplate = restTemplate;
    }

    public String exchangeWebClient(String requestUrl) {
        // WebClient webClient = WebClient.create();
        WebClient.ResponseSpec res = webClient
            .get()
            .uri(requestUrl)
            .retrieve();
        String s = res.bodyToMono(String.class).block();
        return s;
    }

    public String exchangeRestTemplate(String requestUrl) {
        return restTemplate.getForObject(requestUrl, String.class);
    }
}
