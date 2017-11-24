package de.uni_due.s3.evaluator2.sparql;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.openmath.omutils.OMCreator;

public class Sparql {

	public static Object evaluate(String query) throws CasEvaluationException {
		
		ArrayList<String> resultArray = new ArrayList<String>();
		ResultSet results = null;
		String lang = query.substring(query.indexOf("LangCode:") + 9, query.indexOf("LangCode:") + 11);

		if (query.contains("Service:DBpedia") && query.contains("LangCode:")) {

			if (query.contains("CategoryCode:")) {
				if (lang.equals("en")) {

					results = executeQuery(query, "http://dbpedia.org/sparql");
				} else if (lang.length() == 2) {

					results = executeQuery(query, "http://" + lang + ".dbpedia.org/sparql");
				} else {

					throw new CasEvaluationException("Invalid DBpedia sparql endpoint input.");
				}
			} else {

				results = executeQuery(query, "http://dbpedia.org/sparql");
			}

			if (query.contains("LimitSet:")) {

				String limitString = query.substring(query.indexOf("LimitSet:") + 9, query.length());
				int limit = Integer.parseInt(limitString);
				return getLimitedLiteralResults(results, resultArray, lang, limit);
			}

			return getLiteralResults(results, resultArray, lang);

		} else if ((query.contains("Service:Wikidata") && query.contains("LangCode:"))) {

			results = executeQuery(query, "https://query.wikidata.org/sparql");

			if (query.contains("LimitSet:")) {

				String limitString = query.substring(query.indexOf("LimitSet:") + 9, query.length()).replaceAll("\\s+",
						"");
				int limit = Integer.parseInt(limitString);
				return getLimitedLiteralResults(results, resultArray, lang, limit);
			}

			return getLiteralResults(results, resultArray, lang);

		} else if (query.contains("Service:") && query.contains("#ManualQuery:")) {

			String selectResultList = query
					.substring(query.indexOf("SELECT ") + 7, query.indexOf("WHERE") - 1)
					.replace("?", "").replace("DISTINCT", "").trim();

			String[] selectResultArray = selectResultList.split(" ");
			
			String service = query.substring(query.indexOf("Service:") + 8, query.length());
			results = executeQuery(query, service);

			List<Object> resultList = new ArrayList<>();
			
			for (; results.hasNext();) {
				QuerySolution solution = results.nextSolution();
				List<Object> tupleContent = new ArrayList<>();
				
				for (String selectKey : selectResultArray) {
					try {
						String stringElement = solution.get(selectKey.trim()).toString();
						if (stringElement.contains("@"))
							stringElement = stringElement.substring(0, stringElement.lastIndexOf("@"));
						tupleContent.add(OMCreator.createOMSTR(stringElement));
					} catch (NullPointerException npe) {
						// Can be ignored safely here, because it just means that there
						// is an empty result or no result for a particular select key.
					}
				}
				if (tupleContent.size() > 1) {
					resultList.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, tupleContent));
				} else {
					resultList.add(tupleContent.get(0));
				}
			}

