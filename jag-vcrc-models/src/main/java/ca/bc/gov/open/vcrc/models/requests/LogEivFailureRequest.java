package ca.bc.gov.open.vcrc.models.requests;

import java.util.Date;
import lombok.Data;

@Data
public class LogEivFailureRequest {

    private String orgTicketNumber;
    private String sessionId;
    private String legalSurnameNm;
    private String legalFirstNm;
    private String legalSecondNm;
    private Date birthDt;
    private String genderTxt;
    private String eIVVendorErrorMsg;

    public void setSession_Id(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setLegal_Surname_Nm(String legalSurnameNm) {
        this.legalSurnameNm = legalSurnameNm;
    }

    public void setLegal_First_Nm(String legalFirstNm) {
        this.legalFirstNm = legalFirstNm;
    }

    public void setLegal_Second_Nm(String legalSecondNm) {
        this.legalSecondNm = legalSecondNm;
    }

    public void setBirth_Dt(Date birthDt) {
        this.birthDt = birthDt;
    }

    public void setGender_Txt(String genderTxt) {
        this.genderTxt = genderTxt;
    }

    public void setEIV_Vendor_Error_Msg(String eIVVendorErrorMsg) {
        this.eIVVendorErrorMsg = eIVVendorErrorMsg;
    }
}
