package ca.bc.gov.open.services;

import ca.bc.gov.open.vcrc.models.responses.*;
import com.ctc.wstx.api.WstxOutputProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
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

        checkApplicantForPrevCRCCompare();
        checkApplicantForPrevCRCExCompare();
        createApplicantCompare();
        createApplicantExCompare();
        //        authenticateUserExCompare();
        //        getNextSessionIdCompare();
        //        getNextInvoiceIdCompare();
        //        getCountryListCompare();
        //        getProviceListCompare();
        //
        //        logEivFailureCompare();
        //        logPaymentFailureCompare();
        //        createNewCrcServiceCompare();
        //        createSharingServiceCompare();
        //        getServiceFeeAmountCompare();
        //        updateServiceFinancialTxnCompare();
    }

    public void getCountryListCompare() throws IOException {

        int diffCounter = 0;

        log.info("GetCountryList");
        fileOutput = new PrintWriter(outputDir + "GetCountryList.txt", StandardCharsets.UTF_8);
        compare(
                null,
                new GetCountriesListResponse(),
                "GetCountryList/Services",
                GetCountriesListResponse.class);
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
        compare(
                null,
                new GetProvinceListResponse(),
                "GetProvinceList/Services",
                GetProvinceListResponse.class);
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

    public void getNextSessionIdCompare() throws IOException {

        int diffCounter = 0;

        log.info("GetNextSessionId");
        fileOutput = new PrintWriter(outputDir + "GetNextSessionId.txt", StandardCharsets.UTF_8);

        Map<String, String> request = new HashMap<>();
        request.put("OrgTicketNumber", "1");

        compare(
                request,
                new GetNextSessionIdResponse(),
                "GetNextSessionId/Services",
                GetNextSessionIdResponse.class);
        System.out.println(
                "########################################################\n"
                        + "INFO: GetNextSessionId  Completed there are "
                        + diffCounter
                        + " diffs\n"
                        + "########################################################");

        fileOutput.println(
                "########################################################\n"
                        + "INFO: GetNextSessionId  Completed there are "
                        + diffCounter
                        + " diffs\n"
                        + "########################################################");

        overallDiff += diffCounter;
        fileOutput.close();
    }

    public void getNextInvoiceIdCompare() throws IOException {

        int diffCounter = 0;

        log.info("GetNextInvoiceId");
        fileOutput = new PrintWriter(outputDir + "GetNextInvoiceId.txt", StandardCharsets.UTF_8);

        Map<String, String> request = new HashMap<>();
        request.put("OrgTicketNumber", "1");

        compare(
                null,
                new GetNextInvoiceIdResponse(),
                "GetNextInvoiceId/Services",
                GetNextInvoiceIdResponse.class);
        System.out.println(
                "########################################################\n"
                        + "INFO: getNextInvoiceId  Completed there are "
                        + diffCounter
                        + " diffs\n"
                        + "########################################################");

        fileOutput.println(
                "########################################################\n"
                        + "INFO: getNextInvoiceId  Completed there are "
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

        InputStream inputIds = getClass().getResourceAsStream("/checkApplicantForPrevCRC.csv");
        assert inputIds != null;
        Scanner scanner = new Scanner(inputIds);

        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(",");
            Map<String, String> request = new HashMap<>();
            //            request.put("OrgTicketNumber", "1");
            //            request.put("Legal_Surname_Nm", "1");
            //            request.put("Legal_First_Nm", "1");
            //            request.put("Birth_Dt", "1999-05-31");
            //            request.put("Gender_Txt", "1");
            //            request.put("Postal_Code_Txt", "1");
            //            request.put("Drivers_Lic_No", "1");
            //            request.put("Scope_Level_Cd", "1");
            //            request.put("Previous_Service_Id", "1");
            request.put("OrgTicketNumber", line[0]);
            request.put("Legal_Surname_Nm", line[1]);
            request.put("Legal_First_Nm", line[2]);
            request.put("Birth_Dt", line[3]);
            request.put("Gender_Txt", line[4]);
            request.put("Postal_Code_Txt", line[5]);
            request.put("Drivers_Lic_No", line[6]);
            request.put("Scope_Level_Cd", line[7]);
            request.put("Previous_Service_Id", line[8]);

            System.out.format(
                    "\nINFO: checkApplicantForPrevCRCCompare with OrgTicketNumber : %s,  Legal_Surname_Nm: %s, Legal_First_Nm %s, "
                            + "Birth_Dt : %s,  Gender_Txt: %s, Postal_Code_Txt %s, Drivers_Lic_No : %s,  Scope_Level_Cd: %s, Previous_Service_Id %s\n",
                    line[0], line[1], line[2], line[3], line[4], line[5], line[6], line[7],
                    line[8]);

            if (!compare(
                    request,
                    new CheckApplicantForPrevCRCResponse(),
                    "CheckApplicantForPrevCRC/Services",
                    CheckApplicantForPrevCRCResponse.class)) {
                fileOutput.format(
                        "\nINFO: checkApplicantForPrevCRCCompare with OrgTicketNumber : %s,  Legal_Surname_Nm: %s, Legal_First_Nm %s, "
                                + "Birth_Dt : %s,  Gender_Txt: %s, Postal_Code_Txt %s, Drivers_Lic_No : %s,  Scope_Level_Cd: %s, Previous_Service_Id %s\n",
                        line[0], line[1], line[2], line[3], line[4], line[5], line[6], line[7],
                        line[8]);
                ++diffCounter;
            }

            System.out.println(
                    "########################################################\n"
                            + "INFO: CheckApplicantForPrevCRC  Completed there are "
                            + diffCounter
                            + " diffs\n"
                            + "########################################################");

            fileOutput.println(
                    "########################################################\n"
                            + "INFO: CheckApplicantForPrevCRC  Completed there are "
                            + diffCounter
                            + " diffs\n"
                            + "########################################################");

            overallDiff += diffCounter;
            fileOutput.close();
        }
    }

    public void checkApplicantForPrevCRCExCompare() throws IOException {

        int diffCounter = 0;

        log.info("CheckApplicantForPrevCRCEx");
        fileOutput =
                new PrintWriter(
                        outputDir + "CheckApplicantForPrevCRCEx.txt", StandardCharsets.UTF_8);

        InputStream inputIds = getClass().getResourceAsStream("/checkApplicantForPrevCRCEx.csv");
        assert inputIds != null;
        Scanner scanner = new Scanner(inputIds);

        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(",");

            Map<String, String> request = new HashMap<>();
            //            request.put("OrgTicketNumber", "1");
            //            request.put("Legal_Surname_Nm", "1");
            //            request.put("Legal_First_Nm", "1");
            //            request.put("Birth_Dt", "1999-05-31");
            //            request.put("Gender_Txt", "1");
            //            request.put("Postal_Code_Txt", "1");
            //            request.put("Drivers_Lic_No", "1");
            //            request.put("Scope_Level_Cd", "1");

            request.put("OrgTicketNumber", line[0]);
            request.put("Legal_Surname_Nm", line[1]);
            request.put("Legal_First_Nm", line[2]);
            request.put("Birth_Dt", line[3]);
            request.put("Gender_Txt", line[4]);
            request.put("Postal_Code_Txt", line[5]);
            request.put("Drivers_Lic_No", line[6]);
            request.put("Scope_Level_Cd", line[7]);

            System.out.format(
                    "\nINFO: CheckApplicantForPrevCRCEx with OrgTicketNumber : %s,  Legal_Surname_Nm: %s, Legal_First_Nm %s, "
                            + "Birth_Dt : %s,  Gender_Txt: %s, Postal_Code_Txt %s, Drivers_Lic_No : %s,  Scope_Level_Cd: %s\n",
                    line[0], line[1], line[2], line[3], line[4], line[5], line[6], line[7]);

            if (!compare(
                    request,
                    new CheckApplicantForPrevCRCExResponse(),
                    "CheckApplicantForPrevCRCEx/Services",
                    CheckApplicantForPrevCRCExResponse.class)) {
                fileOutput.format(
                        "\nINFO: CheckApplicantForPrevCRCExResponse with OrgTicketNumber: %s, Legal_Surname_Nm %s, "
                                + "Legal_First_Nm : %s,  Birth_Dt: %s, Gender_Txt %s, Postal_Code_Txt : %s,  Drivers_Lic_No: %s, Scope_Level_Cd %s\n",
                        line[0], line[1], line[2], line[3], line[4], line[5], line[6], line[7]);
                ++diffCounter;
            }

            System.out.println(
                    "########################################################\n"
                            + "INFO: CheckApplicantForPrevCRCEx  Completed there are "
                            + diffCounter
                            + " diffs\n"
                            + "########################################################");

            fileOutput.println(
                    "########################################################\n"
                            + "INFO: CheckApplicantForPrevCRCEx  Completed there are "
                            + diffCounter
                            + " diffs\n"
                            + "########################################################");

            overallDiff += diffCounter;
            fileOutput.close();
        }
    }

    public void createApplicantCompare() throws IOException {

        int diffCounter = 0;

        log.info("CreateApplicant");
        fileOutput = new PrintWriter(outputDir + "CreateApplicant.txt", StandardCharsets.UTF_8);

        InputStream inputIds = getClass().getResourceAsStream("/CreateApplicant.csv");
        assert inputIds != null;
        Scanner scanner = new Scanner(inputIds);

        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(",");

            Map<String, String> request = new HashMap<>();

            //            request.put("OrgTicketNumber", "1");
            //            request.put("Call_Purpose", "1");
            //            request.put("Legal_Surname_Nm", "1");
            //            request.put("Legal_First_Nm", "1");
            //            request.put("Legal_Second_Nm", "1");
            //            request.put("Birth_Dt", "1999-05-31");
            //            request.put("Gender_Txt", "1");
            //            request.put("Birth_Place", "1");
            //            request.put("Alias1_Surname_Nm", "1");
            //            request.put("Alias1_First_Nm", "1");
            //            request.put("Alias1_Second_Nm", "1");
            //            request.put("Alias2_Surname_Nm", "1");
            //            request.put("alias2FirstNm", "1");
            //            request.put("alias2SecondNm", "1");
            //            request.put("Alias3_Surname_Nm", "1");
            //            request.put("Alias3_First_Nm", "1");
            //            request.put("Alias3_Second_Nm", "1");
            //            request.put("Phone_Number", "1");
            //            request.put("Address_Line1", "1");
            //            request.put("Address_Line2", "1");
            //            request.put("City_Nm", "1");
            //            request.put("Province_Nm", "1");
            //            request.put("Country_Nm", "1");
            //            request.put("Postal_Code_Txt", "1");
            //            request.put("Drivers_Lic_No", "1");

            request.put("OrgTicketNumber", line[0]);
            request.put("Call_Purpose", line[1]);
            request.put("Legal_Surname_Nm", line[2]);
            request.put("Legal_First_Nm", line[3]);
            request.put("Legal_Second_Nm", line[4]);
            request.put("Birth_Dt", line[5]);
            request.put("Gender_Txt", line[6]);
            request.put("Birth_Place", line[7]);
            request.put("Alias1_Surname_Nm", line[8]);
            request.put("Alias1_First_Nm", line[9]);
            request.put("Alias1_Second_Nm", line[10]);
            request.put("Alias2_Surname_Nm", line[11]);
            request.put("alias2FirstNm", line[12]);
            request.put("alias2SecondNm", line[13]);
            request.put("Alias3_Surname_Nm", line[14]);
            request.put("Alias3_First_Nm", line[15]);
            request.put("Alias3_Second_Nm", line[16]);
            request.put("Phone_Number", line[17]);
            request.put("Address_Line1", line[18]);
            request.put("Address_Line2", line[19]);
            request.put("City_Nm", line[20]);
            request.put("Province_Nm", line[21]);
            request.put("Country_Nm", line[22]);
            request.put("Postal_Code_Txt", line[23]);
            request.put("Drivers_Lic_No", line[24]);

            System.out.format(
                    "\nINFO: CreateApplicant with OrgTicketNumber : %s,  Call_Purpose: %s, Legal_Surname_Nm %s, "
                            + "Legal_First_Nm : %s,  Legal_Second_Nm: %s, Birth_Dt %s, Gender_Txt : %s,  Birth_Place: %s,"
                            + "Alias1_Surname_Nm : %s,  Alias1_First_Nm: %s, Alias1_Second_Nm %s, Alias2_Surname_Nm %s, alias2FirstNm : %s,  alias2SecondNm: %s,"
                            + "Alias3_Surname_Nm : %s,  Alias3_First_Nm: %s, Alias3_Second_Nm %s, Phone_Number : %s,  Address_Line1: %s,"
                            + "Address_Line2 : %s,  City_Nm: %s, Province_Nm: %s, Country_Nm %s, Postal_Code_Txt : %s,  Drivers_Lic_No: %s\n",
                    line[0], line[1], line[2], line[3], line[4], line[5], line[6], line[7], line[8],
                    line[9], line[10], line[11], line[12], line[13], line[14], line[15], line[16],
                    line[17], line[18], line[19], line[20], line[21], line[22], line[23], line[24]);

            if (!compare(
                    request,
                    new CreateApplicantResponse(),
                    "CreateApplicant/Services",
                    CreateApplicantResponse.class)) {
                fileOutput.format(
                        "\nINFO: CreateApplicant with OrgTicketNumber : %s,  Call_Purpose: %s, Legal_Surname_Nm %s, "
                                + "Legal_First_Nm : %s,  Legal_Second_Nm: %s, Birth_Dt %s, Gender_Txt : %s,  Birth_Place: %s,"
                                + "Alias1_Surname_Nm : %s,  Alias1_First_Nm: %s, Alias1_Second_Nm %s, Alias2_Surname_Nm %s, alias2FirstNm : %s,  alias2SecondNm: %s,"
                                + "Alias3_Surname_Nm : %s,  Alias3_First_Nm: %s, Alias3_Second_Nm %s, Phone_Number : %s,  Address_Line1: %s,"
                                + "Address_Line2 : %s,  City_Nm: %s, Province_Nm: %s, Country_Nm %s, Postal_Code_Txt : %s,  Drivers_Lic_No: %s\n",
                        line[0], line[1], line[2], line[3], line[4], line[5], line[6], line[7],
                        line[8], line[9], line[10], line[11], line[12], line[13], line[14],
                        line[15], line[16], line[17], line[18], line[19], line[20], line[21],
                        line[22], line[23], line[24]);
            }

            System.out.println(
                    "########################################################\n"
                            + "INFO: CreateApplicant  Completed there are "
                            + diffCounter
                            + " diffs\n"
                            + "########################################################");

            fileOutput.println(
                    "########################################################\n"
                            + "INFO: CreateApplicant  Completed there are "
                            + diffCounter
                            + " diffs\n"
                            + "########################################################");

            overallDiff += diffCounter;
            fileOutput.close();
        }
    }

    public void createApplicantExCompare() throws IOException {

        int diffCounter = 0;

        log.info("CreateApplicantEx");
        fileOutput = new PrintWriter(outputDir + "CreateApplicantEx.txt", StandardCharsets.UTF_8);

        InputStream inputIds = getClass().getResourceAsStream("/CreateApplicantEx.csv");
        assert inputIds != null;
        Scanner scanner = new Scanner(inputIds);

        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(",");

            Map<String, String> request = new HashMap<>();

            //        request.put("OrgTicketNumber", "1");
            //        request.put("Call_Purpose", "1");
            //        request.put("Legal_Surname_Nm", "1");
            //        request.put("Legal_First_Nm", "1");
            //        request.put("Legal_Second_Nm", "1");
            //        request.put("Birth_Dt", "1999-05-31");
            //        request.put("Gender_Txt", "1");
            //        request.put("Birth_Place", "1");
            //        request.put("Alias1_Surname_Nm", "1");
            //        request.put("Alias1_First_Nm", "1");
            //        request.put("Alias1_Second_Nm", "1");
            //        request.put("Alias2_Surname_Nm", "1");
            //        request.put("alias2FirstNm", "1");
            //        request.put("alias2SecondNm", "1");
            //        request.put("Alias3_Surname_Nm", "1");
            //        request.put("Alias3_First_Nm", "1");
            //        request.put("Alias3_Second_Nm", "1");
            //        request.put("Phone_Number", "1");
            //        request.put("Address_Line1", "1");
            //        request.put("Address_Line2", "1");
            //        request.put("City_Nm", "1");
            //        request.put("Province_Nm", "1");
            //        request.put("Country_Nm", "1");
            //        request.put("Postal_Code_Txt", "1");
            //        request.put("Drivers_Lic_No", "1");
            //        request.put("Email_Address", "1");
            //        request.put("Email_Type", "1");

            request.put("OrgTicketNumber", line[0]);
            request.put("Call_Purpose", line[1]);
            request.put("Legal_Surname_Nm", line[2]);
            request.put("Legal_First_Nm", line[3]);
            request.put("Legal_Second_Nm", line[4]);
            request.put("Birth_Dt", line[5]);
            request.put("Gender_Txt", line[6]);
            request.put("Birth_Place", line[7]);
            request.put("Alias1_Surname_Nm", line[8]);
            request.put("Alias1_First_Nm", line[9]);
            request.put("Alias1_Second_Nm", line[10]);
            request.put("Alias2_Surname_Nm", line[11]);
            request.put("alias2FirstNm", line[12]);
            request.put("alias2SecondNm", line[13]);
            request.put("Alias3_Surname_Nm", line[14]);
            request.put("Alias3_First_Nm", line[15]);
            request.put("Alias3_Second_Nm", line[16]);
            request.put("Phone_Number", line[17]);
            request.put("Address_Line1", line[18]);
            request.put("Address_Line2", line[19]);
            request.put("City_Nm", line[20]);
            request.put("Province_Nm", line[21]);
            request.put("Country_Nm", line[22]);
            request.put("Postal_Code_Txt", line[23]);
            request.put("Drivers_Lic_No", line[24]);
            request.put("Email_Address", line[25]);
            request.put("Email_Type", line[26]);

            System.out.format(
                    "\nINFO: CreateApplicant with OrgTicketNumber : %s,  Call_Purpose: %s, Legal_Surname_Nm %s, "
                            + "Legal_First_Nm : %s,  Legal_Second_Nm: %s, Birth_Dt %s, Gender_Txt : %s,  Birth_Place: %s,"
                            + "Alias1_Surname_Nm : %s,  Alias1_First_Nm: %s, Alias1_Second_Nm %s, Alias2_Surname_Nm %s, alias2FirstNm : %s,  alias2SecondNm: %s,"
                            + "Alias3_Surname_Nm : %s,  Alias3_First_Nm: %s, Alias3_Second_Nm %s, Phone_Number : %s,  Address_Line1: %s,"
                            + "Address_Line2 : %s,  City_Nm: %s, Province_Nm: %s, Country_Nm %s, Postal_Code_Txt : %s,  Drivers_Lic_No: %s,  Email_Address: %s,  Email_Type: %s\n",
                    line[0], line[1], line[2], line[3], line[4], line[5], line[6], line[7], line[8],
                    line[9], line[10], line[11], line[12], line[13], line[14], line[15], line[16],
                    line[17], line[18], line[19], line[20], line[21], line[22], line[23], line[24],
                    line[25], line[26]);

            if (!compare(
                    request,
                    new CreateApplicantResponse(),
                    "CreateApplicantEx/Services",
                    CreateApplicantResponse.class)) {

                fileOutput.format(
                        "\nINFO: CreateApplicant with OrgTicketNumber : %s,  Call_Purpose: %s, Legal_Surname_Nm %s, "
                                + "Legal_First_Nm : %s,  Legal_Second_Nm: %s, Birth_Dt %s, Gender_Txt : %s,  Birth_Place: %s,"
                                + "Alias1_Surname_Nm : %s,  Alias1_First_Nm: %s, Alias1_Second_Nm %s, Alias2_Surname_Nm %s, alias2FirstNm : %s,  alias2SecondNm: %s,"
                                + "Alias3_Surname_Nm : %s,  Alias3_First_Nm: %s, Alias3_Second_Nm %s, Phone_Number : %s,  Address_Line1: %s,"
                                + "Address_Line2 : %s,  City_Nm: %s, Province_Nm: %s, Country_Nm %s, Postal_Code_Txt : %s,  Drivers_Lic_No: %s,  Email_Address: %s,  Email_Type: %s\n",
                        line[0], line[1], line[2], line[3], line[4], line[5], line[6], line[7],
                        line[8], line[9], line[10], line[11], line[12], line[13], line[14],
                        line[15], line[16], line[17], line[18], line[19], line[20], line[21],
                        line[22], line[23], line[24], line[25], line[26]);
            }
            System.out.println(
                    "########################################################\n"
                            + "INFO: CreateApplicantEx  Completed there are "
                            + diffCounter
                            + " diffs\n"
                            + "########################################################");

            fileOutput.println(
                    "########################################################\n"
                            + "INFO: CreateApplicantEx  Completed there are "
                            + diffCounter
                            + " diffs\n"
                            + "########################################################");

            overallDiff += diffCounter;
            fileOutput.close();
        }
    }

    public void authenticateUserExCompare() throws IOException {

        int diffCounter = 0;

        log.info("DoAuthenticateUser");
        fileOutput = new PrintWriter(outputDir + "DoAuthenticateUser.txt", StandardCharsets.UTF_8);

        Map<String, String> request = new HashMap<>();

        request.put("OrgTicketNumber", "1");

        compare(
                request,
                new AuthenticateUserResponse(),
                "DoAuthenticateUser/Services",
                AuthenticateUserResponse.class);
        System.out.println(
                "########################################################\n"
                        + "INFO: DoAuthenticateUser  Completed there are "
                        + diffCounter
                        + " diffs\n"
                        + "########################################################");

        fileOutput.println(
                "########################################################\n"
                        + "INFO: DoAuthenticateUser  Completed there are "
                        + diffCounter
                        + " diffs\n"
                        + "########################################################");

        overallDiff += diffCounter;
        fileOutput.close();
    }

    public void logEivFailureCompare() throws IOException {

        int diffCounter = 0;

        log.info("LogEivFailure");
        fileOutput = new PrintWriter(outputDir + "LogEivFailure.txt", StandardCharsets.UTF_8);

        Map<String, String> request = new HashMap<>();
        request.put("OrgTicketNumber", "1");
        request.put("Session_Id", "1");
        request.put("Legal_Surname_Nm", "1");
        request.put("Legal_First_Nm", "1");
        request.put("Legal_Second_Nm", "1");
        request.put("Birth_Dt", "1999-05-31");
        request.put("Gender_Txt", "1");
        request.put("EIV_Vendor_Error_Msg", "1");

        compare(
                request,
                new LogEivFailureResponse(),
                "LogEivFailure/Services",
                LogEivFailureResponse.class);
        System.out.println(
                "########################################################\n"
                        + "INFO: LogEivFailure  Completed there are "
                        + diffCounter
                        + " diffs\n"
                        + "########################################################");

        fileOutput.println(
                "########################################################\n"
                        + "INFO: LogEivFailure  Completed there are "
                        + diffCounter
                        + " diffs\n"
                        + "########################################################");

        overallDiff += diffCounter;
        fileOutput.close();
    }

    public void logPaymentFailureCompare() throws IOException {

        int diffCounter = 0;

        log.info("LogEivFailure");
        fileOutput = new PrintWriter(outputDir + "LogPaymentFailure.txt", StandardCharsets.UTF_8);

        Map<String, String> request = new HashMap<>();
        request.put("OrgTicketNumber", "1");
        request.put("Service_Id", "1");
        request.put("Appl_Party_Id", "1");
        request.put("Session_Id", "1");
        request.put("Invoice_Id", "1");
        request.put("Service_Fee_Amount", "1");
        request.put("BCEP_Error_Msg", "1");

        compare(
                request,
                new LogPaymentFailureResponse(),
                "LogPaymentFailure/Services",
                LogPaymentFailureResponse.class);
        System.out.println(
                "########################################################\n"
                        + "INFO: LogPaymentFailure  Completed there are "
                        + diffCounter
                        + " diffs\n"
                        + "########################################################");

        fileOutput.println(
                "########################################################\n"
                        + "INFO: LogPaymentFailure  Completed there are "
                        + diffCounter
                        + " diffs\n"
                        + "########################################################");

        overallDiff += diffCounter;
        fileOutput.close();
    }

    public void createNewCrcServiceCompare() throws IOException {

        int diffCounter = 0;

        log.info("CreateNewCRCService");
        fileOutput = new PrintWriter(outputDir + "CreateNewCRCService.txt", StandardCharsets.UTF_8);

        Map<String, String> request = new HashMap<>();
        request.put("OrgTicketNumber", "1");
        request.put("Schedule_Type_Cd", "1");
        request.put("Scope_Level_Cd", "1");
        request.put("Appl_Party_Id", "1");
        request.put("Org_Appl_To_Pay", "1");
        request.put("Applicant_Posn", "1");
        request.put("Child_Care_Fac_Nm", "1");
        request.put("Governing_Body_Nm", "1");
        request.put("Session_Id", "1");
        request.put("Invoice_Id", "1");
        request.put("Auth_Release_EIV_Vendor_YN", "1");
        request.put("Auth_Conduct_CRC_Check_YN", "1");
        request.put("Auth_Release_To_Org_YN", "1");
        request.put("Appl_Identity_Verified_EIV_YN", "1");
        request.put("EivPassDetailsResults", "1");

        compare(
                request,
                new CreateNewCRCServiceResponse(),
                "CreateNewCRCService/Services",
                CreateNewCRCServiceResponse.class);
        System.out.println(
                "########################################################\n"
                        + "INFO: CreateNewCRCService  Completed there are "
                        + diffCounter
                        + " diffs\n"
                        + "########################################################");

        fileOutput.println(
                "########################################################\n"
                        + "INFO: CreateNewCRCService  Completed there are "
                        + diffCounter
                        + " diffs\n"
                        + "########################################################");

        overallDiff += diffCounter;
        fileOutput.close();
    }

    public void createSharingServiceCompare() throws IOException {

        int diffCounter = 0;

        log.info("CreateSharingService");
        fileOutput =
                new PrintWriter(outputDir + "CreateSharingService.txt", StandardCharsets.UTF_8);

        Map<String, String> request = new HashMap<>();
        request.put("OrgTicketNumber", "1");
        request.put("Appl_Party_Id", "1");
        request.put("Scope_Level_Cd", "1");
        request.put("Applicant_Posn", "1");
        request.put("Auth_Release_EIV_Vendor_YN", "1");
        request.put("Auth_Release_To_Org_YN", "1");
        request.put("Appl_Identity_Verified_EIV_YN", "1");
        request.put("Previous_Service_Id", "1");
        request.put("EivPassDetailsResults", "1");

        compare(
                request,
                new CreateSharingServiceResponse(),
                "CreateSharingService/Services",
                CreateSharingServiceResponse.class);
        System.out.println(
                "########################################################\n"
                        + "INFO: CreateSharingService  Completed there are "
                        + diffCounter
                        + " diffs\n"
                        + "########################################################");

        fileOutput.println(
                "########################################################\n"
                        + "INFO: CreateSharingService  Completed there are "
                        + diffCounter
                        + " diffs\n"
                        + "########################################################");

        overallDiff += diffCounter;
        fileOutput.close();
    }

    public void getServiceFeeAmountCompare() throws IOException {

        int diffCounter = 0;

        log.info("GetServiceFeeAmount");
        fileOutput = new PrintWriter(outputDir + "GetServiceFeeAmount.txt", StandardCharsets.UTF_8);

        Map<String, String> request = new HashMap<>();
        request.put("OrgTicketNumber", "1");
        request.put("ScheduleTypeCd", "1");
        request.put("ScopeLevelCd", "1");

        compare(
                request,
                new GetServiceFeeAmountResponse(),
                "GetServiceFeeAmount/Services",
                GetServiceFeeAmountResponse.class);
        System.out.println(
                "########################################################\n"
                        + "INFO: GetServiceFeeAmount  Completed there are "
                        + diffCounter
                        + " diffs\n"
                        + "########################################################");

        fileOutput.println(
                "########################################################\n"
                        + "INFO: GetServiceFeeAmount  Completed there are "
                        + diffCounter
                        + " diffs\n"
                        + "########################################################");

        overallDiff += diffCounter;
        fileOutput.close();
    }

    public void updateServiceFinancialTxnCompare() throws IOException {

        int diffCounter = 0;

        log.info("UpdateServiceFinancialTxn");
        fileOutput =
                new PrintWriter(
                        outputDir + "UpdateServiceFinancialTxn.txt", StandardCharsets.UTF_8);

        Map<String, String> request = new HashMap<>();
        request.put("OrgTicketNumber", "1");
        request.put("Appl_Party_Id", "1");
        request.put("Service_Id", "1");
        request.put("CC_Authorization", "1");
        request.put("Payment_Date", "1999-05-01");
        request.put("Payor_Type_Cd", "1");
        request.put("Payment_Status_Cd", "1");
        request.put("Session_Id", "1");
        request.put("Invoice_Id", "1");
        request.put("Transaction_Id", "1");
        request.put("Transaction_Amount", "1");

        compare(
                request,
                new UpdateServiceFinancialTxnResponse(),
                "UpdateServiceFinancialTxn/Services",
                UpdateServiceFinancialTxnResponse.class);
        System.out.println(
                "########################################################\n"
                        + "INFO: UpdateServiceFinancialTxn  Completed there are "
                        + diffCounter
                        + " diffs\n"
                        + "########################################################");

        fileOutput.println(
                "########################################################\n"
                        + "INFO: UpdateServiceFinancialTxn  Completed there are "
                        + diffCounter
                        + " diffs\n"
                        + "########################################################");

        overallDiff += diffCounter;
        fileOutput.close();
    }

    private <G> boolean compare(
            Map<String, String> request, G response, String connect, Class<G> classType)
            throws JsonProcessingException {

        HttpEntity<String> resultObjectWM = null;
        HttpEntity<String> resultObjectAPI = null;

        G webmessageResponse = null;
        G apimessageResponse = null;

        UriComponentsBuilder builderWM = UriComponentsBuilder.fromHttpUrl(wmHost + connect);
        UriComponentsBuilder builderApi = UriComponentsBuilder.fromHttpUrl(apiHost + connect);

        if (request != null) {
            request.keySet()
                    .forEach(
                            k -> {
                                builderWM.queryParam(k, request.get(k));
                                builderApi.queryParam(k, request.get(k));
                                return;
                            });
        }

        try {

            resultObjectWM =
                    restTemplate.exchange(
                            builderWM.toUriString(),
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            String.class);

            resultObjectAPI =
                    restTemplate.exchange(
                            builderApi.toUriString(),
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            String.class);

            webmessageResponse = xmlMapper.readValue(resultObjectWM.getBody(), classType);
            apimessageResponse = xmlMapper.readValue(resultObjectAPI.getBody(), classType);

            Thread.sleep(5000);

        } catch (Exception e) {
            System.out.println("ERROR: Failed to send request... " + e);
            fileOutput.println("ERROR: Failed to send request... " + e);
        }

        Diff diff = javers.compare(webmessageResponse, apimessageResponse);

        String requestClassName = response.getClass().getName();

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
                                                    if (a[i] != null
                                                            && columnLengths.get(i)
                                                                    < a[i].length()) {
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
