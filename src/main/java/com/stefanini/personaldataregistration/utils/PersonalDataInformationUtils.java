package com.stefanini.personaldataregistration.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonalDataInformationUtils {

	/**
	 * 
	 * @return
	 */
	public static String getFormattedDateNowAsString(){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");  
		LocalDateTime now = LocalDateTime.now();  
		return dtf.format(now);
	}

	
	public static String getDate() {
		String pattern = "dd-MM-yyyy";
		return formatDateByPattern(pattern);
	}

	public static String getDateNow() {
		String pattern = "dd-MM-yyyy HH:mm:ss";
		return formatDateByPattern(pattern);
	}
	
	public static String getDateMilleSeconds() {
		String pattern = "dd-MM-yyyy HH:mm:ss:SSS";
		return formatDateByPattern(pattern);
	}
	
	@SuppressWarnings("static-access")
	private static String formatDateByPattern(String pattern) {
		ZonedDateTime date = null;
		String dateFormatted = null;
		try {
			ZoneId zonedIdSaoPaulo = ZoneId.of("America/Sao_Paulo");
			ZonedDateTime nowSaoPaulo = ZonedDateTime.now(zonedIdSaoPaulo);
			date = nowSaoPaulo.of(nowSaoPaulo.toLocalDateTime(), zonedIdSaoPaulo);
			date.withZoneSameLocal(ZoneId.of("UTC"));
			dateFormatted = java.time.format.DateTimeFormatter.ofPattern(pattern).withZone(zonedIdSaoPaulo).format(date);
		} catch (Exception e) {
		}
		return dateFormatted;

	} 
	
	public static String convertStringDateToBrDate(String date) {
		String dateString = "";
		if(date!=null){
			DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			dateString = LocalDate.parse(date, formatter2).format(formatter).toString();
		}
		return dateString.toString();
	}
	
	public static String convertStringDateToMysqlDate(String date) {
		String dateString = "";
		if(date!=null){
			DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			dateString = LocalDate.parse(date, formatter2).format(formatter).toString();
		}
		return dateString.toString();
	}
	
	
	public static String conversqlDateIntoString(Date date){
		String data =null;
		if(date!=null){
			java.sql.Date dt=new java.sql.Date(date.getTime()); //your SQL date object
			SimpleDateFormat simpDate = new SimpleDateFormat("dd-MM-yyyy");
			data = simpDate.format(dt);
		}
		return data;
	}
	
	public static String convertFormatDateToString(java.util.Date date){
		return new SimpleDateFormat("MM-dd-yyyy").format(date);
	}
	
	/**
	 * validate email, if not filled consider a valid value
	 * @param email
	 * @return
	 */
	public static boolean validateEmail(String email){
		if(email ==null  || email.isEmpty()){
			return true;
		}
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);  
		Matcher matcher = pattern.matcher(email); 
		boolean isValidEmail = matcher.matches();
		return isValidEmail;
	}
}
