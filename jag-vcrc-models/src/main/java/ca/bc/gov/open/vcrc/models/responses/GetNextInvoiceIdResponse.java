package ca.bc.gov.open.vcrc.models.responses;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JacksonXmlRootElement(localName = "GetNextInvoiceId")
@EqualsAndHashCode(callSuper = true)
public class GetNextInvoiceIdResponse extends BaseXMLResponse {
    private String invoiceId;
}
