package modernwave_Utility;

import java.util.List;
import java.util.Arrays;

public enum MessageTypeEnum {
	// TODO REQUEST
	WV0000Q(Arrays.asList(//
			PacketEnum.MESSAGE_TYPE, ////
			PacketEnum.AUTH_KEY, ////
			PacketEnum.SVC_CODE, ////
			PacketEnum.USER_MDN, ////
			PacketEnum.SESSION_ID)), ////
	WV1000Q(Arrays.asList(//
			PacketEnum.MESSAGE_TYPE, ////
			PacketEnum.AUTH_KEY, ////
			PacketEnum.SVC_CODE, ////
			PacketEnum.USER_MDN, ////
			PacketEnum.SESSION_ID)), //// //
	WV1100Q(Arrays.asList(//
			PacketEnum.MESSAGE_TYPE, ////
			PacketEnum.AUTH_KEY, ////
			PacketEnum.SVC_CODE, ////
			PacketEnum.USER_MDN, ////
			PacketEnum.SESSION_ID, ////
			PacketEnum.SMS_KEY)), //// //
	WV2000Q(Arrays.asList(//
			PacketEnum.MESSAGE_TYPE, ////
			PacketEnum.AUTH_KEY, ////
			PacketEnum.SVC_CODE, ////
			PacketEnum.USER_MDN, ////
			PacketEnum.SESSION_ID, ////
			PacketEnum.TIMESTAMP, ////
			PacketEnum.MENU_SIZE, ////
			PacketEnum.MENU)), //// //
	WV3000Q(Arrays.asList(//
			PacketEnum.MESSAGE_TYPE, //
			PacketEnum.AUTH_KEY, ////
			PacketEnum.SVC_CODE, ////
			PacketEnum.USER_MDN, ////
			PacketEnum.SESSION_ID)), // //
	// TODO(null) RESPONSE
	WV0000S(Arrays.asList(//
			PacketEnum.MESSAGE_TYPE, //
			PacketEnum.AUTH_KEY, ////
			PacketEnum.SVC_CODE, ////
			PacketEnum.USER_MDN, ////
			PacketEnum.RESULT_CODE, ////
			PacketEnum.SERVICE_SUPPORT_YN, ////
			PacketEnum.PLATFORM_TYPE)), // //
	WV1000S(Arrays.asList(//
			PacketEnum.MESSAGE_TYPE, //
			PacketEnum.AUTH_KEY, ////
			PacketEnum.SVC_CODE, ////
			PacketEnum.USER_MDN, ////
			PacketEnum.RESULT_CODE, ////
			PacketEnum.SERVICE_SUPPORT_YN, ////
			PacketEnum.PLATFORM_TYPE)), // //
	WV1100S(Arrays.asList(//
			PacketEnum.MESSAGE_TYPE, //
			PacketEnum.AUTH_KEY, ////
			PacketEnum.SVC_CODE, ////
			PacketEnum.USER_MDN, ////
			PacketEnum.RESULT_CODE)), // //
	WV2000S(Arrays.asList(//
			PacketEnum.MESSAGE_TYPE, //
			PacketEnum.AUTH_KEY, ////
			PacketEnum.SVC_CODE, ////
			PacketEnum.USER_MDN, ////
			PacketEnum.RESULT_CODE, ////
			PacketEnum.SELECT_DTMF, ////
			PacketEnum.USER_DATA_SIZE, ////
			PacketEnum.USER_DATA)), // //
	WV3000S(Arrays.asList(//
			PacketEnum.MESSAGE_TYPE, //
			PacketEnum.AUTH_KEY, ////
			PacketEnum.SVC_CODE, ////
			PacketEnum.USER_MDN, ////
			PacketEnum.RESULT_CODE)), // //
	HEADER(Arrays.asList(//
			PacketEnum.HEADER_MESSAGE_SIZE, //
			PacketEnum.HEADER_MESSAGE_NO, //
			PacketEnum.HEADER_CUSTOMER_SERVICE_ID)), //
	TEST(Arrays.asList(//
			PacketEnum.SERVICE_SUPPORT_YN, //
			PacketEnum.HEADER_MESSAGE_NO, //
			PacketEnum.MESSAGE_TYPE));//

	private MessageTypeEnum(List<PacketEnum> names) {
		this.names = names;
	}

	public List<PacketEnum> getNames() {
		return names;
	}

	private List<PacketEnum> names;

}