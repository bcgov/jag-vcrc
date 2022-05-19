package ca.bc.gov.open.vcrc.models.responses;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JacksonXmlRootElement(localName = "Response")
public class GetCountriesListResponse {

    private GetCountriesList getCountriesList;

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class GetCountriesList extends BaseXMLResponse {
        private Countries countries;
    }

    @Data
    public static class Countries {
        List<Country> country;
    }

    @Data
    public static class Country {
        private String name;
    }
}
