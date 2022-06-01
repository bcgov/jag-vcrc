package ca.bc.gov.open.vcrc.models.responses;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JacksonXmlRootElement(localName = "GetCountriesList" )
public class GetCountriesListResponse extends BaseXMLResponse {

    private Countries countries;

    @Data
    public static class Countries {
        List<Country> country;
    }

    @Data
    public static class Country {
        private String name;
    }
}
