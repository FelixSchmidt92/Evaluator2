package de.uni_due.s3.evaluator2.r;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.RList;

/**
 * This is the R to OMOBJ Visitor
 * 
 * It converts the REXP-Object into a OpenMath XML Object
 * 
 * Currently this Visitor supports Floats, Integers, Strings, Vectors, Matrices,
 * Lists, and Booleans
 * 
 * Expressions, Complex Numbers and Symbols currently cannot be parsed back to
 * OpenMath XML's
 * 
 * 
 * @author dlux
 *
 */
public class RToOMOBJVisitor {

	/**
	 * The rexp argument which R returns after evaluation will be parsed with this
	 * method into OpenMath-XML
	 * 
	 * @param rexp
	 *            the evaluated Expression returned from R (The untouched
	 *            REXP-Object)
	 * @return a String containing the OpenMath-XML
	 */
	public String visit(REXP rexp) {
		try {

			if (rexp.isList()) { // It is a List
				return visitList(rexp);

			} else if (rexp.length() != 1) { // it is a Vector or a Matrix

				if (rexp._attr() != null) { // Attribute list is not empty , something like matrix() is the Result
					return visitMatrixNumeric(rexp);
				} else {
					if (rexp.isNumeric()) { // A Combine Function or a Vector is the Result
						return visitVectorNumeric(rexp);
					} else {
						throw new REXPMismatchException(rexp, "Length is != 1 and REXP is NOT Numeric");
					}
				}

			} else if (rexp.isNumeric()) { // It is Double or Integer
				return visitIntDouble(rexp);

			} else if (rexp.isString()) { // It is String
				return visitString(rexp);

			} else if (rexp.isLogical()) { // It is True or False
				return visitBoolean(rexp);

			} else if (rexp.isComplex()) {
				throw new REXPMismatchException(rexp, "REXP is Complex");

			} else if (rexp.isSymbol()) {
				throw new REXPMismatchException(rexp, "REXP is a Symbol");

			} else {
				throw new REXPMismatchException(rexp, "REXP is Unknown");
			}
		} catch (REXPMismatchException e) {
			return "<OME><OMS cd=\"moreerrors\" name=\"unexpected\"/><OMSTR>" + e.getMessage() + ". The REXP-Object: "
					+ rexp.toString() + "</OMSTR></OME>";
		}
	}

	/**
	 * Parsing REXP with Integegers or Doubles into OpenMath-XML
	 * @param rexp the rexp
	 * @return OpenMath-XML
	 * @throws REXPMismatchException
	 */
	private String visitIntDouble(REXP rexp) throws REXPMismatchException {
		Double result = rexp.asDouble();

		if (result == Math.floor(result)) {
			return "<OMI>" + result.intValue() + "</OMI>";
		} else {
			return "<OMF dec=\"" + result + "\"/>";
		}
	}

	
	/**
	 * Parsing REXP with a StringResult into OpenMath-XML
	 * @param rexp the rexp
	 * @return OpenMath-XML
	 * @throws REXPMismatchException
	 */
	private String visitString(REXP rexp) throws REXPMismatchException {
		String result = rexp.asString();
		return "<OMSTR>" + result + "</OMSTR>";
	}

	
	/**
	 * Parsing REXP List (RList) into OpenMath-XML
	 * @param rexp the rexp
	 * @return OpenMath-XML
	 * @throws REXPMismatchException
	 */
	private String visitList(REXP rexp) throws REXPMismatchException {
		String omList = "<OMA><OMS cd=\"list1\" name=\"list\"/>";
		RList list = rexp.asList();

		for (int i = 0; i < list.size(); i++) {
			omList += visit((REXP) list.get(i));
		}

		omList += "</OMA>";
		return omList;

	}

	
	/**
	 * Parsing REXP with Boolean (as String TRUE or FALSE) into OpenMath-XML
	 * @param rexp the rexp
	 * @return OpenMath-XML
	 * @throws REXPMismatchException
	 */
	private String visitBoolean(REXP rexp) throws REXPMismatchException {
		String bool = rexp.asString();
		if (bool.equals("TRUE")) {
			return "<OMS cd=\"logic1\" name=\"true\"/>";

		} else {
			return "<OMS cd=\"logic1\" name=\"false\"/>";
		}

	}

	
	/**
	 * Parsing REXP a Vector (Only containing Numbers) into OpenMath-XML
	 * @param rexp the rexp
	 * @return OpenMath-XML
	 * @throws REXPMismatchException
	 */
	private String visitVectorNumeric(REXP rexp) throws REXPMismatchException {
		double[] results = rexp.asDoubles();
		String omList = "<OMA><OMS cd=\"linalg2\" name=\"vector\"/>";

		for (int i = 0; i < results.length; i++) {
			if (results[i] == Math.floor(results[i])) {
				omList += "<OMI>" + new Double(results[i]).intValue() + "</OMI>";
			} else {
				omList += "<OMF dec=\"" + results[i] + "\"/>";
			}
		}

		omList += "</OMA>";
		return omList;
	}

	
	/**
	 * Parsing REXP Matrix (containing only Numbers) into OpenMath-XML
	 * @param rexp the rexp
	 * @return OpenMath-XML
	 * @throws REXPMismatchException
	 */
	private String visitMatrixNumeric(REXP rexp) throws REXPMismatchException {
		double[][] matrix = rexp.asDoubleMatrix();
		String omList = "<OMA><OMS cd=\"linalg2\" name=\"matrix\"/>";
		for (int y = 0; y < matrix.length; y++) {
			String omPartList = "<OMA><OMS cd=\"linalg2\" name=\"matrixrow\"/>";
			for (int x = 0; x < matrix[y].length; x++) {
				if (matrix[y][x] == Math.floor(matrix[y][x])) {
					omPartList += "<OMI>" + new Double(matrix[y][x]).intValue() + "</OMI>";
				} else {
					omPartList += "<OMF dec=\"" + matrix[y][x] + "\"/>";
				}
			}
			omList += omPartList + "</OMA>";
		}
		omList += "</OMA>";
		return omList;
	}
}
