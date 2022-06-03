package ca.bc.gov.open.vcrc.models.responses;

import lombok.Data;

@Data
public class BaseXMLResponse {
    private String message;
    private String responseCode;
}
