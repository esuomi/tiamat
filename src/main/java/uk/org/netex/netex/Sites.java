//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.06 at 10:37:32 AM CET 
//


package uk.org.netex.netex;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="stopPlaces" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://www.netex.org.uk/netex}StopPlace" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="pointsOfInterest" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://www.netex.org.uk/netex}PointOfInterest" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="parkings" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://www.netex.org.uk/netex}Parking" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "stopPlaces",
    "pointsOfInterest",
    "parkings"
})
public class Sites {

    protected StopPlaces stopPlaces;
    protected PointsOfInterest pointsOfInterest;
    protected Parkings parkings;

    /**
     * Gets the value of the stopPlaces property.
     * 
     * @return
     *     possible object is
     *     {@link StopPlaces }
     *     
     */
    public StopPlaces getStopPlaces() {
        return stopPlaces;
    }

    /**
     * Sets the value of the stopPlaces property.
     * 
     * @param value
     *     allowed object is
     *     {@link StopPlaces }
     *     
     */
    public void setStopPlaces(StopPlaces value) {
        this.stopPlaces = value;
    }

    /**
     * Gets the value of the pointsOfInterest property.
     * 
     * @return
     *     possible object is
     *     {@link PointsOfInterest }
     *     
     */
    public PointsOfInterest getPointsOfInterest() {
        return pointsOfInterest;
    }

    /**
     * Sets the value of the pointsOfInterest property.
     * 
     * @param value
     *     allowed object is
     *     {@link PointsOfInterest }
     *     
     */
    public void setPointsOfInterest(PointsOfInterest value) {
        this.pointsOfInterest = value;
    }

    /**
     * Gets the value of the parkings property.
     * 
     * @return
     *     possible object is
     *     {@link Parkings }
     *     
     */
    public Parkings getParkings() {
        return parkings;
    }

    /**
     * Sets the value of the parkings property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parkings }
     *     
     */
    public void setParkings(Parkings value) {
        this.parkings = value;
    }

}
