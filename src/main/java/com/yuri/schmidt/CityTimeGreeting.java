package com.yuri.schmidt;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Console application that prints out greetings when typing the names of cities from around the world.
 * The form of greeting i.e. 'Good morning. Paris!', 'Good evening. New York!' depends on the locale
 * of the city at the moment of typing.
 * The cities must be typed exactly like they are written in the Java TimeZone Ids list. The typing in this
 * case is case insensitive. 
 * The output is internationalized.
 * 
 * @author Yuri Schmidt
 *
 */
public class CityTimeGreeting {

	public static void main(String[] args) {

		Locale locale = Locale.getDefault();
		ResourceBundle bundle = ResourceBundle.getBundle("greeting", locale);

		//checking the number of arguments. If zero or more than two then exit with Error message.
		if (args.length == 0 || args.length > 2) {
			System.out.println(bundle.getString("outnumberedParams"));
			return;
		}
		//if there is only one argument
		if (args.length == 1) {

			String enteredCityName = args[0]; //the first param - name of the city

			String zoneId = FindZoneIdByCityName(enteredCityName);// zone Id
			if (zoneId == null) {
				System.out.println(bundle.getString("cityMissing"));//warning! no such city in the list of time zones
			} else {
				Set<String> allZoneIds = ZoneId.getAvailableZoneIds();// finding
																		// all
																		// zoneIds
				String name = null;// auxiliary variable
				for (String s : allZoneIds) {//for all records in the list find the city 
					if (s.toLowerCase().contains(enteredCityName.toLowerCase())) {// if the entered name is present in allZoneIds
																				// the city name is extracted from allZoneIds and
																				// cleared of underscores '_'
						name = s.substring(s.lastIndexOf("/") + 1).replace("_", " ");
					}
				}

				LocalTime currentTime = LocalTime.now(ZoneId.of(zoneId)); // current time in the distant zone

				DisplayGreeting(currentTime, name);

			}//end of else
		} // end of IF
		
		//case where there are two arguments for the main method
		if (args.length == 2) {
			String enteredName = args[0];//the first mandatory parameter
			String gMtPlusValue = args[1];//the second OPTIONAL parameter
			
			//check the second argument against a regular expression
			String regex = "(GMT)([+-])([1-9]|1[0-2])";
			
			//if the parameter is typed incorrectly then exit
			if(!Pattern.matches(regex, gMtPlusValue)){
				System.out.println(bundle.getString("mismatch"));
				return;
			}
			
			String zoneIdFull = "Etc/" + gMtPlusValue; //zoneId
			
			LocalTime timeOfCity = LocalTime.now(ZoneId.of(zoneIdFull)); //local time in the GMT+-/d aria
			
			DisplayGreeting(timeOfCity, enteredName);
//			System.out.println("GMT: " + LocalTime.now(ZoneId.of("Etc/GMT")));
//			System.out.println("Local Time in Dnipro: " + timeOfCity);
		} //end of if

	} // end of main

	/**
	 * Greeting phrases depending on time periods of the day
	 * @param time time of the city's zone
	 * @return returning a greeting phrase
	 */
	public static String Greeting(LocalTime time) {

		final LocalTime MORNING_START = LocalTime.of(6, 0);
		final LocalTime MORNING_END = LocalTime.of(8, 59);
		final LocalTime DAY_START = LocalTime.of(9, 0);
		final LocalTime DAY_END = LocalTime.of(18, 59);
		final LocalTime EVENING_START = LocalTime.of(19, 0);
		final LocalTime EVENING_END = LocalTime.of(22, 59);
		final LocalTime NIGHT_START = LocalTime.of(23, 0);
		final LocalTime MIDNIGHT_BEFORE_NULL = LocalTime.of(23, 59);
		final LocalTime MIDNIGHT_AFTER_NULL = LocalTime.of(0, 0);
		final LocalTime NIGHT_END = LocalTime.of(5, 59);

		String goodGreeting = null;//the returning value

		Locale locale = Locale.getDefault();

		ResourceBundle bundle = ResourceBundle.getBundle("greeting", locale);

		if (time.isAfter(MORNING_START) && time.isBefore(MORNING_END)) {
			goodGreeting = bundle.getString("morning");
		}

		if (time.isAfter(DAY_START) && time.isBefore(DAY_END)) {
			goodGreeting = bundle.getString("day");
		}

		if (time.isAfter(EVENING_START) && time.isBefore(EVENING_END)) {
			goodGreeting = bundle.getString("evening");
		}

		if ((time.isAfter(NIGHT_START) && time.isBefore(MIDNIGHT_BEFORE_NULL))||
				(time.isAfter(MIDNIGHT_AFTER_NULL) && time.isBefore(NIGHT_END))) {
			goodGreeting = bundle.getString("night");
		}

		return goodGreeting;
	}//end of Greeting method

	/**
	 * Finding zone id by the city name
	 * @param cityParam name of the city in the standard list of zone ids
	 * @return returning zone id
	 */
	public static String FindZoneIdByCityName(String cityParam) {
		String str = null; // auxiliary variable
		Set<String> allZoneIds = ZoneId.getAvailableZoneIds();// finding all
																// zoneIds
		// checking for identity of City names in the list of zone Ids
		for (String s : allZoneIds) {
			if (s.toLowerCase().contains(cityParam.toLowerCase())) {
				str = s;
				break;
			}
		}
		return str;
	} //end of FindZoneIdByCityName method
	
	/**
	 * Display greeting
	 * @param t time of the zone
	 * @param n name of the city
	 */
	public static void DisplayGreeting(LocalTime t, String n){
		System.out.println(Greeting(t) + " " + n + "!"); //the result of the programm
		
	}

}// end of class
