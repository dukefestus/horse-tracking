package com.assignment.horsetracking.util;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.assignment.horsetracking.model.AppConstant;
import com.assignment.horsetracking.model.ValidationException;

public class ValidatorUtil {

	public static void validateCmd(String cmd[])throws ValidationException {
		if(cmd.length<1 || cmd.length>2) {
			throw new ValidationException("HT_01_INVALID_CMD", invalidCmdMsg(cmd));
		}
		if(cmd.length==1 && !("Q".equalsIgnoreCase(cmd[0]) || "R".equalsIgnoreCase(cmd[0]) )) {
			throw new ValidationException("HT_01_INVALID_CMD", invalidCmdMsg(cmd));
		}
		if(cmd.length==2) {
			if(cmd[0].toUpperCase().startsWith("W")) {
				if(!ValidatorUtil.isValidateHorseNumber(cmd[1])) {
					throw new ValidationException("HT_02_INVALID_HORSE", invalidHorseMsg(cmd[1]));	
				}
			}else if(ValidatorUtil.isNumber(cmd[0]) && ValidatorUtil.isNumber(cmd[1])) {
				if(!ValidatorUtil.isValidateHorseNumber(cmd[0])){
					throw new ValidationException("HT_02_INVALID_HORSE", invalidHorseMsg(cmd[0]));	
				}else if(!ValidatorUtil.isValidateBet(cmd[1])) {
					throw new ValidationException("HT_03_INVALID_BET", invalidBetMsg(cmd[1]));	
				}
			}else {
				throw new ValidationException("HT_01_INVALID_CMD", invalidCmdMsg(cmd));
			}
		}
	}
	
	
	public static boolean isValidateHorseNumber(String  cmd)  {
		try {
			Integer i = Integer.parseInt(cmd);
			if(i<1 || i>7) {
				return false;
			}
		}catch(Exception ex) {
			return false;
		}
		return true;
	}
	public static boolean isValidateBet(String  cmd)  {
		if(cmd.contains(".")) {
			return false;
		}
		try {
			Integer.parseInt(cmd);
		}catch(Exception ex) {
			return false;
		}
		return true;
	}
	
	public static boolean isNumber(String str) {
		try {
			Float.parseFloat(str);
			return true;
		}catch(Exception ex) {}
		return false;
	}

	public static String invalidCmdMsg(String cmd[]) {
		return AppConstant.INVALID_CMD +Arrays.asList(cmd).stream().collect(Collectors.joining(" "));
	}
	public static String invalidHorseMsg(String cmd) {
		return AppConstant.INVALID_HORSE +cmd;
	}
	public static String invalidBetMsg(String cmd) {
		return AppConstant.INVALID_BET +cmd;
	}
	
}
