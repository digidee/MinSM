package rest.hello.org.resttest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "GlobalList",
        "Messages",
        "ReturnCode"
})
public class Object_ActivityTypes {

    @JsonProperty("GlobalList")
    private rest.hello.org.resttest.Object_ActivityGlobalList GlobalList;
    @JsonProperty("Messages")
    private List<Object> Messages = new ArrayList<Object>();
    @JsonProperty("ReturnCode")
    private Integer ReturnCode;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The GlobalList
     */
    @JsonProperty("GlobalList")
    public rest.hello.org.resttest.Object_ActivityGlobalList getGlobalList() {
        return GlobalList;
    }

    /**
     *
     * @param GlobalList
     * The GlobalList
     */
    @JsonProperty("GlobalList")
    public void setGlobalList(rest.hello.org.resttest.Object_ActivityGlobalList GlobalList) {
        this.GlobalList = GlobalList;
    }

    /**
     *
     * @return
     * The Messages
     */
    @JsonProperty("Messages")
    public List<Object> getMessages() {
        return Messages;
    }

    /**
     *
     * @param Messages
     * The Messages
     */
    @JsonProperty("Messages")
    public void setMessages(List<Object> Messages) {
        this.Messages = Messages;
    }

    /**
     *
     * @return
     * The ReturnCode
     */
    @JsonProperty("ReturnCode")
    public Integer getReturnCode() {
        return ReturnCode;
    }

    /**
     *
     * @param ReturnCode
     * The ReturnCode
     */
    @JsonProperty("ReturnCode")
    public void setReturnCode(Integer ReturnCode) {
        this.ReturnCode = ReturnCode;
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