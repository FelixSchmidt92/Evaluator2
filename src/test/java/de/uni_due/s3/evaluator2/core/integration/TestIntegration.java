package de.uni_due.s3.evaluator2.core.integration;

import java.util.HashMap;

import javax.xml.bind.JAXBException;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.sage.TestSage;

public abstract class TestIntegration {

	/**
	 * [var=X] Map
	 * <ul>
	 * <li>a -> OMI 0</li>
	 * <li>b -> OMI 1</li>
	 * <li>c -> OMI -1</li>
	 * <li>d -> OMF 1.5</li>
	 * <li>e -> OMF -12.5</li>
	 * <li>f -> OMF 0.0</li>
	 * </ul>
	 */
	protected static HashMap<String, OMOBJ> exerciseVariableMap;

	private static void resetExerciseVariableMap() {
		exerciseVariableMap = new HashMap<String, OMOBJ>();
		try {
			exerciseVariableMap.put("a", OMConverter.toObject("<OMOBJ><OMI>0</OMI></OMOBJ>"));
			exerciseVariableMap.put("b", OMConverter.toObject("<OMOBJ><OMI>1</OMI></OMOBJ>"));
			exerciseVariableMap.put("c", OMConverter.toObject("<OMOBJ><OMI>-1</OMI></OMOBJ>"));
			exerciseVariableMap.put("d", OMConverter.toObject("<OMOBJ><OMF dec=\"1.5\"/></OMOBJ>"));
			exerciseVariableMap.put("e", OMConverter.toObject("<OMOBJ><OMF dec=\"-12.5\"/></OMOBJ>"));
			exerciseVariableMap.put("f", OMConverter.toObject("<OMOBJ><OMF dec=\"0.0\"/></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen",
					e);
		}
	}

	/**
	 * [pos=X] Map
	 * <ul>
	 * <li>1 -> OMI 0</li>
	 * <li>2 -> OMI 1</li>
	 * <li>3 -> OMI -1</li>
	 * <li>4 -> OMF 1.5</li>
	 * <li>5 -> OMF -12.5</li>
	 * <li>6 -> OMF 0.0</li>
	 * </ul>
	 */
	protected static HashMap<Integer, OMOBJ> fillInVariableMap;

	private static void resetFillInVariableMap() {
		fillInVariableMap = new HashMap<Integer, OMOBJ>();
		try {
			fillInVariableMap.put(1, OMConverter.toObject("<OMOBJ><OMI>0</OMI></OMOBJ>"));
			fillInVariableMap.put(2, OMConverter.toObject("<OMOBJ><OMI>1</OMI></OMOBJ>"));
			fillInVariableMap.put(3, OMConverter.toObject("<OMOBJ><OMI>-1</OMI></OMOBJ>"));
			fillInVariableMap.put(4, OMConverter.toObject("<OMOBJ><OMF dec=\"1.5\"/></OMOBJ>"));
			fillInVariableMap.put(5, OMConverter.toObject("<OMOBJ><OMF dec=\"-12.5\"/></OMOBJ>"));
			fillInVariableMap.put(6, OMConverter.toObject("<OMOBJ><OMF dec=\"0.0\"/></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath fillIn Variablen für TestIntegration fehlgeschlagen", e);
		}
	}

	@BeforeClass
	public static void beforeClass() {
		TestSage.initSage();
		resetFillInVariableMap();
		resetExerciseVariableMap();
	}

	@AfterClass
	public static void afterClass() {
	}
}
