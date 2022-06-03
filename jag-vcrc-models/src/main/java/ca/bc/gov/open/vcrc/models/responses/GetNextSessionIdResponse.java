package ca.bc.gov.open.vcrc.models.responses;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JacksonXmlRootElement(localName = "getNextSessionId")
@EqualsAndHashCode(callSuper = true)
public class GetNextSessionIdResponse extends BaseXMLResponse {
    private String sessionId;
}
