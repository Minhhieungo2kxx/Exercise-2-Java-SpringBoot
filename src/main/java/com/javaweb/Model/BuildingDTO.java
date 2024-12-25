package com.javaweb.Model;

public class BuildingDTO {
	private String name;
	private int numberBasement;
	private String address;
	private String rentArea;
	

	public String getRentArea() {
		return rentArea;
	}

	public void setRentArea(String rentArea) {
		this.rentArea = rentArea;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberBasement() {
		return numberBasement;
	}

	public void setNumberBasement(int numberBasement) {
		this.numberBasement = numberBasement;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	

}
