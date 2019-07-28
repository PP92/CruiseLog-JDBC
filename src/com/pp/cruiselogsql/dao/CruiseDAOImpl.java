package com.pp.cruiselogsql.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pp.cruiselogsql.database.DatabaseConnectionMySQL;
import com.pp.cruiselogsql.database.DatabaseConnectionH2;
import com.pp.cruiselogsql.model.Cruise;

public class CruiseDAOImpl implements CruiseDao {
	private static final String SQL_TABLE_NAME = "cruises";

	private static CruiseDAOImpl cruiseDAOImpl = null;

			/*WYBÓR BAZY DANYCH*/
	//private Connection connection = DatabaseConnectionH2.getConnection();
	private Connection connection = DatabaseConnectionMySQL.getConnection();
	
	// singleton
	public static CruiseDao getInstance() {
		if (cruiseDAOImpl == null)
			cruiseDAOImpl = new CruiseDAOImpl();

		return cruiseDAOImpl;
	}

	@Override
	public void saveCruise(Cruise cruise) {
		if (isCruiseFilled(cruise)) {
			String sql = "INSERT INTO " + SQL_TABLE_NAME
					+ "(captain_name, yacht_name, location, distance, start_date, end_date, "
					+ "crew1 ,crew2, crew3, crew4, crew5, crew6, crew7, " + "crew8, crew9, crew10, crew11, crew12)"
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			try {

				PreparedStatement preparedStatement;
				preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, cruise.getCaptainName());
				preparedStatement.setString(2, cruise.getYachtName());
				preparedStatement.setString(3, cruise.getLocation());
				preparedStatement.setInt(4, cruise.getDistance());
				preparedStatement.setDate(5, Date.valueOf(cruise.getStartDate()));
				preparedStatement.setDate(6, Date.valueOf(cruise.getEndDate()));

				int i = 0;
				for (String crew : cruise.getCrew()) {
					preparedStatement.setString(7 + i, crew);
					i++;
				}
				while (i != 12) {
					preparedStatement.setString(7 + i, null);
					i++;
				}
				System.out.println("pp ps: " + preparedStatement);
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				System.out.println("CruiseDAOImpl saveCruise Exception: " + e.getMessage());
			}
		}
	}

	@Override
	public void updateCruise(Cruise cruise) {
		if (isCruiseFilled(cruise)) {
			String sql = "UPDATE " + SQL_TABLE_NAME
					+ " SET captain_name=?, yacht_name=?, location=?, distance=?, start_date=?, end_date=?,"
					+ " crew1=? ,crew2=?, crew3=?, crew4=?, crew5=?, crew6=?, crew7=?, "
					+ "crew8=?, crew9=?, crew10=?, crew11=?, crew12=? WHERE id = ?";
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, cruise.getCaptainName());
				preparedStatement.setString(2, cruise.getYachtName());
				preparedStatement.setString(3, cruise.getLocation());
				preparedStatement.setInt(4, cruise.getDistance());
				preparedStatement.setDate(5, Date.valueOf(cruise.getStartDate()));
				preparedStatement.setDate(6, Date.valueOf(cruise.getEndDate()));

				int i = 0;
				for (String crew : cruise.getCrew()) {
					preparedStatement.setString(7 + i, crew);
					i++;
				}

				while (i != 12) {
					preparedStatement.setString(7 + i, null);
					i++;
				}

				preparedStatement.setInt(19, cruise.getId());
				preparedStatement.executeUpdate();

			} catch (Exception e) {
				System.out.println("CruiseDAOImpl updateCruise Exception: " + e.getMessage());
			}

		}
	}

	@Override
	public void updateCruiseById(int id, Cruise cruise) {
		if (isCruiseFilled(cruise)) {
			String sql = "UPDATE " + SQL_TABLE_NAME
					+ " SET captain_name=?, yacht_name=?, location=?, distance=?, start_date=?, end_date=?,"
					+ " crew1=? ,crew2=?, crew3=?, crew4=?, crew5=?, crew6=?, crew7=?, "
					+ "crew8=?, crew9=?, crew10=?, crew11=?, crew12=? WHERE id = ?";

			try {
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, cruise.getCaptainName());
				preparedStatement.setString(2, cruise.getYachtName());
				preparedStatement.setString(3, cruise.getLocation());
				preparedStatement.setInt(4, cruise.getDistance());
				preparedStatement.setDate(5, Date.valueOf(cruise.getStartDate()));
				preparedStatement.setDate(6, Date.valueOf(cruise.getEndDate()));
				int i = 0;
				for (String crew : cruise.getCrew()) {
					preparedStatement.setString(7 + i, crew);
					i++;
				}
				while (i != 12) {
					preparedStatement.setString(7 + i, null);
					i++;
				}
				preparedStatement.setInt(19, id);
				System.out.println("update: " + preparedStatement);
				int ps = preparedStatement.executeUpdate();
				System.out.println(ps);
			} catch (Exception e) {
				System.out.println("CruiseDAOImpl updateCruiseById Exception: " + e.getMessage());
			}
		}
	}

	@Override
	public void deleteCruise(Cruise cruise) {
		String sql = "DELETE FROM " + SQL_TABLE_NAME + " WHERE id=?";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cruise.getId());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("CruiseDAOImpl deleteCruise Exception: " + e.getMessage());
		}

	}

	@Override
	public void deleteCruiseById(int id) {
		String sql = "DELETE FROM " + SQL_TABLE_NAME + " WHERE id=?";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			int row = preparedStatement.executeUpdate();
			if (row == 0)
				System.out.println("----Brak podanego indeksu w bazie----");
		} catch (Exception e) {
			System.out.println("CruiseDAOImpl deleteCruiseById Exception: " + e.getMessage());
		}

	}

	@Override
	public Cruise getCruiseById(int id) {
		String sql = "SELECT * FROM " + SQL_TABLE_NAME + " WHERE id=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				Cruise cruise = new Cruise();
				cruise.setId(resultSet.getInt(1));
				cruise.setCaptainName(resultSet.getString(2));
				cruise.setYachtName(resultSet.getString(3));
				cruise.setLocation(resultSet.getString(4));
				cruise.setDistance(resultSet.getInt(5));
				cruise.setStartDate(resultSet.getDate(6).toLocalDate());
				cruise.setEndDate(resultSet.getDate(7).toLocalDate());
				for (int i = 0; i < 12; i++) {
					if (resultSet.getString(i + 8) != null)
						cruise.getCrew().add(resultSet.getString(i + 8));
				}
				return cruise;
			}

		} catch (Exception e) {
			System.out.println("pp: CruiseDAOImpl getCruiseById Exception: " + e.getMessage());
		}
		return null;
	}

	@Override
	public List<Cruise> getAllCruises() {
		String sql = "SELECT * FROM " + SQL_TABLE_NAME;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			List<Cruise> cruises = new ArrayList<Cruise>();

			while (resultSet.next()) {
				Cruise cruise = new Cruise();
				cruise.setId(resultSet.getInt(1));
				cruise.setCaptainName(resultSet.getString(2));
				cruise.setYachtName(resultSet.getString(3));
				cruise.setLocation(resultSet.getString(4));
				cruise.setDistance(resultSet.getInt(5));
				cruise.setStartDate(resultSet.getDate(6).toLocalDate());
				cruise.setEndDate(resultSet.getDate(7).toLocalDate());
				for (int i = 0; i < 12; i++) {
					if (resultSet.getString(i + 8) != null)
						cruise.getCrew().add(resultSet.getString(i + 8));
				}
				cruises.add(cruise);
			}
			return cruises;

		} catch (Exception e) {
			System.out.println("pp: CruiseDAOImpl getAllCruises() Exception: " + e.getMessage());
		}
		return null;
	}

	@Override
	public void deleteAllCruises() {
		String sql = "DELETE FROM " + SQL_TABLE_NAME;

		try {
			Statement statement = connection.createStatement();
			statement.execute(sql);
		} catch (Exception e) {
			System.out.println("CruiseDAOImpl deleteAllCruises Exception: " + e.getMessage());
		}

	}

	public boolean isCruiseFilled(Cruise cruise) {
		if (cruise.getCaptainName() == null || cruise.getLocation() == null || cruise.getLocation() == null) {
			System.out.println("Nie spełnione warunki not null");
			return false;
		} else
			return true;
	}

}
