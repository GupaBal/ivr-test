package kr.co.modernwave.module.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import kr.co.modernwave.log.Logger;
import kr.co.modernwave.module.db.OracleQueryDatabase;
import kr.co.modernwave.module.exception.KeyNullException;
import kr.co.modernwave.module.table.ColumnCustomerInfo;
import kr.co.modernwave.module.table.ColunmBusanBohun;
import kr.co.modernwave.module.table.ColunmReservation;
import kr.co.modernwave.module.table.ColunmSKKU;
import kr.co.modernwave.module.table.ColunmSeoulBohun;
import kr.co.modernwave.module.table.IRecord;
import kr.co.modernwave.scenario_gen.ivr.ConditionType;

public class OracleIVRService {

	private OracleQueryDatabase queryDb;

	private int timeOut_count = 0;

	public Map<String, ArrayList<IRecord>> personalMap = new HashMap<String, ArrayList<IRecord>>();

	public OracleIVRService(OracleQueryDatabase queryDb) {
		this.queryDb = queryDb;
	}

	public String test(String query) throws SQLException, Exception {
		return queryDb.test(query);
	}

	public String SKKU_IsRegistryStudent(String Number, String type) {
		Logger.Write.info(String.format("(MODULE) SKKU_IsRegistryStudent Number:%s type:%s", Number, type));
		ArrayList<IRecord> list = new ArrayList<IRecord>();
		StringBuilder sb = new StringBuilder();
		switch (type) {
		case "phone": {
			sb.append(String.format("select DISTINCT KOR_NAME, A.HAKBUN from VW_ARS_HAKBUN a, VW_ARS_PHONE B WHERE A.HAKBUN=B.HAKBUN AND B.TEL_NO='%s'", Number));
			Logger.Write.info(String.format("(MODULE) SKKU_IsRegistryStudent �ڵ������� üũ %s", sb.toString()));
		}
			break;
		case "hakbun": {
			if (Number.length() < 10) {
				if (Number.length() > 6) {
					if (Number.length() == 8) {
						Number = Number.substring(2, 8);
						System.out.println();
					} else {
						return ConditionType.N.getValue();
					}
				} else {
					if (Number.length() != 6) {
						return ConditionType.N.getValue();
					}
				}
				// ������� üũ
				sb.append(String.format("select DISTINCT KOR_NAME,A.HAKBUN from VW_ARS_HAKBUN a, VW_ARS_PHONE B WHERE A.HAKBUN=B.HAKBUN AND A.BIRTH_DT='%s'", Number));
				Logger.Write.info(String.format("(MODULE) SKKU_IsRegistryStudent ������Ϸ� üũ %s", sb.toString()));
			} else {

				// �й����� üũ
				sb.append(String.format("select DISTINCT KOR_NAME,A.HAKBUN from VW_ARS_HAKBUN a, VW_ARS_PHONE B WHERE A.HAKBUN=B.HAKBUN AND A.HAKBUN='%s'", Number));
				Logger.Write.info(String.format("(MODULE) SKKU_IsRegistryStudent �й����� üũ %s", sb.toString()));
			}
		}
			break;

		default:
			Logger.Write.warn("SSKU : Type�� phone �Ǵ� hakbun���� �����Ǿ���մϴ�.");
			return ConditionType.ERROR.getValue();
		}

		try {
			list = queryDb.querySkkuIsRegistryStudent(sb.toString());
			Logger.Write.info(String.format("(MODULE) SKKU_IsRegistryStudent ����Ʈ��ȯ [size:%d]", list.size()));
		} catch (Exception e) {
			Logger.Write.warn(e.getMessage(), e);
			return ConditionType.ERROR.getValue();
		}

		if (list == null || list.size() == 0) {
			return ConditionType.N.getValue();
		} else {
			String uuidKey = UUID.randomUUID().toString().replaceAll("-", "");
			personalMap.put(uuidKey, list);
			return ConditionType.Y.getValue() + "$" + uuidKey;
		}
	}

