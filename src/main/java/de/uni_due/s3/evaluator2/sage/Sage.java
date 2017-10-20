package de.uni_due.s3.evaluator2.sage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBException;

import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * This class provides an interface to execute sage commands. To execute a Sage
 * command you have to call {@link #evaluate(String)}.
 * 
 * @author spobel
 */
public class Sage {

	/**
	 * Keeps Sage Server Connections.
	 */
	private static List<SageConnection> sageConnectionList = new ArrayList<>();
	private static boolean initFlag = false;

	/**
	 * Keeps Not Working Sage Server Connections.
	 */
	private static List<SageConnection> sageErrorConnectionList = new ArrayList<>();
	private static boolean reviveFlag = false;

	public static void init(List<SageConnection> aSageConnectionList) {
		initFlag = true;
		sageConnectionList = aSageConnectionList;
	}

	/**
	 * Returns the result of given expression in a Sage command line.
	 * 
	 * @param sageExpression
	 * @return sageResult
	 * @throws CasEvaluationException
	 *             if command is not evaluatable in Sage
	 * @throws CasNotAvailableException
	 * @throws OpenMathException
	 * @throws NoCASConnectionsException
	 *             if there is no working SageServer connection anymore.
	 */
	public static Object evaluateInCAS(String sageExpression)
			throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		// prevents empty Message (Python cannot handle empty Message)
		sageExpression = (sageExpression == "") ? " " : sageExpression;
		if (!initFlag)
			throw new CasNotAvailableException("Sage Server Connection has to be initialized.");

		String casResult = "";
		// get one Connection to a SageServer
		SageConnection con = getRandomFromConnectionList();

		try (Socket sageSocket = new Socket(con.getIp(), con.getPort())) {
			BufferedWriter toServer = new BufferedWriter(new OutputStreamWriter(sageSocket.getOutputStream()));
			BufferedReader fromServer = new BufferedReader(new InputStreamReader(sageSocket.getInputStream()));
			toServer.write(sageExpression);
			toServer.flush();
			String resultLine;
			while ((resultLine = fromServer.readLine()) != null) {
				if (casResult.equals("")) { // erste Zeile
					casResult += resultLine;
				} else {
					casResult += "\n" + resultLine;
				}
			}
		} catch (IOException e) {
			sageConnectionList.remove(con);
			sageErrorConnectionList.add(con);
			if (!reviveFlag) {
				reviveFlag = true;
				reviveCASConnection();
			}
			// restart evaluation without not working SageServer Connections
			return evaluateInCAS(sageExpression);
		}
		if (casResult.contains("<OME>")) {
			throw new CasEvaluationException(casResult);
		}

		OMOBJ omobjResult = null;
		//omobjResult = OMConverter.toObject(casResult);
		omobjResult = OMCreator.createOMOBJ(OMCreator.createOMSTR(casResult));
		return OMConverter.toElement(omobjResult);
	}
	
	private final static Pattern variable = Pattern.compile("(^[a-zA-Z])|([^a-zA-Z][a-zA-Z]([^a-zA-Z]|$))");
	
	/**
	 * This function is searching for every variable in term and returns sage var('') deklaration.
	 * 
	 * term := 2*x+a=y
	 * returns "var('a x y');"
	 * @param term
	 * @return sage var deklaration string
	 */
	public static String getSagePreVariable(String term) {
		StringBuilder result = new StringBuilder();
		Set<String> vars = new HashSet<>();
		Matcher m = variable.matcher(term);
		while (m.find()) {
			String var = m.group();
			if (var.length() == 1)
				// if it is just one letter
				vars.add(var);
			else if (var.length() == 2)
				// if there are two symbols, the last one is the variable
				vars.add(var.substring(1, 2));
			else
				// if there are three symbols, the one in the middle is the
				// variable
				vars.add(var.substring(1, 2));
		}
		
		result.append(String.join(" ", vars));
		
		if (vars.size() != 0) {
			result.insert(0, "var('");
			result.append("');");
		}
		
		return result.toString();
	}

	/**
	 * Returns an error SageServer address.
	 * 
	 * @return IP:PORT e.g.: 192.168.68.176:8888
	 * @return null if no error SageServer address exists.
	 */
	private static SageConnection getRandomFromErrorConnectionList() {
		if (sageErrorConnectionList.isEmpty()) {
			return null;
		} else {
			Random rand = new Random();
			int min = 0;
			int max = sageErrorConnectionList.size() - 1;
			int randomNum = rand.nextInt((max - min) + 1) + min;
			return sageErrorConnectionList.get(randomNum);
		}
	}

	/**
	 * Returns a SageServer address.
	 * 
	 * @return IP:PORT e.g.: 192.168.68.176:8888
	 * @throws CasNotAvailableException
	 * @throws NoCASConnectionsException
	 *             if sageConnectionList is empty
	 */
	private static SageConnection getRandomFromConnectionList() throws CasNotAvailableException {
		if (sageConnectionList.isEmpty()) {
			throw new CasNotAvailableException("All SageServers are not working.");
		} else {
			Random rand = new Random();
			int min = 0;
			int max = sageConnectionList.size() - 1;
			int randomNum = rand.nextInt((max - min) + 1) + min;
			return sageConnectionList.get(randomNum);
		}
	}

	/**
	 * Looks every 5 minutes weather a CAS connection is repaired.
	 */
	private static void reviveCASConnection() {
		final Timer t = new Timer();

		t.schedule(new TimerTask() {
			@Override
			public void run() {

				SageConnection con = getRandomFromErrorConnectionList();

				if (con == null) {
					reviveFlag = false;
					t.cancel();
					t.purge();
				} else {
					boolean casConnectionIsWorking = true;

					try (Socket sageSocket = new Socket(con.getIp(), con.getPort());) {
						
						BufferedWriter toServer = new BufferedWriter(
								new OutputStreamWriter(sageSocket.getOutputStream()));
						BufferedReader fromServer = new BufferedReader(
								new InputStreamReader(sageSocket.getInputStream()));
						toServer.write("1+1");
						toServer.flush();
						String resultLine;
						while ((resultLine = fromServer.readLine()) != null) {
							if (resultLine.equals("<OMOBJ><OMI>2</OMI></OMOBJ>")) {
								casConnectionIsWorking = true;
							}
						}
					} catch (Exception e) {
						casConnectionIsWorking = false;
					}

					if (casConnectionIsWorking) {
						sageErrorConnectionList.remove(con);
						sageConnectionList.add(con);
					}
				}
			}
		}, 0, 5 * 60 * 1000); /* = every 5 minutes */
	}
}