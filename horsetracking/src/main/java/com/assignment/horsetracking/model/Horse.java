package com.assignment.horsetracking.model;

import java.util.Objects;


public class Horse {

	private int id;
	private String name;
	private int odd;
	private boolean status;
	
	public Horse(int id, String name, int odd, boolean status){
		this.id = id;
		this.name = name;
		this.odd = odd;
		this.status = status;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOdd() {
		return odd;
	}
	public void setOdd(int odd) {
		this.odd = odd;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, name, odd, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Horse other = (Horse) obj;
		return id == other.id && Objects.equals(name, other.name) && odd == other.odd && status == other.status;
	}

	public String toString() {
		return id + "," + name + "," + odd + "," + (status?"won":"lost");
	}
	
}
