package ca.bc.gov.open.vcrc.models.responses;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JacksonXmlRootElement(localName = "Response")
public class GetServiceFeeAmountResponse {

    private GetServiceFeeAmount getServiceFeeAmount;

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class GetServiceFeeAmount extends BaseXMLResponse {
        private String serviceFeeAmount;
    }
}
