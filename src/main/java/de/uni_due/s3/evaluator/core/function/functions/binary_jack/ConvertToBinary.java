package de.uni_due.s3.evaluator.core.function.functions.binary_jack;

import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.NumberUtils;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.omutils.OMCreator;

/**
 * This Class returns the BinaryRepresentation of the OMF or OMI
 * 
 * Note:
 * 	It returns a String which represents this Argument in Binary.
 * 
 * 	By Negative Double Values (OMF) 64 Bits are returned always. (See more at IEEE_754 double-Value)
 *  (By positive the leading Zeros are trimmed!, but not shorter than 54 bits)
 * 
 *  By negative Integer-Values 32 Bits are returned always. (See more at two's complement integer) 
 *  (By positive the leading Zeros are trimmed!)
 * 
 * @author dlux
 */
public class ConvertToBinary extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws FunctionInvalidArgumentTypeException {
		Double value;
		try {
			value = NumberUtils.convertOMIOMFToDouble(arguments.get(0));
		} catch (InputMismatchException e) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)Integer|(0)Float");
		}
		
		if ((value == Math.floor(value)) && !Double.isInfinite(value)){ 
			//value is Int!
			OMSTR res = OMCreator.createOMSTR(Integer.toBinaryString(value.intValue()));
			
			return res;
			
		}else{
			//value is float!
			OMSTR res = OMCreator.createOMSTR(Long.toBinaryString(Double.doubleToRawLongBits(value)));
			return res;
		}
	}

	@Override
	protected int minArgs() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	protected int maxArgs() {
		// TODO Auto-generated method stub
		return 1;
	}

}
