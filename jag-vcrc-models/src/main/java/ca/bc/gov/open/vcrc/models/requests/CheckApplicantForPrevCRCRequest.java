package ca.bc.gov.open.vcrc.models.requests;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class CheckApplicantForPrevCRCRequest implements Serializable {

    private String orgTicketNumber;

    private String legalSurnameNm;

    private String legalFirstNm;

    private Date birthDt;

    private String genderTxt;

    private String postalCodeTxt;

    private String driversLicNo;

    private String scopeLevelCd;

    private String previousServiceId;

    //    The weird setters are needed to set binding on request ingestion

    public void setPostal_Code_Txt(String postalCodeTxt) {
        this.postalCodeTxt = postalCodeTxt;
    }

    public void setLegal_Surname_Nm(String legalSurnameNm) {
        this.legalSurnameNm = legalSurnameNm;
    }

    public void setLegal_First_Nm(String legalFirstName) {
        this.legalFirstNm = legalFirstName;
    }

    public void setGender_Txt(String genderTxt) {
        this.genderTxt = genderTxt;
    }

    public void setDrivers_Lic_No(String driversLicNo) {
        this.driversLicNo = driversLicNo;
    }

    public void setScope_Level_Cd(String scopeLvlCd) {
        this.scopeLevelCd = scopeLvlCd;
    }

    public void setPrevious_Service_Id(String previousServiceId) {
        this.previousServiceId = previousServiceId;
    }

    public void setBirth_Dt(Date birthDt) {
        this.birthDt = birthDt;
    }
}
