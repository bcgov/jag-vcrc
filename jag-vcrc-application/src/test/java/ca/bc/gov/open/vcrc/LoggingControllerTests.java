package ca.bc.gov.open.vcrc;

import static org.mockito.Mockito.when;

import ca.bc.gov.open.vcrc.controllers.LoggingController;
import ca.bc.gov.open.vcrc.models.requests.LogEivFailureRequest;
import ca.bc.gov.open.vcrc.models.requests.LogPaymentFailureRequest;
import ca.bc.gov.open.vcrc.models.responses.LogEivFailureResponse;
import ca.bc.gov.open.vcrc.models.responses.LogPaymentFailureResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
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
public class LoggingControllerTests {

    @Autowired private ObjectMapper objectMapper;

    @Mock private RestTemplate restTemplate = new RestTemplate();

    private LogEivFailureRequest LogEivFailure_Request() {
        var req = new LogEivFailureRequest();
        req.setOrgTicketNumber("A");
        req.setSessionId("A");
        req.setLegalSurnameNm("A");
        req.setLegalFirstNm("A");
        req.setLegalSecondNm("A");
        req.setBirthDt(new Date());
        req.setGenderTxt("A");
        req.setEIVVendorErrorMsg("A");

        req.setSession_Id("A");
        req.setLegal_Surname_Nm("A");
        req.setLegal_First_Nm("A");
        req.setLegal_Second_Nm("A");
        req.setBirth_Dt(new Date());
        req.setGender_Txt("A");
        req.setEIV_Vendor_Error_Msg("A");

        return req;
    }

    private LogPaymentFailureRequest LogPaymentFailure_Request() {

        var req = new LogPaymentFailureRequest();

        req.setOrgTicketNumber("A");
        req.setServiceId("A");
        req.setSessionId("A");
        req.setApplPartyId("A");
        req.setInvoiceId("A");
        req.setServiceFeeAmount("A");
        req.setBCEPErrorMsg("A");
        req.setService_Id("A");
        req.setSession_Id("A");
        req.setAppl_Party_Id("A");
        req.setInvoice_Id("A");
        req.setService_Fee_Amount("A");
        req.setBCEPErrorMsg("A");

        return req;
    }

    @Test
    public void getLogEivFailureTest() throws JsonProcessingException {
        var req = LogEivFailure_Request();

        var out = new LogEivFailureResponse();
        out.setResponseCode("A");
        out.setMessage("A");

        ResponseEntity<LogEivFailureResponse> responseEntity =
                new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.POST),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito.<Class<LogEivFailureResponse>>any()))
                .thenReturn(responseEntity);

        var loggingController = new LoggingController(restTemplate, objectMapper);
        var resp = loggingController.logEivFailure(req);
        Assertions.assertNotNull(resp);
    }

    @Test
    public void logPaymentFailureTest() throws JsonProcessingException {
        var req = LogPaymentFailure_Request();

        var out = new LogPaymentFailureResponse();
        out.setResponseCode("A");
        out.setMessage("A");

        ResponseEntity<LogPaymentFailureResponse> responseEntity =
                new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.POST),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito.<Class<LogPaymentFailureResponse>>any()))
                .thenReturn(responseEntity);

        var loggingController = new LoggingController(restTemplate, objectMapper);
        var resp = loggingController.logPaymentFailure(req);
        Assertions.assertNotNull(resp);
    }
}
