//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.06 at 10:37:32 AM CET 
//


package uk.org.netex.netex;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element ref="{http://www.netex.org.uk/netex}SchematicMap" minOccurs="0"/>
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
    "schematicMap"
})
public class SchematicMaps {

    @XmlElement(name = "SchematicMap")
    protected SchematicMap schematicMap;

    /**
     * Gets the value of the schematicMap property.
     * 
     * @return
     *     possible object is
     *     {@link SchematicMap }
     *     
     */
    public SchematicMap getSchematicMap() {
        return schematicMap;
    }

    /**
     * Sets the value of the schematicMap property.
     * 
     * @param value
     *     allowed object is
     *     {@link SchematicMap }
     *     
     */
    public void setSchematicMap(SchematicMap value) {
        this.schematicMap = value;
    }

}
