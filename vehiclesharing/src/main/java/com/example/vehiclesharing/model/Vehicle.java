package com.example.vehiclesharing.model;

public class Vehicle
{
    private int vehicle_id;
    private String vehicle_brand;
    private String vehicle_model;
    private String regn_no;
    private float KMs_driven;
    private int capacity;
    private int driver_id;
    
    public Vehicle(){}
	
	public Vehicle(int vehicle_id, String vehicle_brand, String vehicle_model,
			String regn_no, float KMs_driven, int capacity, int driver_id) 
	{
        this.vehicle_brand = vehicle_brand;
        this.vehicle_model = vehicle_model;
        this.regn_no = regn_no;
        this.KMs_driven = KMs_driven;
        this.capacity = capacity;
        this.driver_id = driver_id;
    }
	
	public int getVehicle_id() {
		return vehicle_id;
	}
	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
	}
	public String getVehicle_brand() {
		return vehicle_brand;
	}
	public void setVehicle_brand(String vehicle_brand) {
		this.vehicle_brand = vehicle_brand;
	}
	public String getVehicle_model() {
		return vehicle_model;
	}
	public void setVehicle_model(String vehicle_model) {
		this.vehicle_model = vehicle_model;
	}
	public String getRegn_no() {
		return regn_no;
	}
	public void setRegn_no(String regn_no) {
		this.regn_no = regn_no;
	}
	public float getKMs_driven() {
		return KMs_driven;
	}
	public void setKMs_driven(float kMs_driven) {
		KMs_driven = kMs_driven;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getDriver_id() {
		return driver_id;
	}
	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

}
