package ca.bc.gov.open.vcrc;

import ca.bc.gov.open.vcrc.controllers.*;
import ca.bc.gov.open.vcrc.models.exceptions.ORDSException;
import ca.bc.gov.open.vcrc.models.requests.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class OrdsErrorTests {
    @Autowired private MockMvc mockMvc;

    @Autowired private ObjectMapper objectMapper;

    @Mock private RestTemplate restTemplate;

    @Test
    public void testCheckApplicantForPrevCRCOrdsFail() throws JsonProcessingException {

        ApplicantController reportController = new ApplicantController(restTemplate, objectMapper);
        Assertions.assertThrows(
                ORDSException.class,
                () ->
                        reportController.checkApplicantForPrevCRC(
                                new CheckApplicantForPrevCRCRequest()));
    }

    @Test
    public void testCheckApplicantForPrevCRCExFail() throws JsonProcessingException {
        ApplicantController reportController = new ApplicantController(restTemplate, objectMapper);
        Assertions.assertThrows(
                ORDSException.class,
                () ->
                        reportController.checkApplicantForPrevCRCEx(
                                new CheckApplicantForPrevCRCRequest()));
    }

    @Test
    public void testCreateApplicantFail() throws JsonProcessingException {
        ApplicantController reportController = new ApplicantController(restTemplate, objectMapper);
        Assertions.assertThrows(
                ORDSException.class,
                () -> reportController.createApplicant(new CreateApplicantRequest()));
    }

    @Test
    public void testCreateApplicantExFail() throws JsonProcessingException {
        ApplicantController reportController = new ApplicantController(restTemplate, objectMapper);
        Assertions.assertThrows(
                ORDSException.class,
                () -> reportController.createApplicantEx(new CreateApplicantRequest()));
    }

    @Test
    public void testAuthenticateUserFail() throws JsonProcessingException {
        var authenticationController = new AuthenticationController(restTemplate, objectMapper);
        Assertions.assertThrows(
                ORDSException.class,
                () -> authenticationController.authenticateUser(new AuthenticateUserRequest()));
    }

    @Test
    public void testGetNextSessionIdFail() throws JsonProcessingException {
        var idController = new IdController(restTemplate, objectMapper);
        Assertions.assertThrows(
                ORDSException.class,
                () -> idController.getNextSessionId(new GetNextSessionIdRequest()));
    }

    @Test
    public void testGetNextInvoiceIdFail() throws JsonProcessingException {
        var idController = new IdController(restTemplate, objectMapper);
        Assertions.assertThrows(
                ORDSException.class,
                () -> idController.getNextInvoiceId(new GetNextInvoiceIdRequest()));
    }

    @Test
    public void testGetCountriesListFail() throws JsonProcessingException {
        var locationController = new LocationController(restTemplate, objectMapper);
        Assertions.assertThrows(ORDSException.class, () -> locationController.getCountriesList());
    }

    @Test
    public void testGetProvinceListFail() throws JsonProcessingException {
        var locationController = new LocationController(restTemplate, objectMapper);
        Assertions.assertThrows(ORDSException.class, () -> locationController.getProvinceList());
    }

    @Test
    public void testLogEivFailureFail() throws JsonProcessingException {
        var loggingController = new LoggingController(restTemplate, objectMapper);
        Assertions.assertThrows(
                ORDSException.class,
                () -> loggingController.logEivFailure(new LogEivFailureRequest()));
    }

    @Test
    public void testLogPaymentFailureFail() throws JsonProcessingException {
        var loggingController = new LoggingController(restTemplate, objectMapper);
        Assertions.assertThrows(
                ORDSException.class,
                () -> loggingController.logPaymentFailure(new LogPaymentFailureRequest()));
    }

    @Test
    public void testCreateNewCrcServiceFail() throws JsonProcessingException {
        var serviceController = new ServiceController(restTemplate, objectMapper);
        Assertions.assertThrows(
                ORDSException.class,
                () -> serviceController.createNewCrcService(new CreateNewCRCServiceRequest()));
    }

    @Test
    public void testCreateSharingServiceFail() throws JsonProcessingException {
        var serviceController = new ServiceController(restTemplate, objectMapper);
        Assertions.assertThrows(
                ORDSException.class,
                () -> serviceController.createSharingService(new CreateSharingServiceRequest()));
    }

    @Test
    public void testGetServiceFeeAmountFail() throws JsonProcessingException {
        var serviceController = new ServiceController(restTemplate, objectMapper);
        Assertions.assertThrows(
                ORDSException.class,
                () -> serviceController.getServiceFeeAmount(new GetServiceFeeAmountRequest()));
    }

    @Test
    public void testUpdateServiceFinancialTxnFail() throws JsonProcessingException {
        var serviceController = new ServiceController(restTemplate, objectMapper);
        Assertions.assertThrows(
                ORDSException.class,
                () ->
                        serviceController.updateServiceFinancialTxn(
                                new UpdateServiceFinancialTxnRequest()));
    }
}
