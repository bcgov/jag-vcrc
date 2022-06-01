package ca.bc.gov.open.vcrc.models.requests;

import lombok.Data;

@Data
public class GetNextInvoiceIdRequest {
    private String orgTicketNumber;
}
