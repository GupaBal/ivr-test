package addLogic;



@Override
public String MergeGetValue(String first,String sceond) throws AgiException {
	Logger.Write.info(LOG_TAG + "WT wait person : %s¸í" + customValueList.get(first));
	Logger.Write.info(LOG_TAG + "WP wait time : %dºÐ" + customValueList.get(sceond));
	
	String result = customValueList.get(first)+","+customValueList.get(sceond);
	
	return ConditionType.SUCCESS.getValue() + "$" + result;
}