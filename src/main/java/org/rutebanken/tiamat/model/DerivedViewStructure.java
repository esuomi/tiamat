//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.05 at 07:41:01 PM CET 
//


package org.rutebanken.tiamat.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Type for MANAGED OBJECT VIEW.
 * 
 * <p>Java class for DerivedViewStructure complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DerivedViewStructure">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.netex.org.uk/netex}DerivedViewGroup"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.netex.org.uk/netex}ObjectIdType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DerivedViewStructure", propOrder = {
    "brandingRef"
})
@XmlSeeAlso({
    PointOfInterestClassification_DerivedViewStructure.class,
    PointOfInterest_DerivedViewStructure.class,
    Zone_DerivedViewStructure.class,
    PathLink_DerivedViewStructure.class,
    NoticeAssignment_DerivedViewStructure.class,
    PassengerStopAssignment_DerivedViewStructure.class,
    TopographicPlace_DerivedViewStructure.class,
    StopPlace_DerivedViewStructure.class,
    Operator_DerivedViewStructure.class,
    DestinationDisplay_DerivedViewStructure.class,
    TrainComponent_DerivedViewStructure.class,
    Direction_DerivedViewStructure.class,
    Organisation_DerivedViewStructure.class,
    PassengerCarryingPassengerCarrying_ViewStructure.class,
})
@MappedSuperclass
public abstract class DerivedViewStructure {

    @XmlElement(name = "BrandingRef")
    protected BrandingRefStructure brandingRef;

    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Long id;

    /**
     * Gets the value of the brandingRef property.
     * 
     * @return
     *     possible object is
     *     {@link BrandingRefStructure }
     *     
     */
    public BrandingRefStructure getBrandingRef() {
        return brandingRef;
    }

    /**
     * Sets the value of the brandingRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link BrandingRefStructure }
     *     
     */
    public void setBrandingRef(BrandingRefStructure value) {
        this.brandingRef = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long value) {
        this.id = value;
    }

}
