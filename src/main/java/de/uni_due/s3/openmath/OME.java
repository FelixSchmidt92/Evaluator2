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
 *       &lt;sequence>
 *         &lt;element ref="{http://www.openmath.org/OpenMath}OMS"/>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;group ref="{http://www.openmath.org/OpenMath}omel"/>
 *           &lt;element ref="{http://www.openmath.org/OpenMath}OMFOREIGN"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.openmath.org/OpenMath}common.attributes"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"oms", "omsOrOMVOrOMI"})
@XmlRootElement(name = "OME")
public class OME {

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
   *       &lt;sequence>
   *         &lt;element ref="{http://www.openmath.org/OpenMath}OMATP"/>
   *         &lt;group ref="{http://www.openmath.org/OpenMath}omel"/>
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
  @XmlType(name = "", propOrder = {"omatp", "oms", "omv", "omi", "omb", "omstr", "omf", "oma",
      "ombind", "ome", "omattr", "omr"})
  public static class OMATTR {

    @XmlElement(name = "OMATP", required = true)
    protected OMATP omatp;
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
     * Ruft den Wert der omatp-Eigenschaft ab.
     * 
     * @return possible object is {@link OMATP }
     * 
     */
    public OMATP getOMATP() {
      return omatp;
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
     * Legt den Wert der omatp-Eigenschaft fest.
     * 
     * @param value allowed object is {@link OMATP }
     * 
     */
    public void setOMATP(OMATP value) {
      this.omatp = value;
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

  }

  @XmlElement(name = "OMS", required = true)
  protected OMS oms;
  @XmlElements({@XmlElement(name = "OMS", type = OMS.class),
      @XmlElement(name = "OMV", type = OMV.class), @XmlElement(name = "OMI", type = OMI.class),
      @XmlElement(name = "OMB", type = OMB.class), @XmlElement(name = "OMSTR", type = OMSTR.class),
      @XmlElement(name = "OMF", type = OMF.class), @XmlElement(name = "OMA", type = OMA.class),
      @XmlElement(name = "OMBIND", type = OMBIND.class),
      @XmlElement(name = "OME", type = OME.class),
      @XmlElement(name = "OMATTR", type = OME.OMATTR.class),
      @XmlElement(name = "OMR", type = OMR.class),
      @XmlElement(name = "OMFOREIGN", type = OMFOREIGN.class)})
  protected List<Object> omsOrOMVOrOMI;

  @XmlAttribute(name = "id")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  @XmlSchemaType(name = "ID")
  protected String id;

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
   * Ruft den Wert der oms-Eigenschaft ab.
   * 
   * @return possible object is {@link OMS }
   * 
   */
  public OMS getOMS() {
    return oms;
  }

  /**
   * Gets the value of the omsOrOMVOrOMI property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the omsOrOMVOrOMI property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getOMSOrOMVOrOMI().add(newItem);
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
  public List<Object> getOMSOrOMVOrOMI() {
    if (omsOrOMVOrOMI == null) {
      omsOrOMVOrOMI = new ArrayList<Object>();
    }
    return this.omsOrOMVOrOMI;
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
   * Legt den Wert der oms-Eigenschaft fest.
   * 
   * @param value allowed object is {@link OMS }
   * 
   */
  public void setOMS(OMS value) {
    this.oms = value;
  }

}
