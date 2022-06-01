package ca.bc.gov.open.vcrc.models.requests;

import lombok.Data;

@Data
public class CreateSharingServiceRequest {
    private String orgTicketNumber;
    private String applPartyId;
    private String scopeLevelCd;
    private String applicantPosn;
    private String authReleaseEIVVendorYN;
    private String authReleaseToOrgYN;
    private String applIdentityVerifiedEIVYN;
    private String previousServiceId;
    private String eivPassDetailsResults;

    public void setAppl_Party_Id(String applPartyId) {
        this.applPartyId = applPartyId;
    }

    public void setScope_Level_Cd(String scopeLevelCd) {
        this.scopeLevelCd = scopeLevelCd;
    }

    public void setApplicant_Posn(String applicantPosn) {
        this.applicantPosn = applicantPosn;
    }

    public void setAuth_Release_EIV_Vendor_YN(String authReleaseEIVVendorYN) {
        this.authReleaseEIVVendorYN = authReleaseEIVVendorYN;
    }

    public void setAuth_Release_To_Org_YN(String authReleaseToOrgYN) {
        this.authReleaseToOrgYN = authReleaseToOrgYN;
    }

    public void setAppl_Identity_Verified_EIV_YN(String applIdentityVerifiedEIVYN) {
        this.applIdentityVerifiedEIVYN = applIdentityVerifiedEIVYN;
    }

    public void setPrevious_Service_Id(String previousServiceId) {
        this.previousServiceId = previousServiceId;
    }
}
