package ca.bc.gov.open.vcrc.controllers;

import ca.bc.gov.open.vcrc.models.exceptions.ORDSException;
import ca.bc.gov.open.vcrc.models.logs.OrdsErrorLog;
import ca.bc.gov.open.vcrc.models.logs.RequestSuccessLog;
import ca.bc.gov.open.vcrc.models.responses.GetCountriesListResponse;
import ca.bc.gov.open.vcrc.models.responses.GetProvinceListResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LocationController {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${ords.host}")
    private String ordsHost = "https://127.0.0.1/";

    @Autowired
    public LocationController(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @GetMapping(value = "GetCountryList/Services", produces = MediaType.TEXT_XML_VALUE)
    public GetCountriesListResponse getCountriesList() throws JsonProcessingException {

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(ordsHost + "locations/countries");

        try {
            HttpEntity<GetCountriesListResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            GetCountriesListResponse.class);

            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog("Request Success", "getCountriesList")));

            return resp.getBody();
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "getCountriesList",
                                    ex.getMessage(),
                                    null)));
            throw new ORDSException();
        }
    }

    @GetMapping(value = "GetProvinceList/Services", produces = MediaType.TEXT_XML_VALUE)
    public GetProvinceListResponse getProvinceList() throws JsonProcessingException {
        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(ordsHost + "locations/provinces");

        try {
            HttpEntity<GetProvinceListResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            GetProvinceListResponse.class);

            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog("Request Success", "getProvinceList")));

            GetProvinceListResponse out = new GetProvinceListResponse();
            return resp.getBody();
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "getProvinceList",
                                    ex.getMessage(),
                                    null)));
            throw new ORDSException();
        }
    }
}
