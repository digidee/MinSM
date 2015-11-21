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
        "DisplayList",
        "Name",
        "ValueList"
})
public class Object_ActivityGlobalList {

    @JsonProperty("DisplayList")
    private String DisplayList;
    @JsonProperty("Name")
    private String Name;
    @JsonProperty("ValueList")
    private String ValueList;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The DisplayList
     */
    @JsonProperty("DisplayList")
    public String getDisplayList() {
        return DisplayList;
    }

    /**
     *
     * @param DisplayList
     * The DisplayList
     */
    @JsonProperty("DisplayList")
    public void setDisplayList(String DisplayList) {
        this.DisplayList = DisplayList;
    }

    /**
     *
     * @return
     * The Name
     */
    @JsonProperty("Name")
    public String getName() {
        return Name;
    }

    /**
     *
     * @param Name
     * The Name
     */
    @JsonProperty("Name")
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     *
     * @return
     * The ValueList
     */
    @JsonProperty("ValueList")
    public String getValueList() {
        return ValueList;
    }

    /**
     *
     * @param ValueList
     * The ValueList
     */
    @JsonProperty("ValueList")
    public void setValueList(String ValueList) {
        this.ValueList = ValueList;
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