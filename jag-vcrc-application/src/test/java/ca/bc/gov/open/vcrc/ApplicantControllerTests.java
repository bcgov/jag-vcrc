package ca.bc.gov.open.vcrc;

import static org.mockito.Mockito.when;

import ca.bc.gov.open.vcrc.controllers.ApplicantController;
import ca.bc.gov.open.vcrc.controllers.AuthenticationController;
import ca.bc.gov.open.vcrc.controllers.IdController;
import ca.bc.gov.open.vcrc.models.requests.CheckApplicantForPrevCRCRequest;
import ca.bc.gov.open.vcrc.models.requests.CreateApplicantRequest;
import ca.bc.gov.open.vcrc.models.responses.CheckApplicantForPrevCRCExResponse;
import ca.bc.gov.open.vcrc.models.responses.CheckApplicantForPrevCRCResponse;
import ca.bc.gov.open.vcrc.models.responses.CreateApplicantResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
public class ApplicantControllerTests {
    @Mock private RestTemplate restTemplate;
    @Mock private ObjectMapper objectMapper;

    @InjectMocks
    private ApplicantController applicantController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        applicantController = Mockito.spy(new ApplicantController(restTemplate, objectMapper));
    }

    private CheckApplicantForPrevCRCRequest CheckApplicantForPrevCRC_Request() {

        var req = new CheckApplicantForPrevCRCRequest();
        req.setOrgTicketNumber("A");
        req.setLegalSurnameNm("A");
        req.setLegal_First_Nm("A");
        req.setBirthDt(new Date());
        req.setGenderTxt("A");
        req.setPostalCodeTxt("A");
        req.setDriversLicNo("A");
        req.setScope_Level_Cd("A");
        req.setPreviousServiceId("A");

        req.setPostal_Code_Txt("A");
        req.setOrgTicketNumber("A");
        req.setLegal_Surname_Nm("A");
        req.setLegal_First_Nm("A");
        req.setGender_Txt("A");
        req.setDriversLicNo("A");
        req.setScope_Level_Cd("A");
        req.setPrevious_Service_Id("A");
        req.setBirth_Dt(new Date());

        return req;
    }

    private CreateApplicantRequest CreateApplicant_Request() {

        var req = new CreateApplicantRequest();

        req.setOrgTicketNumber("A");
        req.setCallPurpose("A");
        req.setLegalSurnameNm("A");
        req.setLegalFirstNm("A");
        req.setLegalSecondNm("A");
        req.setBirthDt(new Date());
        req.setGenderTxt("A");
        req.setBirthPlace("A");
        req.setAlias1SurnameNm("A");
        req.setAlias1FirstNm("A");
        req.setAlias1SecondNm("A");
        req.setAlias2SurnameNm("A");
        req.setAlias2FirstNm("A");
        req.setAlias2SecondNm("A");
        req.setAlias3SurnameNm("A");
        req.setAlias3FirstNm("A");
        req.setAlias3SecondNm("A");
        req.setPhoneNumber("A");
        req.setAddressLine1("A");
        req.setAddressLine2("A");
        req.setCityNm("A");
        req.setProvinceNm("A");
        req.setCountryNm("A");
        req.setPostalCodeTxt("A");
        req.setDriversLicNo("A");
        req.setEmailAddress("A");
        req.setEmailType("A");

        req.setOrgTicketNumber("A");
        req.setCall_Purpose("A");
        req.setLegal_Surname_Nm("A");
        req.setLegal_First_Nm("A");
        req.setLegal_Second_Nm("A");
        req.setBirth_Dt(new Date());
        req.setGender_Txt("A");
        req.setBirth_Place("A");
        req.setAlias1_Surname_Nm("A");
        req.setAlias1_First_Nm("A");
        req.setAlias1_Second_Nm("A");
        req.setAlias2_Surname_Nm("A");
        req.setAlias2_First_Nm("A");
        req.setAlias2_Second_Nm("A");
        req.setAlias3_Surname_Nm("A");
        req.setAlias3_First_Nm("A");
        req.setAlias3_Second_Nm("A");
        req.setPhone_Number("A");
        req.setAddress_Line1("A");
        req.setAddress_Line2("A");
        req.setCity_Nm("A");
        req.setProvince_Nm("A");
        req.setCountry_Nm("A");
        req.setPostal_Code_Txt("A");
        req.setDrivers_Lic_No("A");
        req.setEmail_Address("A");
        req.setEmail_Type("A");

        return req;
    }

    @Test
    public void checkApplicantForPrevCRCTest() throws JsonProcessingException {
        var req = CheckApplicantForPrevCRC_Request();

        var out = new CheckApplicantForPrevCRCResponse();
        out.setMessage("A");
        out.setResponseCode("A");

        ResponseEntity<CheckApplicantForPrevCRCResponse> responseEntity =
                new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.POST),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito.<Class<CheckApplicantForPrevCRCResponse>>any()))
                .thenReturn(responseEntity);

        var resp = applicantController.checkApplicantForPrevCRC(req);
        Assertions.assertNotNull(resp);
    }

    @Test
    public void checkApplicantForPrevCRCExTest() throws JsonProcessingException {
        var req = CheckApplicantForPrevCRC_Request();

        var out = new CheckApplicantForPrevCRCExResponse();
        out.setServiceId("A");
        out.setMessage("A");
        out.setResponseCode("A");

        ResponseEntity<CheckApplicantForPrevCRCExResponse> responseEntity =
                new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.POST),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito.<Class<CheckApplicantForPrevCRCExResponse>>any()))
                .thenReturn(responseEntity);

        var resp = applicantController.checkApplicantForPrevCRCEx(req);
        Assertions.assertNotNull(resp);
    }

    @Test
    public void createApplicantTest() throws JsonProcessingException {

        var req = CreateApplicant_Request();

        var out = new CreateApplicantResponse();
        out.setPartyId("A");
        out.setMessage("A");
        out.setResponseCode("A");

        ResponseEntity<CreateApplicantResponse> responseEntity =
                new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.POST),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito.<Class<CreateApplicantResponse>>any()))
                .thenReturn(responseEntity);

        var resp = applicantController.createApplicant(req);
        Assertions.assertNotNull(resp);
    }

    @Test
    public void createApplicantExTest() throws JsonProcessingException {
        var req = CreateApplicant_Request();
        var out = new CreateApplicantResponse();
        out.setPartyId("A");
        out.setMessage("A");
        out.setResponseCode("A");

        ResponseEntity<CreateApplicantResponse> responseEntity =
                new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.POST),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito.<Class<CreateApplicantResponse>>any()))
                .thenReturn(responseEntity);

        var resp = applicantController.createApplicantEx(req);
        Assertions.assertNotNull(resp);
    }
}
