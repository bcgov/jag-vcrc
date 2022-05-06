package ca.bc.gov.open.vcrc.models.responses;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JacksonXmlRootElement(localName = "Response")
public class AuthenticateUserResponse {

    private AuthenticateInner doAuthenticateUser;

    @Data
    @EqualsAndHashCode(callSuper = true)
    public class AuthenticateInner extends BaseXMLResponse {
        private AccessCodeResponse accessCodeResponse;
        private ScopeLevels scopeLevels;
        private ScheduleTypes scheduleTypes;
    }

    @Data
    static class AccessCodeResponse {
        @JacksonXmlProperty(localName = "org_party_id")
        private String orgPartyId;

        @JacksonXmlProperty(localName = "org_nm")
        private String orgNm;

        @JacksonXmlProperty(localName = "contact_sur_nm")
        private String contactSurNm;

        @JacksonXmlProperty(localName = "contact_first_nm")
        private String contactFirstNm;

        @JacksonXmlProperty(localName = "address_line_1")
        private String addressLine1;

        @JacksonXmlProperty(localName = "address_line_2")
        private String addressLine2;

        @JacksonXmlProperty(localName = "city_nm")
        private String cityNm;

        @JacksonXmlProperty(localName = "province_nm")
        private String provinceNm;

        @JacksonXmlProperty(localName = "postal_code_txt")
        private String postalCodeTxt;

        @JacksonXmlProperty(localName = "contact_phone_no")
        private String contactPhoneNo;

        @JacksonXmlProperty(localName = "contact_fax_no")
        private String contactFaxNo;

        @JacksonXmlProperty(localName = "org_applicant_relationship")
        private String orgApplicantRelationship;

        @JacksonXmlProperty(localName = "default_schedule_type_cd")
        private String defaultScheduleTypeCd;

        @JacksonXmlProperty(localName = "default_crc_scope_level_cd")
        private String defaultCrcScopeLevelCd;

        @JacksonXmlProperty(localName = "ticket_found_yn")
        private String ticketFoundYN;

        @JacksonXmlProperty(localName = "already_used_yn")
        private String alreadyUsedYN;

        @JacksonXmlProperty(localName = "valid_date_range_yn")
        private String validDateRangeYN;
    }

    @Data
    static class ScopeLevels {
        List<ScopeLevelItem> scopeLevel;
    }

    @Data
    static class ScheduleTypes {
        List<ScheduleTypeItem> scheduleType;
    }

    @Data
    static class ScheduleTypeItem {
        private String crc_schedule_type_cd;
        private String crc_schedule_type_dsc;
    }

    @Data
    static class ScopeLevelItem {
        private String crc_scope_level_cd;
        private String crc_scope_level_dsc;
    }
}
