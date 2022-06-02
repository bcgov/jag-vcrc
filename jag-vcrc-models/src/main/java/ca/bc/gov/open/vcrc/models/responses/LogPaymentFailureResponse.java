package ca.bc.gov.open.vcrc.models.responses;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "logPaymentFailure")
public class LogPaymentFailureResponse extends BaseXMLResponse {}
;
