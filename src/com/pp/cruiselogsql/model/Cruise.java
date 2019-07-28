package com.pp.cruiselogsql.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cruise {
	private int id;
	private String captainName;
	private String yachtName;
	private String location;
	private int distance;
	private LocalDate startDate;
	private LocalDate endDate;
	private List<String> crew = new ArrayList<>();
	
	public Cruise() {};
	
	public Cruise(String captainName, String yachtName, int distance) {
		this.captainName = captainName;
		this.yachtName = yachtName;
		this.distance = distance;
	}	

	public Cruise(int id, String captainName, String yachtName, int distance, List<String> crew) {
		this.id = id;
		this.captainName = captainName;
		this.yachtName = yachtName;
		this.distance = distance;
		this.crew = crew;
	}


	public String getCaptainName() {
		return captainName;
	}

	public void setCaptainName(String captainName) {
		this.captainName = captainName;
	}

	public String getYachtName() {
		return yachtName;
	}

	public void setYachtName(String yachtName) {
		this.yachtName = yachtName;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<String> getCrew() {
		return crew;
	}

	public void setCrew(List<String> crew) {
		this.crew = crew;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate date) {
		this.startDate = date;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Cruise [id=" + id + ", captainName=" + captainName + ", yachtName=" + yachtName + ", location="
				+ location + ", distance=" + distance + ", startDate=" + startDate + ", endDate=" + endDate + ", crew="
				+ crew + "]";
	}


	
	

}
