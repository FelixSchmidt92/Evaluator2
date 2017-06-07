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
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.w3c.dom.Element;


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
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;group ref="{http://www.openmath.org/OpenMath}omel"/>
 *         &lt;group ref="{http://www.openmath.org/OpenMath}notom"/>
 *       &lt;/choice>
 *       &lt;attGroup ref="{http://www.openmath.org/OpenMath}compound.attributes"/>
 *       &lt;attribute name="encoding" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"content"})
@XmlRootElement(name = "OMFOREIGN")
public class OMFOREIGN {

  @XmlElementRefs({
      @XmlElementRef(name = "OMS", namespace = "http://www.openmath.org/OpenMath",
          type = OMS.class),
      @XmlElementRef(name = "OMI", namespace = "http://www.openmath.org/OpenMath",
          type = OMI.class),
      @XmlElementRef(name = "OMATTR", namespace = "http://www.openmath.org/OpenMath",
          type = JAXBElement.class),
      @XmlElementRef(name = "OMV", namespace = "http://www.openmath.org/OpenMath",
          type = OMV.class),
      @XmlElementRef(name = "OMA", namespace = "http://www.openmath.org/OpenMath",
          type = OMA.class),
      @XmlElementRef(name = "OME", namespace = "http://www.openmath.org/OpenMath",
          type = OME.class),
      @XmlElementRef(name = "OMF", namespace = "http://www.openmath.org/OpenMath",
          type = OMF.class),
      @XmlElementRef(name = "OMSTR", namespace = "http://www.openmath.org/OpenMath",
          type = OMSTR.class),
      @XmlElementRef(name = "OMBIND", namespace = "http://www.openmath.org/OpenMath",
          type = OMBIND.class),
      @XmlElementRef(name = "OMB", namespace = "http://www.openmath.org/OpenMath",
          type = OMB.class),
      @XmlElementRef(name = "OMR", namespace = "http://www.openmath.org/OpenMath",
          type = OMR.class)})
  @XmlMixed
  @XmlAnyElement
  protected List<Object> content;
  @XmlAttribute(name = "encoding")
  protected String encoding;
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
   * Gets the value of the content property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the content property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getContent().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list {@link OMV } {@link OMA }
   * {@link String } {@link OME } {@link OMS } {@link OMI } {@link JAXBElement }{@code <}
   * {@link OME.OMATTR }{@code >} {@link Element } {@link OMF } {@link OMSTR } {@link OMBIND }
   * {@link OMB } {@link OMR }
   * 
   * 
   */
  public List<Object> getContent() {
    if (content == null) {
      content = new ArrayList<Object>();
    }
    return this.content;
  }

  /**
   * Ruft den Wert der encoding-Eigenschaft ab.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getEncoding() {
    return encoding;
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
   * Legt den Wert der cdbase-Eigenschaft fest.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCdbase(String value) {
    this.cdbase = value;
  }

  /**
   * Legt den Wert der encoding-Eigenschaft fest.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setEncoding(String value) {
    this.encoding = value;
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
