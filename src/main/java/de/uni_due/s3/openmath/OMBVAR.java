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
 *       &lt;group ref="{http://www.openmath.org/OpenMath}omvar" maxOccurs="unbounded"/>
 *       &lt;attGroup ref="{http://www.openmath.org/OpenMath}common.attributes"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"omvar"})
@XmlRootElement(name = "OMBVAR")
public class OMBVAR {

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
	 *         &lt;choice>
	 *           &lt;element ref="{http://www.openmath.org/OpenMath}OMV"/>
	 *           &lt;group ref="{http://www.openmath.org/OpenMath}attvar"/>
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
	@XmlType(name = "", propOrder = {"omatp", "omv", "omattr"})
	public static class OMATTR {

		@XmlElement(name = "OMATP", required = true)
		protected OMATP omatp;
		@XmlElement(name = "OMV")
		protected OMV omv;
		@XmlElement(name = "OMATTR")
		protected OMBVAR.OMATTR omattr;
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
			return this.id;
		}

		/**
		 * Ruft den Wert der omatp-Eigenschaft ab.
		 *
		 * @return possible object is {@link OMATP }
		 *
		 */
		public OMATP getOMATP() {
			return this.omatp;
		}

		/**
		 * Ruft den Wert der omattr-Eigenschaft ab.
		 *
		 * @return possible object is {@link OMBVAR.OMATTR }
		 *
		 */
		public OMBVAR.OMATTR getOMATTR() {
			return this.omattr;
		}

		/**
		 * Ruft den Wert der omv-Eigenschaft ab.
		 *
		 * @return possible object is {@link OMV }
		 *
		 */
		public OMV getOMV() {
			return this.omv;
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
		 * @param value allowed object is {@link OMBVAR.OMATTR }
		 *
		 */
		public void setOMATTR(OMBVAR.OMATTR value) {
			this.omattr = value;
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

	@XmlElements({@XmlElement(name = "OMV", type = OMV.class),
		@XmlElement(name = "OMATTR", type = OMBVAR.OMATTR.class)})
	protected List<Object> omvar;

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
		return this.id;
	}

	/**
	 * Gets the value of the omvar property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot. Therefore any
	 * modification you make to the returned list will be present inside the JAXB object. This is why
	 * there is not a <CODE>set</CODE> method for the omvar property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 *
	 * <pre>
	 * getOmvar().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link OMV } {@link OMBVAR.OMATTR }
	 *
	 *
	 */
	public List<Object> getOmvar() {
		if (this.omvar == null) {
			this.omvar = new ArrayList<Object>();
		}
		return this.omvar;
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
