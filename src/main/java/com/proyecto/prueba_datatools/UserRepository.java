package com.proyecto.prueba_datatools;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
	
	JDBC jdbc = null;
	User user;
	
	public UserRepository() {
		this.user = new User();
		this.jdbc = new JDBC();
	}
	
	public List<User> getUsers(int company_id) {
		List<User> usersList = new ArrayList<>();
		
		try {
			String company_complement = "";
			if (company_id!=0) {
				company_complement = "WHERE company_id="+company_id;
			}
			
			String sql = "SELECT * FROM users "+company_complement;
			Statement st = this.jdbc.connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setId_type(rs.getString(2));
				user.setName(rs.getString(3));
				user.setLastname(rs.getString(4));
				user.setAddress(rs.getString(5));
				user.setZone_id(rs.getInt(6));
				user.setPhone(rs.getString(7));
				user.setCompany_id(rs.getInt(8));
				user.setPosition_id(rs.getInt(9));
				user.setRol_id(rs.getInt(10));
				
				usersList.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return usersList;
		
	}
	
	public User getUser(int id) {
		User user = new User();
		try {
			String sql = "SELECT * FROM users WHERE id=?";
			PreparedStatement ps = this.jdbc.connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if( rs.next() ) {
				user.setId(rs.getInt(1));
				user.setId_type(rs.getString(2));
				user.setName(rs.getString(3));
				user.setName(rs.getString(4));
				user.setAddress(rs.getString(5));
				user.setZone_id(rs.getInt(6));
				user.setPhone(rs.getString(7));
				user.setCompany_id(rs.getInt(8));
				user.setPosition_id(rs.getInt(9));
				user.setRol_id(rs.getInt(10));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	
	public int createUser(User user) {
		
		int insertedId = 0;
		
		try {
			String sql = "INSERT INTO users VALUES (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = this.jdbc.connection.prepareStatement(sql);
			ps.setInt(1, user.getId());
			ps.setString(2, user.getId_type());
			ps.setString(3, user.getName());
			ps.setString(4, user.getLastname());
			ps.setString(5, user.getAddress());
			ps.setInt(6, user.getZone_id());
			ps.setString(7, user.getPhone());
			ps.setInt(8, user.getCompany_id());
			ps.setInt(9, user.getPosition_id());
			ps.setInt(10, user.getRol_id());
			ps.executeUpdate();
			
			System.out.println("Inserted User Id: " + user.getId());
			insertedId = user.getId();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
		}
		
		return insertedId;
	}
	
	public boolean updateUser(User user) {
		boolean ok = false;
		try {
			String sql = "UPDATE users SET "
							+ "id=?, "
							+ "id_type=?, "
							+ "name=?, "
							+ "lastname=?, "
							+ "address=?, "
							+ "zone_id=?, "
							+ "phone=?, "
							+ "company_id=?, "
							+ "position_id=?, "
							+ "rol_id=? "
						+ "WHERE id=?";
			PreparedStatement ps = this.jdbc.connection.prepareStatement(sql);
			ps.setInt(1, user.getId());
			ps.setString(2, user.getId_type());
			ps.setString(3, user.getName());
			ps.setString(4, user.getLastname());
			ps.setString(5, user.getAddress());
			ps.setInt(6, user.getZone_id());
			ps.setString(7, user.getPhone());
			ps.setInt(8, user.getCompany_id());
			ps.setInt(9, user.getPosition_id());
			ps.setInt(10, user.getRol_id());
			ps.setInt(11, user.getId());
			ps.executeUpdate();
			
			ok = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ok;
	}
	
	public boolean deleteUser(int id) {
		boolean ok = false;
		try {
			String sql = "DELETE FROM users WHERE id=?";
			PreparedStatement ps = this.jdbc.connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			
			ok = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ok;
	}
	
	/* Vehicle assignment */
	public boolean assignVehicle(Driving driving) {
		boolean ok = false;
		try {
			String sql = "INSERT INTO driving(user_id, vehicle_id) VALUES(?,?)";
			PreparedStatement ps = this.jdbc.connection.prepareStatement(sql);
			ps.setInt(1, driving.getUser_id());
			ps.setInt(2, driving.getVehicle_id());
			ps.executeUpdate();
			
			ok = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ok;
	}
	
	/* Check vehicle assignment */
	public List<Vehicle> checkAssignments(String id) {
		List<Vehicle> vehiclesList = new ArrayList<>();
		try {
			String sql = "SELECT v.id, v.plate,v.brand,v.lineup FROM driving d JOIN vehicles v ON v.id=d.vehicle_id WHERE d.user_id=?";
			PreparedStatement ps = this.jdbc.connection.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Vehicle vehicle = new Vehicle();
				vehicle.setId(rs.getInt(1));
				vehicle.setPlate(rs.getString(2));
//				vehicle.setEngine(rs.getFloat(3));
//				vehicle.setChassis(rs.getString(4));
//				vehicle.setModel(rs.getInt(5));
//				vehicle.setRegistration_date(rs.getString(6));
//				vehicle.setSeated_passengers(rs.getInt(7));
//				vehicle.setStanding_passengers(rs.getInt(8));
//				vehicle.setGross_weight(rs.getInt(9));
//				vehicle.setNet_weight(rs.getInt(10));
//				vehicle.setDoors(rs.getInt(11));
				vehicle.setBrand(rs.getString(3));
				vehicle.setLineup(rs.getString(4));
				
				vehiclesList.add(vehicle);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return vehiclesList;
	}
}
