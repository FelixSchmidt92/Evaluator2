package de.uni_due.s3.evaluator.tree;


import java.util.List;


/**
 * TODO
 * @author dlux
 */
public class EApplication extends EObject{

	private List<EObject> childs;
	private EFunction efunction;

	/*
	 * 
	 * TODO eFunction
	 */
	public EApplication(EFunction efunction, List<EObject> childs) {
		this.childs = childs;
		this.efunction = efunction;
	}
	
	
	@Override
	public EObject evaluate(){
		return null;
	}


	@Override
	protected String getPartialXML() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected String getSageSyntax() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected String getRSyntax() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected String getSymjaSyntax() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<EObject> getChilds(){
		return childs;
	}
}
