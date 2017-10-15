package com.yuri.schmidt;

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZonedTimeGreeterTest {
	
	static final Logger LOGGER = LoggerFactory.getLogger(ZonedTimeGreeterTest.class);
	
	@Rule
	public final TestRule watchman = new TestWatcher() {
	@Override
	protected void succeeded(Description description){
		LOGGER.info("TEST of METHOD {} SUCCEEDED.", description.getMethodName());
	}
	@Override
	protected void failed(Throwable e, Description description){
		LOGGER.error("TEST of METHOD {} FAILED with {}.", description.getMethodName(), e.getClass().getSimpleName());
	}
	
	};

	@Test
	public void testFindZoneIdByCityName() {
		String cityParam = "New_York";
		String str = CityTimeGreeting.FindZoneIdByCityName(cityParam);
		assertEquals("Saying America/New_York", "America/New_York", str);
		
	}
	@Test
	public void testGreetingMorning() {
		Locale locale = Locale.getDefault();
		ResourceBundle bundle = ResourceBundle.getBundle("greeting", locale);
		String goodGreeting = bundle.getString("morning");
		LocalTime lt = LocalTime.of(8, 30);
		String str = CityTimeGreeting.Greeting(lt);
		assertEquals("Saying 'Good morning'", goodGreeting, str);
		
	}
	@Test
	public void testGreetingDay() {
		Locale locale = Locale.getDefault();
		ResourceBundle bundle = ResourceBundle.getBundle("greeting", locale);
		String goodGreeting = bundle.getString("day");
		LocalTime lt = LocalTime.of(9, 31);
		String str = CityTimeGreeting.Greeting(lt);
		assertEquals("Saying 'Good day'", goodGreeting, str);
		
	}
	
	@Test
	public void testGreetingEvening() {
		Locale locale = Locale.getDefault();
		ResourceBundle bundle = ResourceBundle.getBundle("greeting", locale);
		String goodGreeting = bundle.getString("evening");
		LocalTime lt = LocalTime.of(21, 32);
		String str = CityTimeGreeting.Greeting(lt);
		assertEquals("Saying 'Good evening'", goodGreeting, str);
		
	}
	
	@Test
	public void testGreetingNightBeforeMidNight() {
		Locale locale = Locale.getDefault();
		ResourceBundle bundle = ResourceBundle.getBundle("greeting", locale);
		String goodGreeting = bundle.getString("night");
		LocalTime lt = LocalTime.of(23, 30);
		String str = CityTimeGreeting.Greeting(lt);
		assertEquals("Saying 'Good night'", goodGreeting, str);
		
	}

	@Test
	public void testGreetingNightAfterMidNight() {
		Locale locale = Locale.getDefault();
		ResourceBundle bundle = ResourceBundle.getBundle("greeting", locale);
		String goodGreeting = bundle.getString("night");
		LocalTime lt = LocalTime.of(1, 30);
		String str = CityTimeGreeting.Greeting(lt);
		assertEquals("Saying 'Good night'", goodGreeting, str);
		
	}



}
