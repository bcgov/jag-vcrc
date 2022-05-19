package ca.bc.gov.open.vcrc.models.responses;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JacksonXmlRootElement(localName = "Response")
public class GetProvinceListResponse {

    private GetProvinceList getProvinceList;

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class GetProvinceList extends BaseXMLResponse {
        private Provinces provinces;
    }

    @Data
    public static class Provinces {
        private List<Province> province;
    }

    @Data
    public static class Province {
        private String name;
    }
}
