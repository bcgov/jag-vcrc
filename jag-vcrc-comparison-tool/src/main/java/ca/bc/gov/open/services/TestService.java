package ca.bc.gov.open.services;

import ca.bc.gov.open.vcrc.models.responses.CheckApplicantForPrevCRCExResponse;
import ca.bc.gov.open.vcrc.models.responses.CheckApplicantForPrevCRCResponse;
import ca.bc.gov.open.vcrc.models.responses.GetCountriesListResponse;
import ca.bc.gov.open.vcrc.models.responses.GetProvinceListResponse;
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
import org.springframework.core.ParameterizedTypeReference;
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

    private int overallDiff = 0;

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
        checkApplicantForPrevCRCCompare();
        checkApplicantForPrevCRCExCompare();
    }

    public void getCountryListCompare() throws IOException {

        int diffCounter = 0;

        log.info("GetCountryList");
        fileOutput = new PrintWriter(outputDir + "GetCountryList.txt", StandardCharsets.UTF_8);
        compare(new GetCountriesListResponse(), null, "GetCountryList/Services");
        System.out.println(
                "########################################################\n"
                        + "INFO: getCountryList  Completed there are "
                        + diffCounter
                        + " diffs\n"
                        + "########################################################");

        fileOutput.println(
                "########################################################\n"
                        + "INFO: getCountryList  Completed there are "
                        + diffCounter
                        + " diffs\n"
                        + "########################################################");

        overallDiff += diffCounter;
        fileOutput.close();
    }

    public void getProviceListCompare() throws IOException {

        int diffCounter = 0;

        log.info("GetProvinceList");
        fileOutput = new PrintWriter(outputDir + "GetProvinceList.txt", StandardCharsets.UTF_8);
        compare(new GetProvinceListResponse(), null, "GetProvinceList/Services");
        System.out.println(
                "########################################################\n"
                        + "INFO: GetProvinceList  Completed there are "
                        + diffCounter
                        + " diffs\n"
                        + "########################################################");

        fileOutput.println(
                "########################################################\n"
                        + "INFO: GetProvinceList  Completed there are "
                        + diffCounter
                        + " diffs\n"
                        + "########################################################");

        overallDiff += diffCounter;
        fileOutput.close();
    }

    public void checkApplicantForPrevCRCCompare() throws IOException {

        int diffCounter = 0;

        log.info("CheckApplicantForPrevCRC");
        fileOutput =
                new PrintWriter(outputDir + "CheckApplicantForPrevCRC.txt", StandardCharsets.UTF_8);
        compare(new CheckApplicantForPrevCRCResponse(), null, "CheckApplicantForPrevCRC/Services");
        System.out.println(
                "########################################################\n"
                        + "INFO: checkApplicantForPrevCRC  Completed there are "
                        + diffCounter
                        + " diffs\n"
                        + "########################################################");

        fileOutput.println(
                "########################################################\n"
                        + "INFO: checkApplicantForPrevCRC  Completed there are "
                        + diffCounter
                        + " diffs\n"
                        + "########################################################");

        overallDiff += diffCounter;
        fileOutput.close();
    }

    public void checkApplicantForPrevCRCExCompare() throws IOException {

        int diffCounter = 0;

        log.info("CheckApplicantForPrevCRCEx");
        fileOutput =
                new PrintWriter(
                        outputDir + "CheckApplicantForPrevCRCEx.txt", StandardCharsets.UTF_8);
        compare(
                new CheckApplicantForPrevCRCExResponse(),
                null,
                "CheckApplicantForPrevCRCEx/Services");
        System.out.println(
                "########################################################\n"
                        + "INFO: checkApplicantForPrevCRCEx  Completed there are "
                        + diffCounter
                        + " diffs\n"
                        + "########################################################");

        fileOutput.println(
                "########################################################\n"
                        + "INFO: checkApplicantForPrevCRCEx  Completed there are "
                        + diffCounter
                        + " diffs\n"
                        + "########################################################");

        overallDiff += diffCounter;
        fileOutput.close();
    }

    private <T, G> boolean compare(T request, G response, String connect)
            throws JsonProcessingException {

        HttpEntity<G> resultObjectWM = null;
        HttpEntity<G> resultObjectAPI = null;

        try {
            UriComponentsBuilder builderWM = UriComponentsBuilder.fromHttpUrl(wmHost + connect);
            resultObjectWM =
                    restTemplate.exchange(
                            builderWM.toUriString(),
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            new ParameterizedTypeReference<G>() {});

            UriComponentsBuilder builderApi = UriComponentsBuilder.fromHttpUrl(apiHost + connect);
            resultObjectAPI =
                    restTemplate.exchange(
                            builderApi.toUriString(),
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            new ParameterizedTypeReference<G>() {});
            Thread.sleep(5000);

        } catch (Exception e) {
            System.out.println("ERROR: Failed to send request... " + e);
            fileOutput.println("ERROR: Failed to send request... " + e);
        }

        Diff diff = javers.compare(resultObjectWM.getBody(), resultObjectAPI.getBody());

        String requestClassName = request.getClass().getName();

        if (diff.hasChanges()) {
            printDiff(diff);
            return false;
        } else {
            if (resultObjectAPI == null && resultObjectWM == null)
                System.out.println(
                        "WARN: "
                                + requestClassName.substring(requestClassName.lastIndexOf('.') + 1)
                                + ": NULL responses");
            else
                System.out.println(
                        "INFO: "
                                + requestClassName.substring(requestClassName.lastIndexOf('.') + 1)
                                + ": No Diff Detected");
            return true;
        }
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
