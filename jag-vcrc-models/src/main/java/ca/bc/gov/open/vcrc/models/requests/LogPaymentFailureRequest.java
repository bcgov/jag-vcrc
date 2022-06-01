package ca.bc.gov.open.vcrc.models.requests;

import lombok.Data;

@Data
public class LogPaymentFailureRequest {

    private String orgTicketNumber;
    private String serviceId;
    private String sessionId;
    private String applPartyId;
    private String invoiceId;
    private String serviceFeeAmount;
    private String BCEPErrorMsg;

    public void setService_Id(String serviceId) {
        this.serviceId = serviceId;
    }

    public void setSession_Id(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setAppl_Party_Id(String applPartyId) {
        this.applPartyId = applPartyId;
    }

    public void setInvoice_Id(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public void setService_Fee_Amount(String serviceFeeAmount) {
        this.serviceFeeAmount = serviceFeeAmount;
    }

    public void setBCEPErrorMsg(String BCEPErrorMsg) {
        this.BCEPErrorMsg = BCEPErrorMsg;
    }
}
