package de.uni_due.s3.evaluator.core.function.functions.string_jack;

import java.util.List;

import org.openmath.omutils.OMCreator;
import org.openmath.openmath.OMI;
import org.openmath.openmath.OMSTR;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;

public class CharAt extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws FunctionInvalidArgumentException {
		if (arguments.get(0) instanceof OMSTR && arguments.get(1) instanceof OMI){
			
			String string = ((OMSTR)arguments.get(0)).getContent();
			int pos = Integer.parseInt(((OMI)arguments.get(1)).getValue());
			
			if (string.length() <= pos || pos < 0){
				throw new FunctionInvalidArgumentException(this, "Second Argument of charAt is invalid. Not in Range of String");
			}
			
			char chr = string.charAt(pos);
			
			return OMCreator.createOMSTR(String.valueOf(chr));
			
			
		}else{
			throw new FunctionInvalidArgumentException(this, "Arguments in CharAt needs to be: String, Integer");
		}
		
	}

	@Override
	protected int minArgs() {
		return 2;
	}

	@Override
	protected int maxArgs() {
		return 2;
	}

}
