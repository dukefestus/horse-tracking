package com.assignment.horsetracking.model;

import java.util.Objects;


public class Inventory {
	private int denomination;
	private int count;
	
	public Inventory(int denomination, int count) {
		this.denomination = denomination;
		this.count = count;
	}
	
	public int getDenomination() {
		return denomination;
	}
	public void setDenomination(int denomination) {
		this.denomination = denomination;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(count, denomination);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inventory other = (Inventory) obj;
		return count == other.count && denomination == other.denomination;
	}
	
	@Override
	public String toString() {
		return AppConstant.DOLLAR + denomination + "," + count;
	}

}
