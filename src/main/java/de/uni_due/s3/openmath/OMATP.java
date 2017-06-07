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
 *       &lt;sequence maxOccurs="unbounded">
 *         &lt;element ref="{http://www.openmath.org/OpenMath}OMS"/>
 *         &lt;choice>
 *           &lt;group ref="{http://www.openmath.org/OpenMath}omel"/>
 *           &lt;element ref="{http://www.openmath.org/OpenMath}OMFOREIGN"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.openmath.org/OpenMath}compound.attributes"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"omsAndOMSOrOMV"})
@XmlRootElement(name = "OMATP")
public class OMATP {

  @XmlElements({@XmlElement(name = "OMS", required = true, type = OMS.class),
      @XmlElement(name = "OMV", required = true, type = OMV.class),
      @XmlElement(name = "OMI", required = true, type = OMI.class),
      @XmlElement(name = "OMB", required = true, type = OMB.class),
      @XmlElement(name = "OMSTR", required = true, type = OMSTR.class),
      @XmlElement(name = "OMF", required = true, type = OMF.class),
      @XmlElement(name = "OMA", required = true, type = OMA.class),
      @XmlElement(name = "OMBIND", required = true, type = OMBIND.class),
      @XmlElement(name = "OME", required = true, type = OME.class),
      @XmlElement(name = "OMATTR", required = true, type = OME.OMATTR.class),
      @XmlElement(name = "OMR", required = true, type = OMR.class),
      @XmlElement(name = "OMFOREIGN", required = true, type = OMFOREIGN.class)})
  protected List<Object> omsAndOMSOrOMV;
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
   * Gets the value of the omsAndOMSOrOMV property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the omsAndOMSOrOMV property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getOMSAndOMSOrOMV().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list {@link OMS } {@link OMV } {@link OMI }
   * {@link OMB } {@link OMSTR } {@link OMF } {@link OMA } {@link OMBIND } {@link OME }
   * {@link OME.OMATTR } {@link OMR } {@link OMFOREIGN }
   * 
   * 
   */
  public List<Object> getOMSAndOMSOrOMV() {
    if (omsAndOMSOrOMV == null) {
      omsAndOMSOrOMV = new ArrayList<Object>();
    }
    return this.omsAndOMSOrOMV;
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
