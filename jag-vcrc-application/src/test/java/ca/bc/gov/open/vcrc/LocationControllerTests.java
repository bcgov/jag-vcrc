package ca.bc.gov.open.vcrc;

import static org.mockito.Mockito.when;

import ca.bc.gov.open.vcrc.controllers.LocationController;
import ca.bc.gov.open.vcrc.models.responses.GetCountriesListResponse;
import ca.bc.gov.open.vcrc.models.responses.GetProvinceListResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class LocationControllerTests {

    @Autowired private ObjectMapper objectMapper;

    @Mock private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void getCountriesListTest() throws JsonProcessingException {

        var out = new GetCountriesListResponse.GetCountriesList();
        GetCountriesListResponse.Countries countries = new GetCountriesListResponse.Countries();
        List<GetCountriesListResponse.Country> list = new ArrayList<>();
        var country = new GetCountriesListResponse.Country();
        country.setName("A");
        list.add(country);
        countries.setCountry(list);
        out.setCountries(countries);
        out.setMessage("A");
        out.setResponseCode("A");

        ResponseEntity<GetCountriesListResponse.GetCountriesList> responseEntity =
                new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito.<Class<GetCountriesListResponse.GetCountriesList>>any()))
                .thenReturn(responseEntity);

        var locationController = new LocationController(restTemplate, objectMapper);
        var resp = locationController.getCountriesList();
        Assertions.assertNotNull(resp);
    }

    @Test
    public void getProvinceListTest() throws JsonProcessingException {

        var out = new GetProvinceListResponse.GetProvinceList();
        GetProvinceListResponse.Provinces provinces = new GetProvinceListResponse.Provinces();
        List<GetProvinceListResponse.Province> list = new ArrayList<>();
        var province = new GetProvinceListResponse.Province();
        province.setName("A");
        list.add(province);
        provinces.setProvince(list);
        out.setProvinces(provinces);
        out.setMessage("A");
        out.setResponseCode("A");

        ResponseEntity<GetProvinceListResponse.GetProvinceList> responseEntity =
                new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito.<Class<GetProvinceListResponse.GetProvinceList>>any()))
                .thenReturn(responseEntity);

        var locationController = new LocationController(restTemplate, objectMapper);
        var resp = locationController.getProvinceList();
        Assertions.assertNotNull(resp);
    }
}
