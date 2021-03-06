package de.uni_due.s3.evaluator2.r;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.bind.JAXBException;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OpenMathException;


public class R {

	/**
	 *  a R Connection List
	 */
	private static List<RConn> rConnectionList = new ArrayList<>();
	private static boolean initFlag = false;

	/**
	 * Keep a R ConnectionList which Connections are not working
	 */
	private static List<RConn> rErrorConnectionList = new ArrayList<>();
	private static boolean reviveFlag = false;

	public static void init(List<RConn> aSageConnectionList) {
		initFlag = true;
		rConnectionList = aSageConnectionList;
	}

	
	/**
	 * Returns the result of given expression in a R command line.
	 * 
	 * @param rExpression
	 * @return an REXP result converted from R into OM*
	 * @throws RserveException
	 *             when rExpression is not able to be evaluated
	 * @throws CasNotAvailableException
	 *             when R is not initialized
	 * @throws CasEvaluationException
	 *             when R returns unsupported Elements, which are not able to convert into OMOBJ
	 * @throws OpenMathException
	 *             when OMConverter cannot convert OMOBJ to element
	 */
	public static Object evaluateInCAS(String rExpression)
			throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		// Filling (if) empty String with a Blank
		rExpression = (rExpression == "") ? " " : rExpression;
		if (!initFlag)
			throw new CasNotAvailableException("R Server Connection has to be initialized.");

		String casResult = "";
		RConn con = getRandomFromConnectionList();
		RConnection rConnection = null;
		try {
			rConnection = new RConnection(con.getIp(), con.getPort());
			
			REXP result = rConnection.eval(rExpression);

			// Convert XML String to OM*-Object
			casResult = new RToOMOBJVisitor().visit(result);
			

		} catch (RserveException e) {
			if (! e.getLocalizedMessage().contains("127")  && ! e.getLocalizedMessage().contains("syntax error")) { 
				//If Exception does not contain Error-Code 127 (127: expr could not be evaled) or syntax Error
				//then R Server might be down or is behaving wrong
			rConnectionList.remove(con);
			rErrorConnectionList.add(con);
			if (!reviveFlag) {
				reviveFlag = true;
				reviveCASConnection();
			}
			// restart evaluation
			return evaluateInCAS(rExpression);
			}
			casResult = "<OME><OMSTR>Syntax Error in Expression</OMSTR></OME>";
			
			
		}finally {
			if (rConnection != null) {
				// Uncomment this if R has to many Problems  handling alot of Sockets
//				try {
//					rConnection.voidEval("closeAllConnections()");
//				} catch (RserveException  e) {
//					//tried everything to close
//				}
				rConnection.close();
			}
		}
		if (casResult.contains("<OME>")) {
			throw new CasEvaluationException(casResult);
		}

		OMOBJ omobjResult = null;
		try {
			casResult = "<OMOBJ>" + casResult + "</OMOBJ>";
			omobjResult = OMConverter.toObject(casResult);
		} catch (JAXBException e) {
			throw new CasEvaluationException("Error Converting OMXML back to OMOBJ with :" + casResult);
		}
		return OMConverter.toElement(omobjResult);
	}
	
	
	/**
	 * Returns a RServer address.
	 * 
	 * @return IP:PORT e.g.: 192.168.68.176:8888
	 * @throws CasNotAvailableException
	 * @throws NoCASConnectionsException
	 *             if rConnectionList is empty
	 */
	private static RConn getRandomFromConnectionList() throws CasNotAvailableException {
		if (rConnectionList.isEmpty()) {
			throw new CasNotAvailableException("All RServers are not working.");
		} else {
			Random rand = new Random();
			int max = rConnectionList.size() - 1;
			int randomNum = rand.nextInt(max + 1);
			return rConnectionList.get(randomNum);
		}
	}
	
	
	/**
	 * Returns an error RServer address.
	 * 
	 * @return IP:PORT e.g.: 192.168.68.176:8888
	 * @return null if no error RServer address exists.
	 */
	private static RConn getRandomFromErrorConnectionList() {
		if (rErrorConnectionList.isEmpty()) {
			return null;
		} else {
			Random rand = new Random();
			int max = rErrorConnectionList.size() - 1;
			int randomNum = rand.nextInt(max + 1);
			return rErrorConnectionList.get(randomNum);
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

				RConn con = getRandomFromErrorConnectionList();

				if (con == null) {
					reviveFlag = false;
					t.cancel();
					t.purge();
				} else {
					boolean casConnectionIsWorking = true;

					try {
						RConnection rConnection = new RConnection(con.getIp(), con.getPort());
						REXP result = rConnection.eval("1+1");
						rConnection.detach();
						if (result.asInteger() == 2) {
							casConnectionIsWorking = true;
						}
					} catch (Exception e) {
						casConnectionIsWorking = false;
					}

					if (casConnectionIsWorking) {
						rErrorConnectionList.remove(con);
						rConnectionList.add(con);
					}
				}
			}
		}, 0, 5 * 60 * 1000); /* = every 5 minutes */
	}
}
