//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation,
// v2.2.8-b130911.1802 generiert
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren.
// Generiert: 2015.02.04 um 03:42:32 PM CET
//


package de.uni_due.s3.openmath;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>
 * Java-Klasse für anonymous complex type.
 * 
 * <p>
 * Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;group ref="{http://www.openmath.org/OpenMath}omel" maxOccurs="unbounded"/>
 *       &lt;attGroup ref="{http://www.openmath.org/OpenMath}compound.attributes"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"omel"})
@XmlRootElement(name = "OMA")
public class OMA {

  @XmlElements({@XmlElement(name = "OMS", type = OMS.class),
      @XmlElement(name = "OMV", type = OMV.class), @XmlElement(name = "OMI", type = OMI.class),
      @XmlElement(name = "OMB", type = OMB.class), @XmlElement(name = "OMSTR", type = OMSTR.class),
      @XmlElement(name = "OMF", type = OMF.class), @XmlElement(name = "OMA", type = OMA.class),
      @XmlElement(name = "OMBIND", type = OMBIND.class),
      @XmlElement(name = "OME", type = OME.class),
      @XmlElement(name = "OMATTR", type = OME.OMATTR.class),
      @XmlElement(name = "OMR", type = OMR.class)})
  protected List<Object> omel;
  @XmlAttribute(name = "cdbase")
  @XmlSchemaType(name = "anyURI")
  protected String cdbase;
  @XmlAttribute(name = "id")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  @XmlSchemaType(name = "ID")
  protected String id;

  /**
   * Ruft den Wert der cdbase-Eigenschaft ab.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCdbase() {
    return cdbase;
  }

  /**
   * Ruft den Wert der id-Eigenschaft ab.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getId() {
    return id;
  }

  /**
   * Gets the value of the omel property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the omel property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getOmel().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list {@link OMS } {@link OMV } {@link OMI }
   * {@link OMB } {@link OMSTR } {@link OMF } {@link OMA } {@link OMBIND } {@link OME }
   * {@link OME.OMATTR } {@link OMR }
   * 
   * 
   */
  public List<Object> getOmel() {
    if (omel == null) {
      omel = new ArrayList<Object>();
    }
    return this.omel;
  }

  /**
   * Legt den Wert der cdbase-Eigenschaft fest.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCdbase(String value) {
    this.cdbase = value;
  }

  /**
   * Legt den Wert der id-Eigenschaft fest.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setId(String value) {
    this.id = value;
  }

}
