package ca.bc.gov.open.vcrc.models.requests;

import lombok.Data;

@Data
public class CreateNewCRCServiceRequest {

    private String orgTicketNumber;
    private String scheduleTypeCd;
    private String scopeLevelCd;
    private String applPartyId;
    private String orgApplToPay;
    private String applicantPosn;
    private String childCareFacNm;
    private String governingBodyNm;
    private String sessionId;
    private String invoiceId;
    private String authReleaseEIVVendorYN;
    private String authConductCRCCheckYN;
    private String authReleaseToOrgYN;
    private String applIdentityVerifiedEIVYN;
    private String eivPassDetailsResults;

    public void setSchedule_Type_Cd(String scheduleTypeCd) {
        this.scheduleTypeCd = scheduleTypeCd;
    }

    public void setScope_Level_Cd(String scopeLevelCd) {
        this.scopeLevelCd = scopeLevelCd;
    }

    public void setAppl_Party_Id(String applPartyId) {
        this.applPartyId = applPartyId;
    }

    public void setOrg_Appl_To_Pay(String orgApplToPay) {
        this.orgApplToPay = orgApplToPay;
    }

    public void setApplicant_Posn(String applicantPosn) {
        this.applicantPosn = applicantPosn;
    }

    public void setChild_Care_Fac_Nm(String childCareFacNm) {
        this.childCareFacNm = childCareFacNm;
    }

    public void setGoverning_Body_Nm(String governingBodyNm) {
        this.governingBodyNm = governingBodyNm;
    }

    public void setSession_Id(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setInvoice_Id(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public void setAuth_Release_EIV_Vendor_YN(String authReleaseEIVVendorYN) {
        this.authReleaseEIVVendorYN = authReleaseEIVVendorYN;
    }

    public void setAuth_Conduct_CRC_Check_YN(String authConductCRCCheckYN) {
        this.authConductCRCCheckYN = authConductCRCCheckYN;
    }

    public void setAuth_Release_To_Org_YN(String authReleaseToOrgYN) {
        this.authReleaseToOrgYN = authReleaseToOrgYN;
    }

    public void setAppl_Identity_Verified_EIV_YN(String applIdentityVerifiedEIVYN) {
        this.applIdentityVerifiedEIVYN = applIdentityVerifiedEIVYN;
    }
}
