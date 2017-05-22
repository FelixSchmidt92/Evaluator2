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
	 * This Function checks if the OMA is COMPLETELY a Terminal (including all Child Nodes and itself)
	 * If thats the Case it returns itself.
	 * If Not the Function evaluate() will be called to handle the evaluation
	 * 
	 * @return an OpenMathObject<?> Terminal always
	 */
	@Override
	public OpenMathObject<?> getValue() {
		if (isTerminal()){
			return this;
		}
		return evaluate();
	}
	
	/**
	 * This Function does all the logic to evaluate child Nodes first.
	 * Then the parent Node. After Evaluation this Function returns always 
	 * an Terminal Node. If this OMA is a Terminal it returns itself.
	 * If not return the Function Result (which should always be an Result!)
	 * 
	 * 
	 * @see OpenMathTerminalLexicon where OMA can check if itself is a Terminal
	 * @return An OpenMathObject<?> Terminal
	 */
	@Override
	protected OpenMathObject<?> evaluate(){
		//First evaluate childs. Terminals give themselfes to parent.
		List<OpenMathObject<?>> evalChilds = new LinkedList<>();
		for(OpenMathObject<?> child : childs){
			evalChilds.add(child.evaluate());
		}
		
		if(OpenMathTerminalLexicon.TerminalCheck(cd, name)){	
			// OMA is Terminal return new OMA Terminal
			return new OpenMathApplication(cd, name, evalChilds);
		}else{
			// OMA is not a Terminal return result of Function as Terminal
			// TODO Getting another Terminal as return  
			// TODO TestCase for this Function
			// Look here up which cd to use (maybe one in Evaluator) and which name it has
			// then call the specific function  for this specific cd name combination
			// and give this function ALWAYS the evalChilds-List
			return null;
		}
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
	 * Checks First itself if it is a Terminal, then the Childs if they are Terminals
	 * 
	 * @see OpenMathTerminalLexicon where OMA can check if itseld is a Terminal
	 * @return true If Itself and all childs are Terimals
	 */
	@Override
	public boolean isTerminal(){
		if(OpenMathTerminalLexicon.TerminalCheck(cd, name) && areChildsTerminals()){
			return true;
		}
		return false;
	}
	
	/**
	 * Check if all childs are Terminals
	 * 
	 * @return true if all childs are Terminals
	 */
	private boolean areChildsTerminals(){
		for (OpenMathObject<?> child  :childs){
			if(!child.isTerminal()){
				return false;		
			}
		}
		return true;
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
