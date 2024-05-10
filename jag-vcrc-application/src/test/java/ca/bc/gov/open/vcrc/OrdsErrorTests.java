package ca.bc.gov.open.vcrc;

import ca.bc.gov.open.vcrc.controllers.*;
import ca.bc.gov.open.vcrc.models.exceptions.ORDSException;
import ca.bc.gov.open.vcrc.models.requests.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class OrdsErrorTests {

    @Mock private ObjectMapper objectMapper;
    @Mock private RestTemplate restTemplate;

    @InjectMocks
    private HealthController healthController;
    @InjectMocks private ApplicantController applicantController;
    @InjectMocks private AuthenticationController authenticationController;
    @InjectMocks private IdController idController;

    @InjectMocks private LocationController locationController;
    @InjectMocks private LoggingController loggingController;
    @InjectMocks private ServiceController serviceController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        healthController = Mockito.spy(new HealthController(restTemplate, objectMapper));
        applicantController = Mockito.spy(new ApplicantController(restTemplate, objectMapper));
        authenticationController = Mockito.spy(new AuthenticationController(restTemplate, objectMapper));
        idController = Mockito.spy(new IdController(restTemplate, objectMapper));
        locationController = Mockito.spy(new LocationController(restTemplate, objectMapper));
        loggingController = Mockito.spy(new LoggingController(restTemplate, objectMapper));
        serviceController = Mockito.spy(new ServiceController(restTemplate, objectMapper));
    }

    @Test
    public void testCheckApplicantForPrevCRCOrdsFail() throws JsonProcessingException {

        Assertions.assertThrows(
                ORDSException.class,
                () ->
                        applicantController.checkApplicantForPrevCRC(
                                new CheckApplicantForPrevCRCRequest()));
    }

    @Test
    public void testCheckApplicantForPrevCRCExFail() throws JsonProcessingException {
        ApplicantController reportController = new ApplicantController(restTemplate, objectMapper);
        Assertions.assertThrows(
                ORDSException.class,
                () ->
                        applicantController.checkApplicantForPrevCRCEx(
                                new CheckApplicantForPrevCRCRequest()));
    }

    @Test
    public void testCreateApplicantFail() throws JsonProcessingException {
        ApplicantController reportController = new ApplicantController(restTemplate, objectMapper);
        Assertions.assertThrows(
                ORDSException.class,
                () -> applicantController.createApplicant(new CreateApplicantRequest()));
    }

    @Test
    public void testCreateApplicantExFail() throws JsonProcessingException {
        ApplicantController reportController = new ApplicantController(restTemplate, objectMapper);
        Assertions.assertThrows(
                ORDSException.class,
                () -> applicantController.createApplicantEx(new CreateApplicantRequest()));
    }

    @Test
    public void testAuthenticateUserFail() throws JsonProcessingException {
        Assertions.assertThrows(
                ORDSException.class,
                () -> authenticationController.authenticateUser(new AuthenticateUserRequest()));
    }

    @Test
    public void testGetNextSessionIdFail() throws JsonProcessingException {
        Assertions.assertThrows(
                ORDSException.class,
                () -> idController.getNextSessionId(new GetNextSessionIdRequest()));
    }

    @Test
    public void testGetNextInvoiceIdFail() throws JsonProcessingException {
        Assertions.assertThrows(
                ORDSException.class,
                () -> idController.getNextInvoiceId(new GetNextInvoiceIdRequest()));
    }

    @Test
    public void testGetCountriesListFail() throws JsonProcessingException {
        Assertions.assertThrows(ORDSException.class, () -> locationController.getCountriesList());
    }

    @Test
    public void testGetProvinceListFail() throws JsonProcessingException {
        Assertions.assertThrows(ORDSException.class, () -> locationController.getProvinceList());
    }

    @Test
    public void testLogEivFailureFail() throws JsonProcessingException {
        Assertions.assertThrows(
                ORDSException.class,
                () -> loggingController.logEivFailure(new LogEivFailureRequest()));
    }

    @Test
    public void testLogPaymentFailureFail() throws JsonProcessingException {
        Assertions.assertThrows(
                ORDSException.class,
                () -> loggingController.logPaymentFailure(new LogPaymentFailureRequest()));
    }

    @Test
    public void testCreateNewCrcServiceFail() throws JsonProcessingException {
        Assertions.assertThrows(
                ORDSException.class,
                () -> serviceController.createNewCrcService(new CreateNewCRCServiceRequest()));
    }

    @Test
    public void testCreateSharingServiceFail() throws JsonProcessingException {
        Assertions.assertThrows(
                ORDSException.class,
                () -> serviceController.createSharingService(new CreateSharingServiceRequest()));
    }

    @Test
    public void testGetServiceFeeAmountFail() throws JsonProcessingException {
        Assertions.assertThrows(
                ORDSException.class,
                () -> serviceController.getServiceFeeAmount(new GetServiceFeeAmountRequest()));
    }

    @Test
    public void testUpdateServiceFinancialTxnFail() throws JsonProcessingException {
        Assertions.assertThrows(
                ORDSException.class,
                () ->
                        serviceController.updateServiceFinancialTxn(
                                new UpdateServiceFinancialTxnRequest()));
    }
}
