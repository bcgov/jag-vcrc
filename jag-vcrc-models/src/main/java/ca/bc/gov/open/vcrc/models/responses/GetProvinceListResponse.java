package ca.bc.gov.open.vcrc.models.responses;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JacksonXmlRootElement(localName = "GetProvinceList")
@EqualsAndHashCode(callSuper = true)
public class GetProvinceListResponse extends BaseXMLResponse {

    private Provinces provinces;

    @Data
    public static class Provinces {
        @JacksonXmlElementWrapper(useWrapping = false)
        private List<Province> province;
    }

    @Data
    public static class Province {
        private String name;
    }
}
