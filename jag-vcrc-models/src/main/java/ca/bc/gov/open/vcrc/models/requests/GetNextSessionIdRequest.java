package ca.bc.gov.open.vcrc.models.requests;

import lombok.Data;

@Data
public class GetNextSessionIdRequest {
    private String orgTicketNumber;
}
