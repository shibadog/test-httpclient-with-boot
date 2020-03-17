package jp.shibadog.test.httpclient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
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

    public String exchangeHttpClient(String requestUrl)
            throws InterruptedException, ExecutionException, TimeoutException {
        HttpClient client = HttpClient.newBuilder()
            .executor(Executors.newSingleThreadExecutor())
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(1L))
            .build();

        HttpRequest req = HttpRequest.newBuilder(URI.create(requestUrl))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        CompletableFuture<HttpResponse<String>> cf = client.sendAsync(
            req,
            HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        HttpResponse<String> res = cf.get(59L, TimeUnit.SECONDS);
        return res.body();
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
