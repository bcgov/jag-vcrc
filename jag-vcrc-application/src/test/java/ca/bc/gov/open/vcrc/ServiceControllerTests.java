package ca.bc.gov.open.vcrc;

import static org.mockito.Mockito.when;

import ca.bc.gov.open.vcrc.controllers.LoggingController;
import ca.bc.gov.open.vcrc.controllers.ServiceController;
import ca.bc.gov.open.vcrc.models.requests.*;
import ca.bc.gov.open.vcrc.models.responses.*;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
public class ServiceControllerTests {
    @Mock private ObjectMapper objectMapper;
    @Mock private RestTemplate restTemplate;
    @InjectMocks
    private ServiceController serviceController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        serviceController = Mockito.spy(new ServiceController(restTemplate, objectMapper));
    }

    private CreateSharingServiceRequest CreateSharingService_Request() {
        var req = new CreateSharingServiceRequest();

        req.setOrgTicketNumber("A");
        req.setApplPartyId("A");
        req.setScopeLevelCd("A");
        req.setApplicantPosn("A");
        req.setAuthReleaseEIVVendorYN("A");
        req.setAuthReleaseToOrgYN("A");
        req.setApplIdentityVerifiedEIVYN("A");
        req.setPreviousServiceId("A");
        req.setEivPassDetailsResults("A");

        req.setAppl_Party_Id("A");
        req.setScope_Level_Cd("A");
        req.setApplicant_Posn("A");
        req.setAuth_Release_EIV_Vendor_YN("A");
        req.setAuth_Release_To_Org_YN("A");
        req.setAppl_Identity_Verified_EIV_YN("A");
        req.setPrevious_Service_Id("A");

        return req;
    }

    private UpdateServiceFinancialTxnRequest UpdateServiceFinancialTxn_Request() {
        var req = new UpdateServiceFinancialTxnRequest();
        req.setOrgTicketNumber("A");
        req.setApplPartyId("A");
        req.setServiceId("A");
        req.setCCAuthorization("A");
        req.setPaymentDate(new Date());
        req.setPayorTypeCd("A");
        req.setPaymentStatusCd("A");
        req.setSessionId("A");
        req.setInvoiceId("A");
        req.setTransactionId("A");
        req.setTransactionAmount("A");

        req.setOrgTicketNumber("A");
        req.setApplPartyId("A");
        req.setServiceId("A");
        req.setCCAuthorization("A");
        req.setPaymentDate(new Date());
        req.setPayorTypeCd("A");
        req.setPaymentStatusCd("A");
        req.setSessionId("A");
        req.setInvoiceId("A");
        req.setTransactionId("A");
        req.setTransactionAmount("A");

        req.setAppl_Party_Id("A");
        req.setService_Id("A");
        req.setCC_Authorization("A");
        req.setPayment_Date(new Date());
        req.setPayor_Type_Cd("A");
        req.setPayment_Status_Cd("A");
        req.setSession_Id("A");
        req.setInvoice_Id("A");
        req.setTransaction_Id("A");
        req.setTransaction_Amount("A");

        return req;
    }

    private CreateNewCRCServiceRequest CreateNewCRCService_Request() {
        var req = new CreateNewCRCServiceRequest();

        req.setOrgTicketNumber("A");
        req.setScheduleTypeCd("A");
        req.setScopeLevelCd("A");
        req.setApplPartyId("A");
        req.setOrgApplToPay("A");
        req.setApplicantPosn("A");
        req.setChildCareFacNm("A");
        req.setGoverningBodyNm("A");
        req.setSessionId("A");
        req.setInvoiceId("A");
        req.setAuthReleaseEIVVendorYN("A");
        req.setAuthConductCRCCheckYN("A");
        req.setAuthReleaseToOrgYN("A");
        req.setApplIdentityVerifiedEIVYN("A");
        req.setEivPassDetailsResults("A");

        req.setSchedule_Type_Cd("A");
        req.setScope_Level_Cd("A");
        req.setAppl_Party_Id("A");
        req.setOrg_Appl_To_Pay("A");
        req.setApplicant_Posn("A");
        req.setChild_Care_Fac_Nm("A");
        req.setGoverning_Body_Nm("A");
        req.setSession_Id("A");
        req.setInvoice_Id("A");
        req.setAuth_Release_EIV_Vendor_YN("A");
        req.setAuth_Conduct_CRC_Check_YN("A");
        req.setAuth_Release_To_Org_YN("A");
        req.setAppl_Identity_Verified_EIV_YN("A");

        return req;
    }

    private GetServiceFeeAmountRequest GetServiceFeeAmount_Request() {
        var req = new GetServiceFeeAmountRequest();
        req.setOrgTicketNumber("A");
        req.setScheduleTypeCd("A");
        req.setScopeLevelCd("A");

        req.setOrgTicketNumber("A");
        req.setScheduleTypeCd("A");
        req.setScopeLevelCd("A");

        var out = new GetServiceFeeAmountResponse();
        out.setServiceFeeAmount("A");
        out.setMessage("A");
        out.setResponseCode("A");
        return req;
    }

    @Test
    public void createNewCrcServiceTest() throws JsonProcessingException {

        var req = CreateNewCRCService_Request();

        var out = new CreateNewCRCServiceResponse();
        out.setServiceId("A");
        out.setMessage("A");
        out.setResponseCode("A");

        ResponseEntity<CreateNewCRCServiceResponse> responseEntity =
                new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.POST),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito.<Class<CreateNewCRCServiceResponse>>any()))
                .thenReturn(responseEntity);

        var resp = serviceController.createNewCrcService(req);
        Assertions.assertNotNull(resp);
    }

    @Test
    public void createSharingServiceTest() throws JsonProcessingException {
        var req = CreateSharingService_Request();

        var out = new CreateSharingServiceResponse();
        out.setServiceId("A");
        out.setMessage("A");
        out.setResponseCode("A");

        ResponseEntity<CreateSharingServiceResponse> responseEntity =
                new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.POST),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito.<Class<CreateSharingServiceResponse>>any()))
                .thenReturn(responseEntity);

        var resp = serviceController.createSharingService(req);
        Assertions.assertNotNull(resp);
    }

    @Test
    public void getServiceFeeAmountTest() throws JsonProcessingException {
        var req = GetServiceFeeAmount_Request();

        req.setScopeLevelCd("A");
        req.setScheduleTypeCd("A");
        req.setOrgTicketNumber("A");

        var out = new GetServiceFeeAmountResponse();
        out.setMessage("A");
        out.setServiceFeeAmount("A");
        out.setResponseCode("A");

        ResponseEntity<GetServiceFeeAmountResponse> responseEntity =
                new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito.<Class<GetServiceFeeAmountResponse>>any()))
                .thenReturn(responseEntity);

        var resp = serviceController.getServiceFeeAmount(req);
        Assertions.assertNotNull(resp);
    }

    @Test
    public void updateServiceFinancialTxnTest() throws JsonProcessingException {
        var req = UpdateServiceFinancialTxn_Request();

        var out = new UpdateServiceFinancialTxnResponse();
        var baseXMLResponse = new BaseXMLResponse();
        out.setResponseCode("A");
        out.setMessage("A");

        ResponseEntity<UpdateServiceFinancialTxnResponse> responseEntity =
                new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.PUT),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito.<Class<UpdateServiceFinancialTxnResponse>>any()))
                .thenReturn(responseEntity);

        var resp = serviceController.updateServiceFinancialTxn(req);
        Assertions.assertNotNull(resp);
    }
}
