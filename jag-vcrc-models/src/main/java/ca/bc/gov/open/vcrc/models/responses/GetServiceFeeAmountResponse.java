package ca.bc.gov.open.vcrc.models.responses;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JacksonXmlRootElement(localName = "GetServiceFeeAmount")
@EqualsAndHashCode(callSuper = true)
public class GetServiceFeeAmountResponse extends BaseXMLResponse {
    private String serviceFeeAmount;
}
