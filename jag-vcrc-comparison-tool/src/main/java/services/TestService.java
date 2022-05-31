package services;

import ca.bc.gov.open.vcrc.models.requests.AuthenticateUserRequest;
import ca.bc.gov.open.vcrc.models.responses.GetCountriesListResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.PrintWriter;

@Service
public class TestService {

    @Value("${host.api-host}")
    private String apiHost;

    @Value("${host.username}")
    private String username;

    @Value("${host.password}")
    private String password;

    @Value("${host.wm-host}")
    private String wmHost;

    private PrintWriter fileOutput;
    private static String outputDir = "jag-vcrc-comparison-tool/results/";

    int diffCounter = 0;

    private final RestTemplate restTemplate = new RestTemplate();


     public TestService() {

    }

    public void runCompares() throws Exception {
        getCountryListCompare();
    }

    public void getCountryListCompare() {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(wmHost + "GetCountryList/Services");

        HttpEntity<String> resp =
                restTemplate.exchange(
                        builder.toUriString(),
                        HttpMethod.GET,
                        new HttpEntity<>(new HttpHeaders()),
                        String.class);

    }
}
