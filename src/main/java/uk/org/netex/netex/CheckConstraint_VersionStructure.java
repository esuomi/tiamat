//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.06 at 10:37:32 AM CET 
//


package uk.org.netex.netex;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Type for a CHECK CONSTRAINT.
 * 
 * <p>Java class for CheckConstraint_VersionStructure complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CheckConstraint_VersionStructure">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.netex.org.uk/netex}Assignment_VersionStructure">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.netex.org.uk/netex}CheckConstraintGroup"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckConstraint_VersionStructure", propOrder = {
    "placeRef",
    "checkDirection",
    "checkProcess",
    "checkService",
    "accessFeatureType",
    "congestion",
    "classOfUseRef",
    "typeOfEquipmentRef",
    "facilityRef",
    "equipmentRef",
    "delays",
    "throughput"
})
@XmlSeeAlso({
    CheckConstraint.class
})
public class CheckConstraint_VersionStructure
    extends Assignment_VersionStructure
{

    @XmlElement(name = "PlaceRef")
    protected PlaceRef placeRef;
    @XmlElement(name = "CheckDirection")
    @XmlSchemaType(name = "NMTOKEN")
    protected CheckDirectionEnumeration checkDirection;
    @XmlElement(name = "CheckProcess")
    @XmlSchemaType(name = "string")
    protected CheckProcessTypeEnumeration checkProcess;
    @XmlElement(name = "CheckService")
    @XmlSchemaType(name = "string")
    protected CheckServiceEnumeration checkService;
    @XmlElement(name = "AccessFeatureType")
    @XmlSchemaType(name = "string")
    protected AccessFeatureEnumeration accessFeatureType;
    @XmlElement(name = "Congestion")
    @XmlSchemaType(name = "string")
    protected CongestionEnumeration congestion;
    @XmlElement(name = "ClassOfUseRef")
    protected ClassOfUseRef classOfUseRef;
    @XmlElement(name = "TypeOfEquipmentRef")
    protected TypeOfEquipmentRefStructure typeOfEquipmentRef;
    @XmlElement(name = "FacilityRef")
    protected FacilityRefStructure facilityRef;
    @XmlElementRef(name = "EquipmentRef", namespace = "http://www.netex.org.uk/netex", type = JAXBElement.class, required = false)
    protected JAXBElement<? extends EquipmentRefStructure> equipmentRef;
    protected Delays delays;
    protected Throughput throughput;

    /**
     * Gets the value of the placeRef property.
     * 
     * @return
     *     possible object is
     *     {@link PlaceRef }
     *     
     */
    public PlaceRef getPlaceRef() {
        return placeRef;
    }

    /**
     * Sets the value of the placeRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link PlaceRef }
     *     
     */
    public void setPlaceRef(PlaceRef value) {
        this.placeRef = value;
    }

    /**
     * Gets the value of the checkDirection property.
     * 
     * @return
     *     possible object is
     *     {@link CheckDirectionEnumeration }
     *     
     */
    public CheckDirectionEnumeration getCheckDirection() {
        return checkDirection;
    }

    /**
     * Sets the value of the checkDirection property.
     * 
     * @param value
     *     allowed object is
     *     {@link CheckDirectionEnumeration }
     *     
     */
    public void setCheckDirection(CheckDirectionEnumeration value) {
        this.checkDirection = value;
    }

    /**
     * Gets the value of the checkProcess property.
     * 
     * @return
     *     possible object is
     *     {@link CheckProcessTypeEnumeration }
     *     
     */
    public CheckProcessTypeEnumeration getCheckProcess() {
        return checkProcess;
    }

    /**
     * Sets the value of the checkProcess property.
     * 
     * @param value
     *     allowed object is
     *     {@link CheckProcessTypeEnumeration }
     *     
     */
    public void setCheckProcess(CheckProcessTypeEnumeration value) {
        this.checkProcess = value;
    }

    /**
     * Gets the value of the checkService property.
     * 
     * @return
     *     possible object is
     *     {@link CheckServiceEnumeration }
     *     
     */
    public CheckServiceEnumeration getCheckService() {
        return checkService;
    }

    /**
     * Sets the value of the checkService property.
     * 
     * @param value
     *     allowed object is
     *     {@link CheckServiceEnumeration }
     *     
     */
    public void setCheckService(CheckServiceEnumeration value) {
        this.checkService = value;
    }

    /**
     * Gets the value of the accessFeatureType property.
     * 
     * @return
     *     possible object is
     *     {@link AccessFeatureEnumeration }
     *     
     */
    public AccessFeatureEnumeration getAccessFeatureType() {
        return accessFeatureType;
    }

    /**
     * Sets the value of the accessFeatureType property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccessFeatureEnumeration }
     *     
     */
    public void setAccessFeatureType(AccessFeatureEnumeration value) {
        this.accessFeatureType = value;
    }

    /**
     * Gets the value of the congestion property.
     * 
     * @return
     *     possible object is
     *     {@link CongestionEnumeration }
     *     
     */
    public CongestionEnumeration getCongestion() {
        return congestion;
    }

    /**
     * Sets the value of the congestion property.
     * 
     * @param value
     *     allowed object is
     *     {@link CongestionEnumeration }
     *     
     */
    public void setCongestion(CongestionEnumeration value) {
        this.congestion = value;
    }

    /**
     * Gets the value of the classOfUseRef property.
     * 
     * @return
     *     possible object is
     *     {@link ClassOfUseRef }
     *     
     */
    public ClassOfUseRef getClassOfUseRef() {
        return classOfUseRef;
    }

    /**
     * Sets the value of the classOfUseRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClassOfUseRef }
     *     
     */
    public void setClassOfUseRef(ClassOfUseRef value) {
        this.classOfUseRef = value;
    }

    /**
     * Gets the value of the typeOfEquipmentRef property.
     * 
     * @return
     *     possible object is
     *     {@link TypeOfEquipmentRefStructure }
     *     
     */
    public TypeOfEquipmentRefStructure getTypeOfEquipmentRef() {
        return typeOfEquipmentRef;
    }

    /**
     * Sets the value of the typeOfEquipmentRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeOfEquipmentRefStructure }
     *     
     */
    public void setTypeOfEquipmentRef(TypeOfEquipmentRefStructure value) {
        this.typeOfEquipmentRef = value;
    }

    /**
     * Gets the value of the facilityRef property.
     * 
     * @return
     *     possible object is
     *     {@link FacilityRefStructure }
     *     
     */
    public FacilityRefStructure getFacilityRef() {
        return facilityRef;
    }

    /**
     * Sets the value of the facilityRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link FacilityRefStructure }
     *     
     */
    public void setFacilityRef(FacilityRefStructure value) {
        this.facilityRef = value;
    }

    /**
     * Gets the value of the equipmentRef property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link HireServiceRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link AccessEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link CycleStorageEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link CommunicationServiceRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link TicketingEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link AccessEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link InstalledEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link TrolleyStandEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link SiteEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link RetailServiceRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link ActivatedEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link AccessVehicleEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link CustomerServiceRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link LuggageLockerEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link CateringServiceRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link PassengerSafetyEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link HeadingSignRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link AccessEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link PlaceSignRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link InstalledEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link RoughSurfaceRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link RubbishDisposalEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link AssistanceServiceRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link InstalledEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link MeetingPointServiceRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link MoneyServiceRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link TravelatorEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link AccessEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link HelpPointEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link SanitaryEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link StaircaseEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link PassengerInformationEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link AccessEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link AccessEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link TicketValidatorEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link AccessEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link ComplaintsServiceRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link LuggageServiceRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link EntranceEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link LeftLuggageServiceRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link WaitingEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link TicketingServiceRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link EquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link GeneralSignRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link InstalledEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link AssistanceBookingServiceRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link SeatingEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link LocalServiceRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link LostPropertyServiceRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link WaitingRoomEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link EquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link VehicleChargingEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link ShelterEquipmentRefStructure }{@code >}
     *     
     */
    public JAXBElement<? extends EquipmentRefStructure> getEquipmentRef() {
        return equipmentRef;
    }

    /**
     * Sets the value of the equipmentRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link HireServiceRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link AccessEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link CycleStorageEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link CommunicationServiceRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link TicketingEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link AccessEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link InstalledEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link TrolleyStandEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link SiteEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link RetailServiceRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link ActivatedEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link AccessVehicleEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link CustomerServiceRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link LuggageLockerEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link CateringServiceRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link PassengerSafetyEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link HeadingSignRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link AccessEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link PlaceSignRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link InstalledEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link RoughSurfaceRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link RubbishDisposalEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link AssistanceServiceRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link InstalledEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link MeetingPointServiceRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link MoneyServiceRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link TravelatorEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link AccessEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link HelpPointEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link SanitaryEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link StaircaseEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link PassengerInformationEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link AccessEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link AccessEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link TicketValidatorEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link AccessEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link ComplaintsServiceRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link LuggageServiceRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link EntranceEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link LeftLuggageServiceRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link WaitingEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link TicketingServiceRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link EquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link GeneralSignRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link InstalledEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link AssistanceBookingServiceRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link SeatingEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link LocalServiceRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link LostPropertyServiceRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link WaitingRoomEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link EquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link VehicleChargingEquipmentRefStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link ShelterEquipmentRefStructure }{@code >}
     *     
     */
    public void setEquipmentRef(JAXBElement<? extends EquipmentRefStructure> value) {
        this.equipmentRef = value;
    }

    /**
     * Gets the value of the delays property.
     * 
     * @return
     *     possible object is
     *     {@link Delays }
     *     
     */
    public Delays getDelays() {
        return delays;
    }

    /**
     * Sets the value of the delays property.
     * 
     * @param value
     *     allowed object is
     *     {@link Delays }
     *     
     */
    public void setDelays(Delays value) {
        this.delays = value;
    }

    /**
     * Gets the value of the throughput property.
     * 
     * @return
     *     possible object is
     *     {@link Throughput }
     *     
     */
    public Throughput getThroughput() {
        return throughput;
    }

    /**
     * Sets the value of the throughput property.
     * 
     * @param value
     *     allowed object is
     *     {@link Throughput }
     *     
     */
    public void setThroughput(Throughput value) {
        this.throughput = value;
    }

}