			if (resultList.size() > 1) {
				return OMCreator.createOMA(OMSymbol.LIST1_LIST, resultList);
			} else {
				return resultList.get(0);
			}
		} else if (query.contains("ItemExpression:") && query.contains("LangCode:")) {
			String expression = query.substring(query.indexOf("ItemExpression:") + 15, query.length());
			results = executeQuery(query, "https://query.wikidata.org/sparql");
			if (expression.matches("P\\d+")) {

				throw new CasEvaluationException(
						"Invalid inputs for queryExpressionToWikidataIDItem. Use CASFunction \"queryExpressionToWikidataIDProperty\" instead.");
			} else if (expression.matches("Q\\d+")) {

				return expression;
			} else if (!expression.matches("Q\\d+")) {

				return getResourceResults(results, resultArray, lang);
			} else {

				throw new CasEvaluationException("Invalid inputs for queryExpressionToWikidataIDItem");
			}

		} else if (query.contains("PropExpression:") && query.contains("LangCode:")) {
			String expression = query.substring(query.indexOf("PropExpression:") + 15, query.length());
			results = executeQuery(query, "https://query.wikidata.org/sparql");
			if (expression.matches("Q\\d+")) {

				throw new CasEvaluationException(
						"Invalid inputs for queryExpressionToWikidataIDProperty. Use CASFunction \"queryExpressionToWikidataIDItem\" instead.");
			} else if (expression.matches("P\\d+")) {

				return expression;
			} else if (!expression.matches("P\\d+")) {

				return getResourceResults(results, resultArray, lang);
			} else {

				throw new CasEvaluationException("Invalid inputs for queryExpressionToWikidataIDProperty");
			}
		} else {

			throw new CasEvaluationException("Invalid language code, sparql endpoint service or query.");
		}
	}

	private static ResultSet executeQuery(String queryString, String service) {
		Query query = QueryFactory.create(queryString);
		QueryEngineHTTP queryEngine = QueryExecutionFactory.createServiceRequest(service, query);
		queryEngine.setTimeout(15, TimeUnit.SECONDS);
		ResultSet resultSet = queryEngine.execSelect();
		return resultSet;
	}

	private static Object getResourceResults(ResultSet results, ArrayList<String> resultArray, String lang) {
		for (; results.hasNext();) {
			QuerySolution solution = results.nextSolution();
			String stringElement = solution.getResource("objectLabel").toString()
					.replaceAll("http://www.wikidata.org/entity/", "");
			resultArray.add(stringElement);
		}
		return getUnmodifiedResultString(resultArray);
	}

	private static Object getLiteralResults(ResultSet results, ArrayList<String> resultArray, String lang) {
		for (; results.hasNext();) {
			QuerySolution solution = results.nextSolution();
			String stringElement = solution.getLiteral("objectLabel").toString().replaceAll("@" + lang, "");
			if (!stringElement.matches("Q\\d+")) {
				resultArray.add(stringElement);
			}
		}
		return getResultString(resultArray);
	}

	private static Object getLimitedLiteralResults(ResultSet results, ArrayList<String> resultArray, String lang,
			int limit) throws CasEvaluationException {
		for (; results.hasNext();) {
			QuerySolution solution = results.nextSolution();
			String stringElement = solution.getLiteral("objectLabel").toString().replaceAll("@" + lang, "");
			if (!stringElement.matches("Q\\d+")) {
				resultArray.add(stringElement);
			}
		}
		return getLimitedResultString(resultArray, limit);
	}

	private static Object getUnmodifiedResultString(ArrayList<String> arrayList) {

		List<Object> resultList = new ArrayList<>();
		
		if (!arrayList.isEmpty()) {
			for (String value : arrayList) {
				resultList.add(OMCreator.createOMSTR(value));
			}
		}
		
		return OMCreator.createOMA(OMSymbol.LIST1_LIST, resultList);
	}

	private static Object getResultString(ArrayList<String> arrayList) {

		List<Object> resultList = new ArrayList<>();
		
		if (!arrayList.isEmpty()) {
			for (String value : arrayList) {
				value = value.substring(0, value.lastIndexOf("@"));
				resultList.add(OMCreator.createOMSTR(value));
			}
		}
		
		return OMCreator.createOMA(OMSymbol.LIST1_LIST, resultList);
	}

	private static Object getLimitedResultString(ArrayList<String> arrayList, int limit) throws CasEvaluationException {

		if (limit <= 0)
			throw new CasEvaluationException("Lower Bound must be positive or a valid number.");
		
		List<Object> resultList = new ArrayList<>();
		
		if (!arrayList.isEmpty()) {
			for (String value : new ArrayList<String>(arrayList.subList(0, limit))) {
				value = value.substring(0, value.lastIndexOf("@"));
				resultList.add(OMCreator.createOMSTR(value));
			}
		}
		
		return OMCreator.createOMA(OMSymbol.LIST1_LIST, resultList);
	}

}