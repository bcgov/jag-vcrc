package ca.bc.gov.open.vcrc.models.responses;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JacksonXmlRootElement(localName = "Response")
public class GetNextSessionIdResponse {

    private GetNextSessionId getNextSessionId;

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class GetNextSessionId extends BaseXMLResponse {
        private String sessionId;
    }
}
