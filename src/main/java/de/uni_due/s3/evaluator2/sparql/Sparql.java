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
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.omutils.OMCreator;


/**
 * Evaluates a Query to the specified Server.
 * 
 * 
 * @author ?
 *
 */
public class Sparql {

	public static Object evaluate(String query) throws CasEvaluationException {

		// Suppress some annoying warnings from underlying implementation
		Logger.getLogger(org.apache.http.client.protocol.ResponseProcessCookies.class).setLevel(Level.ERROR);
		
		ArrayList<String> resultArray = new ArrayList<String>();
		ResultSet results = null;
		String lang = query.substring(query.indexOf("LangCode:") + 9, query.indexOf("LangCode:") + 11);

		if (query.contains("Service:DBpedia") && query.contains("LangCode:")) {
			//CASE: Service:DBpedia
			
			// initialize results, executes query!
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

			
			// after result is initialized
			if (query.contains("LimitSet:")) {
				//return limited Result
				String limitString = query.substring(query.indexOf("LimitSet:") + 9, query.length());
				int limit = Integer.parseInt(limitString);
				return getLimitedLiteralResults(results, resultArray, lang, limit);
			} else {
				//return full result
				return getLiteralResults(results, resultArray, lang);
			}
			
			
			

		} else if ((query.contains("Service:Wikidata") && query.contains("LangCode:"))) {
			//CASE: Service:Wikidata
			// initialize results!
			results = executeQuery(query, "https://query.wikidata.org/sparql");

			if (query.contains("LimitSet:")) {
				//return limited Result
				String limitString = query.substring(query.indexOf("LimitSet:") + 9, query.length()).replaceAll("\\s+",
						"");
				int limit = Integer.parseInt(limitString);
				return getLimitedLiteralResults(results, resultArray, lang, limit);
			} else {
				//return full result
				return getLiteralResults(results, resultArray, lang);
			}

			

		} else if (query.contains("Service:") && query.contains("#ManualQuery:")) {
			//CASE: Service: Custom with a ManualQuerry

			String selectResultList = query
					.substring(query.indexOf("SELECT ") + 7, query.indexOf("WHERE") - 1)
					.replace("?", "").replace("DISTINCT", "").trim();

			String[] selectResultArray = selectResultList.split(" ");
			
			String service = query.substring(query.indexOf("Service:") + 8, query.length());

			
			// OPEN-Connection
			Query queryObject = QueryFactory.create(query);
			QueryEngineHTTP queryEngine = QueryExecutionFactory.createServiceRequest(service, queryObject);
			queryEngine.setTimeout(15, TimeUnit.SECONDS);
			results = queryEngine.execSelect();

			
			
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
				// is  
				if (tupleContent.size() > 1) {
					resultList.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, tupleContent));
				} else if (tupleContent.size() == 1){
					resultList.add(tupleContent.get(0));
				}
			}
			// CLOSE-Connection
			queryEngine.close();
			
			
			// return itself if resultList contains only one element
			if (resultList.size() > 1) {
				return OMCreator.createOMA(OMSymbol.LIST1_LIST, resultList);
			} else if (resultList.size() == 1){
				return resultList.get(0);
			} else {
				return OMCreator.createOMA(OMSymbol.LIST1_LIST, resultList);
			}
			
			
		} else if (query.contains("ItemExpression:") && query.contains("LangCode:")) {
			//CASE ItemExpression
			
			//extract ItemExpression and execute Query
			String expression = query.substring(query.indexOf("ItemExpression:") + 15, query.length());
			results = executeQuery(query, "https://query.wikidata.org/sparql");
			
			//check ItemExpression
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
			//CASE PropExpression
			
			//extract ItemExpression and execute Query
			String expression = query.substring(query.indexOf("PropExpression:") + 15, query.length());
			results = executeQuery(query, "https://query.wikidata.org/sparql");
			
			//check PropExpression
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

	
	/**
	 * Executes the QueryString to the specific service.
	 * @param queryString the String which has to be executed
	 * @param service the service, where the queryString should be executed.
	 * @return the ResultSet
	 */
	private static ResultSet executeQuery(String queryString, String service) {
		Query query = QueryFactory.create(queryString);
		QueryEngineHTTP queryEngine = QueryExecutionFactory.createServiceRequest(service, query);
		queryEngine.setTimeout(15, TimeUnit.SECONDS);
		ResultSet resultSet = queryEngine.execSelect();
		return resultSet;
	}

	
	
	/**
	 * Appends the resultArray for every result in ResultSet.
	 * 
	 * @param results the ResultSet from a Query
	 * @param resultArray the arrayList which is appended
	 * @param lang !?!?!?! TODO
	 * @return an OMA-List with the (unmodified) arrayList 
	 */
	private static OMA getResourceResults(ResultSet results, ArrayList<String> resultArray, String lang) {
		for (; results.hasNext();) {
			QuerySolution solution = results.nextSolution();
			String stringElement = solution.getResource("objectLabel").toString()
					.replaceAll("http://www.wikidata.org/entity/", "");
			resultArray.add(stringElement);
		}
		return getUnmodifiedResultString(resultArray);
	}
	
	
	/**
	 * Appends the results from a Query into the ArrayList
	 * Then calls getResultString to convert it into a OMA-List 
	 * 
	 * @param results the results from the Query
	 * @param resultArray the result array, where every element is added.
	 * @param lang  !?!?!?! TODO
	 * @return an OMA-List containing the (not-limited) arrayList
	 */
	private static OMA getLiteralResults(ResultSet results, ArrayList<String> resultArray, String lang) {
		for (; results.hasNext();) {
			QuerySolution solution = results.nextSolution();
			String stringElement = solution.getLiteral("objectLabel").toString().replaceAll("@" + lang, "");
			if (!stringElement.matches("Q\\d+")) {
				resultArray.add(stringElement);
			}
		}
		return getResultString(resultArray);
	}

	
	/**
	 * Appends the results from a Query into the ArrayList
	 * Then calls getLimitedResultString to convert it into a (limited, specified by limit) OMA-List 
	 * 
	 * @param results the results from the Query
	 * @param resultArray the result array, where every element is added.
	 * @param lang  the languageCode TODO
	 * @param limit the limit of the OMAString
	 * @return a OMA-List with a subArrayList specified as in Limit
	 * @throws CasEvaluationException
	 */
	private static OMA getLimitedLiteralResults(ResultSet results, ArrayList<String> resultArray, String lang,
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

	
	/**
	 * Creates an OMA-List containing the FULL arrayList (unmodified)
	 * 
	 * @param arrayList the arrayList to be converted
	 * @return the OMA-List containing the arrayList
	 */
	private static OMA getUnmodifiedResultString(ArrayList<String> arrayList) {

		List<Object> resultList = new ArrayList<>();
		
		if (!arrayList.isEmpty()) {
			for (String value : arrayList) {
				resultList.add(OMCreator.createOMSTR(value));
			}
		}
		
		return OMCreator.createOMA(OMSymbol.LIST1_LIST, resultList);
	}

	
	/**
	 * Creates an OMA List containing the arrayList until the @ Character
	 * 
	 * @param arrayList the arrayList to be converted
	 * @return the OMA-List containing the arrayList
	 */
	private static OMA getResultString(ArrayList<String> arrayList) {

		List<Object> resultList = new ArrayList<>();
		
		if (!arrayList.isEmpty()) {
			for (String value : arrayList) {
				value = value.substring(0, value.lastIndexOf("@"));
				resultList.add(OMCreator.createOMSTR(value));
			}
		}
		
		return OMCreator.createOMA(OMSymbol.LIST1_LIST, resultList);
	}

	
	/**
	 * Gets the first n Elements of an ArrayList until the @ Character.
	 * 
	 * @param arrayList The ArrayList, where the first n Elements are taken from.  
	 * @param limit the Number of the first elements in the ArrayList 
	 * @return a new smaller ArrayList as an OMA-List
	 * @throws CasEvaluationException
	 */
	private static OMA getLimitedResultString(ArrayList<String> arrayList, int limit) throws CasEvaluationException {

		if (limit <= 0)
			throw new CasEvaluationException("Higher Bound must be positive or a valid number.");
		if (arrayList.size() - 1 > 0)
			throw new CasEvaluationException("Higher Bound cannot exceed the ArrayList-size.");
		
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