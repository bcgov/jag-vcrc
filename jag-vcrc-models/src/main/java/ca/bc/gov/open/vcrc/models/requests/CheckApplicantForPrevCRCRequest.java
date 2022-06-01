package ca.bc.gov.open.vcrc.models.requests;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class CheckApplicantForPrevCRCRequest implements Serializable {

    private String orgTicketNumber;

    private String legalSurnameNm;

    private String legalFirstName;

    private Date birthDt;

    private String genderTxt;

    private String postalCodeTxt;

    private String driversLicNo;

    private String scopeLvlCd;

    private String previousServiceId;

    //    The weird setters are needed to set binding on request ingestion

    public void setPostal_Code_Txt(String postalCodeTxt) {
        this.postalCodeTxt = postalCodeTxt;
    }

    public String getPostalCodeTxt() {
        return postalCodeTxt;
    }

    public String getOrgTicketNumber() {
        return orgTicketNumber;
    }

    public void setOrgTicketNumber(String orgTicketNumber) {
        this.orgTicketNumber = orgTicketNumber;
    }

    public String getLegal_Surname_Nm() {
        return legalSurnameNm;
    }

    public void setLegal_Surname_Nm(String legalSurnameNm) {
        this.legalSurnameNm = legalSurnameNm;
    }

    public String getLegal_First_Name() {
        return legalFirstName;
    }

    public void setLegal_First_Name(String legalFirstName) {
        this.legalFirstName = legalFirstName;
    }

    public String getGender_Txt() {
        return genderTxt;
    }

    public void setGender_Txt(String genderTxt) {
        this.genderTxt = genderTxt;
    }

    public String getDriversLicNo() {
        return driversLicNo;
    }

    public void setDriversLicNo(String driversLicNo) {
        this.driversLicNo = driversLicNo;
    }

    public String getScopeLvlCd() {
        return scopeLvlCd;
    }

    public void setScope_Lvl_Cd(String scopeLvlCd) {
        this.scopeLvlCd = scopeLvlCd;
    }

    public String getPreviousServiceId() {
        return previousServiceId;
    }

    public void setPrevious_Service_Id(String previousServiceId) {
        this.previousServiceId = previousServiceId;
    }

    public Date getBirthDt() {
        return birthDt;
    }

    public void setBirth_Dt(Date birthDt) {
        this.birthDt = birthDt;
    }
}
