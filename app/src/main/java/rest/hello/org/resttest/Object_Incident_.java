
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
        "AffectedCI",
        "Area",
        "Assignee",
        "AssignmentGroup",
        "Category",
        "ClosedBy",
        "ClosedTime",
        "ClosureCode",
        "Company",
        "Description",
        "Impact",
        "IncidentID",
        "JournalUpdates",
        "Location",
        "OpenTime",
        "OpenedBy",
        "ProblemType",
        "SLAAgreementID",
        "Service",
        "Solution",
        "Status",
        "Subarea",
        "TicketOwner",
        "Title",
        "UpdatedBy",
        "UpdatedTime",
        "Urgency"
})
public class Object_Incident_ {

    @JsonProperty("AffectedCI")
    private String AffectedCI;
    @JsonProperty("Area")
    private String Area;
    @JsonProperty("Assignee")
    private String Assignee;
    @JsonProperty("AssignmentGroup")
    private String AssignmentGroup;
    @JsonProperty("Category")
    private String Category;
    @JsonProperty("ClosedBy")
    private String ClosedBy;
    @JsonProperty("ClosedTime")
    private String ClosedTime;
    @JsonProperty("ClosureCode")
    private String ClosureCode;
    @JsonProperty("Company")
    private String Company;
    @JsonProperty("Description")
    private List<String> Description = new ArrayList<String>();
    @JsonProperty("Impact")
    private String Impact;
    @JsonProperty("IncidentID")
    private String IncidentID;
    @JsonProperty("JournalUpdates")
    private List<String> JournalUpdates = new ArrayList<String>();
    @JsonProperty("Location")
    private String Location;
    @JsonProperty("OpenTime")
    private String OpenTime;
    @JsonProperty("OpenedBy")
    private String OpenedBy;
    @JsonProperty("ProblemType")
    private String ProblemType;
    @JsonProperty("SLAAgreementID")
    private Integer SLAAgreementID;
    @JsonProperty("Service")
    private String Service;
    @JsonProperty("Solution")
    private List<String> Solution = new ArrayList<String>();
    @JsonProperty("Status")
    private String Status;
    @JsonProperty("Subarea")
    private String Subarea;
    @JsonProperty("TicketOwner")
    private String TicketOwner;
    @JsonProperty("Title")
    private String Title;
    @JsonProperty("UpdatedBy")
    private String UpdatedBy;
    @JsonProperty("UpdatedTime")
    private String UpdatedTime;
    @JsonProperty("Urgency")
    private String Urgency;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The AffectedCI
     */
    @JsonProperty("AffectedCI")
    public String getAffectedCI() {
        return AffectedCI;
    }

    /**
     *
     * @param AffectedCI
     *     The AffectedCI
     */
    @JsonProperty("AffectedCI")
    public void setAffectedCI(String AffectedCI) {
        this.AffectedCI = AffectedCI;
    }

    /**
     *
     * @return
     *     The Area
     */
    @JsonProperty("Area")
    public String getArea() {
        return Area;
    }

    /**
     *
     * @param Area
     *     The Area
     */
    @JsonProperty("Area")
    public void setArea(String Area) {
        this.Area = Area;
    }

    /**
     *
     * @return
     *     The Assignee
     */
    @JsonProperty("Assignee")
    public String getAssignee() {
        return Assignee;
    }

    /**
     *
     * @param Assignee
     *     The Assignee
     */
    @JsonProperty("Assignee")
    public void setAssignee(String Assignee) {
        this.Assignee = Assignee;
    }

    /**
     *
     * @return
     *     The AssignmentGroup
     */
    @JsonProperty("AssignmentGroup")
    public String getAssignmentGroup() {
        return AssignmentGroup;
    }

    /**
     *
     * @param AssignmentGroup
     *     The AssignmentGroup
     */
    @JsonProperty("AssignmentGroup")
    public void setAssignmentGroup(String AssignmentGroup) {
        this.AssignmentGroup = AssignmentGroup;
    }

    /**
     *
     * @return
     *     The Category
     */
    @JsonProperty("Category")
    public String getCategory() {
        return Category;
    }

