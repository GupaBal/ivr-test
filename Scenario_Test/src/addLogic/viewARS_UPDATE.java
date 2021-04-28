package addLogic;

@Override
public String viewARS_MENU(int number, String index, String ment, int timeout, String promotion) throws AgiException {

	if(number == -1) {
		t_viewARS에서 가져오기 
	}
	
	
	String menuString = getParameterConvey(number, index);
	String[] indexs = null;
	String substitutionString = "";
	try {
		if (promotion.contains(",")) {
			indexs = promotion.split(",");
		}
		if (indexs != null && indexs.length == 2) {
			String yn_index = getParameterConvey(2, indexs[0]).equalsIgnoreCase("1") ? "Y" : "N";
			if (yn_index.equals("Y")) {
				substitutionString = getParameterConvey(2, indexs[1]);
			}
		}
	} catch (Exception e) {
		Logger.Write.warn(e.getMessage(), e);
	}

	menuString += substitutionString;

	return viewARS_MENU(menuString, ment, timeout);
}