package ca.bc.gov.open.vcrc;

import static org.mockito.Mockito.when;

import ca.bc.gov.open.vcrc.controllers.*;
import ca.bc.gov.open.vcrc.models.exceptions.ORDSException;
import ca.bc.gov.open.vcrc.models.requests.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class OrdsErrorTests {
    @Mock private RestTemplate restTemplate;

    @Autowired private ObjectMapper objectMapper;

    @Autowired private MockMvc mockMvc;

    @Test
    public void testCheckApplicantForPrevCRCOrdsFail() throws JsonProcessingException {
        ApplicantController reportController = new ApplicantController(restTemplate, objectMapper);

        // Set up to mock ords response
        setUpRestTemplate();

        try {
            reportController.checkApplicantForPrevCRC(new CheckApplicantForPrevCRCRequest());
        } catch (ORDSException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    @Test
    public void testCheckApplicantForPrevCRCExFail() throws JsonProcessingException {
        ApplicantController reportController = new ApplicantController(restTemplate, objectMapper);

        // Set up to mock ords response
        setUpRestTemplate();

        try {
            reportController.checkApplicantForPrevCRCEx(new CheckApplicantForPrevCRCRequest());
        } catch (ORDSException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    @Test
    public void testCreateApplicantFail() throws JsonProcessingException {
        ApplicantController reportController = new ApplicantController(restTemplate, objectMapper);

        // Set up to mock ords response
        setUpRestTemplate();

        try {
            reportController.createApplicant(new CreateApplicantRequest());
        } catch (ORDSException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    @Test
    public void testCreateApplicantExFail() throws JsonProcessingException {
        ApplicantController reportController = new ApplicantController(restTemplate, objectMapper);

        // Set up to mock ords response
        setUpRestTemplate();

        try {
            reportController.createApplicantEx(new CreateApplicantRequest());
        } catch (ORDSException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    @Test
    public void testAuthenticateUserFail() throws JsonProcessingException {
        var authenticationController = new AuthenticationController(restTemplate, objectMapper);

        // Set up to mock ords response
        setUpRestTemplate();

        try {
            authenticationController.authenticateUser(new AuthenticateUserRequest());
        } catch (ORDSException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    @Test
    public void testGetNextSessionIdFail() throws JsonProcessingException {
        var idController = new IdController(restTemplate, objectMapper);

        // Set up to mock ords response
        setUpRestTemplate();

        try {
            idController.getNextSessionId(new GetNextSessionIdRequest());
        } catch (ORDSException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    @Test
    public void testGetNextInvoiceIdFail() throws JsonProcessingException {
        var idController = new IdController(restTemplate, objectMapper);

        // Set up to mock ords response
        setUpRestTemplate();

        try {
            idController.getNextInvoiceId(new GetNextInvoiceIdRequest());
        } catch (ORDSException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    @Test
    public void testGetCountriesListFail() throws JsonProcessingException {
        var locationController = new LocationController(restTemplate, objectMapper);

        // Set up to mock ords response
        setUpRestTemplate();

        try {
            locationController.getCountriesList();
        } catch (ORDSException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    @Test
    public void testGetProvinceListFail() throws JsonProcessingException {
        var locationController = new LocationController(restTemplate, objectMapper);

        // Set up to mock ords response
        setUpRestTemplate();

        try {
            locationController.getProvinceList();
        } catch (ORDSException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    @Test
    public void testLogEivFailureFail() throws JsonProcessingException {
        var loggingController = new LoggingController(restTemplate, objectMapper);

        // Set up to mock ords response
        setUpRestTemplate();

        try {
            loggingController.logEivFailure(new LogEivFailureRequest());
        } catch (ORDSException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    @Test
    public void testLogPaymentFailureFail() throws JsonProcessingException {
        var loggingController = new LoggingController(restTemplate, objectMapper);

        // Set up to mock ords response
        setUpRestTemplate();

        try {
            loggingController.logPaymentFailure(new LogPaymentFailureRequest());
        } catch (ORDSException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    @Test
    public void testCreateNewCrcServiceFail() throws JsonProcessingException {
        var serviceController = new ServiceController(restTemplate, objectMapper);

        // Set up to mock ords response
        setUpRestTemplate();

        try {
            serviceController.createNewCrcService(new CreateNewCRCServiceRequest());
        } catch (ORDSException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    @Test
    public void testCreateSharingServiceFail() throws JsonProcessingException {
        var serviceController = new ServiceController(restTemplate, objectMapper);

        // Set up to mock ords response
        setUpRestTemplate();

        try {
            serviceController.createSharingService(new CreateSharingServiceRequest());
        } catch (ORDSException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    @Test
    public void testGetServiceFeeAmountFail() throws JsonProcessingException {
        var serviceController = new ServiceController(restTemplate, objectMapper);

        // Set up to mock ords response
        setUpRestTemplate();

        try {
            serviceController.getServiceFeeAmount(new GetServiceFeeAmountRequest());
        } catch (ORDSException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    @Test
    public void testUpdateServiceFinancialTxnFail() throws JsonProcessingException {
        var serviceController = new ServiceController(restTemplate, objectMapper);

        // Set up to mock ords response
        setUpRestTemplate();

        try {
            serviceController.updateServiceFinancialTxn(new UpdateServiceFinancialTxnRequest());
        } catch (ORDSException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    private void setUpRestTemplate() {
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito.<Class<Object>>any()))
                .thenThrow(new RestClientException("BAD"));

        when(restTemplate.exchange(
                        Mockito.any(URI.class),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito.<Class<Object>>any()))
                .thenThrow(new RestClientException("BAD"));

        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.POST),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito.<ParameterizedTypeReference<Map<String, String>>>any()))
                .thenThrow(new RestClientException("BAD"));

        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.POST),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito.<Class<Object>>any()))
                .thenThrow(new RestClientException("BAD"));

        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.PUT),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito.<Class<Object>>any()))
                .thenThrow(new RestClientException("BAD"));

        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.DELETE),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito.<Class<Object>>any()))
                .thenThrow(new RestClientException("BAD"));
    }
}
