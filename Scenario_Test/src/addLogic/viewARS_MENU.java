package addLogic;

@Override
public String viewARS_MENU(int number, String index, String substitutionString, String ment, int timeout) throws AgiException {
	String menuString = getParameterConvey(number, index);
	
	String[] indexs = null;

	if(substitutionString.contains(",")) {	//,∞° ¿÷¿Ω
		indexs = substitutionString.split(",");
		menuString = String.format(menuString, indexs[0],indexs[1]);
	}else {
		menuString = String.format(menuString, substitutionString);
	}
	
	
	return viewARS_MENU(menuString, ment, timeout);
}