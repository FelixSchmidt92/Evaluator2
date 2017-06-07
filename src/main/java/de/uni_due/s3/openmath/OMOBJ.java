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
import javax.xml.bind.annotation.XmlElement;
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
 *       &lt;group ref="{http://www.openmath.org/OpenMath}omel"/>
 *       &lt;attGroup ref="{http://www.openmath.org/OpenMath}compound.attributes"/>
 *       &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"oms", "omv", "omi", "omb", "omstr", "omf", "oma", "ombind", "ome",
    "omattr", "omr"})
@XmlRootElement(name = "OMOBJ")
public class OMOBJ {

  @XmlElement(name = "OMS")
  protected OMS oms;
  @XmlElement(name = "OMV")
  protected OMV omv;
  @XmlElement(name = "OMI")
  protected OMI omi;
  @XmlElement(name = "OMB")
  protected OMB omb;
  @XmlElement(name = "OMSTR")
  protected OMSTR omstr;
  @XmlElement(name = "OMF")
  protected OMF omf;
  @XmlElement(name = "OMA")
  protected OMA oma;
  @XmlElement(name = "OMBIND")
  protected OMBIND ombind;
  @XmlElement(name = "OME")
  protected OME ome;
  @XmlElement(name = "OMATTR")
  protected OME.OMATTR omattr;
  @XmlElement(name = "OMR")
  protected OMR omr;
  @XmlAttribute(name = "version")
  protected String version;
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
   * Ruft den Wert der oma-Eigenschaft ab.
   * 
   * @return possible object is {@link OMA }
   * 
   */
  public OMA getOMA() {
    return oma;
  }

  /**
   * Ruft den Wert der omattr-Eigenschaft ab.
   * 
   * @return possible object is {@link OME.OMATTR }
   * 
   */
  public OME.OMATTR getOMATTR() {
    return omattr;
  }

  /**
   * Ruft den Wert der omb-Eigenschaft ab.
   * 
   * @return possible object is {@link OMB }
   * 
   */
  public OMB getOMB() {
    return omb;
  }

  /**
   * Ruft den Wert der ombind-Eigenschaft ab.
   * 
   * @return possible object is {@link OMBIND }
   * 
   */
  public OMBIND getOMBIND() {
    return ombind;
  }

  /**
   * Ruft den Wert der ome-Eigenschaft ab.
   * 
   * @return possible object is {@link OME }
   * 
   */
  public OME getOME() {
    return ome;
  }

  /**
   * Ruft den Wert der omf-Eigenschaft ab.
   * 
   * @return possible object is {@link OMF }
   * 
   */
  public OMF getOMF() {
    return omf;
  }

  /**
   * Ruft den Wert der omi-Eigenschaft ab.
   * 
   * @return possible object is {@link OMI }
   * 
   */
  public OMI getOMI() {
    return omi;
  }

  /**
   * Ruft den Wert der omr-Eigenschaft ab.
   * 
   * @return possible object is {@link OMR }
   * 
   */
  public OMR getOMR() {
    return omr;
  }

  /**
   * Ruft den Wert der oms-Eigenschaft ab.
   * 
   * @return possible object is {@link OMS }
   * 
   */
  public OMS getOMS() {
    return oms;
  }

  /**
   * Ruft den Wert der omstr-Eigenschaft ab.
   * 
   * @return possible object is {@link OMSTR }
   * 
   */
  public OMSTR getOMSTR() {
    return omstr;
  }

  /**
   * Ruft den Wert der omv-Eigenschaft ab.
   * 
   * @return possible object is {@link OMV }
   * 
   */
  public OMV getOMV() {
    return omv;
  }

  /**
   * Ruft den Wert der version-Eigenschaft ab.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getVersion() {
    return version;
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

  /**
   * Legt den Wert der oma-Eigenschaft fest.
   * 
   * @param value allowed object is {@link OMA }
   * 
   */
  public void setOMA(OMA value) {
    this.oma = value;
  }

  /**
   * Legt den Wert der omattr-Eigenschaft fest.
   * 
   * @param value allowed object is {@link OME.OMATTR }
   * 
   */
  public void setOMATTR(OME.OMATTR value) {
    this.omattr = value;
  }

  /**
   * Legt den Wert der omb-Eigenschaft fest.
   * 
   * @param value allowed object is {@link OMB }
   * 
   */
  public void setOMB(OMB value) {
    this.omb = value;
  }

  /**
   * Legt den Wert der ombind-Eigenschaft fest.
   * 
   * @param value allowed object is {@link OMBIND }
   * 
   */
  public void setOMBIND(OMBIND value) {
    this.ombind = value;
  }

  /**
   * Legt den Wert der ome-Eigenschaft fest.
   * 
   * @param value allowed object is {@link OME }
   * 
   */
  public void setOME(OME value) {
    this.ome = value;
  }

  /**
   * Legt den Wert der omf-Eigenschaft fest.
   * 
   * @param value allowed object is {@link OMF }
   * 
   */
  public void setOMF(OMF value) {
    this.omf = value;
  }

  /**
   * Legt den Wert der omi-Eigenschaft fest.
   * 
   * @param value allowed object is {@link OMI }
   * 
   */
  public void setOMI(OMI value) {
    this.omi = value;
  }

  /**
   * Legt den Wert der omr-Eigenschaft fest.
   * 
   * @param value allowed object is {@link OMR }
   * 
   */
  public void setOMR(OMR value) {
    this.omr = value;
  }

  /**
   * Legt den Wert der oms-Eigenschaft fest.
   * 
   * @param value allowed object is {@link OMS }
   * 
   */
  public void setOMS(OMS value) {
    this.oms = value;
  }

  /**
   * Legt den Wert der omstr-Eigenschaft fest.
   * 
   * @param value allowed object is {@link OMSTR }
   * 
   */
  public void setOMSTR(OMSTR value) {
    this.omstr = value;
  }

  /**
   * Legt den Wert der omv-Eigenschaft fest.
   * 
   * @param value allowed object is {@link OMV }
   * 
   */
  public void setOMV(OMV value) {
    this.omv = value;
  }

  /**
   * Legt den Wert der version-Eigenschaft fest.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setVersion(String value) {
    this.version = value;
  }

}
