package de.uni_due.s3.evaluator2.core.visitor.syntax;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.visitor.OMToSyntaxVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.jaxb.OMV;
import de.uni_due.s3.openmath.omutils.OMCreator;

public class OMVariableVisitor extends OMToSyntaxVisitor<Set<OMV>> {

	private static OMVariableVisitor visitor;
	
	private OMVariableVisitor() throws EvaluatorException { }

	public static OMToSyntaxVisitor<Set<OMV>> getInstance() throws EvaluatorException {
		if (visitor != null) {
			return visitor;
		}
		visitor = new OMVariableVisitor();
		return visitor;
	}
	
	@Override
	protected Set<OMV> visit(OMF omf) {
		Set<OMV> omvSet = new HashSet<>();
		return omvSet;
	}

	@Override
	protected Set<OMV> visit(OMI omi) {
		Set<OMV> omvSet = new HashSet<>();
		return omvSet;
	}

	@Override
	protected Set<OMV> visit(OMSTR omstr) throws NoRepresentationAvailableException {
		Set<OMV> omvSet = new HashSet<>();
		omvSet.addAll(getVariables(omstr.getContent()));
		return omvSet;
	}

	@Override
	protected Set<OMV> visit(OMV omv) throws NoRepresentationAvailableException {
		Set<OMV> omvSet = new HashSet<>();
		omvSet.add(omv);
		return omvSet;
	}

	@Override
	protected Set<OMV> getSyntaxRepresentationForFunction(Function function, OMS oms, List<Object> omel) {
		Set<OMV> omvSet = new HashSet<>();
		for (Object omObj : omel) {
			try {
				omvSet.addAll(visit(omObj));
			} catch (EvaluatorException e) {
				//do nothing
			}
		}
		return omvSet;
	}

	private final static Pattern variable = Pattern.compile("(^[a-zA-Z])|([^a-zA-Z][a-zA-Z]([^a-zA-Z]|$))");

	public static Set<OMV> getVariables(String polynomial) {
		Set<OMV> result = new HashSet<OMV>();
		Matcher m = variable.matcher(polynomial);
		while (m.find()) {
			String var = m.group();
			if (var.length() == 1)
				// if it is just one letter
				result.add(OMCreator.createOMV(var));
			else if (var.length() == 2)
				// if there are two symbols, the last one is the variable
				result.add(OMCreator.createOMV(var.substring(1, 2)));
			else
				// if there are three symbols, the one in the middle is the
				// variable
				result.add(OMCreator.createOMV(var.substring(1, 2)));
		}
		return result;

	}
}
