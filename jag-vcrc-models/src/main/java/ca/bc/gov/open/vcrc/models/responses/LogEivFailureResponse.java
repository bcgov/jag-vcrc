package ca.bc.gov.open.vcrc.models.responses;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "LogEivFailure")
public class LogEivFailureResponse {
    private BaseXMLResponse logEivFailure;
}