    /**
     *
     * @param Category
     *     The Category
     */
    @JsonProperty("Category")
    public void setCategory(String Category) {
        this.Category = Category;
    }

    /**
     *
     * @return
     *     The ClosedBy
     */
    @JsonProperty("ClosedBy")
    public String getClosedBy() {
        return ClosedBy;
    }

    /**
     *
     * @param ClosedBy
     *     The ClosedBy
     */
    @JsonProperty("ClosedBy")
    public void setClosedBy(String ClosedBy) {
        this.ClosedBy = ClosedBy;
    }

    /**
     *
     * @return
     *     The ClosedTime
     */
    @JsonProperty("ClosedTime")
    public String getClosedTime() {
        return ClosedTime;
    }

    /**
     *
     * @param ClosedTime
     *     The ClosedTime
     */
    @JsonProperty("ClosedTime")
    public void setClosedTime(String ClosedTime) {
        this.ClosedTime = ClosedTime;
    }

    /**
     *
     * @return
     *     The ClosureCode
     */
    @JsonProperty("ClosureCode")
    public String getClosureCode() {
        return ClosureCode;
    }

    /**
     *
     * @param ClosureCode
     *     The ClosureCode
     */
    @JsonProperty("ClosureCode")
    public void setClosureCode(String ClosureCode) {
        this.ClosureCode = ClosureCode;
    }

    /**
     *
     * @return
     *     The Company
     */
    @JsonProperty("Company")
    public String getCompany() {
        return Company;
    }

    /**
     *
     * @param Company
     *     The Company
     */
    @JsonProperty("Company")
    public void setCompany(String Company) {
        this.Company = Company;
    }

    /**
     *
     * @return
     *     The Description
     */
    @JsonProperty("Description")
    public List<String> getDescription() {
        return Description;
    }

    /**
     *
     * @param Description
     *     The Description
     */
    @JsonProperty("Description")
    public void setDescription(List<String> Description) {
        this.Description = Description;
    }

    /**
     *
     * @return
     *     The Impact
     */
    @JsonProperty("Impact")
    public String getImpact() {
        return Impact;
    }

    /**
     *
     * @param Impact
     *     The Impact
     */
    @JsonProperty("Impact")
    public void setImpact(String Impact) {
        this.Impact = Impact;
    }

    /**
     *
     * @return
     *     The IncidentID
     */
    @JsonProperty("IncidentID")
    public String getIncidentID() {
        return IncidentID;
    }

    /**
     *
     * @param IncidentID
     *     The IncidentID
     */
    @JsonProperty("IncidentID")
    public void setIncidentID(String IncidentID) {
        this.IncidentID = IncidentID;
    }


    /**
     *
     * @return
     * The JournalUpdates
     */
    @JsonProperty("JournalUpdates")
    public List<String> getJournalUpdates() {
        return JournalUpdates;
    }

    /**
     *
     * @param JournalUpdates
     * The JournalUpdates
     */
    @JsonProperty("JournalUpdates")
    public void setJournalUpdates(List<String> JournalUpdates) {
        this.JournalUpdates = JournalUpdates;
    }


    /**
     *
     * @return
     *     The Location
     */
    @JsonProperty("Location")
    public String getLocation() {
        return Location;
    }

    /**
     *
     * @param Location
     *     The Location
     */
    @JsonProperty("Location")
    public void setLocation(String Location) {
        this.Location = Location;
    }

    /**
     *
     * @return
     *     The OpenTime
     */
    @JsonProperty("OpenTime")
    public String getOpenTime() {
        return OpenTime;
    }

    /**
     *
     * @param OpenTime
     *     The OpenTime
     */
    @JsonProperty("OpenTime")
    public void setOpenTime(String OpenTime) {
        this.OpenTime = OpenTime;
    }

    /**
     *
     * @return
     *     The OpenedBy
     */
    @JsonProperty("OpenedBy")
    public String getOpenedBy() {
        return OpenedBy;
    }

    /**
     *
     * @param OpenedBy
     *     The OpenedBy
     */
    @JsonProperty("OpenedBy")
    public void setOpenedBy(String OpenedBy) {
        this.OpenedBy = OpenedBy;
    }

