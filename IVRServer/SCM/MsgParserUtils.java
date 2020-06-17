package SCM;
import Scm_eunms.*;

public class MsgParserUtils {
	private ByteParser parser;
	private byte[] byteArray;

	public MsgParserUtils(String HexString) {
		HexString = HexString.replaceAll(" ", "");
		this.setHexStringToByteArray(HexString);
	}

	public MsgParserUtils(byte type, short payloadSize, byte[] payloadArray) {
		byte[] tmp = new byte[payloadSize + 3];
		ByteParser parserTmp = new ByteParser(tmp);
		parserTmp.putByte(type);
		parserTmp.putShort(payloadSize);
		parserTmp.putByteArray(payloadArray);

		this.byteArray = parserTmp.getByteArray();
		this.parser = new ByteParser(byteArray);
	}

	public MsgParserUtils(byte stx, byte[] length_b, byte[] command_b, byte[] nextFlagb, byte[] argumentCntb, byte[] payload) {
		byte[] tmp = new byte[1 + length_b.length + command_b.length + nextFlagb.length + argumentCntb.length + payload.length];
		ByteParser parserTmp = new ByteParser(tmp);
		parserTmp.putByte(stx);
		parserTmp.putByteArray(length_b);
		parserTmp.putByteArray(command_b);
		parserTmp.putByteArray(nextFlagb);
		parserTmp.putByteArray(argumentCntb);
		parserTmp.putByteArray(payload);

		this.byteArray = parserTmp.getByteArray();
		this.parser = new ByteParser(byteArray);
	}

	public MsgParserUtils(byte[] payload) {
		byte[] tmp = new byte[payload.length];
		ByteParser parserTmp = new ByteParser(tmp);
		parserTmp.putByteArray(payload);
		this.byteArray = parserTmp.getByteArray();
		this.parser = new ByteParser(byteArray);
	}

	public void setHexStringToByteArray(String s) {
		this.byteArray = hexStringToByteArray(s);
		this.parser = new ByteParser(byteArray);
	}

	public boolean checkUsable() {
		if (byteArray.length <= 0) {
			return false;
		} else {
			return true;
		}
	}

	public byte getTypeFromByteArray() {
		return parser.getbyte();
	}

	public byte getRoteModeFromByteArray() {
		return parser.getbyte();
	}

	public byte getConnectResultCodeFromByteArray() {
		return parser.getbyte();
	}

	public byte getQueueCountFromByteArray() {
		return parser.getbyte();
	}

	public byte getSucFailFromByteArray() {
		return parser.getbyte();
	}

	public byte getIndexFromByteArray() {
		return parser.getbyte();
	}

	public byte getEXTFromByteArray() {
		return parser.getbyte();
	}

	public short getPayloadSizeFromByteArray(boolean isReverse) {
		if (isReverse)
			return Short.reverseBytes(parser.getShort());
		else
			return parser.getShort();
	}

	public String getString(int size) {
		return parser.getString(size).trim();
	}

	public String getExtensionFromByteArray() {
		return parser.getString(InfoType.EXTENSION.getTotalSize()).trim();
	}

	public String getGroupCodeFromByteArray() {
		return parser.getString(InfoType.GROUP_CODE.getTotalSize()).trim();
	}

	public String getServiceCodeFromByteArray() {
		return parser.getString(InfoType.SERVICE_CODE.getTotalSize()).trim();
	}

	public String getDateTimeStringFromByteArray() {
		return parser.getString(InfoType.DATETIME_STRING.getTotalSize()).trim();
	}

	public String getCIDFromByteArray() {
		return parser.getString(InfoType.CID.getTotalSize()).trim();
	}

	public String getDialNumberFromByteArray() {
		return parser.getString(InfoType.DN.getTotalSize()).trim();
	}

	public String getETCInfoFromByteArray() {
		return parser.getString(InfoType.ETC_INFO.getTotalSize()).trim();
	}

	public String getForecastSECFromByteArray() {
		return parser.getString(InfoType.FORECAST_SEC.getTotalSize()).trim();
	}

	public String getNameFromByteArray() {
		return parser.getString(InfoType.NAME.getTotalSize()).trim();
	}

	public String getValueFromByteArray() {
		return parser.getString(InfoType.VALUE.getTotalSize()).trim();
	}

	public String getArsExtensionFromByteArray_Scm() {
		return parser.getString(ScmInfoType.ARSEXTENSION.getTotalSize()).trim();
	}

	public String getCallerInfoFromByteArray_Scm() {
		return parser.getString(ScmInfoType.CALLER_INFO.getTotalSize()).trim();
	}

	public String getExpectedWaitCntFromByteArray_Scm() {
		return parser.getString(ScmInfoType.EXPECTED_WAIT_CNT.getTotalSize()).trim();
	}

	public String getExpectedWaitTimeFromByteArray_Scm() {
		return parser.getString(ScmInfoType.EXPECTED_WAIT_TIME.getTotalSize()).trim();
	}

	public String getAnnounceFromByteArray_Scm() {
		return parser.getString(ScmInfoType.ANNOUNCE.getTotalSize()).trim();
	}

	public String getAreaCodeFromByteArray_Scm() {
		return parser.getString(ScmInfoType.AREA_CODE.getTotalSize()).trim();
	}

	public String getIvrIpFromByteArray_Scm() {
		return parser.getString(ScmInfoType.IVR_IP_ADDR.getTotalSize()).trim();
	}

	public String getServiceCodeFromByteArray_Scm() {
		return parser.getString(ScmInfoType.SERVICE_CODE.getTotalSize()).trim();
	}

	public String getCustClassFromByteArray_Scm() {
		return parser.getString(ScmInfoType.CUST_CLASS.getTotalSize()).trim();
	}

	public String getSubscriberCodeFromByteArray_Scm() {
		return parser.getString(ScmInfoType.SUBSCRIBER_CODE.getTotalSize()).trim();
	}

	public String getCidFromByteArray_Scm() {
		return parser.getString(ScmInfoType.CID.getTotalSize()).trim();
	}

	public String getIncommingDateFromByteArray_Scm() {
		return parser.getString(ScmInfoType.INCOMMINGDATE.getTotalSize()).trim();
	}

	public String getDesignatedAgentFromByteArray_Scm() {
		return parser.getString(ScmInfoType.DESIGNATED_AGENT.getTotalSize()).trim();
	}

	public String getEtcInfoFromByteArray_Scm() {
		return parser.getString(ScmInfoType.ETC_INFO.getTotalSize()).trim();
	}

	public String getScreenpopDataFromByteArray_Scm() {
		return parser.getString(ScmInfoType.SCREENPOP_DATA.getTotalSize()).trim();
	}

	public byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	public static String byteArrayToHex(byte[] a) {
		StringBuilder sb = new StringBuilder(a.length * 2);
		for (byte b : a)
			sb.append(String.format("%02x", b));
		return sb.toString();
	}

	public String getHexString() {
		StringBuilder sb = new StringBuilder(byteArray.length * 2);
		for (byte b : byteArray)
			sb.append(String.format("%02x", b));
		return sb.toString();
	}

}