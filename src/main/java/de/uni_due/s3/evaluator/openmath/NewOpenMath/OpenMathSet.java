package de.uni_due.s3.evaluator.openmath.NewOpenMath;

import java.util.List;

/**
 * This Class is a Terminal for Sets
 * It returns a List of OpenMathTerminals 
 * 
 * In a set can be all sorts of Terminals (including other sets)
 * 
 * @author dlux
 */
public class OpenMathSet extends OpenMathTerminal<List<OpenMathTerminal<?>>>{

	public OpenMathSet(List<OpenMathTerminal<?>> values){
		this.value = values;
	}
	
	
	@Override
	protected String getPartialXML() {
		String xml = "";
		for(OpenMathTerminal<?> omt : value){
			xml += omt.getPartialXML();
		}
		return "<OMA><OMS cd=\"set1\" name=\"set\"/>" + xml + "</OMA>";
	}


	@Override
	public int getNodesCount() {
		int count = 1;
		for(OpenMathTerminal<?> omt : value){
			count += omt.getNodesCount();
		}
		return count;
	}
}
