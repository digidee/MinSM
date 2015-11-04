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
"IncidentID"
})
public class Incident_ {

@JsonProperty("IncidentID")
private String IncidentID;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* 
* @return
* The IncidentID
*/
@JsonProperty("IncidentID")
public String getIncidentID() {
return IncidentID;
}

/**
* 
* @param IncidentID
* The IncidentID
*/
@JsonProperty("IncidentID")
public void setIncidentID(String IncidentID) {
this.IncidentID = IncidentID;
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