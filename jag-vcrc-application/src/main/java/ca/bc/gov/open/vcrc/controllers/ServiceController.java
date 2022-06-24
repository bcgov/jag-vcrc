package ca.bc.gov.open.vcrc.controllers;

import ca.bc.gov.open.vcrc.models.exceptions.ORDSException;
import ca.bc.gov.open.vcrc.models.logs.OrdsErrorLog;
import ca.bc.gov.open.vcrc.models.logs.RequestSuccessLog;
import ca.bc.gov.open.vcrc.models.requests.*;
import ca.bc.gov.open.vcrc.models.responses.*;
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
public class ServiceController {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${ords.host}")
    private String ordsHost = "https://127.0.0.1/";

    public ServiceController(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @GetMapping(value = "CreateNewCRCService/Services", produces = MediaType.TEXT_XML_VALUE)
    public CreateNewCRCServiceResponse createNewCrcService(
            CreateNewCRCServiceRequest createNewCRCServiceRequest) throws JsonProcessingException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ordsHost + "services/crc");
        HttpEntity<CreateNewCRCServiceRequest> payload =
                new HttpEntity<>(createNewCRCServiceRequest, new HttpHeaders());

        try {
            HttpEntity<CreateNewCRCServiceResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.POST,
                            payload,
                            CreateNewCRCServiceResponse.class);

            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog("Request Success", "createNewCrcService")));

            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog(
                                    "Request Success",
                                    objectMapper.writeValueAsString(createNewCRCServiceRequest))));

            CreateNewCRCServiceResponse out = new CreateNewCRCServiceResponse();
            return resp.getBody();
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "createNewCrcService",
                                    ex.getMessage(),
                                    null)));
            throw new ORDSException();
        }
    }

    @GetMapping(value = "CreateSharingService/Services", produces = MediaType.TEXT_XML_VALUE)
    public CreateSharingServiceResponse createSharingService(
            CreateSharingServiceRequest createSharingServiceRequest)
            throws JsonProcessingException {

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(ordsHost + "services/sharing");
        HttpEntity<CreateSharingServiceRequest> payload =
                new HttpEntity<>(createSharingServiceRequest, new HttpHeaders());

        try {
            HttpEntity<CreateSharingServiceResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.POST,
                            payload,
                            CreateSharingServiceResponse.class);

            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog("Request Success", "createSharingService")));

            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog(
                                    "Request Success",
                                    objectMapper.writeValueAsString(createSharingServiceRequest))));

            CreateSharingServiceResponse out = new CreateSharingServiceResponse();
            return resp.getBody();
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "createSharingService",
                                    ex.getMessage(),
                                    null)));
            throw new ORDSException();
        }
    }

    @GetMapping(value = "GetServiceFeeAmount/Services", produces = MediaType.TEXT_XML_VALUE)
    public GetServiceFeeAmountResponse getServiceFeeAmount(
            GetServiceFeeAmountRequest getServiceFeeAmountRequest) throws JsonProcessingException {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ordsHost + "services/fee");
        HttpEntity<GetServiceFeeAmountRequest> payload =
                new HttpEntity<>(getServiceFeeAmountRequest, new HttpHeaders());

        try {
            HttpEntity<GetServiceFeeAmountResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.POST,
                            payload,
                            GetServiceFeeAmountResponse.class);

            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog("Request Success", "getServiceFeeAmount")));

            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog(
                                    "Request Success",
                                    objectMapper.writeValueAsString(getServiceFeeAmountRequest))));

            GetServiceFeeAmountResponse out = new GetServiceFeeAmountResponse();
            return resp.getBody();
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "getServiceFeeAmount",
                                    ex.getMessage(),
                                    null)));
            throw new ORDSException();
        }
    }

    @GetMapping(value = "UpdateServiceFinancialTxn/Services", produces = MediaType.TEXT_XML_VALUE)
    public UpdateServiceFinancialTxnResponse updateServiceFinancialTxn(
            UpdateServiceFinancialTxnRequest updateServiceFinancialTxnRequest)
            throws JsonProcessingException {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ordsHost + "services/txn");
        HttpEntity<UpdateServiceFinancialTxnRequest> payload =
                new HttpEntity<>(updateServiceFinancialTxnRequest, new HttpHeaders());

        try {
            HttpEntity<UpdateServiceFinancialTxnResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.PUT,
                            payload,
                            UpdateServiceFinancialTxnResponse.class);

            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog("Request Success", "updateServiceFinancialTxn")));

            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog(
                                    "Request Success",
                                    objectMapper.writeValueAsString(
                                            updateServiceFinancialTxnRequest))));

            return resp.getBody();
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "updateServiceFinancialTxn",
                                    ex.getMessage(),
                                    null)));
            throw new ORDSException();
        }
    }
}
