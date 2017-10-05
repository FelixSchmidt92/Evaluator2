package de.uni_due.s3.evaluator2.core.function.testterminal_jack;

import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.jaxb.OMV;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;

/**
 * OMI OMF are not Empty, so they return always false
 * 
 * isEmpty returns true if one or all Elements in this argument is empty.
 * It returns falls otherwise.
 * 
 * @author dlux
 *
 */
public class IsEmpty extends Function {

	@Override
	protected Object execute(List<Object> arguments) {
		
		boolean isEmpty = false;
		Object obj = arguments.get(0);
		
		//OMI OMF are Non-Empty so no if-clauses for them
		
		
		//OMV/OMSTR without using the TypeChecker, because the TypeChecker returns false by empty OMV/OMSTR 's
		if(obj instanceof OMSTR){
			//trimming before checking!
			isEmpty = ((OMSTR)obj).getContent().trim().isEmpty();
		}
		if(obj instanceof OMV){
			isEmpty = ((OMV)obj).getName().isEmpty();
		}
		
		//Here are OMS defined which are empty, same Reason, not using TypeChecker as in OMV/OMSTR
		if(obj instanceof OMS){
			if (((OMS)obj).equals(OMSymbol.SET1_EMPTYSET))
				isEmpty = true;
			if (((OMS)obj).equals(OMSymbol.EDITOR1_INPUT_BOX))
				isEmpty = true;
		}

		
		if(OMTypeChecker.isOMA(obj)){
			OMA oma = (OMA) obj;
			
			//Special Case Set!
			if (OMTypeChecker.isOMAWithSymbol(obj, OMSymbol.SET1_SET) && oma.getOmel().size() == 1){
				return OMSymbol.LOGIC1_TRUE;
			}
			else if (OMTypeChecker.isOMAWithSymbol(obj, OMSymbol.SET1_SET) && oma.getOmel().size() > 1){
				return OMSymbol.LOGIC1_FALSE;
			}
			
			
			//All other OMAs especially for Matrix and MatrixRow
			for (int i = 0; i < oma.getOmel().size(); i++){
				ArrayList<Object> newArgs = new ArrayList<>();
				newArgs.add(oma.getOmel().get(i));
				OMS oms = (OMS)this.execute(newArgs); //call this function again, if something empty --> return Empty!
				if (oms.equals(OMSymbol.LOGIC1_TRUE)){
					isEmpty = true;
					break; // Stop if something empty was found return empty then!
				}
			}
		}
		if (isEmpty){
			return OMSymbol.LOGIC1_TRUE;
		}else{
			return OMSymbol.LOGIC1_FALSE;
		}
	}

	@Override
	protected int minArgs() {
		return 1;
	}

	@Override
	protected int maxArgs() {
		return 1;
	}

}
