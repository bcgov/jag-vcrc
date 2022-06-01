package ca.bc.gov.open.vcrc.models.responses;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JacksonXmlRootElement(localName = "Response")
public class GetNextInvoiceIdResponse {

    private GetNextInvoiceId getNextInvoiceId;

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class GetNextInvoiceId extends BaseXMLResponse {
        private String invoiceId;
    }
}
