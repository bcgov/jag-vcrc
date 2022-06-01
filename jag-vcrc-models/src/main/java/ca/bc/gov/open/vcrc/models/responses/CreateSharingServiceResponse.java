package ca.bc.gov.open.vcrc.models.responses;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JacksonXmlRootElement(localName = "Response")
public class CreateSharingServiceResponse {

    private CreateSharingService createSharingService;

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class CreateSharingService extends BaseXMLResponse {
        private String serviceId;
    }
}
