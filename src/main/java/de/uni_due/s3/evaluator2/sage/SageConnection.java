package de.uni_due.s3.evaluator2.sage;

/**
 * Represents one Connection to one SageServer.
 * @author spobel
 *
 */
public class SageConnection {

	private final String ip;
	private final Integer port;
	private boolean available = true;
	
	public SageConnection(String ip, Integer port) {
		this.ip = ip;
		this.port = port;
	}
	
	public String getIp() {
		return ip;
	}
	
	public Integer getPort() {
		return port;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
}
