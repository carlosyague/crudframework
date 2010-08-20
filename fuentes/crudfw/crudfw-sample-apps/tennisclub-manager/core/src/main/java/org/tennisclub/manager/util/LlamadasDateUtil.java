package org.tennisclub.manager.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public final class LlamadasDateUtil {

	public static final Object[] MES_ENE     = {Calendar.JANUARY, "Ene."};
	public static final Object[] MES_FEB     = {Calendar.FEBRUARY, "Feb."};
	public static final Object[] MES_MAR     = {Calendar.MARCH, "Mar."};
	public static final Object[] MES_ABR     = {Calendar.APRIL, "Abr."};	
	public static final Object[] MES_MAY     = {Calendar.MAY, "May."};
	public static final Object[] MES_JUN     = {Calendar.JUNE, "Jun."};
	public static final Object[] MES_JUL     = {Calendar.JULY, "Jul."};
	public static final Object[] MES_AGO     = {Calendar.AUGUST, "Ago."};
	public static final Object[] MES_SEP     = {Calendar.SEPTEMBER, "Sep."};
	public static final Object[] MES_OCT     = {Calendar.OCTOBER, "Oct."};
	public static final Object[] MES_NOV     = {Calendar.NOVEMBER, "Nov."};
	public static final Object[] MES_DIC     = {Calendar.DECEMBER, "Dic."};
		
	public static int getMonthKey(Object[] month) {
		return (Integer)month[0];
	}
	
	public static String getMonthValue(Object[] month) {
		return (String)month[1];
	}
	
	public static boolean isMonthKey(int monthKey, Object[] month) {
		return monthKey == getMonthKey(month);
	}
	
	public static boolean isMonthValue(String monthStr, Object[] month) {
		return monthStr.equalsIgnoreCase(getMonthValue(month));
	}
	
	public static int parseMonth(String monthStr) {
		int result = -1;
		
		if (monthStr == null) {
			result = -1;
			
		} else if (isMonthValue(monthStr, MES_ENE)) {
			result = getMonthKey(MES_ENE);
			
		} else if (isMonthValue(monthStr, MES_FEB)) {
			result = getMonthKey(MES_FEB);
			
		} else if (isMonthValue(monthStr, MES_MAR)) {
			result = getMonthKey(MES_MAR);
			
		} else if (isMonthValue(monthStr, MES_ABR)) {
			result = getMonthKey(MES_ABR);
			
		} else if (isMonthValue(monthStr, MES_MAY)) {
			result = getMonthKey(MES_MAY);
			
		} else if (isMonthValue(monthStr, MES_JUN)) {
			result = getMonthKey(MES_JUN);
			
		} else if (isMonthValue(monthStr, MES_JUL)) {
			result = getMonthKey(MES_JUL);
			
		} else if (isMonthValue(monthStr, MES_AGO)) {
			result = getMonthKey(MES_AGO);
			
		} else if (isMonthValue(monthStr, MES_SEP)) {
			result = getMonthKey(MES_SEP);
		
		} else if (isMonthValue(monthStr, MES_OCT)) {
			result = getMonthKey(MES_OCT);
		
		} else if (isMonthValue(monthStr, MES_NOV)) {
			result = getMonthKey(MES_NOV);
		
		} else if (isMonthValue(monthStr, MES_DIC)) {
			result = getMonthKey(MES_DIC);
		}
		
		return result;
	}
	
	public static String parseMonth(int monthKey) {
		String result = null;
		
		if (monthKey < 0) {
			result = "";
			
		} else if (isMonthKey(monthKey, MES_ENE)) {
			result = getMonthValue(MES_ENE);
			
		} else if (isMonthKey(monthKey, MES_FEB)) {
			result = getMonthValue(MES_FEB);
			
		} else if (isMonthKey(monthKey, MES_MAR)) {
			result = getMonthValue(MES_MAR);
			
		} else if (isMonthKey(monthKey, MES_ABR)) {
			result = getMonthValue(MES_ABR);
			
		} else if (isMonthKey(monthKey, MES_MAY)) {
			result = getMonthValue(MES_MAY);
			
		} else if (isMonthKey(monthKey, MES_JUN)) {
			result = getMonthValue(MES_JUN);
			
		} else if (isMonthKey(monthKey, MES_JUL)) {
			result = getMonthValue(MES_JUL);
			
		} else if (isMonthKey(monthKey, MES_AGO)) {
			result = getMonthValue(MES_AGO);
			
		} else if (isMonthKey(monthKey, MES_SEP)) {
			result = getMonthValue(MES_SEP);
		
		} else if (isMonthKey(monthKey, MES_OCT)) {
			result = getMonthValue(MES_OCT);
		
		} else if (isMonthKey(monthKey, MES_NOV)) {
			result = getMonthValue(MES_NOV);
		
		} else if (isMonthKey(monthKey, MES_DIC)) {
			result = getMonthValue(MES_DIC);
		}
		
		return result;
	}
	
	public static Date getDate(String fecha, String hora) {
		final ArrayList<String> tokensFecha = TokensUtil.extractSpacedTokens(fecha);		
		final ArrayList<String> tokensHora = TokensUtil.extractTokenList(hora, ":");
		
		Calendar calendar = GregorianCalendar.getInstance();
		
		if (tokensFecha != null && tokensFecha.size() == 2) {
			calendar.set(Calendar.MONTH, Integer.parseInt(tokensFecha.get(0)));
			calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(tokensFecha.get(1)));
		}
		
		if (tokensHora != null && tokensHora.size() == 3) {
			calendar.set(Calendar.HOUR, Integer.parseInt(tokensHora.get(0)));
			calendar.set(Calendar.MINUTE, Integer.parseInt(tokensHora.get(1)));
			calendar.set(Calendar.SECOND, Integer.parseInt(tokensHora.get(2)));
		}
		
		return calendar.getTime();
	}
}
