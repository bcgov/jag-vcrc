package ca.bc.gov.open.vcrc.models.responses;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "getPingResponse")
public class GetHealthResponse {
    private static final long serialVersionUID = 1L;
    protected String appid;
    protected String method;
    protected String status;
    protected String host;
    protected String instance;
    protected String version;
    protected String compatibility;

    /**
     * Gets the value of the appid property.
     *
     * @return possible object is {@link String }
     */
    public String getAppid() {
        return appid;
    }

    /**
     * Sets the value of the appid property.
     *
     * @param value allowed object is {@link String }
     */
    public void setAppid(String value) {
        this.appid = value;
    }

    /**
     * Gets the value of the method property.
     *
     * @return possible object is {@link String }
     */
    public String getMethod() {
        return method;
    }

    /**
     * Sets the value of the method property.
     *
     * @param value allowed object is {@link String }
     */
    public void setMethod(String value) {
        this.method = value;
    }

    /**
     * Gets the value of the status property.
     *
     * @return possible object is {@link String }
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     *
     * @param value allowed object is {@link String }
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the host property.
     *
     * @return possible object is {@link String }
     */
    public String getHost() {
        return host;
    }

    /**
     * Sets the value of the host property.
     *
     * @param value allowed object is {@link String }
     */
    public void setHost(String value) {
        this.host = value;
    }

    /**
     * Gets the value of the instance property.
     *
     * @return possible object is {@link String }
     */
    public String getInstance() {
        return instance;
    }

    /**
     * Sets the value of the instance property.
     *
     * @param value allowed object is {@link String }
     */
    public void setInstance(String value) {
        this.instance = value;
    }

    /**
     * Gets the value of the version property.
     *
     * @return possible object is {@link String }
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     *
     * @param value allowed object is {@link String }
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the compatibility property.
     *
     * @return possible object is {@link String }
     */
    public String getCompatibility() {
        return compatibility;
    }

    /**
     * Sets the value of the compatibility property.
     *
     * @param value allowed object is {@link String }
     */
    public void setCompatibility(String value) {
        this.compatibility = value;
    }
}
