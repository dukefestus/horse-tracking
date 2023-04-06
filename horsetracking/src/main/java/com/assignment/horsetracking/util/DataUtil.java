package com.assignment.horsetracking.util;

import java.util.List;

import com.assignment.horsetracking.model.Horse;
import com.assignment.horsetracking.model.Inventory;

public class DataUtil {
	public static List<Inventory> invList;
	public static List<Horse> horseList;
	static {
		invList = InventoryUtil.init();
		horseList = HorseUtil.init();
	}
}
