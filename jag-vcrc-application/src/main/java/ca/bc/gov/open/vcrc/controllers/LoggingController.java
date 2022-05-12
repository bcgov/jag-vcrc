package ca.bc.gov.open.vcrc.controllers;

import ca.bc.gov.open.vcrc.models.exceptions.ORDSException;
import ca.bc.gov.open.vcrc.models.logs.OrdsErrorLog;
import ca.bc.gov.open.vcrc.models.logs.RequestSuccessLog;
import ca.bc.gov.open.vcrc.models.requests.LogEivFailureRequest;
import ca.bc.gov.open.vcrc.models.requests.LogPaymentFailureRequest;
import ca.bc.gov.open.vcrc.models.responses.LogEivFailureResponse;
import ca.bc.gov.open.vcrc.models.responses.LogPaymentFailureResponse;
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
@RequestMapping("VCRC/Source")
@Slf4j
public class LoggingController {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${ords.host}")
    private String ordsHost = "https://127.0.0.1/";

    public LoggingController(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @GetMapping(value = "LogEivFailure/Services", produces = MediaType.TEXT_XML_VALUE)
    public LogEivFailureResponse logEivFailure(LogEivFailureRequest logEivFailureRequest)
            throws JsonProcessingException {

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(ordsHost + "logs/eiv/failure");
        HttpEntity<LogEivFailureRequest> payload =
                new HttpEntity<>(logEivFailureRequest, new HttpHeaders());

        try {
            HttpEntity<LogEivFailureResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.POST,
                            payload,
                            LogEivFailureResponse.class);

            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog("Request Success", "logEivFailure")));

            return resp.getBody();
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "logEivFailure",
                                    ex.getMessage(),
                                    null)));
            throw new ORDSException();
        }
    }

    @GetMapping(value = "LogPaymentFailure/Services", produces = MediaType.TEXT_XML_VALUE)
    public LogPaymentFailureResponse logPaymentFailure(
            LogPaymentFailureRequest logPaymentFailureRequest) throws JsonProcessingException {
        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(ordsHost + "logs/payment/failure");
        HttpEntity<LogPaymentFailureRequest> payload =
                new HttpEntity<>(logPaymentFailureRequest, new HttpHeaders());

        try {
            HttpEntity<LogPaymentFailureResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.POST,
                            payload,
                            LogPaymentFailureResponse.class);

            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog("Request Success", "logPaymentFailure")));

            return resp.getBody();
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "logPaymentFailure",
                                    ex.getMessage(),
                                    null)));
            throw new ORDSException();
        }
    }
}
