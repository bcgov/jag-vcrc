package ca.bc.gov.open.vcrc.controllers;

import ca.bc.gov.open.vcrc.models.exceptions.ORDSException;
import ca.bc.gov.open.vcrc.models.logs.OrdsErrorLog;
import ca.bc.gov.open.vcrc.models.logs.RequestSuccessLog;
import ca.bc.gov.open.vcrc.models.requests.AuthenticateUserRequest;
import ca.bc.gov.open.vcrc.models.responses.AuthenticateUserResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("rest/VCRC/Source")
@Slf4j
public class AuthenticationController {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${ords.host}")
    private String ordsHost = "https://127.0.0.1/";

    public AuthenticationController(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @GetMapping(value = "DoAuthenticateUser/Services", produces = MediaType.TEXT_XML_VALUE)
    public AuthenticateUserResponse authenticateUser(
            AuthenticateUserRequest authenticateUserRequest) throws JsonProcessingException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ordsHost + "users/auth");
        HttpEntity<AuthenticateUserRequest> payload =
                new HttpEntity<>(authenticateUserRequest, new HttpHeaders());

        try {
            HttpEntity<AuthenticateUserResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.POST,
                            payload,
                            AuthenticateUserResponse.class);

            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog("Request Success", "authenticateUser")));

            return resp.getBody();
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "authenticateUser",
                                    ex.getMessage(),
                                    authenticateUserRequest)));
            throw new ORDSException();
        }
    }
}
