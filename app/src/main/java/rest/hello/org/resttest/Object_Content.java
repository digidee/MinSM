
package rest.hello.org.resttest;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({
    "Incident"
})
public class Object_Content {

    @JsonProperty("Incident")
    private Object_Incident_ Incident;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The Incident
     */
    @JsonProperty("Incident")
    public Object_Incident_ getIncident() {
        return Incident;
    }

    /**
     * 
     * @param Incident
     *     The Incident
     */
    @JsonProperty("Incident")
    public void setIncident(Object_Incident_ Incident) {
        this.Incident = Incident;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
