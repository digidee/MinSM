
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
    "@count",
    "@start",
    "@totalcount",
    "Messages",
    "ResourceName",
    "ReturnCode",
    "content"
})
public class Object_Incident {

    @JsonProperty("@count")
    private Integer Count;
    @JsonProperty("@start")
    private Integer Start;
    @JsonProperty("@totalcount")
    private Integer Totalcount;
    @JsonProperty("Messages")
    private List<Object> Messages = new ArrayList<Object>();
    @JsonProperty("ResourceName")
    private String ResourceName;
    @JsonProperty("ReturnCode")
    private Integer ReturnCode;
    @JsonProperty("content")
    private List<Object_Content> content = new ArrayList<Object_Content>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The Count
     */
    @JsonProperty("@count")
    public Integer getCount() {
        return Count;
    }

    /**
     * 
     * @param Count
     *     The @count
     */
    @JsonProperty("@count")
    public void setCount(Integer Count) {
        this.Count = Count;
    }

    /**
     * 
     * @return
     *     The Start
     */
    @JsonProperty("@start")
    public Integer getStart() {
        return Start;
    }

    /**
     * 
     * @param Start
     *     The @start
     */
    @JsonProperty("@start")
    public void setStart(Integer Start) {
        this.Start = Start;
    }

    /**
     * 
     * @return
     *     The Totalcount
     */
    @JsonProperty("@totalcount")
    public Integer getTotalcount() {
        return Totalcount;
    }

    /**
     * 
     * @param Totalcount
     *     The @totalcount
     */
    @JsonProperty("@totalcount")
    public void setTotalcount(Integer Totalcount) {
        this.Totalcount = Totalcount;
    }

    /**
     * 
     * @return
     *     The Messages
     */
    @JsonProperty("Messages")
    public List<Object> getMessages() {
        return Messages;
    }

    /**
     * 
     * @param Messages
     *     The Messages
     */
    @JsonProperty("Messages")
    public void setMessages(List<Object> Messages) {
        this.Messages = Messages;
    }

    /**
     * 
     * @return
     *     The ResourceName
     */
    @JsonProperty("ResourceName")
    public String getResourceName() {
        return ResourceName;
    }

    /**
     * 
     * @param ResourceName
     *     The ResourceName
     */
    @JsonProperty("ResourceName")
    public void setResourceName(String ResourceName) {
        this.ResourceName = ResourceName;
    }

    /**
     * 
     * @return
     *     The ReturnCode
     */
    @JsonProperty("ReturnCode")
    public Integer getReturnCode() {
        return ReturnCode;
    }

    /**
     * 
     * @param ReturnCode
     *     The ReturnCode
     */
    @JsonProperty("ReturnCode")
    public void setReturnCode(Integer ReturnCode) {
        this.ReturnCode = ReturnCode;
    }

    /**
     * 
     * @return
     *     The content
     */
    @JsonProperty("content")
    public List<Object_Content> getContent() {
        return content;
    }

    /**
     * 
     * @param content
     *     The content
     */
    @JsonProperty("content")
    public void setContent(List<Object_Content> content) {
        this.content = content;
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
