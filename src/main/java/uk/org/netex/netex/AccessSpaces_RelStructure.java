//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.06 at 10:37:32 AM CET 
//


package uk.org.netex.netex;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * Type for a list of ACCESS SPACEs.
 * 
 * <p>Java class for accessSpaces_RelStructure complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="accessSpaces_RelStructure">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.netex.org.uk/netex}containmentAggregationStructure">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element ref="{http://www.netex.org.uk/netex}AccessSpaceRef"/>
 *         &lt;element ref="{http://www.netex.org.uk/netex}AccessSpace"/>
 *       &lt;/choice>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "accessSpaces_RelStructure", propOrder = {
    "accessSpaceRefOrAccessSpace"
})
@Entity
public class AccessSpaces_RelStructure
    extends ContainmentAggregationStructure
{

    @XmlElements({
        @XmlElement(name = "AccessSpaceRef", type = AccessSpaceRefStructure.class),
        @XmlElement(name = "AccessSpace", type = AccessSpace.class)
    })
    @Column
    @ElementCollection(targetClass=AccessSpace.class)
    protected List<AccessSpace> accessSpaceRefOrAccessSpace;

    /**
     * Gets the value of the accessSpaceRefOrAccessSpace property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accessSpaceRefOrAccessSpace property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccessSpaceRefOrAccessSpace().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccessSpaceRefStructure }
     * {@link AccessSpace }
     * 
     * 
     */

    public List<AccessSpace> getAccessSpaceRefOrAccessSpace() {
        if (accessSpaceRefOrAccessSpace == null) {
            accessSpaceRefOrAccessSpace = new ArrayList<>();
        }
        return this.accessSpaceRefOrAccessSpace;
    }

}
