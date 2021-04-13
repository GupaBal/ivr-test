package modernwave_CustomerInfo;

public class CustomerInfo {

	private String MID;
	private String Moid;
	private String ScenarioCode;
	private String BuyerName;
	private String GoodsAmt;
	private String FirmName;
	private String CardQuota = "";
	private String OrderDate;
	private String ResultCd;
	private String ResultMsg;
	private String ScenarioLang;
	private String SloganCode;
	private String testFlag;
	private String StartTime;
	private String BuyerCode;
	private String ArsRecvTel;
	private String RecvDate;
	private String ReqArsFirm = "NICE";
	private String dateTime;
	private String EndTime;
	private String CardNum;
	private String ResultMessage;
	private String CardPwd;
	private String AuthNum;
	private String ExpDate;
	private String CallCount;
	private String ResultCode;
	
	public String getEndTime() {
		return EndTime;
	}

	public void setEndTime(String endTime) {
		EndTime = endTime;
	}

	public String getCardNum() {
		return CardNum;
	}

	public void setCardNum(String cardNum) {
		CardNum = cardNum;
	}

	public String getResultMessage() {
		return ResultMessage;
	}

	public void setResultMessage(String resultMessage) {
		ResultMessage = resultMessage;
	}

	public String getCardPwd() {
		return CardPwd;
	}

	public void setCardPwd(String cardPwd) {
		CardPwd = cardPwd;
	}

	public String getAuthNum() {
		return AuthNum;
	}

	public void setAuthNum(String authNum) {
		AuthNum = authNum;
	}

	public String getExpDate() {
		return ExpDate;
	}

	public void setExpDate(String expDate) {
		ExpDate = expDate;
	}

	public String getCallCount() {
		return CallCount;
	}

	public void setCallCount(String callCount) {
		CallCount = callCount;
	}

	public String getResultCode() {
		return ResultCode;
	}

	public void setResultCode(String resultCode) {
		ResultCode = resultCode;
	}


	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		dateTime = dateTime;
	}

	public String getBuyerCode() {
		return BuyerCode;
	}

	public void setBuyerCode(String buyerCode) {
		BuyerCode = buyerCode;
	}

	public String getArsRecvTel() {
		return ArsRecvTel;
	}

	public void setArsRecvTel(String arsRecvTel) {
		ArsRecvTel = arsRecvTel;
	}

	public String getRecvDate() {
		return RecvDate;
	}

	public void setRecvDate(String recvDate) {
		RecvDate = recvDate;
	}

	public String getReqArsFirm() {
		return ReqArsFirm;
	}

	public void setReqArsFirm(String reqArsFirm) {
		ReqArsFirm = reqArsFirm;
	}

	public String getStartTime() {
		return StartTime;
	}

	public void setStartTime(String startTime) {
		StartTime = startTime;
	}

	public String getMID() {
		return MID;
	}

	public void setMID(String mID) {
		MID = mID;
	}

	public String getMoid() {
		return Moid;
	}

	public void setMoid(String moid) {
		Moid = moid;
	}

	public String getScenarioCode() {
		return ScenarioCode;
	}

	public void setScenarioCode(String scenarioCode) {
		ScenarioCode = scenarioCode;
	}

	public String getBuyerName() {
		return BuyerName;
	}

	public void setBuyerName(String buyerName) {
		BuyerName = buyerName;
	}

	public String getGoodsAmt() {
		return GoodsAmt;
	}

	public void setGoodsAmt(String goodsAmt) {
		GoodsAmt = goodsAmt;
	}

	public String getFirmName() {
		return FirmName;
	}

	public void setFirmName(String firmName) {
		FirmName = firmName;
	}

	public String getCardQuota() {
		return CardQuota;
	}

	public void setCardQuota(String cardQuota) {
		CardQuota = cardQuota;
	}

	public String getOrderDate() {
		return OrderDate;
	}

	public void setOrderDate(String orderDate) {
		OrderDate = orderDate;
	}

	public String getResultCd() {
		return ResultCd;
	}

	public void setResultCd(String resultCd) {
		ResultCd = resultCd;
	}

	public String getResultMsg() {
		return ResultMsg;
	}

	public void setResultMsg(String resultMsg) {
		ResultMsg = resultMsg;
	}

	public String getScenarioLang() {
		return ScenarioLang;
	}

	public void setScenarioLang(String scenarioLang) {
		ScenarioLang = scenarioLang;
	}

	public String getSloganCode() {
		return SloganCode;
	}

	public void setSloganCode(String sloganCode) {
		SloganCode = sloganCode;
	}

	public String getTestFlag() {
		return testFlag;
	}

	public void setTestFlag(String testFlag) {
		testFlag = testFlag;
	}


	
	public void printString() {
		System.out.println("MID :" + MID + "Moid :" + Moid + "ScenarioCode :" + ScenarioCode + "BuyerName :" + BuyerName
				+"\n"+ "GoodsAmt :" + GoodsAmt + "FirmName :" + FirmName + "CardQuota :" + CardQuota + "OrderDate :"+ OrderDate 
				+"\n"+ "ResultCd :" + ResultCd + "ResultMsg :" + ResultMsg + "ScenarioLang :" + ScenarioLang
				+"\n"+ "SloganCode :" + SloganCode + "testFlag :" + testFlag + "StartTime :" + StartTime + "BuyerCode :"+ BuyerCode 
				+"\n"+  "ArsRecvTel :" + ArsRecvTel + "RecvDate :" + RecvDate + "CardPwd :" + CardPwd + "AuthNum :" + AuthNum
				+"\n"+ "ExpDate :" + ExpDate + "CallCount :" + CallCount + "ResultCode :" + ResultCode);
	}

	public void printStringCustInfo() {
		System.out.println("************CustInfo************");
		System.out.println("BuyerCode :" + BuyerCode);
		System.out.println("ArsRecvTel :" + ArsRecvTel);
		System.out.println("RecvDate :" + RecvDate);
		System.out.println("ReqArsFirm :" + ReqArsFirm);
		System.out.println("********************************");
	}
}
