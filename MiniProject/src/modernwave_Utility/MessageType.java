package modernwave_Utility;

public enum MessageType {
	RequestAgentConnectMsg(1), //
	AgentConnectResultMsg(2), //
	AgentReadyMsg(3), //
	ReservedMsg(4), //
	CallInfoMsg(5), //
	DisconnectedInfoConnectingMsg(6), //
	DisconnectedInfoIVRMsg(7), //
	DisconnectedInfoCallbackMsg(8), //
	DisconnectedInfoForwardingMsg(9), //
	RequestAgentMsgEx(200), //
	QueryLastAgentMsg(201), //
	QueryLastAgentResultMsg(202), //
	DisconnectedInfoAgentMsg(98), //
	CancelAgentConnectMsg(99), //
	SetCallVariableMsg(100), //
	QueryLoginAgentMsg(101), //
	QueryLogineAgentResultMsg(102), //
	QueryAvailableAgentMsg(103), //
	QueryAvailableAgentResultMsg(104), //
	AliveCheckMsg(105), //
	CheckAgentConnectMsg(106), //
	CheckAgentConnectResultMsg(107), //
	RequestSingleStepTransfer(108);//

	private int value;

	private MessageType(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public static MessageType getEnum(int value) {

		for (MessageType type : MessageType.values()) {
			if (type.getValue() == value) {
				return type;
			}
		}
		return null;
	}
}
