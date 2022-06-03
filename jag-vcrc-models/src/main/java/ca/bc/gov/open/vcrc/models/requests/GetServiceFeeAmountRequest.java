package ca.bc.gov.open.vcrc.models.requests;

import lombok.Data;

@Data
public class GetServiceFeeAmountRequest {

    private String OrgTicketNumber;
    private String scheduleTypeCd;
    private String scopeLevelCd;
}
