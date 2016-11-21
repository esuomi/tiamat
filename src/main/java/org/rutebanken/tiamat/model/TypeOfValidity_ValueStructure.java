

package org.rutebanken.tiamat.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.Duration;


public class TypeOfValidity_ValueStructure
    extends TypeOfValue_VersionStructure
{

    protected Duration periodicity;
    protected FrameNatureEnumeration nature;
    protected ClassRefs_RelStructure classes;

    public Duration getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Duration value) {
        this.periodicity = value;
    }

    public FrameNatureEnumeration getNature() {
        return nature;
    }

    public void setNature(FrameNatureEnumeration value) {
        this.nature = value;
    }

    public ClassRefs_RelStructure getClasses() {
        return classes;
    }

    public void setClasses(ClassRefs_RelStructure value) {
    }

}
