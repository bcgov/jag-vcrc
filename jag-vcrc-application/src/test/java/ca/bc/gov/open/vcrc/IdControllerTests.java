package ca.bc.gov.open.vcrc;

import static org.mockito.Mockito.when;

import ca.bc.gov.open.vcrc.controllers.AuthenticationController;
import ca.bc.gov.open.vcrc.controllers.IdController;
import ca.bc.gov.open.vcrc.models.requests.GetNextInvoiceIdRequest;
import ca.bc.gov.open.vcrc.models.requests.GetNextSessionIdRequest;
import ca.bc.gov.open.vcrc.models.responses.GetNextInvoiceIdResponse;
import ca.bc.gov.open.vcrc.models.responses.GetNextSessionIdResponse;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
public class IdControllerTests {

    @Mock private RestTemplate restTemplate;
    @Mock private ObjectMapper objectMapper;

    @InjectMocks
    private IdController idController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        idController = Mockito.spy(new IdController(restTemplate, objectMapper));
    }

    @Test
    public void getNextSessionIdTest() throws JsonProcessingException {
        var req = new GetNextSessionIdRequest();
        req.setOrgTicketNumber("A");

        var getNextSessionId = new GetNextSessionIdResponse();
        getNextSessionId.setSessionId("A");
        getNextSessionId.setMessage("A");
        getNextSessionId.setResponseCode("A");

        ResponseEntity<GetNextSessionIdResponse> responseEntity =
                new ResponseEntity<>(getNextSessionId, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito.<Class<GetNextSessionIdResponse>>any()))
                .thenReturn(responseEntity);

        var resp = idController.getNextSessionId(req);
        Assertions.assertNotNull(resp);
    }

    @Test
    public void getNextInvoiceIdTest() throws JsonProcessingException {
        var req = new GetNextInvoiceIdRequest();
        req.setOrgTicketNumber("A");

        var getNextInvoiceId = new GetNextInvoiceIdResponse();
        getNextInvoiceId.setInvoiceId("A");
        getNextInvoiceId.setMessage("A");
        getNextInvoiceId.setResponseCode("A");

        ResponseEntity<GetNextInvoiceIdResponse> responseEntity =
                new ResponseEntity<>(getNextInvoiceId, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito.<Class<GetNextInvoiceIdResponse>>any()))
                .thenReturn(responseEntity);

        var resp = idController.getNextInvoiceId(req);
        Assertions.assertNotNull(resp);
    }
}
