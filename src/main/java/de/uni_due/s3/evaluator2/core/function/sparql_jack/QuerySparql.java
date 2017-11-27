package de.uni_due.s3.evaluator2.core.function.sparql_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.sparql.Sparql;
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
		// with minArgs and maxArgs it is guaranteed, that arguments has this length.
		
		// getStringSyntax throws automatically the FunctionInvalidArgumentTypeException
		// if String is not representable in String
		String endpoint = getStringSyntax(arguments.get(0));
		String query = getStringSyntax(arguments.get(1));
		
		if (endpoint.isEmpty() || query.isEmpty())
			throw new FunctionInvalidArgumentException(this, "Invalid argument inputs for querySparql(). Malformed argument. Both endpoint and query must not be empty strings.");
		
		if (!(endpoint.startsWith("http://") || endpoint.startsWith("https://")))
			throw new FunctionInvalidArgumentException(this, "Invalid argument inputs for querySparql(). Sparql service endpoint input requires http(s):// as prefix.");

		if (!(query.matches(".*\\bSELECT\\b.*") && query.matches(".*\\bWHERE\\b.*")))
			throw new FunctionInvalidArgumentException(this, "Invalid argument inputs for querySparql(). Missing SELECT or WHERE statement.");
		
		if (query.contains("*"))
			throw new FunctionInvalidArgumentException(this, "Invalid argument inputs for querySparql(). Invalid SELECT statement. *-Operator is not permitted.");
			
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
