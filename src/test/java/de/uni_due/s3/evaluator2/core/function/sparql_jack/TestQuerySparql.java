package de.uni_due.s3.evaluator2.core.function.sparql_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.core.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestQuerySparql extends TestFunctionAbstract{

	@Test
	public void testQuerySparqlTupleList() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("querySparql('https://query.wikidata.org/sparql', 'PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> PREFIX wdt: <http://www.wikidata.org/prop/direct/> PREFIX wikibase: <http://wikiba.se/ontology#> PREFIX wd: <http://www.wikidata.org/entity/> PREFIX bd: <http://www.bigdata.com/rdf#> SELECT DISTINCT ?cityLabel ?prov ?city WHERE {{?city wdt:P31 wd:Q515.} UNION {?city wdt:P31 wd:Q839954.} ?city wdt:P131 ?prov. ?prov wdt:P31 wd:Q182547. ?prov rdfs:label ?provL. ?city rdfs:label ?cityL. FILTER (langMatches( lang(?provL), \"de\" ) ) FILTER (langMatches( lang(?cityL), \"de\" ) ) SERVICE wikibase:label { bd:serviceParam wikibase:language \"de\". }}')", null, null);
		Object result = OMToResultVisitor.getInstance().visit(omobj);
		assertEquals(true, OMTypeChecker.isOMAWithSymbol(result,OMSymbol.LIST1_LIST));
		OMA resultList = (OMA)result;
		for (Object o : new ArrayList<>(resultList.getOmel().subList(1, resultList.getOmel().size()))) {
			assertEquals(true, OMTypeChecker.isOMAWithSymbol(o, OMSymbol.LIST1_LIST));
		}
	}
	
	@Test
	public void testQuerySparqlSingleTuple() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("querySparql('https://query.wikidata.org/sparql', 'PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> PREFIX wdt: <http://www.wikidata.org/prop/direct/> PREFIX wikibase: <http://wikiba.se/ontology#> PREFIX wd: <http://www.wikidata.org/entity/> PREFIX bd: <http://www.bigdata.com/rdf#> SELECT DISTINCT ?cityLabel ?prov ?city WHERE {{?city wdt:P31 wd:Q515.} UNION {?city wdt:P31 wd:Q839954.} ?city wdt:P131 ?prov. ?prov wdt:P31 wd:Q182547. ?prov rdfs:label ?provL. ?city rdfs:label ?cityL. FILTER (langMatches( lang(?provL), \"de\" ) ) FILTER (langMatches( lang(?cityL), \"de\" ) ) SERVICE wikibase:label { bd:serviceParam wikibase:language \"de\". }} LIMIT 1')", null, null);
		Object result = OMToResultVisitor.getInstance().visit(omobj);
		assertEquals(true, OMTypeChecker.isOMAWithSymbol(result, OMSymbol.LIST1_LIST));
	}
	
	@Test
	public void testQuerySparqlStringList() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("querySparql('https://query.wikidata.org/sparql', 'PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> PREFIX wdt: <http://www.wikidata.org/prop/direct/> PREFIX wikibase: <http://wikiba.se/ontology#> PREFIX wd: <http://www.wikidata.org/entity/> PREFIX bd: <http://www.bigdata.com/rdf#> SELECT ?cityLabel WHERE {{?city wdt:P31 wd:Q515.} UNION {?city wdt:P31 wd:Q839954.} ?city wdt:P131 ?prov. ?prov wdt:P31 wd:Q182547. ?prov rdfs:label ?provL. ?city rdfs:label ?cityL. FILTER (langMatches( lang(?provL), \"de\" ) ) FILTER (langMatches( lang(?cityL), \"de\" ) ) SERVICE wikibase:label { bd:serviceParam wikibase:language \"de\". }}')", null, null);
		Object result = OMToResultVisitor.getInstance().visit(omobj);
		assertEquals(true, OMTypeChecker.isOMAWithSymbol(result, OMSymbol.LIST1_LIST));
		OMA resultList = (OMA)result;
		for (Object o : new ArrayList<>(resultList.getOmel().subList(1, resultList.getOmel().size()))) {
			assertEquals(true, OMTypeChecker.isOMSTR(o));
		}
	}
	
	@Test
	public void testQuerySparqlSingleString() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("querySparql('https://query.wikidata.org/sparql', 'PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> PREFIX wdt: <http://www.wikidata.org/prop/direct/> PREFIX wikibase: <http://wikiba.se/ontology#> PREFIX wd: <http://www.wikidata.org/entity/> PREFIX bd: <http://www.bigdata.com/rdf#> SELECT ?cityLabel WHERE {{?city wdt:P31 wd:Q515.} UNION {?city wdt:P31 wd:Q839954.} ?city wdt:P131 ?prov. ?prov wdt:P31 wd:Q182547. ?prov rdfs:label ?provL. ?city rdfs:label ?cityL. FILTER (langMatches( lang(?provL), \"de\" ) ) FILTER (langMatches( lang(?cityL), \"de\" ) ) SERVICE wikibase:label { bd:serviceParam wikibase:language \"de\". }} LIMIT 1')", null, null);
		Object result = OMToResultVisitor.getInstance().visit(omobj);
		assertEquals(true, OMTypeChecker.isOMSTR(result));
	}
	
	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testDeterminantWithZeroParam() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("querySparql()", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testDeterminantWithWrongArguments() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("querySparql(1,2)", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}
	
}
