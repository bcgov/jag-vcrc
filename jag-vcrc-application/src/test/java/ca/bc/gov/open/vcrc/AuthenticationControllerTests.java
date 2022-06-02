package ca.bc.gov.open.vcrc;

import static org.mockito.Mockito.when;

import ca.bc.gov.open.vcrc.controllers.AuthenticationController;
import ca.bc.gov.open.vcrc.models.requests.AuthenticateUserRequest;
import ca.bc.gov.open.vcrc.models.responses.AuthenticateUserResponse;
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
public class AuthenticationControllerTests {
    @Autowired private ObjectMapper objectMapper;

    @Mock private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void authenticateUserTest() throws JsonProcessingException {
        var req = new AuthenticateUserRequest();
        var inner = new AuthenticateUserResponse();
        var accessCodeResponse = new AuthenticateUserResponse.AccessCodeResponse();

        req.setOrgTicketNumber("A");
        accessCodeResponse.setOrgPartyId("A");
        accessCodeResponse.setOrgNm("A");
        accessCodeResponse.setContactSurNm("A");
        accessCodeResponse.setContactFirstNm("A");
        accessCodeResponse.setAddressLine1("A");
        accessCodeResponse.setAddressLine2("A");
        accessCodeResponse.setCityNm("A");
        accessCodeResponse.setProvinceNm("A");
        accessCodeResponse.setPostalCodeTxt("A");
        accessCodeResponse.setContactPhoneNo("A");
        accessCodeResponse.setContactFaxNo("A");
        accessCodeResponse.setOrgApplicantRelationship("A");
        accessCodeResponse.setDefaultScheduleTypeCd("A");
        accessCodeResponse.setDefaultCrcScopeLevelCd("A");
        accessCodeResponse.setTicketFoundYN("A");
        accessCodeResponse.setAlreadyUsedYN("A");
        accessCodeResponse.setValidDateRangeYN("A");
        inner.setAccessCodeResponse(accessCodeResponse);

        var scheduleTypes = new AuthenticateUserResponse.ScheduleTypes();
        List<AuthenticateUserResponse.ScheduleTypeItem> scheduleTypeItems = new ArrayList<>();
        var scheduleTypeItem = new AuthenticateUserResponse.ScheduleTypeItem();
        scheduleTypeItem.setCrcScheduleTypeCd("A");
        scheduleTypeItem.setCrcScheduleTypeDsc("A");
        scheduleTypeItems.add(scheduleTypeItem);
        scheduleTypes.setScheduleType(scheduleTypeItems);
        inner.setScheduleTypes(scheduleTypes);

        var scopeLevels = new AuthenticateUserResponse.ScopeLevels();
        List<AuthenticateUserResponse.ScopeLevelItem> scopeLevelItems = new ArrayList<>();
        var scopeLevelItem = new AuthenticateUserResponse.ScopeLevelItem();
        scopeLevelItem.setCrcScopeLevelCd("A");
        scopeLevelItem.setCrcScopeLevelDsc("A");
        scopeLevelItems.add(scopeLevelItem);
        scopeLevels.setScopeLevel(scopeLevelItems);
        inner.setScopeLevels(scopeLevels);

        inner.setMessage("A");
        inner.setResponseCode("A");

        ResponseEntity<AuthenticateUserResponse> responseEntity =
                new ResponseEntity<>(inner, HttpStatus.OK);

        // Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.POST),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito.<Class<AuthenticateUserResponse>>any()))
                .thenReturn(responseEntity);

        var authenticationController = new AuthenticationController(restTemplate, objectMapper);
        var resp = authenticationController.authenticateUser(req);
        Assertions.assertNotNull(resp);
    }
}
