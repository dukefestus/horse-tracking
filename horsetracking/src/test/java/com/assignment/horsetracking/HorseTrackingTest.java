package com.assignment.horsetracking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

//import org.junit.Before;
//import org.junit.Test;

import org.junit.jupiter.api.Test;

import com.assignment.horsetracking.model.Horse;
import com.assignment.horsetracking.model.Inventory;
import com.assignment.horsetracking.model.ValidationException;
import com.assignment.horsetracking.util.DataUtil;
import com.assignment.horsetracking.util.ValidatorUtil;

public class HorseTrackingTest {
	List<Inventory> expInvList;
	List<Horse> expHorseList;
	
	public HorseTrackingTest() {
		expInvList = new ArrayList<>();
		expInvList.add(new Inventory(1,   10));
		expInvList.add(new Inventory(5,   10));
		expInvList.add(new Inventory(10,  10));
		expInvList.add(new Inventory(20,  10));
		expInvList.add(new Inventory(100, 10));
		
		expHorseList = new ArrayList<>();
		expHorseList.add(new Horse(1, "That Darn Gray Cat", 5, true));
		expHorseList.add(new Horse(2, "Fort Utopia", 10, false));
		expHorseList.add(new Horse(3, "Count Sheep", 9, false));
		expHorseList.add(new Horse(4, "Ms Traitour", 4, false));
		expHorseList.add(new Horse(5, "Real Princess", 3, false));
		expHorseList.add(new Horse(6, "Pa Kettle", 5, false));
		expHorseList.add(new Horse(7, "Gin Stinger", 6, false));
	}

	@Test
	public void testInvData() {
		HorseTracking.startTracking(new String[] {"R"});
		List<Inventory> actInvList = DataUtil.invList;
		assertEquals(true, actInvList.containsAll(expInvList));
		assertEquals(true, expInvList.containsAll(actInvList));
	}
	
	@Test
	public void testHorseData() {
		HorseTracking.startTracking(new String[] {"R"});
		List<Horse> actHorseList = DataUtil.horseList;
		assertTrue(actHorseList.containsAll(expHorseList));
		assertTrue(expHorseList.containsAll(actHorseList));
	}
	
	@Test
	public void testchangeWinningHorse() {
		HorseTracking.startTracking(new String[] {"W","4"});
		for(Horse horse:DataUtil.horseList) {
			if(horse.getId()==4) {
				assertTrue(horse.isStatus());
			}else {
				assertFalse(horse.isStatus());
			}
		}
	}
	
	@Test
	public void testResetData() {
		HorseTracking.startTracking(new String[] {"W","4"});
		for(Horse horse:DataUtil.horseList) {
			if(horse.getId()==4) {
				assertTrue(horse.isStatus());
			}else {
				assertFalse(horse.isStatus());
			}
		}
		HorseTracking.startTracking(new String[] {"R"});
		for(Horse horse:DataUtil.horseList) {
			if(horse.getId()==1) {
				assertTrue(horse.isStatus());
			}else {
				assertFalse(horse.isStatus());
			}
		}
	}
	
	@Test
	public void testHorseValidation() {
		assertFalse(ValidatorUtil.isValidateHorseNumber("8"));
		assertTrue(ValidatorUtil.isValidateHorseNumber("7"));
	}
	
	@Test
	public void testBetValidation() {
		assertFalse(ValidatorUtil.isValidateBet("10.25"));
		assertTrue(ValidatorUtil.isValidateBet("10"));
	}
	
	@Test
	public void testBetting() {
		HorseTracking.startTracking(new String[] {"R"});
		int bal = calBalance();
		HorseTracking.startTracking(new String[] {"1","55"});
		int expCurrBal = bal - calDispense(1, 55);;
		assertEquals(expCurrBal, calBalance());
		HorseTracking.startTracking(new String[] {"2","25"});
		assertEquals(expCurrBal, calBalance());
		HorseTracking.startTracking(new String[] {"W","4"});
		HorseTracking.startTracking(new String[]{"4","10"});
		expCurrBal = expCurrBal - calDispense(4, 10);
		assertEquals(expCurrBal, calBalance());
	}
	
	@Test
	public void testValidatorUtilInvalidCmd() {
		try {
			ValidatorUtil.validateCmd(new String[] {"1","2","3"});
		}catch(ValidationException vex) {assertEquals(ValidatorUtil.invalidCmdMsg(new String[] {"1","2","3"}), vex.getErrorMsg());}
		try {
			ValidatorUtil.validateCmd(new String[] {"H"});
		}catch(ValidationException vex) {assertEquals(ValidatorUtil.invalidCmdMsg(new String[] {"H"}), vex.getErrorMsg());}
		try {
			ValidatorUtil.validateCmd(new String[] {"A","A"});
		}catch(ValidationException vex) {assertEquals(ValidatorUtil.invalidCmdMsg(new String[] {"A","A"}), vex.getErrorMsg());}
		
	}
	
	
	
	
	private int calBalance() {
		return DataUtil.invList.stream().map(i -> i.getCount() * i.getDenomination()).reduce(0,(s,p)->s+p);
	}
	
	private int calDispense(int horseNumber, int bet) {
		return DataUtil.horseList.stream().filter(h->h.getId()==horseNumber).findFirst().get().getOdd()*bet;
	}
	
}
