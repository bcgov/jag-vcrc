package ca.bc.gov.open.vcrc.controllers;

import ca.bc.gov.open.vcrc.models.exceptions.ORDSException;
import ca.bc.gov.open.vcrc.models.logs.OrdsErrorLog;
import ca.bc.gov.open.vcrc.models.logs.RequestSuccessLog;
import ca.bc.gov.open.vcrc.models.requests.CheckApplicantForPrevCRCRequest;
import ca.bc.gov.open.vcrc.models.requests.CreateApplicantRequest;
import ca.bc.gov.open.vcrc.models.responses.CheckApplicantForPrevCRCExResponse;
import ca.bc.gov.open.vcrc.models.responses.CheckApplicantForPrevCRCResponse;
import ca.bc.gov.open.vcrc.models.responses.CreateApplicantResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("rest/VCRC/Source")
@Slf4j
public class ApplicantController {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${ords.host}")
    private String ordsHost = "https://127.0.0.1/";

    @Autowired
    public ApplicantController(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @GetMapping(value = "CheckApplicantForPrevCRC/Services", produces = MediaType.TEXT_XML_VALUE)
    public CheckApplicantForPrevCRCResponse checkApplicantForPrevCRC(
            CheckApplicantForPrevCRCRequest checkApplicantForPrevCRCRequest)
            throws JsonProcessingException {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ordsHost + "prev/crc");

        HttpEntity<CheckApplicantForPrevCRCRequest> payload =
                new HttpEntity<>(checkApplicantForPrevCRCRequest, new HttpHeaders());
        try {
            HttpEntity<CheckApplicantForPrevCRCResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.POST,
                            payload,
                            CheckApplicantForPrevCRCResponse.class);

            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog("Request Success", "checkApplicantForPrevCRC")));

            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog(
                                    "Request Success",
                                    objectMapper.writeValueAsString(
                                            checkApplicantForPrevCRCRequest))));

            return resp.getBody();
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "checkApplicantForPrevCRC",
                                    ex.getMessage(),
                                    null)));
            throw new ORDSException();
        }
    }

    @GetMapping(value = "CheckApplicantForPrevCRCEx/Services", produces = MediaType.TEXT_XML_VALUE)
    public CheckApplicantForPrevCRCExResponse checkApplicantForPrevCRCEx(
            CheckApplicantForPrevCRCRequest checkApplicantForPrevCRCRequest)
            throws JsonProcessingException {

        // CheckApplicantForPrevCRCRequest has the field 'previousServiceId' that, used for
        // checkApplicantForPrevCRC,
        // the current end point does not need it, but it does not cause issue here.

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ordsHost + "prev/crc/ex");

        HttpEntity<CheckApplicantForPrevCRCRequest> payload =
                new HttpEntity<>(checkApplicantForPrevCRCRequest, new HttpHeaders());
        try {
            HttpEntity<CheckApplicantForPrevCRCExResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.POST,
                            payload,
                            CheckApplicantForPrevCRCExResponse.class);

            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog(
                                    "Request Success", "checkApplicantForPrevCRCEx")));

            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog(
                                    "Request Success",
                                    objectMapper.writeValueAsString(
                                            checkApplicantForPrevCRCRequest))));

            return resp.getBody();
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "checkApplicantForPrevCRCEx",
                                    ex.getMessage(),
                                    null)));
            throw new ORDSException();
        }
    }

    @GetMapping(value = "CreateApplicant/Services", produces = MediaType.TEXT_XML_VALUE)
    public CreateApplicantResponse createApplicant(CreateApplicantRequest createApplicant)
            throws JsonProcessingException {

        // CreateApplicantRequest has the field 'emailAddress' and 'emailType' that, used for
        // createApplicantEx,
        // the current end point does not need it, but it does not cause issue here.

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ordsHost + "applicant");

        HttpEntity<CreateApplicantRequest> payload =
                new HttpEntity<>(createApplicant, new HttpHeaders());
        try {
            HttpEntity<CreateApplicantResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.POST,
                            payload,
                            CreateApplicantResponse.class);

            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog("Request Success", "createApplicant")));

            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog(
                                    "Request Success",
                                    objectMapper.writeValueAsString(createApplicant))));

            return resp.getBody();
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "createApplicant",
                                    ex.getMessage(),
                                    null)));
            throw new ORDSException();
        }
    }

    @GetMapping(value = "CreateApplicantEx/Services", produces = MediaType.TEXT_XML_VALUE)
    public CreateApplicantResponse createApplicantEx(CreateApplicantRequest createApplicant)
            throws JsonProcessingException {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ordsHost + "applicant/ex");

        HttpEntity<CreateApplicantRequest> payload =
                new HttpEntity<>(createApplicant, new HttpHeaders());
        try {
            HttpEntity<CreateApplicantResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.POST,
                            payload,
                            CreateApplicantResponse.class);

            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog("Request Success", "createApplicantEx")));

            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog(
                                    "Request Success",
                                    objectMapper.writeValueAsString(createApplicant))));

            return resp.getBody();
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "createApplicantEx",
                                    ex.getMessage(),
                                    null)));
            throw new ORDSException();
        }
    }
}
