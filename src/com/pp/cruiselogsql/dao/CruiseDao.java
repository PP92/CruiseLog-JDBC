package com.pp.cruiselogsql.dao;

import java.util.List;

import com.pp.cruiselogsql.model.Cruise;

public interface CruiseDao {
	void saveCruise(Cruise cruise);
	void updateCruise(Cruise cruise);
	void updateCruiseById(int id, Cruise cruise);
	void deleteCruise(Cruise cruise);
	void deleteCruiseById(int id);
	void deleteAllCruises();
	Cruise getCruiseById(int id);
	List<Cruise> getAllCruises();
		

}
