package ca.bc.gov.open.vcrc.models.responses;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JacksonXmlRootElement(localName = "GetNextSessionId")
@EqualsAndHashCode(callSuper = true)
public class GetNextSessionIdResponse extends BaseXMLResponse {
    private String sessionId;
}
