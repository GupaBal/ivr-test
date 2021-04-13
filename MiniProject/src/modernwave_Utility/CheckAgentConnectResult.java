package modernwave_Utility;

import java.util.LinkedHashMap;

public class CheckAgentConnectResult {

	private Header header;
	private String ARSExtension;
	private ConnectResultCodes ConnectResultCode;
	private String ConnectedAgent;
	private String ForecastSec;
	private byte QueueCount;

	public CheckAgentConnectResult(Header header, String aRSExtension, ConnectResultCodes connectResultCode, String connectedAgent, String forecastSec, byte queueCount) {
		this.header = header;
		ARSExtension = aRSExtension;
		ConnectResultCode = connectResultCode;
		ConnectedAgent = connectedAgent;
		ForecastSec = forecastSec;
		QueueCount = queueCount;
	}

	public String getARSExtension() {
		return ARSExtension;
	}

	public ConnectResultCodes getConnectResultCode() {
		return ConnectResultCode;
	}

	public String getConnectedAgent() {
		return ConnectedAgent;
	}

	public String getForecastSec() {
		return ForecastSec;
	}

	public byte getQueueCount() {
		return QueueCount;
	}

	public LinkedHashMap<String, Object> getPayloadMap() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("ARSExtension", ARSExtension);
		map.put("ConnectResultCode", ConnectResultCode);
		map.put("ConnectedAgent", ConnectedAgent);
		map.put("ForecastSec", ForecastSec);
		map.put("QueueCount", QueueCount);
		return map;
	}

	public Header getHeader() {
		return header;
	}

}
