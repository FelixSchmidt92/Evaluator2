package de.uni_due.s3.evaluator.openmath.NewOpenMath;

import java.util.LinkedList;
import java.util.List;


/**
 * This Class is an OpenMathSymbol combined with an OpenMathApplication
 * It is also a Terminal and not a Terminal. 
 * 
 * Set, Matrix, Vector are Terminals The terminals for OMA are all 
 * specified in @see OpenMathTerminalLexicon
 * 
 * Arith1 Plus ... are not, they are calculable, so the Functions are called 
 * from this OpenMathObject
 * 
 * @author dlux
 */
public class OpenMathApplication extends OpenMathObject<OpenMathObject<?>>{

	private List<OpenMathObject<?>> childs;
	
	/**
	 * Constructor of OMA
	 * 
	 * @param cd as Content Dictionary
	 * @param name as Name in Content Dictionary
	 * @param linkedList as an List of childs for this Application NOT NULL
	 */
	public OpenMathApplication(String cd, String name, List<OpenMathObject<?>> linkedList){
		this.cd = cd;
		this.name = name;
		this.childs = linkedList;
	}

	/**
	 * This method returns Always an OpenMathTerminal!
	 * This method calls evaluate();
	 * 
	 * @return an OpenMathTerminal<?> Terminal, always
	 */
	@Override
	public OpenMathTerminal<?> getValue() {
		return evaluate();
	}
	
	/**
	 * This Function does all the logic to evaluate child Nodes first.
	 * Then the parent Node. 
	 * (OR checking if it has to be evaluated, e.g for countNodes it is unnecessary)
	 * 
	 * After Evaluation this Function returns always an Terminal Node.
	 * 
	 * @return An OpenMathTerminal<?> Terminal, always
	 * @UnderConstruction This Function makes currently NonSense
	 */
	@Override
	protected OpenMathTerminal<?> evaluate(){

		//First Check if childs has to be evaluated!
		//if (OpenMathSymbols.needsEvaluation){ 	//<-- check for specific cd and name
			//First evaluate childs. OpenMathTerminal give themselfes to parent.
			List<OpenMathObject<?>> evalChilds = new LinkedList<>();
			for(OpenMathObject<?> child : childs){
				evalChilds.add(child.evaluate());
			}
		//}else{
			//No need to evaluate, so set evalchilds = childs
			evalChilds = childs;
		//}


		// Look here up which cd to use (maybe one in Evaluator) and which name it has
		// then call the specific function with OpenMathSymbol for this specific cd, name combination
		// and give this function ALWAYS the evalChilds-List
			
		// TODO returning the result (OpenMathTerminal)
		// TODO TestCase for this Function
		return null;
	}

	@Override
	protected String getPartialXML() {
		String temp = "";
		for (OpenMathObject<?> child: childs){
			temp += child.getPartialXML();
		}
		return "<OMA><OMS cd=\""+ cd +"\" name=\"" + name + "\"/>" + temp + "</OMA>"; 
	}
	
	/**
	 * Counts the Nodes (including itself)
	 * 
	 * @return Number Of Nodes 
	 */
	@Override
	public int getNodesCount(){
		int count = 1;
		for (OpenMathObject<?> child: childs){
			count += child.getNodesCount();
		}
		return count;
	}
	
	/**
	 * Returns if OMA is a Terminal (which is always false)
	 * 
	 * @return false, because OMA is NOT a Terminal
	 */
	@Override
	public boolean isTerminal(){
		return false;
	}
	
	/**
	 * Debugging adding dynamically Children to an OMA 
	 * 
	 * @param omo a new Child
	 */
	public void addChild(OpenMathObject<?> omo){
		childs.add(omo);
	}

	
	/**
	 * This method checks, if this object equals to another Object.
	 * 
	 * @param Object, the Object to check with
	 */
	@Override
	public boolean equals(Object obj){
		if(this == obj)
			return true;
		
		if(obj == null)
			return false;
			
		if (obj.getClass() == this.getClass()){
			if (!((OpenMathApplication)obj).cd.equals(this.cd))
				return false;
			
			if (!((OpenMathApplication)obj).name.equals(this.name))
				return false;
			
			if (((OpenMathApplication)obj).childs.equals(this.childs))
				return true;
		}
		
		return false;
		
	}
}
