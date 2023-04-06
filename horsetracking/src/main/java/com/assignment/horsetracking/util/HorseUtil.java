package com.assignment.horsetracking.util;

import java.util.ArrayList;
import java.util.List;

import com.assignment.horsetracking.model.AppConstant;
import com.assignment.horsetracking.model.Horse;


public class HorseUtil {

	public static List<Horse> init(){
		DataUtil.horseList = new ArrayList<>();
		DataUtil.horseList.add(new Horse(1, "That Darn Gray Cat", 5, true));
		DataUtil.horseList.add(new Horse(2, "Fort Utopia", 10, false));
		DataUtil.horseList.add(new Horse(3, "Count Sheep", 9, false));
		DataUtil.horseList.add(new Horse(4, "Ms Traitour", 4, false));
		DataUtil.horseList.add(new Horse(5, "Real Princess", 3, false));
		DataUtil.horseList.add(new Horse(6, "Pa Kettle", 5, false));
		DataUtil.horseList.add(new Horse(7, "Gin Stinger", 6, false));
		return DataUtil.horseList;
	}
	public static void printAll() {
		System.out.println(AppConstant.HORSES);
		DataUtil.horseList.stream().forEach(System.out::println);
	}
	public static List<Horse> setWinner(List<Horse> horseList, int id) {
		for(Horse horse:horseList) {
			if(horse.getId()==id) {
				horse.setStatus(true);
			}else {
				horse.setStatus(false);
			}
		}
		return horseList;
	}	

}