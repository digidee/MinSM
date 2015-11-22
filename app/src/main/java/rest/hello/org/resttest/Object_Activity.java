package rest.hello.org.resttest;

/**
 * Created by digi on 22.11.2015.
 */


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
        "DateStamp",
        "Description",
        "JournalEntryNumber",
        "Operator",
        "RelatedNumber",
        "Sysmodcount",
        "Sysmodtime",
        "Sysmoduser",
        "Type"
})
public class Object_Activity {

    @JsonProperty("DateStamp")
    private String DateStamp;
    @JsonProperty("Description")
    private List<String> Description = new ArrayList<String>();
    @JsonProperty("JournalEntryNumber")
    private String JournalEntryNumber;
    @JsonProperty("Operator")
    private String Operator;
    @JsonProperty("RelatedNumber")
    private String RelatedNumber;
    @JsonProperty("Sysmodcount")
    private Integer Sysmodcount;
    @JsonProperty("Sysmodtime")
    private String Sysmodtime;
    @JsonProperty("Sysmoduser")
    private String Sysmoduser;
    @JsonProperty("Type")
    private String Type;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The DateStamp
     */
    @JsonProperty("DateStamp")
    public String getDateStamp() {
        return DateStamp;
    }

    /**
     *
     * @param DateStamp
     * The DateStamp
     */
    @JsonProperty("DateStamp")
    public void setDateStamp(String DateStamp) {
        this.DateStamp = DateStamp;
    }

    /**
     *
     * @return
     * The Description
     */
    @JsonProperty("Description")
    public List<String> getDescription() {
        return Description;
    }

    /**
     *
     * @param Description
     * The Description
     */
    @JsonProperty("Description")
    public void setDescription(List<String> Description) {
        this.Description = Description;
    }

    /**
     *
     * @return
     * The JournalEntryNumber
     */
    @JsonProperty("JournalEntryNumber")
    public String getJournalEntryNumber() {
        return JournalEntryNumber;
    }

    /**
     *
     * @param JournalEntryNumber
     * The JournalEntryNumber
     */
    @JsonProperty("JournalEntryNumber")
    public void setJournalEntryNumber(String JournalEntryNumber) {
        this.JournalEntryNumber = JournalEntryNumber;
    }

    /**
     *
     * @return
     * The Operator
     */
    @JsonProperty("Operator")
    public String getOperator() {
        return Operator;
    }

    /**
     *
     * @param Operator
     * The Operator
     */
    @JsonProperty("Operator")
    public void setOperator(String Operator) {
        this.Operator = Operator;
    }

    /**
     *
     * @return
     * The RelatedNumber
     */
    @JsonProperty("RelatedNumber")
    public String getRelatedNumber() {
        return RelatedNumber;
    }

    /**
     *
     * @param RelatedNumber
     * The RelatedNumber
     */
    @JsonProperty("RelatedNumber")
    public void setRelatedNumber(String RelatedNumber) {
        this.RelatedNumber = RelatedNumber;
    }

    /**
     *
     * @return
     * The Sysmodcount
     */
    @JsonProperty("Sysmodcount")
    public Integer getSysmodcount() {
        return Sysmodcount;
    }

    /**
     *
     * @param Sysmodcount
     * The Sysmodcount
     */
    @JsonProperty("Sysmodcount")
    public void setSysmodcount(Integer Sysmodcount) {
        this.Sysmodcount = Sysmodcount;
    }

    /**
     *
     * @return
     * The Sysmodtime
     */
    @JsonProperty("Sysmodtime")
    public String getSysmodtime() {
        return Sysmodtime;
    }

    /**
     *
     * @param Sysmodtime
     * The Sysmodtime
     */
    @JsonProperty("Sysmodtime")
    public void setSysmodtime(String Sysmodtime) {
        this.Sysmodtime = Sysmodtime;
    }

    /**
     *
     * @return
     * The Sysmoduser
     */
    @JsonProperty("Sysmoduser")
    public String getSysmoduser() {
        return Sysmoduser;
    }

    /**
     *
     * @param Sysmoduser
     * The Sysmoduser
     */
    @JsonProperty("Sysmoduser")
    public void setSysmoduser(String Sysmoduser) {
        this.Sysmoduser = Sysmoduser;
    }

    /**
     *
     * @return
     * The Type
     */
    @JsonProperty("Type")
    public String getType() {
        return Type;
    }

    /**
     *
     * @param Type
     * The Type
     */
    @JsonProperty("Type")
    public void setType(String Type) {
        this.Type = Type;
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