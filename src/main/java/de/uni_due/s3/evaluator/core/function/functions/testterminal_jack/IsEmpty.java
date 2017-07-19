package de.uni_due.s3.evaluator.core.function.functions.testterminal_jack;

import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.functionData.OMSymbol;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.jaxb.OMV;

/**
 * OMI OMF is not Empty, so they return always false
 * 
 * isEmpty returns true if one Oo all Elements in this argument is empty.
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
		
		if(obj instanceof OMSTR){
			isEmpty = ((OMSTR)obj).getContent().isEmpty();
		}
		//Here are OMS defined which are empty
		if(obj instanceof OMS){
			if (((OMS)obj).equals(OMSymbol.SET1_EMPTYSET))
				isEmpty = true;
			if (((OMS)obj).equals(OMSymbol.EDITOR1_INPUT_BOX))
				isEmpty = true;
		}
		if(obj instanceof OMV){
			isEmpty = ((OMV)obj).getName().isEmpty();
		}
		
		if(obj instanceof OMA){
			OMA oma = (OMA) obj;
			
			if (oma.getOmel().get(0).equals(OMSymbol.SET1_SET) && oma.getOmel().size() == 1){
				System.out.println("dsa");
				isEmpty = true;
			}else{
				return OMSymbol.LOGIC1_FALSE; // NonEmpty-Set return False directly!
			}
			
			for (int i = 0; i < oma.getOmel().size(); i++){
				ArrayList<Object> newArgs = new ArrayList<>();
				newArgs.add(oma.getOmel().get(i));
				OMS oms = (OMS)this.execute(newArgs);
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
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	protected int maxArgs() {
		// TODO Auto-generated method stub
		return 1;
	}

}
