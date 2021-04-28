package addLogic;

	
	@Override
	public String getWaitCount(String type) throws AgiException {
		Logger.Write.info(LOG_TAG + "serviceAGI getWaitCount " + type);
		if (userInfo.getWait_count() == (byte) -1) {
			return ConditionType.FAIL.getValue();
		}
		switch (type.toUpperCase()) {
		case "WP":
			Byte wp = userInfo.getWait_count();

			int wp_int = wp.intValue();
			if (wp_int <= 0) {
				wp_int = 1;
			}
			return ConditionType.SUCCESS.getValue() + "$" + wp_int;
		case "WT"://

			Byte wp2 = userInfo.getWait_count();
			int wp_int2 = wp2.intValue();
			int sec = 0;

			try {
				sec = Integer.parseInt(dars_querydb.getParameter(9995));
			} catch (Exception e) {
				e.printStackTrace();
			}

			int total = sec * wp_int2;

			return ConditionType.SUCCESS.getValue() + "$" + total;
		default:
			return ConditionType.FAIL.getValue();
		}
	}
	
	
