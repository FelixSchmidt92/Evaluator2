package de.uni_due.s3.evaluator.function.arith1;

import java.util.List;

import de.uni_due.s3.evaluator.function.Function;
import de.uni_due.s3.openmath.OMI;

public class Plus extends Function{


	@Override
	protected Object execute(List<Object> arguments) {
		Object left = arguments.get(0);
		Object right = arguments.get(1);
		

		
		if (left instanceof OMI && right instanceof OMI){
			OMI omi = new OMI();  //FIXME dlux only evaluate in CAS!
			
			int l = Integer.valueOf(((OMI)left).getValue());
			int r = Integer.valueOf(((OMI)right).getValue());

			omi.setValue(String.valueOf(r + l));
			return omi;
		}
		
		
		return null;  //FIXME dlux die execute Methode bei Komplexen Funktionen als "deligierer"
					  //verwenden, so das in der der exec nur typcheck ist und entsprechende (private) methode aufruft
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
