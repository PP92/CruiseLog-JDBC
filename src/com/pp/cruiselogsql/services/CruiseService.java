package com.pp.cruiselogsql.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.github.javafaker.Faker;
import com.pp.cruiselogsql.model.Cruise;

public class CruiseService {
	
	private static CruiseService cruiseService = null;

	// singleton
	public static CruiseService getInstance() {
		if (cruiseService == null)
			cruiseService = new CruiseService();

		return cruiseService;
	}
	
	public Cruise getRandomCruise() {
		Faker faker = new Faker();
		Random random = new Random();
		
		Cruise randomCruise = new Cruise();
		String captainName = faker.name().fullName();
		String yachtName = "S/Y " + faker.name().firstName();
		int distance = random.nextInt(100) + 100;

		List<String> locations = new ArrayList<>();
		locations.add("Morze Północne");
		locations.add("Atlantyk");
		locations.add("Morze Śródziemne");
		locations.add("Bałtyk");
		locations.add("Morze Egejskie");
		locations.add("Adriatyk");

		String location = locations.get(random.nextInt(locations.size()));

		RandomDate rd = new RandomDate(LocalDate.of(2010, 1, 1), LocalDate.of(2019, 1, 1));
		LocalDate startDate = rd.nextDate();
		LocalDate endDate = startDate.plusDays(7);

		int crewNumber = random.nextInt(10) + 3;		//losuje od 3 do 12

		for (int i = 0; i < crewNumber; i++) {
			String crew = faker.name().fullName();
			randomCruise.getCrew().add(crew);
		}

		randomCruise.setCaptainName(captainName);
		randomCruise.setYachtName(yachtName);
		randomCruise.setLocation(location);
		randomCruise.setStartDate(startDate);
		randomCruise.setEndDate(endDate);
		randomCruise.setDistance(distance);
		
		System.out.println("Wylosowano rejs, kapitan: " + randomCruise.getCaptainName());
		return randomCruise;
	}
}
