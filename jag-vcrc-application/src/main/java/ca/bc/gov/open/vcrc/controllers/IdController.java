package ca.bc.gov.open.vcrc.controllers;

import ca.bc.gov.open.vcrc.models.exceptions.ORDSException;
import ca.bc.gov.open.vcrc.models.logs.OrdsErrorLog;
import ca.bc.gov.open.vcrc.models.logs.RequestSuccessLog;
import ca.bc.gov.open.vcrc.models.requests.GetNextInvoiceIdRequest;
import ca.bc.gov.open.vcrc.models.requests.GetNextSessionIdRequest;
import ca.bc.gov.open.vcrc.models.responses.GetNextInvoiceIdResponse;
import ca.bc.gov.open.vcrc.models.responses.GetNextSessionIdResponse;
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
public class IdController {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${ords.host}")
    private String ordsHost = "https://127.0.0.1/";

    public IdController(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @GetMapping(value = "GetNextSessionId/Services", produces = MediaType.TEXT_XML_VALUE)
    public GetNextSessionIdResponse getNextSessionId(
            GetNextSessionIdRequest getNextSessionIdRequest) throws JsonProcessingException {
        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(ordsHost + "ids/session")
                        .queryParam(
                                "orgTicketNumber", getNextSessionIdRequest.getOrgTicketNumber());

        try {
            HttpEntity<GetNextSessionIdResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            GetNextSessionIdResponse.class);

            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog("Request Success", "getNextSessionId")));

            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog(
                                    "Request Success",
                                    objectMapper.writeValueAsString(getNextSessionIdRequest))));

            GetNextSessionIdResponse out = new GetNextSessionIdResponse();
            return resp.getBody();
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "getNextSessionId",
                                    ex.getMessage(),
                                    null)));
            throw new ORDSException();
        }
    }

    @GetMapping(value = "GetNextInvoiceId/Services", produces = MediaType.TEXT_XML_VALUE)
    public GetNextInvoiceIdResponse getNextInvoiceId(
            GetNextInvoiceIdRequest getNextInvoiceIdRequest) throws JsonProcessingException {
        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(ordsHost + "ids/invoice")
                        .queryParam(
                                "orgTicketNumber", getNextInvoiceIdRequest.getOrgTicketNumber());

        try {
            HttpEntity<GetNextInvoiceIdResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            GetNextInvoiceIdResponse.class);

            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog("Request Success", "getNextInvoiceId")));

            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog(
                                    "Request Success",
                                    objectMapper.writeValueAsString(getNextInvoiceIdRequest))));

            GetNextInvoiceIdResponse out = new GetNextInvoiceIdResponse();
            return resp.getBody();
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "getNextInvoiceId",
                                    ex.getMessage(),
                                    null)));
            throw new ORDSException();
        }
    }
}
