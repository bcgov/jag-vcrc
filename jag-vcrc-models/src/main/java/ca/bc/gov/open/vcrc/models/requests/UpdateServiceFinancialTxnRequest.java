package ca.bc.gov.open.vcrc.models.requests;

import java.util.Date;
import lombok.Data;

@Data
public class UpdateServiceFinancialTxnRequest {

    private String orgTicketNumber;
    private String applPartyId;
    private String serviceId;
    private String cCAuthorization;
    private Date paymentDate;
    private String payorTypeCd;
    private String paymentStatusCd;
    private String sessionId;
    private String invoiceId;
    private String transactionId;
    private String transactionAmount;

    public void setAppl_Party_Id(String applPartyId) {
        this.applPartyId = applPartyId;
    }

    public void setService_Id(String serviceId) {
        this.serviceId = serviceId;
    }

    public void setCC_Authorization(String ccAuthorization) {
        this.cCAuthorization = ccAuthorization;
    }

    public void setPayment_Date(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setPayor_Type_Cd(String payorTypeCd) {
        this.payorTypeCd = payorTypeCd;
    }

    public void setPayment_Status_Cd(String paymentStatusCd) {
        this.paymentStatusCd = paymentStatusCd;
    }

    public void setSession_Id(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setInvoice_Id(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public void setTransaction_Id(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setTransaction_Amount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
}