	public String SKKU_getNameTTSMent(String key) {
		ArrayList<IRecord> list = personalMap.get(key);
		if (list == null) {
			return ConditionType.FAIL.getValue();
		}

		if (list.size() >= 2) {

			return ConditionType.NEXT.getValue();
		}

		String name = "";
		for (IRecord record : list) {
			try {
				name = String.valueOf(record.getFieldValue(ColunmSKKU.KOR_NAME).getValue());
				System.out.println();
			} catch (KeyNullException e) {
				Logger.Write.error(e.getMessage(), e);
			}
		}
		String nameString = String.format("%s", name);
		return ConditionType.SUCCESS.getValue() + "$" + nameString;
	}

	public String BUSAN_CheckReservation(String PT_NO) {
		Logger.Write.info("[module] BUSAN_CheckReservation : " + PT_NO);
		ArrayList<IRecord> list = new ArrayList<IRecord>();
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("select PT_NAME,SS_NO_1,MED_DTTM,DEPT_NAME,GUBN from ARRTTSHTV where pt_no = '%s' order by MED_DTTM asc", PT_NO));
		try {
			list = queryDb.queryBusanReservation(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (list.size() == 0) {
			return ConditionType.N.getValue();
		} else {
			String uuidKey = UUID.randomUUID().toString().replaceAll("-", "");
			personalMap.put(uuidKey, list);
			return ConditionType.Y.getValue() + "$" + uuidKey;
		}
	}

	public String BUSAN_CheckReservationTTS(String key) {

		ArrayList<IRecord> list = personalMap.get(key);
		if (list != null && list.size() <= 0) {
			return ConditionType.FAIL.getValue();
		}

		StringBuilder sb = new StringBuilder();
		IRecord nrecord = list.get(0);

		String PT_NAME = "";

		Logger.Write.info("[module] BUSAN_CheckReservationTTS : ( SIZE - " + list.size() + " )");

		try {
			PT_NAME = String.valueOf(nrecord.getFieldValue(ColunmBusanBohun.PT_NAME).getValue());
		} catch (KeyNullException e1) {
			e1.printStackTrace();
		}

		sb.append(String.format("%s�Բ����� �� %d���� ����Ǿ� �ֽ��ϴ�. ", PT_NAME, list.size()));

		String[] starts = { "ù ��°,", "�� ��°,", "�� ��°,", "�� ��°,", "�ټ� ��°,", "���� ��°,", "�ϰ� ��°,", "���� ��°,", "��ȩ��°,", "�� ��°," };

		int count = 0;
		for (IRecord record : list) {
			try {
				String MED_DTTM = String.valueOf(record.getFieldValue(ColunmBusanBohun.MED_DTTM).getValue());
				String DEPT_NAME = String.valueOf(record.getFieldValue(ColunmBusanBohun.DEPT_NAME).getValue());
				String GUBN = String.valueOf(record.getFieldValue(ColunmBusanBohun.GUBN).getValue());
				// String DOCTOR_NAME =
				// String.valueOf(record.getFieldValue(ColunmBusanBohun.DOCTOR_NAME).getValue());

				String[] daytimes = MED_DTTM.split(" ");
				String time = "";
				try {
					time = daytimes[1].substring(0, 5);
				} catch (Exception e) {
				}
				sb.append(String.format("%s %s, %s, %s,�� ������ �Ǿ� �ֽ��ϴ�.", starts[count++], daytimes[0], time, DEPT_NAME));
				if (count == 10)
					break;
			} catch (KeyNullException e) {
				e.printStackTrace();
			}
		}

		return ConditionType.SUCCESS.getValue() + "$" + sb.toString();
	}

	public String NEPPA_CheckDayOfWeek(String patternDayString) {
		String temp = patternDayString;
		String[] checkDays = temp.split(",");

		Calendar cal = Calendar.getInstance();
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		String day = "";
		switch (dayOfWeek) {
		case 1:
			day = "��";
			break;
		case 2:
			day = "��";
			break;
		case 3:
			day = "ȭ";
			break;
		case 4:
			day = "��";
			break;
		case 5:
			day = "��";
			break;
		case 6:
			day = "��";
			break;
		case 7:
			day = "��";
			break;

		}

		for (String days : checkDays) {
			if (days.equalsIgnoreCase(day)) {
				return ConditionType.OK.getValue();
			}
		}

		return ConditionType.SUCCESS.getValue();
	}

	public String ISH_CheckReConsumer(String mode, String value) {
		if (mode.equalsIgnoreCase("cid")) {
			ArrayList<IRecord> list = new ArrayList<IRecord>();
			try {
				list = queryDb.queryReCusumerByCID(value, "VW_CALL_IVR_PATIENT_INFO");
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (list.size() == 0) {
				return ConditionType.N.getValue();
			} else {
				return ConditionType.Y.getValue();
			}
		} else if (mode.equalsIgnoreCase("personnum")) {
			int center = value.length() / 2;
			String personal1 = value.substring(0, center);
			String personal2 = value.substring(center, value.length());

			String mode_ = "jumin";
			if (value.length() >= 13) {
				mode_ = "jumin";
			} else {
				mode_ = "bunho";
			}

			ArrayList<IRecord> list = new ArrayList<IRecord>();
			try {
				list = queryDb.queryReCusumerByJUMIN(personal1, personal2, "VW_CALL_IVR_PATIENT_INFO", mode_);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (list.size() == 0) {
				return ConditionType.N.getValue();
			} else {
				return ConditionType.Y.getValue();
			}
		}
		return ConditionType.N.getValue();
	}

	public String ISH_CheckReservation(String personalNum) {

		int center = personalNum.length() / 2;
		String personal1 = personalNum.substring(0, center);
		String personal2 = personalNum.substring(center, personalNum.length());

		ArrayList<IRecord> list = new ArrayList<IRecord>();
		String mode = "jumin";
		if (personalNum.length() >= 13) {
			mode = "jumin";
		} else {
			mode = "bunho";
		}
		try {
			list = queryDb.queryReservation(personal1, personal2, "VW_CALL_IVR_RESER_INFO", mode);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (list.size() == 0) {
			return ConditionType.N.getValue();
		} else {
			String uuidKey = UUID.randomUUID().toString().replaceAll("-", "");
			personalMap.put(uuidKey, list);
			return ConditionType.Y.getValue() + "$" + uuidKey;
		}
	}

	public String ISH_SayName(String key) {
		ArrayList<IRecord> list = personalMap.get(key);
		String name = "";
		for (IRecord record : list) {
			try {
				name = String.valueOf(record.getFieldValue(ColunmReservation.SUNAME).getValue());
			} catch (KeyNullException e) {
				e.printStackTrace();
			}
		}
		StringBuffer sb = new StringBuffer();
		for (char n : name.toCharArray()) {
			sb.append(n + ".");
		}
		String nameString = String.format("������ ������, %s,��, ", sb.toString());
		return ConditionType.SUCCESS.getValue() + "$" + nameString;
	}

	private String reservDate(String PRE_DATE, String PRE_TIME) {

		String date_ = PRE_DATE;
		String time = PRE_TIME;
		String newDate = "";
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(date_);
			newDate = new SimpleDateFormat("yyyy�� MM�� dd��,").format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		int hour = Integer.parseInt(time.substring(0, 2));
		int minute = Integer.parseInt(time.substring(2, 4));
		String timeString = "";
		if (hour > 12) {
			hour = hour - 12;
			timeString = String.format("���� %d�� %d��", hour, minute);
		} else {
			timeString = String.format("���� %d�� %d��", hour, minute);
		}

		return newDate + timeString;
	}

	public String ISH_SayReservTTS(String key) {
		ArrayList<IRecord> list = personalMap.get(key);
		if (list != null && list.size() <= 0) {
			return ConditionType.FAIL.getValue();
		}

		StringBuilder sb = new StringBuilder();
		IRecord nrecord = list.get(0);
		String SUNAME = "";
		try {
			SUNAME = String.valueOf(nrecord.getFieldValue(ColunmReservation.SUNAME).getValue());
		} catch (KeyNullException e1) {
			e1.printStackTrace();
		}
		sb.append(String.format("%s�Բ����� �� %d���� ����Ǿ� �ֽ��ϴ�. ", SUNAME, list.size()));

		String[] starts = { "ù ��°,", "�� ��°,", "�� ��°,", "�� ��°,", "�ټ� ��°,", "���� ��°,", "�ϰ� ��°,", "���� ��°,", "��ȩ��°,", "�� ��°," };
		int count = 0;
		for (IRecord record : list) {
			try {
				String JINRYO_PRE_DATE = String.valueOf(record.getFieldValue(ColunmReservation.JINRYO_PRE_DATE).getValue());
				String JINRYO_PRE_TIME = String.valueOf(record.getFieldValue(ColunmReservation.JINRYO_PRE_TIME).getValue());
				String GWA_NAME = String.valueOf(record.getFieldValue(ColunmReservation.GWA_NAME).getValue());
				String DOCTOR_NAME = String.valueOf(record.getFieldValue(ColunmReservation.DOCTOR_NAME).getValue());

				sb.append(String.format("%s %s %s %s�����Բ�, ������ �Ǿ� �ֽ��ϴ�.", starts[count++], reservDate(JINRYO_PRE_DATE, JINRYO_PRE_TIME), GWA_NAME, DOCTOR_NAME));
			} catch (KeyNullException e) {
				e.printStackTrace();
			}
		}

		return ConditionType.SUCCESS.getValue() + "$" + sb.toString();
	}

	public String ISH_TimeoutCount() {
		if (timeOut_count == 2) {
			return ConditionType.FAIL.getValue();
		} else {
			timeOut_count++;
			return ConditionType.SUCCESS.getValue();
		}
	}

	private Map<String, String> svcMap = new HashMap<String, String>();

	public String ANYANG_SettingSvcCode(String key, String svcCode) {
		svcMap.put(key, svcCode);
		return ConditionType.SUCCESS.getValue();
	}

	public String ANYANG_insertCallback(String callbackNumber, String serviceCodeKey) {
		Logger.Write.info("[module] ANYANG_insertCallback " + String.format("[CallbackNumber:%s][ServiceCodeKey:%s]", callbackNumber, serviceCodeKey));
		Integer list = new Integer(0);
		String SCHDUL_TYPE_CD = "CB";
		String SCHEMA_NAME = "UCAREAPP_AYCITY";
		String SVC_CODE = svcMap.get(serviceCodeKey);

		if (SVC_CODE.equalsIgnoreCase("S1710")) {
			SCHDUL_TYPE_CD = "WF";
			SCHEMA_NAME = "UCAREAPP_AYCITY_WEL";
		}

		try {
			list = queryDb.insertCallback(callbackNumber, SVC_CODE, SCHEMA_NAME, SCHDUL_TYPE_CD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list < 0) {
			return ConditionType.FAIL.getValue();
		} else {
			return ConditionType.SUCCESS.getValue();
		}
	}

	// 2020.09.14 �߾Ӻ��ƺ��� ���Ό�� Oracle ���� + seoulBohun_CheckReservation
	// �߰����� ���� Ȯ���ؾ�����
	public String seoulBohun_CheckReservation(String PT_NO) {
		Logger.Write.info("[module] seoulBohun_CheckReservation : " + PT_NO);
		ArrayList<IRecord> list = new ArrayList<IRecord>();
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("select PT_NAME,SS_NO_1,MED_DTTM,DEPT_NAME,GUBN from ARRTTSHTV where pt_no = '%s' order by MED_DTTM asc", PT_NO));
		try {
			list = queryDb.querySeoulBouhynReservation(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (list.size() == 0) {
			return ConditionType.N.getValue();
		} else {
			String uuidKey = UUID.randomUUID().toString().replaceAll("-", "");
			personalMap.put(uuidKey, list);
			return ConditionType.Y.getValue() + "$" + uuidKey;
		}
	}

	// 2020.09.14 �߾Ӻ��ƺ��� ���Ό�� Oracle ���� + seoulBohun_CheckReservationTTS
	public String seoulBohun_CheckReservationTTS(String key) {

		ArrayList<IRecord> list = personalMap.get(key);
		if (list != null && list.size() <= 0) {
			return ConditionType.FAIL.getValue();
		}

		StringBuilder sb = new StringBuilder();
		IRecord nrecord = list.get(0);

		String PT_NAME = "";

		Logger.Write.info("[module] seoulBohun_CheckReservationTTS : ( SIZE - " + list.size() + " )");

		try {
			PT_NAME = String.valueOf(nrecord.getFieldValue(ColunmSeoulBohun.PT_NAME).getValue());
		} catch (KeyNullException e1) {
			e1.printStackTrace();
		}

		sb.append(String.format("%s�Բ����� �� %d���� ����Ǿ� �ֽ��ϴ�. ", PT_NAME, list.size()));

		String[] starts = { "ù ��°,", "�� ��°,", "�� ��°,", "�� ��°,", "�ټ� ��°,", "���� ��°,", "�ϰ� ��°,", "���� ��°,", "��ȩ��°,", "�� ��°," };

		int count = 0;
		for (IRecord record : list) {
			try {
				String MED_DTTM = String.valueOf(record.getFieldValue(ColunmSeoulBohun.MED_DTTM).getValue());
				String DEPT_NAME = String.valueOf(record.getFieldValue(ColunmSeoulBohun.DEPT_NAME).getValue());
				String GUBN = String.valueOf(record.getFieldValue(ColunmSeoulBohun.GUBN).getValue());
				// String DOCTOR_NAME =
				// String.valueOf(record.getFieldValue(ColunmSeoulBohun.DOCTOR_NAME).getValue());

				String[] daytimes = MED_DTTM.split(" ");
				String time = "";
				try {
					time = daytimes[1].substring(0, 5);
				} catch (Exception e) {
				}
				sb.append(String.format("%s %s, %s, %s,�� ������ �Ǿ� �ֽ��ϴ�.", starts[count++], daytimes[0], time, DEPT_NAME));
				if (count == 10)
					break;
			} catch (KeyNullException e) {
				e.printStackTrace();
			}
		}

		return ConditionType.SUCCESS.getValue() + "$" + sb.toString();
	}

	// 2020.10.05 �츮�ſ����� �������� ���� ���� ��ȸ
	public String Woori_CheckPrivacy(String personalNum) {
		Logger.Write.info("[module] Woori_CheckPrivacy : ( personalNum = " + personalNum);
		int center = personalNum.length() / 2;
//		String personal1 = personalNum.substring(0, center);
//		String personal2 = personalNum.substring(center, personalNum.length());

		ArrayList<IRecord> list = new ArrayList<IRecord>();
		String mode = "jumin";
		if (personalNum.length() >= 13) {
			mode = "jumin";
		} else {
			mode = "bunho";
		}
		try {
			list = queryDb.queryWooriCreditPrivacy(personalNum);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (list.size() == 0) {
			return ConditionType.N.getValue();
		} else {
			String uuidKey = UUID.randomUUID().toString().replaceAll("-", "");
			personalMap.put(uuidKey, list);
			return ConditionType.Y.getValue() + "$" + uuidKey;
		}
	}

	// 2020.10.06 �츮�ſ����� ������ ��ȸ
//	public String Woori_CustomerInformation(String cid, String gubun, String group, String pressureOfBusiness) {
//
//		Logger.Write.info("[module] Woori_CustomerInformation : ( 1.cid = " + cid + "2.gubun = " + gubun + "3.group = "
//				+ group + "4.pressureOfBusiness = " + pressureOfBusiness);
//		
//		Map<String,String> resultMap = new HashMap<String,String>();
//		ArrayList<IRecord> list = new ArrayList<IRecord>();
//		try {
//			resultMap = queryDb.queryWooriCustomerInformation(cid, gubun, group, pressureOfBusiness);
//		} catch (Exception e) {
//			Logger.Write.error(e.getMessage(), e);
//			return ConditionType.N.getValue();
//		}
//
//		if (resultMap.size() == 0) {
//			return ConditionType.N.getValue();
//		} else {
//			
//			
//			 String uuidKey = UUID.randomUUID().toString().replaceAll("-", "");
//			 personalMap.put(uuidKey, list);
//			IRecord nrecord = list.get(0);
//			String targetExtension;
//			try {
//				targetExtension = String.valueOf(nrecord.getFieldValue(ColumnCustomerInfo.INNO).getValue());
//			} catch (KeyNullException e) {
//				Logger.Write.error(e.getMessage(), e);
//				return ConditionType.N.getValue();
//			}
//			return ConditionType.Y.getValue() + "$" + targetExtension;
//		}
//
//		/*
//		 * if(gubun == "CARD") // ī�� �ݼ��� ��ǥ��ȣ { try { list =
//		 * queryDb.queryWooriCustomerInformation(cid, gubun, group, pressureOfBusiness);
//		 * } catch (Exception e) { e.printStackTrace(); }
//		 * 
//		 * if (list.size() == 0) { return ConditionType.N.getValue(); } else { String
//		 * uuidKey = UUID.randomUUID().toString().replaceAll("-", "");
//		 * personalMap.put(uuidKey, list); return ConditionType.Y.getValue() + "$" +
//		 * uuidKey; } }else if(gubun == "COMM") //��� �ݼ��� ��ǥ��ȣ { try { list =
//		 * queryDb.queryWooriCustomerInformation(cid, gubun, group, pressureOfBusiness);
//		 * } catch (Exception e) { e.printStackTrace(); }
//		 * 
//		 * if (list.size() == 0) { return ConditionType.N.getValue(); } else { String
//		 * uuidKey = UUID.randomUUID().toString().replaceAll("-", "");
//		 * personalMap.put(uuidKey, list); return ConditionType.Y.getValue() + "$" +
//		 * uuidKey; } } else if(gubun == "DID") //DID ���� ���� group = 1 (ī��), group = 2
//		 * (���LGU), group = 3 (���SKT), pressureOfBusiness ������ { try { list =
//		 * queryDb.queryWooriCustomerInformation(cid, gubun, group, pressureOfBusiness);
//		 * } catch (Exception e) { e.printStackTrace(); }
//		 * 
//		 * if (list.size() == 0) { return ConditionType.N.getValue(); } else { String
//		 * uuidKey = UUID.randomUUID().toString().replaceAll("-", "");
//		 * personalMap.put(uuidKey, list); return ConditionType.Y.getValue() + "$" +
//		 * uuidKey; } }
//		 */
//
//		// return ConditionType.N.getValue();
//	}

///////////////////////////////////////////////////////////////////////////////////////////
	// 2020.10.06 �츮�ſ����� ������ ��ȸ

	private String woori_agent_ext = "";

	public String woori_get_cui_info_result() {
		synchronized (woori_agent_ext) {
			if (woori_agent_ext.equals("0000"))
				return ConditionType.FAIL.getValue();
			return ConditionType.SUCCESS.getValue() + "$" + woori_agent_ext;
		}
	}

	public String Woori_CustomerInformation(String cid, String gubun, String group, String pressureOfBusiness) {

		Logger.Write.info("[module] Woori_CustomerInformation : ( 1.cid = " + cid + "2.gubun = " + gubun);
		Logger.Write.info("3.group = " + group + "4.pressureOfBusiness = " + pressureOfBusiness);

		synchronized (woori_agent_ext) {

			woori_agent_ext = "";

			new Thread(() -> {
				synchronized (woori_agent_ext) {
					try {
						ArrayList<IRecord> list = queryDb.queryWooriCustomerInformation(cid, gubun, group, pressureOfBusiness);

						IRecord nrecord = list.get(0);
						String BRANCHNAME = String.valueOf(nrecord.getFieldValue(ColumnCustomerInfo.INNO).getValue());
						String MEMBERNAME = String.valueOf(nrecord.getFieldValue(ColumnCustomerInfo.IDNM).getValue());
						String CUSTOM_NAME = String.valueOf(nrecord.getFieldValue(ColumnCustomerInfo.CUSTNM).getValue());
						String CUSTOM_ID = String.valueOf(nrecord.getFieldValue(ColumnCustomerInfo.BDNO).getValue());
						String AGENT_ID = String.valueOf(nrecord.getFieldValue(ColumnCustomerInfo.IDNO).getValue());

						if (list.size() != 0) {
							if (BRANCHNAME.equalsIgnoreCase("0000")) {
								Logger.Write.info(" BRANCHNAME : " + BRANCHNAME);
								woori_agent_ext = "0000";
							} else {
								try {
									woori_agent_ext = String.valueOf(nrecord.getFieldValue(ColumnCustomerInfo.INNO).getValue());
								} catch (KeyNullException e) {
									Logger.Write.error(e.getMessage(), e);
								}

							}
						} else {
							woori_agent_ext = "0000";
						}

						Logger.Write.info(" WooriCustomerInformation : " + BRANCHNAME + MEMBERNAME + CUSTOM_NAME + CUSTOM_ID + AGENT_ID);
					} catch (Exception e) {
						Logger.Write.error(e.getMessage(), e);
					}
				}
			}).start();
		}

		return ConditionType.SUCCESS.getValue();

		/*
		 * 
		 * try { list = queryDb.queryWooriCustomerInformation(cid, gubun, group, pressureOfBusiness);
		 * 
		 * IRecord nrecord = list.get(0); BRANCHNAME = String.valueOf(nrecord.getFieldValue(ColumnCustomerInfo.INNO).getValue()); MEMBERNAME = String.valueOf(nrecord.getFieldValue(ColumnCustomerInfo.IDNM).getValue()); CUSTOM_NAME = String.valueOf(nrecord.getFieldValue(ColumnCustomerInfo.CUSTNM).getValue()); CUSTOM_ID = String.valueOf(nrecord.getFieldValue(ColumnCustomerInfo.BDNO).getValue()); AGENT_ID = String.valueOf(nrecord.getFieldValue(ColumnCustomerInfo.IDNO).getValue()); Logger.Write.info(" WooriCustomerInformation : " +BRANCHNAME+MEMBERNAME+CUSTOM_NAME+CUSTOM_ID+AGENT_ID);
		 * 
		 * 
		 * } catch (Exception e) { Logger.Write.error(e.getMessage(), e); return ConditionType.N.getValue(); }
		 * 
		 * if (list.size() == 0) { return ConditionType.N.getValue(); } else { // String uuidKey = UUID.randomUUID().toString().replaceAll("-", ""); // personalMap.put(uuidKey, list); if(BRANCHNAME.equalsIgnoreCase("0000")) { Logger.Write.info(" BRANCHNAME : " +BRANCHNAME); return ConditionType.N.getValue();
		 * 
		 * }
		 * 
		 * IRecord nrecord = list.get(0); String targetExtension; try { targetExtension = String.valueOf(nrecord.getFieldValue(ColumnCustomerInfo.INNO).getValue()); } catch (KeyNullException e) { Logger.Write.error(e.getMessage(), e); return ConditionType.N.getValue(); } return ConditionType.Y.getValue() + "$" + targetExtension; }
		 * 
		 */

		/*
		 * if(gubun == "CARD") // ī�� �ݼ��� ��ǥ��ȣ { try { list = queryDb.queryWooriCustomerInformation(cid, gubun, group, pressureOfBusiness); } catch (Exception e) { e.printStackTrace(); }
		 * 
		 * if (list.size() == 0) { return ConditionType.N.getValue(); } else { String uuidKey = UUID.randomUUID().toString().replaceAll("-", ""); personalMap.put(uuidKey, list); return ConditionType.Y.getValue() + "$" + uuidKey; } }else if(gubun == "COMM") //��� �ݼ��� ��ǥ��ȣ { try { list = queryDb.queryWooriCustomerInformation(cid, gubun, group, pressureOfBusiness); } catch (Exception e) { e.printStackTrace(); }
		 * 
		 * if (list.size() == 0) { return ConditionType.N.getValue(); } else { String uuidKey = UUID.randomUUID().toString().replaceAll("-", ""); personalMap.put(uuidKey, list); return ConditionType.Y.getValue() + "$" + uuidKey; } } else if(gubun == "DID") //DID ���� ���� group = 1 (ī��), group = 2 (���LGU), group = 3 (���SKT), pressureOfBusiness ������ { try { list = queryDb.queryWooriCustomerInformation(cid, gubun, group, pressureOfBusiness); } catch (Exception e) { e.printStackTrace(); }
		 * 
		 * if (list.size() == 0) { return ConditionType.N.getValue(); } else { String uuidKey = UUID.randomUUID().toString().replaceAll("-", ""); personalMap.put(uuidKey, list); return ConditionType.Y.getValue() + "$" + uuidKey; } }
		 */

		// return ConditionType.N.getValue();
	}

//////////////////////////////////////////////////////////////////////////////////////////////////

	// 2020.10.06 �츮�ſ����� �ݹ�
	// GROUP2 VARCHAR2 DNIS ��
	// REG_DAY VARCHAR2 �������
	// REG_NO NUMBER ������ں� ����
	// AGENT_ID VARCHAR2 ���� ��� CTI���̺��� ���
	// BRANCHNAME VARCHAR2 ������ȣ
	// MEMBERNAME VARCHAR2 �����̸�
	// CUSTOM_NAME VARCHAR2 ���̸�
	// CUSTOM_ID VARCHAR2 ä�ǹ�ȣ
	// TEL_NO_1 VARCHAR2 �ݹ� ��ȣ
	// DIST_FLAG VARCHAR2 [000] ��������ȣ / [001] �׷쿹��ȣ]

	private String woori_callback_cid = "";

	public String woori_get_callback_cid_result() {
		synchronized (woori_callback_cid) {
			if (woori_callback_cid.equals(""))
				return ConditionType.FAIL.getValue();
			return ConditionType.SUCCESS.getValue() + "$" + woori_callback_cid;
		}
	}

	public String Woori_CallbackReservation(String cid, String did, String gubun, String group, String pressureOfBusiness, String callbacktype) {
		Logger.Write.info("[module] Woori_CallbackReservation : ( cid = " + cid + " did =" + did + "gubun = " + gubun + " group = " + group + " pressureOfBusiness = " + pressureOfBusiness);

		synchronized (woori_callback_cid) {
			woori_callback_cid = "";

			new Thread(() -> {
				synchronized (woori_callback_cid) {

					String GROUP2 = did;
					String DIST_FLAG = "";
					String BRANCHNAME = "";
					String MEMBERNAME = "";
					String CUSTOM_NAME = "";
					String CUSTOM_ID = "";
					String AGENT_ID = "";
					String TEL_NO_1 = cid;
					woori_callback_cid = cid;
//					incommingLocalDateTime = LocalDateTime.now();
//					String localDate = incommingLocalDateTime.toString();
//					String parsingDate = localDate.replaceAll("-", "");
 
//					// REG_NO ������ں� ����
//					if (!tempdate.equals(REG_DAY)) {
//						todayCount = 0;
//						todayCount++;
//						REG_NO = todayCount;
//						tempdate = REG_DAY;
//					} else if (tempdate.equals(REG_DAY)) {
//						todayCount++;
//						REG_NO = todayCount;
//					}

					// DIST_FLAG [000] ��������ȣ / [001] �׷쿹��ȣ]
					if (callbacktype.equals("000")) { // ����
						DIST_FLAG = "000";
					} else { // �׷�
						DIST_FLAG = "001";
					}
					

					ArrayList<IRecord> list = new ArrayList<IRecord>();
					try {
						list = queryDb.queryWooriCustomerInformation(cid, gubun, group, pressureOfBusiness);
					} catch (Exception e) {
						Logger.Write.error(e.getMessage(), e);
					}


					
					if (list.size() != 0) {
						IRecord nrecord = list.get(0);
						try {
							BRANCHNAME = String.valueOf(nrecord.getFieldValue(ColumnCustomerInfo.INNO).getValue());
							MEMBERNAME = String.valueOf(nrecord.getFieldValue(ColumnCustomerInfo.IDNM).getValue());
							CUSTOM_NAME = String.valueOf(nrecord.getFieldValue(ColumnCustomerInfo.CUSTNM).getValue());
							CUSTOM_ID = String.valueOf(nrecord.getFieldValue(ColumnCustomerInfo.BDNO).getValue());
							AGENT_ID = String.valueOf(nrecord.getFieldValue(ColumnCustomerInfo.IDNO).getValue());
						} catch (KeyNullException e) {
							Logger.Write.error(e.getMessage(), e);
						}
					}

					try {
						Logger.Write.info("[module] Woori_CallbackReservation : " + GROUP2 + AGENT_ID + BRANCHNAME + MEMBERNAME + CUSTOM_NAME + CUSTOM_ID + TEL_NO_1 + DIST_FLAG);
						queryDb.queryWooriCallbackReservation(GROUP2, AGENT_ID, BRANCHNAME, MEMBERNAME, CUSTOM_NAME, CUSTOM_ID, TEL_NO_1, DIST_FLAG);

					} catch (Exception e) {
						Logger.Write.error(e.getMessage(), e);
					}

				}
			}).start();
		}

		return ConditionType.SUCCESS.getValue();
	}
}
