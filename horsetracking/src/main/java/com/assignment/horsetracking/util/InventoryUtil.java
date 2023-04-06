package com.assignment.horsetracking.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.assignment.horsetracking.model.AppConstant;
import com.assignment.horsetracking.model.Horse;
import com.assignment.horsetracking.model.Inventory;

public class InventoryUtil {
	public static List<Inventory> init(){
		DataUtil.invList = new ArrayList<>();
		DataUtil.invList.add(new Inventory(1,   10));
		DataUtil.invList.add(new Inventory(5,   10));
		DataUtil.invList.add(new Inventory(10,  10));
		DataUtil.invList.add(new Inventory(20,  10));
		DataUtil.invList.add(new Inventory(100, 10));
		return DataUtil.invList;
	}
	public static void printAll() {
		System.out.println(AppConstant.INVENTORY);
		DataUtil.invList.stream().forEach(System.out::println);
	}
	public static void processBet(int horseNum, int betAmt){
		Horse horse = DataUtil.horseList.get(horseNum-1);
		if(DataUtil.horseList.get(horseNum-1).isStatus()==false){
			//lost
			System.out.println(new StringBuffer(AppConstant.NO_PAYOUT).append(horse.getName()).toString());
			return ;
		}else{
			//Won
			int amt = horse.getOdd()*betAmt;
			if(DataUtil.invList.stream().map(inv -> inv.getCount() * inv.getDenomination()).reduce(0, (p1,p2)->p1+p2) < amt){
				System.out.println(new StringBuffer(AppConstant.INSUFFICIENT_FUNDS).append(amt).toString());
				return ;
			}
			
			List<Inventory> tempInvList = new ArrayList<>();
			tempInvList.addAll(DataUtil.invList);
			List<Inventory> payInvList  = DataUtil.invList.stream()
					.map(inv -> new Inventory(inv.getDenomination(), 0)).collect(Collectors.toList());
			
			int pending = amt;
			for(int i = tempInvList.size()-1;i>=0;i--) {
				
				int payDeno  = tempInvList.get(i).getDenomination();
				int payCount = pending/payDeno;
				if(tempInvList.get(i).getCount()<=0 || tempInvList.get(i).getDenomination()*1 > amt){
					continue;
				}
				payCount = payCount>tempInvList.get(i).getCount() ? tempInvList.get(i).getCount():payCount;
				
				for(Inventory inv:tempInvList) {if(inv.getDenomination() == payDeno) {inv.setCount(inv.getCount() - payCount);break;}}
				for(Inventory inv:payInvList ) {if(inv.getDenomination() == payDeno) {inv.setCount(inv.getCount() + payCount);break;}}
				//payInvList.add(new Inventory(payDeno, payCount));
				
				pending  = amt - payInvList.stream().map(inv -> inv.getCount() * inv.getDenomination()).reduce(0, (p1,p2)->p1+p2);
				if(pending ==0) {
					break;
				}
			}
			
			if(pending ==0) {
				DataUtil.invList = tempInvList;
				StringBuffer sb = new StringBuffer();
								sb.append(AppConstant.PAYOUT).append(horse.getName()).append(",").append(horse.getOdd()*betAmt).append("\n")
				                  .append(AppConstant.DISPENSING).append("\n");
				payInvList.stream().forEach(inv ->sb.append(AppConstant.DOLLAR+inv.getDenomination()+","+inv.getCount()).append("\n"));
				System.out.println(sb.toString());
				return ;
			}else {
				System.out.println(new StringBuffer().append(AppConstant.INSUFFICIENT_FUNDS).append(amt).toString());
				return ;
			}
		}
	}


}
