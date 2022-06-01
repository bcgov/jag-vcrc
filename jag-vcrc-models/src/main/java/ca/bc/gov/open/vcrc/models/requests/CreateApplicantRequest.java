package ca.bc.gov.open.vcrc.models.requests;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class CreateApplicantRequest implements Serializable {

    private String orgTicketNumber;
    private String callPurpose;
    private String legalSurenameNm;
    private String legalFirstNm;
    private String legalSecondNm;
    private Date birthDt;
    private String genderTxt;
    private String birthPlace;
    private String alias1SurnameNm;
    private String alias1FirstNm;
    private String alias1SecondNm;
    private String alias2SurenameNm;
    private String alias2FirstNm;
    private String alias2SecondNm;
    private String alias3SurenameNm;
    private String alias3FirstNm;
    private String alias3SecondNm;
    private String phoneNumber;
    private String addressLine1;
    private String addressLine2;
    private String cityNm;
    private String provinceNm;
    private String countryNm;
    private String postalCodeTxt;
    private String driversLicNo;
    private String emailAddress;
    private String emailType;

    //    The weird setters are needed to set binding on request ingestion

    public void setCall_Purpose(String callPurpose) {
        this.callPurpose = callPurpose;
    }

    public void setLegal_Surename_Nm(String legalSurenameNm) {
        this.legalSurenameNm = legalSurenameNm;
    }

    public void setLegal_First_Nm(String legalFirstName) {
        this.legalFirstNm = legalFirstName;
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

    public void setBirth_Place(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public void setAlias1_Surname_Nm(String alias1SurnameNm) {
        this.alias1SurnameNm = alias1SurnameNm;
    }

    public void setAlias1_First_Nm(String alias1FirstNm) {
        this.alias1FirstNm = alias1FirstNm;
    }

    public void setAlias1_Second_Nm(String alias1SecondNm) {
        this.alias1SecondNm = alias1SecondNm;
    }

    public void setAlias2_Surename_Nm(String alias2SurenameNm) {
        this.alias2SurenameNm = alias2SurenameNm;
    }

    public void setAlias2_First_Nm(String alias2FirstNm) {
        this.alias2FirstNm = alias2FirstNm;
    }

    public void setAlias2_Second_Nm(String alias2SecondNm) {
        this.alias2SecondNm = alias2SecondNm;
    }

    public void setAlias3_Surename_Nm(String alias3SurenameNm) {
        this.alias3SurenameNm = alias3SurenameNm;
    }

    public void setAlias3_First_Nm(String alias3FirstNm) {
        this.alias3FirstNm = alias3FirstNm;
    }

    public void setAlias3_Second_Nm(String alias3SecondNm) {
        this.alias3SecondNm = alias3SecondNm;
    }

    public void setPhone_Number(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress_Line1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public void setAddress_Line2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public void setCity_Nm(String cityNm) {
        this.cityNm = cityNm;
    }

    public void setProvince_Nm(String provinceNm) {
        this.provinceNm = provinceNm;
    }

    public void setCountry_Nm(String countryNm) {
        this.countryNm = countryNm;
    }

    public void setPostal_Code_Txt(String postalCodeTxt) {
        this.postalCodeTxt = postalCodeTxt;
    }

    public void setDrivers_Lic_No(String driversLicNo) {
        this.driversLicNo = driversLicNo;
    }

    public void setEmail_Address(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setEmail_Type(String emailType) {
        this.emailType = emailType;
    }
}
