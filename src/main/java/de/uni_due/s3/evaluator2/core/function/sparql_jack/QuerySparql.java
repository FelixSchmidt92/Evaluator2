package de.uni_due.s3.evaluator2.core.function.sparql_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.sparql.Sparql;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Returns result(s) from any existing sparql endpoint of a query consisting of
 * following argument parameters:
 *
 * @param string1
 *            Sparql Endpoint URL: http(s)://...
 * @param string2
 *            Minimal required SPARQL Query syntax: SELECT @param WHERE { conditions }
 *            If more than one @param in the SELECT-clause: Algorithm always
 *            takes first @param . *-Operator in the SELECT-clause is NOT
 *            permitted.
 * 
 * @author Michael Striewe
 */
public class QuerySparql extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		if (arguments.size() != 2)
			throw new FunctionInvalidNumberOfArgumentsException(this, "Invalid argument inputs for querySparql(). Missing argument, query or Select result statement.");
		
		if (!(OMTypeChecker.isOMSTR(arguments.get(0)) && OMTypeChecker.isOMSTR(arguments.get(1))))
			throw new FunctionInvalidArgumentTypeException("Invalid argument inputs for querySparql(). Malformed argument. Both endpoint and query must be strings.");

		String endpoint = ((OMSTR)arguments.get(0)).getContent();
		String query = ((OMSTR)arguments.get(1)).getContent();
		
		if (endpoint.isEmpty() || query.isEmpty())
			throw new CasEvaluationException("Invalid argument inputs for querySparql(). Malformed argument. Both endpoint and query must not be empty strings.");
		
		if (!(endpoint.startsWith("http://") || endpoint.startsWith("https://")))
			throw new CasEvaluationException("Invalid argument inputs for querySparql(). Sparql service endpoint input requires http(s):// as prefix.");

		if (!(query.matches(".*\\bSELECT\\b.*") && query.matches(".*\\bWHERE\\b.*")))
			throw new CasEvaluationException("Invalid argument inputs for querySparql(). Missing SELECT or WHERE statement.");
		
		if (query.contains("*"))
			throw new CasEvaluationException("Invalid argument inputs for querySparql(). Invalid SELECT statement. *-Operator is not permitted.");
			
		return Sparql.evaluate(query + " #ManualQuery: Service:" + endpoint);
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
