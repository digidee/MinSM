package rest.hello.org.resttest;

/**
 * Created by digi on 22.11.2015.
 */

import com.fasterxml.jackson.annotation.JsonAnySetter;


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
        "Activity"
})
public class Object_ActivityContent {

    @JsonProperty("Activity")
    private rest.hello.org.resttest.Object_Activity Activity;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The Activity
     */
    @JsonProperty("Activity")
    public rest.hello.org.resttest.Object_Activity getActivity() {
        return Activity;
    }

    /**
     * @param Activity The Activity
     */
    @JsonProperty("Activity")
    public void setActivity(rest.hello.org.resttest.Object_Activity Activity) {
        this.Activity = Activity;
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