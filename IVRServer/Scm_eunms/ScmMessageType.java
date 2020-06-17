package Scm_eunms;
public enum ScmMessageType {

	SMC_ARS_AGENT_REQ("091", ScmMessageEnum.Request), //
	SMC_ARS_QUEUE_TRANSFER("093", ScmMessageEnum.Request), //
	ARS1STEPTNS_CONFIRM("019", ScmMessageEnum.Response), //
	SMC_ARS_TRANSFER("009", ScmMessageEnum.Response), //
	SMC_ARS_TRANSFER_CONFIRM("020", ScmMessageEnum.Request), //
	SMC_ARS_CALLBACK("006", ScmMessageEnum.Request), //
	SMC_ARS_RECORD("012", ScmMessageEnum.Request), //
	SMC_VMS_DISCONNECT("002", ScmMessageEnum.Request), // ¾È¾ç
	SMC_ARS_CALL_LOG("021", ScmMessageEnum.Request), //
	SMC_VMS_REQUEUE_REQUEST("301", ScmMessageEnum.Request), //
	SMC_ARS_PASSWORD_INFO_RESPONSE("099", ScmMessageEnum.Response), //
	SMC_ARS_PASSWORD_STRING("098", ScmMessageEnum.Request), //
	SMC_ARS_PASSWORD_INFO_REQUEST("099", ScmMessageEnum.Request), //
	SMC_ARS_PASSWORD_QUIT("094", ScmMessageEnum.Request), //
	SMC_ARS_USRSVC_RESPONSE("018", ScmMessageEnum.Response), //
	SMC_ARS_USRSVC_REQUEST("018", ScmMessageEnum.Request), //
	SMC_ARS_USRSVC_QUIT("019", ScmMessageEnum.Response), //
	SMC_ARS_NAVIGATE_LOG("092", ScmMessageEnum.Request), //
	SMC_EVT_INCOMMING("031", ScmMessageEnum.Response), //
	SMC_ARS_ALIVE_CHECK("022", ScmMessageEnum.Response), //
	SMC_VMS_CUSTDATA_INFORM("302", ScmMessageEnum.Response); //

	private String value;
	private ScmMessageEnum CONFIRM_REQ_REP;

	private ScmMessageType(String value, ScmMessageEnum CONFIRM_REQ_REP) {
		this.CONFIRM_REQ_REP = CONFIRM_REQ_REP;
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public ScmMessageEnum getCONFIRM_REQ_REP() {
		return CONFIRM_REQ_REP;
	}

	public static ScmMessageType getEnum(String value) {
		for (ScmMessageType type : ScmMessageType.values()) {
			if (type.getValue().equalsIgnoreCase(value)) {
				return type;
			}
		}
		return null;
	}
}
