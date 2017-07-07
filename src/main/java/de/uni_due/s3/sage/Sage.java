package de.uni_due.s3.sage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.openmath.omutils.OMConverter;

/**
 * This class provides an interface to execute sage commands. To execute a Sage
 * command you have to call {@link #evaluate(String)}.
 * 
 * @author spobel
 */
public class Sage {

	private static final Logger log = Logger.getLogger(Sage.class);
	private static boolean reviveFlag = false;
	private static boolean initFlag = false;

	/**
	 * Keeps Sage Server Connections.
	 */
	private static List<String> sageConnectionsList = new ArrayList<String>();

	/**
	 * Keeps Sage Server Connections that are currently not working.
	 */
	private static List<String> sageErrorConnectionsList = new ArrayList<String>();

	public static void init(List<String> aSageConnectionsList) {
		initFlag = true;
		sageConnectionsList = aSageConnectionsList;
	}

	/**
	 * Returns the result of given expression in a Sage command line.
	 * 
	 * @param sageExpression
	 * @return sageResult
	 * @throws CasEvaluationException
	 *             if command is not evaluatable in Sage
	 * @throws CasNotAvailableException
	 * @throws NoCASConnectionsException
	 *             if there is no working SageServer connection anymore.
	 */
	public static Object evaluateInCAS(String sageExpression) throws CasEvaluationException, CasNotAvailableException {
		if (sageExpression == "") { // Python erkennt eine Leere Message nicht
									// als Connection
			sageExpression = " ";
		}
		if (!initFlag) {
			throw new CasNotAvailableException("Sage Server Connection has to be initialized.");
		}
		String casResult = "";
		// get one Connection to a SageServer
		String randConn = getRandomFromConnectionList();
		int pos = randConn.indexOf(':');
		String hostName = randConn.substring(0, pos);
		int portNumber = Integer.parseInt(randConn.substring(pos + 1));

		try {
			Socket sageSocket = new Socket(hostName, portNumber);
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
			sageSocket.close();
		} catch (IOException e) {
			log.warn("SageServer Connection '" + randConn + "' is currently not working.");
			sageConnectionsList.remove(randConn);
			sageErrorConnectionsList.add(randConn);
			if (!reviveFlag) {
				reviveFlag = true;
				reviveCASConnection();
			}
			// restart evaluation without not working SageServer Connections
			return evaluateInCAS(sageExpression);
		}
		Object omobjResult = null;
		try {
			omobjResult = OMConverter.toObject(casResult);
		} catch (JAXBException e) {
			// log.error("Sage produced an JAXBException in OMConverter.", e);
		}
//		if (((OMOBJ) omobjResult).getOME() != null) {
//			OME ome = ((OME) omobjResult);
//			throw new CasEvaluationException("Sage command: '" + sageExpression
//					+ "' could not be evaluated in Sage CAS. Error Message:" + ome.getOMSOrOMVOrOMI().get(0));
//		}
		return omobjResult;
	}

	/**
	 * Returns a SageServer address.
	 * 
	 * @return IP:PORT e.g.: 192.168.68.176:8888
	 * @throws CasNotAvailableException
	 * @throws NoCASConnectionsException
	 *             if sageConnectionList is empty
	 */
	private static String getRandomFromConnectionList() throws CasNotAvailableException {
		if (sageConnectionsList.size() == 0) {
			throw new CasNotAvailableException("All SageServers are not working.");
		} else {
			Random rand = new Random();
			int min = 0;
			int max = sageConnectionsList.size() - 1;
			int randomNum = rand.nextInt((max - min) + 1) + min;
			return sageConnectionsList.get(randomNum);
		}
	}

	/**
	 * Returns an error SageServer address.
	 * 
	 * @return IP:PORT e.g.: 192.168.68.176:8888
	 * @return null if no error SageServer address exists.
	 */
	private static String getRandomFromErrorConnectionList() {
		if (sageErrorConnectionsList.size() == 0) {
			return null;
		} else {
			Random rand = new Random();
			int min = 0;
			int max = sageErrorConnectionsList.size() - 1;
			int randomNum = rand.nextInt((max - min) + 1) + min;
			return sageErrorConnectionsList.get(randomNum);
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

				String casConn = getRandomFromErrorConnectionList();

				if (casConn == null) {
					reviveFlag = false;
					t.cancel();
					t.purge();
				} else {
					boolean casConnectionIsWorking = true;

					int pos = casConn.indexOf(':');
					String hostName = casConn.substring(0, pos);
					int portNumber = Integer.parseInt(casConn.substring(pos + 1));

					try {
						Socket sageSocket = new Socket(hostName, portNumber);
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
						sageSocket.close();
					} catch (Exception e) {
						casConnectionIsWorking = false;
					}

					if (casConnectionIsWorking) {
						sageErrorConnectionsList.remove(casConn);
						sageConnectionsList.add(casConn);
						log.warn("SageServer Connection '" + casConn + "' is revived.");
					}
				}
			}
		}, 0, 5 * 60 * 1000); /* = every 5 minutes */
	}
}