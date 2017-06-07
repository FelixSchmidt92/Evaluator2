//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation,
// v2.2.8-b130911.1802 generiert
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren.
// Generiert: 2015.02.04 um 03:42:32 PM CET
//


package de.uni_due.s3.openmath;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *       &lt;attGroup ref="{http://www.openmath.org/OpenMath}common.attributes"/>
 *       &lt;attribute name="href" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "OMR")
public class OMR {

  @XmlAttribute(name = "href", required = true)
  @XmlSchemaType(name = "anyURI")
  protected String href;
  @XmlAttribute(name = "id")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  @XmlSchemaType(name = "ID")
  protected String id;

  /**
   * Ruft den Wert der href-Eigenschaft ab.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getHref() {
    return href;
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
   * Legt den Wert der href-Eigenschaft fest.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setHref(String value) {
    this.href = value;
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
