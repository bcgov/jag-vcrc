package ca.bc.gov.open.vcrc.models.responses;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JacksonXmlRootElement(localName = "CreateSharingService")
@EqualsAndHashCode(callSuper = true)
public class CreateSharingServiceResponse extends BaseXMLResponse {
    private String serviceId;
}