    /**
     *
     * @return
     *     The ProblemType
     */
    @JsonProperty("ProblemType")
    public String getProblemType() {
        return ProblemType;
    }

    /**
     *
     * @param ProblemType
     *     The ProblemType
     */
    @JsonProperty("ProblemType")
    public void setProblemType(String ProblemType) {
        this.ProblemType = ProblemType;
    }

    /**
     *
     * @return
     *     The SLAAgreementID
     */
    @JsonProperty("SLAAgreementID")
    public Integer getSLAAgreementID() {
        return SLAAgreementID;
    }

    /**
     *
     * @param SLAAgreementID
     *     The SLAAgreementID
     */
    @JsonProperty("SLAAgreementID")
    public void setSLAAgreementID(Integer SLAAgreementID) {
        this.SLAAgreementID = SLAAgreementID;
    }

    /**
     *
     * @return
     *     The Service
     */
    @JsonProperty("Service")
    public String getService() {
        return Service;
    }

    /**
     *
     * @param Service
     *     The Service
     */
    @JsonProperty("Service")
    public void setService(String Service) {
        this.Service = Service;
    }

    /**
     *
     * @return
     *     The Solution
     */
    @JsonProperty("Solution")
    public List<String> getSolution() {
        return Solution;
    }

    /**
     *
     * @param Solution
     *     The Solution
     */
    @JsonProperty("Solution")
    public void setSolution(List<String> Solution) {
        this.Solution = Solution;
    }

    /**
     *
     * @return
     *     The Status
     */
    @JsonProperty("Status")
    public String getStatus() {
        return Status;
    }

    /**
     *
     * @param Status
     *     The Status
     */
    @JsonProperty("Status")
    public void setStatus(String Status) {
        this.Status = Status;
    }

    /**
     *
     * @return
     *     The Subarea
     */
    @JsonProperty("Subarea")
    public String getSubarea() {
        return Subarea;
    }

    /**
     *
     * @param Subarea
     *     The Subarea
     */
    @JsonProperty("Subarea")
    public void setSubarea(String Subarea) {
        this.Subarea = Subarea;
    }

    /**
     *
     * @return
     *     The TicketOwner
     */
    @JsonProperty("TicketOwner")
    public String getTicketOwner() {
        return TicketOwner;
    }

    /**
     *
     * @param TicketOwner
     *     The TicketOwner
     */
    @JsonProperty("TicketOwner")
    public void setTicketOwner(String TicketOwner) {
        this.TicketOwner = TicketOwner;
    }

    /**
     *
     * @return
     *     The Title
     */
    @JsonProperty("Title")
    public String getTitle() {
        return Title;
    }

    /**
     *
     * @param Title
     *     The Title
     */
    @JsonProperty("Title")
    public void setTitle(String Title) {
        this.Title = Title;
    }

    /**
     *
     * @return
     *     The UpdatedBy
     */
    @JsonProperty("UpdatedBy")
    public String getUpdatedBy() {
        return UpdatedBy;
    }

    /**
     *
     * @param UpdatedBy
     *     The UpdatedBy
     */
    @JsonProperty("UpdatedBy")
    public void setUpdatedBy(String UpdatedBy) {
        this.UpdatedBy = UpdatedBy;
    }

    /**
     *
     * @return
     *     The UpdatedTime
     */
    @JsonProperty("UpdatedTime")
    public String getUpdatedTime() {
        return UpdatedTime;
    }

    /**
     *
     * @param UpdatedTime
     *     The UpdatedTime
     */
    @JsonProperty("UpdatedTime")
    public void setUpdatedTime(String UpdatedTime) {
        this.UpdatedTime = UpdatedTime;
    }

    /**
     *
     * @return
     *     The Urgency
     */
    @JsonProperty("Urgency")
    public String getUrgency() {
        return Urgency;
    }

    /**
     *
     * @param Urgency
     *     The Urgency
     */
    @JsonProperty("Urgency")
    public void setUrgency(String Urgency) {
        this.Urgency = Urgency;
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