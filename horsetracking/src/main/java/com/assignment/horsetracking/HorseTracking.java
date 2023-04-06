package com.assignment.horsetracking;

import java.util.Scanner;

import com.assignment.horsetracking.model.ValidationException;
import com.assignment.horsetracking.util.DataUtil;
import com.assignment.horsetracking.util.HorseUtil;
import com.assignment.horsetracking.util.InventoryUtil;
import com.assignment.horsetracking.util.ValidatorUtil;

public class HorseTracking {
	/**
	 * This method is the entry point for the entire application
	 * it has an infinite loop which keeps running till the user quits
	 */
	public static void startHorseTracking() {
		InventoryUtil.printAll();
		HorseUtil.printAll();
		Scanner scanner = null;
		try {
			scanner = new Scanner(System.in);
			 while(true) {
				 startTracking(scanner.nextLine().split(" "));
			 }
		}catch(Exception ex) {
			System.out.println("Internal System Error");
			ex.printStackTrace();
		}finally {
			if(scanner != null) {
				scanner.close();
			}
		}
	}
	
	/**
	 * This method is serves as a menu card providing different options
	 * options of the user
	 */
	public static void startTracking(String cmd[]) {
		try {
			cmd[0]=cmd[0].toUpperCase();
			ValidatorUtil.validateCmd(cmd);
			switch(cmd[0]) {
				case "Q" : System.exit(0);
				case "R" : InventoryUtil.init();
						   HorseUtil.init();
						   break;
				case "W" : DataUtil.horseList = HorseUtil.setWinner(DataUtil.horseList, Integer.parseInt(cmd[1]));
						   break;
				default  : InventoryUtil.processBet(Integer.parseInt(cmd[0]), Integer.parseInt(cmd[1]));
			}
			InventoryUtil.printAll();
			HorseUtil.printAll();
		}catch(ValidationException vex) {
			System.out.println(vex.getErrorMsg());
		}catch(Exception ex) {
			System.out.println("Internal System Error");
			ex.printStackTrace();
		}
	}
}
