package com.proyecto.prueba_datatools;

public class Vehicle {
	private int id;
	private String plate;
	private float engine;
	private String chassis;
	private int model;
	private String registration_date;
	private int seated_passengers;
	private int standing_passengers;
	private int gross_weight;
	private int net_weight;
	private int doors;
	private String brand;
	private String lineup;
	
	private String enrollment_status;
	
	public String getEnrollment_status() {
		return enrollment_status;
	}
	public void setEnrollment_status(String enrollment_status) {
		this.enrollment_status = enrollment_status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public float getEngine() {
		return engine;
	}
	public void setEngine(float engine) {
		this.engine = engine;
	}
	public String getChassis() {
		return chassis;
	}
	public void setChassis(String chassis) {
		this.chassis = chassis;
	}
	public int getModel() {
		return model;
	}
	public void setModel(int model) {
		this.model = model;
	}
	public String getRegistration_date() {
		return registration_date;
	}
	public void setRegistration_date(String registration_date) {
		this.registration_date = registration_date;
	}
	public int getSeated_passengers() {
		return seated_passengers;
	}
	public void setSeated_passengers(int seated_passengers) {
		this.seated_passengers = seated_passengers;
	}
	public int getStanding_passengers() {
		return standing_passengers;
	}
	public void setStanding_passengers(int standing_passengers) {
		this.standing_passengers = standing_passengers;
	}
	public int getGross_weight() {
		return gross_weight;
	}
	public void setGross_weight(int gross_weight) {
		this.gross_weight = gross_weight;
	}
	public int getNet_weight() {
		return net_weight;
	}
	public void setNet_weight(int net_weight) {
		this.net_weight = net_weight;
	}
	public int getDoors() {
		return doors;
	}
	public void setDoors(int doors) {
		this.doors = doors;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getLineup() {
		return lineup;
	}
	public void setLineup(String lineup) {
		this.lineup = lineup;
	}
	
}
