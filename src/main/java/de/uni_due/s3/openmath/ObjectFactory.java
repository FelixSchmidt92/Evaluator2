//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation,
// v2.2.8-b130911.1802 generiert
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren.
// Generiert: 2015.02.04 um 03:42:32 PM CET
//


package de.uni_due.s3.openmath;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the de.uni_due.s3.jack2.server.common.schemas.openmath package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the Java representation
 * for XML content. The Java representation of XML content can consist of schema derived interfaces
 * and classes representing the binding of schema type definitions, element declarations and model
 * groups. Factory methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

  private final static QName _OMFOREIGNOMATTR_QNAME =
      new QName("http://www.openmath.org/OpenMath", "OMATTR");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: de.uni_due.s3.jack2.server.common.schemas.openmath
   * 
   */
  public ObjectFactory() {}

  /**
   * Create an instance of {@link OMA }
   * 
   */
  public OMA createOMA() {
    return new OMA();
  }

  /**
   * Create an instance of {@link OMATP }
   * 
   */
  public OMATP createOMATP() {
    return new OMATP();
  }

  /**
   * Create an instance of {@link OMB }
   * 
   */
  public OMB createOMB() {
    return new OMB();
  }

  /**
   * Create an instance of {@link OMBIND }
   * 
   */
  public OMBIND createOMBIND() {
    return new OMBIND();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link OME.OMATTR }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://www.openmath.org/OpenMath", name = "OMATTR",
      scope = OMBIND.class)
  public JAXBElement<OME.OMATTR> createOMBINDOMATTR(OME.OMATTR value) {
    return new JAXBElement<OME.OMATTR>(_OMFOREIGNOMATTR_QNAME, OME.OMATTR.class, OMBIND.class,
        value);
  }

  /**
   * Create an instance of {@link OMBVAR }
   * 
   */
  public OMBVAR createOMBVAR() {
    return new OMBVAR();
  }

  /**
   * Create an instance of {@link OMBVAR.OMATTR }
   * 
   */
  public OMBVAR.OMATTR createOMBVAROMATTR() {
    return new OMBVAR.OMATTR();
  }

  /**
   * Create an instance of {@link OME }
   * 
   */
  public OME createOME() {
    return new OME();
  }

  /**
   * Create an instance of {@link OME.OMATTR }
   * 
   */
  public OME.OMATTR createOMEOMATTR() {
    return new OME.OMATTR();
  }

  /**
   * Create an instance of {@link OMF }
   * 
   */
  public OMF createOMF() {
    return new OMF();
  }

  /**
   * Create an instance of {@link OMFOREIGN }
   * 
   */
  public OMFOREIGN createOMFOREIGN() {
    return new OMFOREIGN();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link OME.OMATTR }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://www.openmath.org/OpenMath", name = "OMATTR",
      scope = OMFOREIGN.class)
  public JAXBElement<OME.OMATTR> createOMFOREIGNOMATTR(OME.OMATTR value) {
    return new JAXBElement<OME.OMATTR>(_OMFOREIGNOMATTR_QNAME, OME.OMATTR.class, OMFOREIGN.class,
        value);
  }

  /**
   * Create an instance of {@link OMI }
   * 
   */
  public OMI createOMI() {
    return new OMI();
  }

  /**
   * Create an instance of {@link OMOBJ }
   * 
   */
  public OMOBJ createOMOBJ() {
    return new OMOBJ();
  }

  /**
   * Create an instance of {@link OMR }
   * 
   */
  public OMR createOMR() {
    return new OMR();
  }

  /**
   * Create an instance of {@link OMS }
   * 
   */
  public OMS createOMS() {
    return new OMS();
  }

  /**
   * Create an instance of {@link OMSTR }
   * 
   */
  public OMSTR createOMSTR() {
    return new OMSTR();
  }

  /**
   * Create an instance of {@link OMV }
   * 
   */
  public OMV createOMV() {
    return new OMV();
  }

}
