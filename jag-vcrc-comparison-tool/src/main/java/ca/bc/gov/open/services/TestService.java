package ca.bc.gov.open.services;

import ca.bc.gov.open.vcrc.models.responses.GetCountriesListResponse;
import com.ctc.wstx.api.WstxOutputProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Change;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.ValueChange;
import org.javers.core.diff.changetype.container.ListChange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
public class TestService {

    @Value("${host.api-host}")
    private String apiHost;

    @Value("${host.username}")
    private String username;

    @Value("${host.password}")
    private String password;

    @Value("${host.wm-host}")
    private String wmHost;

    private PrintWriter fileOutput;
    private static String outputDir = "jag-vcrc-comparison-tool/results/";
    private final Javers javers = JaversBuilder.javers().build();
    private final XmlMapper xmlMapper;

    private final RestTemplate restTemplate = new RestTemplate();

    public TestService() {
        xmlMapper = new XmlMapper();
        xmlMapper.setPropertyNamingStrategy(PropertyNamingStrategies.UPPER_CAMEL_CASE);
        xmlMapper.enable(ToXmlGenerator.Feature.WRITE_XML_DECLARATION);
        xmlMapper
                .getFactory()
                .getXMLOutputFactory()
                .setProperty(WstxOutputProperties.P_USE_DOUBLE_QUOTES_IN_XML_DECL, true);
    }

    public void runCompares() throws Exception {
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(username, password));

        getCountryListCompare();
        getProviceListCompare();
    }

    public void getCountryListCompare() throws IOException {

        UriComponentsBuilder builderWM =
                UriComponentsBuilder.fromHttpUrl(wmHost + "GetCountryList/Services");

        UriComponentsBuilder builderAPI =
                UriComponentsBuilder.fromHttpUrl(apiHost + "GetCountryList/Services");

        log.info("GetCountryList");
        fileOutput = new PrintWriter(outputDir + "GetCountryList.txt", StandardCharsets.UTF_8);
        compare(builderWM, builderAPI);
    }

    public void getProviceListCompare() throws IOException {

        UriComponentsBuilder builderWM =
                UriComponentsBuilder.fromHttpUrl(wmHost + "GetProvinceList/Services");

        UriComponentsBuilder builderAPI =
                UriComponentsBuilder.fromHttpUrl(apiHost + "GetProvinceList/Services");

        log.info("GetProvinceList");
        fileOutput = new PrintWriter(outputDir + "GetProvinceList.txt", StandardCharsets.UTF_8);
        compare(builderWM, builderAPI);
    }


    private boolean compare(UriComponentsBuilder builderWM, UriComponentsBuilder builderAPI)
            throws JsonProcessingException {
        var wmResp =
                restTemplate.exchange(
                        builderWM.toUriString(),
                        HttpMethod.GET,
                        new HttpEntity<>(new HttpHeaders()),
                        String.class);

        var apiResp =
                restTemplate.exchange(
                        builderAPI.toUriString(),
                        HttpMethod.GET,
                        new HttpEntity<>(new HttpHeaders()),
                        String.class);

        var gclWM = xmlMapper.readValue(wmResp.getBody(), GetCountriesListResponse.class);
        var gclAPI = xmlMapper.readValue(apiResp.getBody(), GetCountriesListResponse.class);

        Diff diff = javers.compare(gclAPI, gclWM);

        if (diff.hasChanges()) {
            printDiff(diff);
            return false;
        }

        return true;
    }

    private void printDiff(Diff diff) {
        int diffSize = diff.getChanges().size();
        if (diffSize == 0) {
            return;
        }

        String[] header = new String[] {"Property", "API Response", "WM Response"};
        String[][] table = new String[diffSize + 1][3];
        table[0] = header;

        for (int i = 0; i < diffSize; ++i) {
            Change ch = diff.getChanges().get(i);

            if (ch instanceof ListChange) {
                String apiVal =
                        ((ListChange) ch).getLeft() == null
                                ? "null"
                                : ((ListChange) ch).getLeft().toString();
                String wmVal =
                        ((ListChange) ch).getRight() == null
                                ? "null"
                                : ((ListChange) ch).getRight().toString();
                table[i + 1][0] = ((ListChange) ch).getPropertyNameWithPath();
                table[i + 1][1] = apiVal;
                table[i + 1][2] = wmVal;
            } else if (ch instanceof ValueChange) {
                String apiVal =
                        ((ValueChange) ch).getLeft() == null
                                ? "null"
                                : ((ValueChange) ch).getLeft().toString();
                String wmVal =
                        ((ValueChange) ch).getRight() == null
                                ? "null"
                                : ((ValueChange) ch).getRight().toString();
                table[i + 1][0] = ((ValueChange) ch).getPropertyNameWithPath();
                table[i + 1][1] = apiVal;
                table[i + 1][2] = wmVal;
            }
        }

        boolean leftJustifiedRows = false;
        int totalColumnLength = 10;
        /*
         * Calculate appropriate Length of each column by looking at width of data in
         * each column.
         *
         * Map columnLengths is <column_number, column_length>
         */
        Map<Integer, Integer> columnLengths = new HashMap<>();
        Arrays.stream(table)
                .forEach(
                        a ->
                                Stream.iterate(0, (i -> i < a.length), (i -> ++i))
                                        .forEach(
                                                i -> {
                                                    if (columnLengths.get(i) == null) {
                                                        columnLengths.put(i, 0);
                                                    }
                                                    if (columnLengths.get(i) < a[i].length()) {
                                                        columnLengths.put(i, a[i].length());
                                                    }
                                                }));

        for (Map.Entry<Integer, Integer> e : columnLengths.entrySet()) {
            totalColumnLength += e.getValue();
        }
        fileOutput.println("=".repeat(totalColumnLength));
        System.out.println("=".repeat(totalColumnLength));

        final StringBuilder formatString = new StringBuilder("");
        String flag = leftJustifiedRows ? "-" : "";
        columnLengths.entrySet().stream()
                .forEach(e -> formatString.append("| %" + flag + e.getValue() + "s "));
        formatString.append("|\n");

        Stream.iterate(0, (i -> i < table.length), (i -> ++i))
                .forEach(
                        a -> {
                            fileOutput.printf(formatString.toString(), table[a]);
                            System.out.printf(formatString.toString(), table[a]);
                        });

        fileOutput.println("=".repeat(totalColumnLength));
        System.out.println("=".repeat(totalColumnLength));
    }
}
