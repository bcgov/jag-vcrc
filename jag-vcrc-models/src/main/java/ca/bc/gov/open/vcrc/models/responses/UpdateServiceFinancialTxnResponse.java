package ca.bc.gov.open.vcrc.models.responses;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "Response")
public class UpdateServiceFinancialTxnResponse {
    private BaseXMLResponse updateServiceFinancialTxn;
}
