package ca.bc.gov.open.vcrc.models.responses;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JacksonXmlRootElement(localName = "CheckApplicantForPrevCRC")
public class CheckApplicantForPrevCRCExResponse extends BaseXMLResponse {
    private String serviceId;
}
