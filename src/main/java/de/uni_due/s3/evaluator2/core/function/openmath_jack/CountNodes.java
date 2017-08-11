package de.uni_due.s3.evaluator2.core.function.openmath_jack;


import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.jaxb.OMV;
import de.uni_due.s3.openmath.omutils.OMCreator;

/**
 * This Class counts all Nodes.
 * In an OMA the OMA and the leading OMS is also counted as a Node
 * 
 * @author dlux
 */
public class CountNodes extends Function {

	@Override
	protected Object execute(List<Object> arguments) {
		Object obj = arguments.get(0);

		if (obj instanceof OMI || obj instanceof OMF || obj instanceof OMSTR || obj instanceof OMS
				|| obj instanceof OMV) {
			return OMCreator.createOMI(1);
			
		} else { // obj has to be an OMA
			OMA oma = (OMA) obj; 
			int counter = 1; // 1  OMA is a Node 
			
			for (int i = 0; i < oma.getOmel().size(); i++){
				ArrayList<Object> newArgs = new ArrayList<>();
				newArgs.add((oma.getOmel().get(i)));
				
				OMI result = (OMI) this.execute(newArgs); //Get count of all Childs
				counter += Integer.parseInt(result.getValue());
			}
			return OMCreator.createOMI(counter);
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

	@Override
	public boolean argumentsShouldBeEvaluated() {
		return false;
	}

}
