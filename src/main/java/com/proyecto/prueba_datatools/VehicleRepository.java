package com.proyecto.prueba_datatools;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepository {
	JDBC jdbc = null;
	Vehicle vehicle;
	
	public VehicleRepository() {
		this.vehicle = new Vehicle();
		this.jdbc = new JDBC();
	}
	
	public List<Vehicle> getVehicles(int company_id) {
		List<Vehicle> vehiclesList = new ArrayList<>();
		
		try {
			String company_complement = "";
			if (company_id!=0) {
				company_complement = "WHERE en.company_id="+company_id+" OR company_id IS NULL";
			}
			/*String sql = "SELECT \n"
					+ "	v.*\n"
					+ "    ,(\n"
					+ "        SELECT CASE WHEN type=\"disenroll\" THEN \"disenrolled\" WHEN type=\"enroll\" THEN \"enrolled\" ELSE \"disenrolled\" END FROM enrollment WHERE vehicle_id=v.id ORDER BY date DESC LIMIT 1 \n"
					+ "    ) AS \"status\"\n"
					+ "FROM \n"
					+ "	vehicles v \n"
					+ "ORDER BY v.id";*/
			String sql = "SELECT en.* FROM (\n"
					+ "    SELECT v.*,\n"
					+ "        (\n"
					+ "            SELECT id FROM enrollment WHERE vehicle_id=v.id ORDER BY date DESC LIMIT 1 \n"
					+ "        ) AS \"enrollment_id\",\n"
					+ "        (\n"
					+ "            SELECT company_id FROM enrollment WHERE vehicle_id=v.id ORDER BY date DESC LIMIT 1 \n"
					+ "        ) AS \"company_id\",\n"
					+ "        (\n"
					+ "            SELECT CASE WHEN type=\"disenroll\" THEN \"disenrolled\" WHEN type=\"enroll\" THEN \"enrolled\" ELSE \"disenrolled\" END FROM enrollment WHERE vehicle_id=v.id ORDER BY date DESC LIMIT 1 \n"
					+ "        ) AS \"status\"\n"
					+ "        FROM \n"
					+ "            vehicles v \n"
					+ "        ORDER BY v.id\n"
					+ ") as en "+company_complement;
			
			System.out.println(sql);
//			String sql = "SELECT v.* FROM vehicles v";
			Statement st = this.jdbc.connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Vehicle vehicle = new Vehicle();
				vehicle.setId(rs.getInt(1));
				vehicle.setPlate(rs.getString(2));
				vehicle.setEngine(rs.getFloat(3));
				vehicle.setChassis(rs.getString(4));
				vehicle.setModel(rs.getInt(5));
				vehicle.setRegistration_date(rs.getString(6));
				vehicle.setSeated_passengers(rs.getInt(7));
				vehicle.setStanding_passengers(rs.getInt(8));
				vehicle.setGross_weight(rs.getInt(9));
				vehicle.setNet_weight(rs.getInt(10));
				vehicle.setDoors(rs.getInt(11));
				vehicle.setBrand(rs.getString(12));
				vehicle.setLineup(rs.getString(13));
				vehicle.setEnrollment_status(rs.getString(16)); 
				
				vehiclesList.add(vehicle);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return vehiclesList;
		
	}
	
	public Vehicle getVehicle(int id) {
		Vehicle vehicle = new Vehicle();
		try {
			String sql = "SELECT * FROM vehicles WHERE id=?";
			PreparedStatement ps = this.jdbc.connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if( rs.next() ) {
				vehicle.setId(rs.getInt(1));
				vehicle.setPlate(rs.getString(2));
				vehicle.setEngine(rs.getFloat(3));
				vehicle.setChassis(rs.getString(4));
				vehicle.setModel(rs.getInt(5));
				vehicle.setRegistration_date(rs.getString(6));
				vehicle.setSeated_passengers(rs.getInt(7));
				vehicle.setStanding_passengers(rs.getInt(8));
				vehicle.setGross_weight(rs.getInt(9));
				vehicle.setNet_weight(rs.getInt(10));
				vehicle.setDoors(rs.getInt(11));
				vehicle.setBrand(rs.getString(12));
				vehicle.setLineup(rs.getString(13));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return vehicle;
	}
	
	public int createVehicle(Vehicle vehicle) {
		
		int insertedId = 0;
		
		try {
			String sql = "INSERT INTO vehicles("
					+ "plate,"
					+ "engine,"
					+ "chassis,"
					+ "model,"
					+ "registration_date,"
					+ "seated_passengers,"
					+ "standing_passengers,"
					+ "gross_weight,"
					+ "net_weight,"
					+ "doors,"
					+ "brand,"
					+ "lineup"
					+ ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = this.jdbc.connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,vehicle.getPlate());
			ps.setFloat(2,vehicle.getEngine());
			ps.setString(3,vehicle.getChassis());
			ps.setInt(4,vehicle.getModel());
			ps.setString(5,vehicle.getRegistration_date());
			ps.setInt(6,vehicle.getSeated_passengers());
			ps.setInt(7,vehicle.getStanding_passengers());
			ps.setInt(8,vehicle.getGross_weight());
			ps.setInt(9,vehicle.getNet_weight());
			ps.setInt(10,vehicle.getDoors());
			ps.setString(11,vehicle.getBrand());
			ps.setString(12,vehicle.getLineup());
			ps.executeUpdate();
			
			ResultSet inserted = ps.getGeneratedKeys();
			if (inserted.next()) {
			    insertedId = inserted.getInt(1);
			    System.out.println("Inserted vehicle Id: " + insertedId);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
		}
		
		return insertedId;
	}
	
	public boolean updateVehicle(Vehicle vehicle) {
		boolean ok = false;
		try {
			String sql = "UPDATE vehicles SET "
							+ "plate=?,"
							+ "engine=?,"
							+ "chassis=?,"
							+ "model=?,"
							+ "registration_date=?,"
							+ "seated_passengers=?,"
							+ "standing_passengers=?,"
							+ "gross_weight=?,"
							+ "net_weight=?,"
							+ "doors=?,"
							+ "brand=?,"
							+ "lineup=? "
						+ "WHERE id=?";
			PreparedStatement ps = this.jdbc.connection.prepareStatement(sql);
			ps.setString(1,vehicle.getPlate());
			ps.setFloat(2,vehicle.getEngine());
			ps.setString(3,vehicle.getChassis());
			ps.setInt(4,vehicle.getModel());
			ps.setString(5,vehicle.getRegistration_date());
			ps.setInt(6,vehicle.getSeated_passengers());
			ps.setInt(7,vehicle.getStanding_passengers());
			ps.setInt(8,vehicle.getGross_weight());
			ps.setInt(9,vehicle.getNet_weight());
			ps.setInt(10,vehicle.getDoors());
			ps.setString(11,vehicle.getBrand());
			ps.setString(12,vehicle.getLineup());
			ps.setInt(13, vehicle.getId());
			ps.executeUpdate();
			
			ok = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ok;
	}
	
	public boolean deleteVehicle(int id) {
		boolean ok = false;
		try {
			String sql = "DELETE FROM vehicles WHERE id=?";
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
	
	/* Vehicle enrollment */
	public String  enrollVehicle(Enrollment enrollment) {
		String status = "false";
		try {
			int vehicle_id = 0;
			String sql_plate = "SELECT id FROM vehicles WHERE plate = ?";
			PreparedStatement ps_plate = this.jdbc.connection.prepareStatement(sql_plate);
			ps_plate.setString(1, enrollment.getVehicle_plate());
			ResultSet rs = ps_plate.executeQuery();
			if( rs.next() ) {
				vehicle_id = rs.getInt(1);
			}
			
			String sql = "INSERT INTO enrollment(company_id, vehicle_id, type, date) VALUES(?,?,?,?)";
			PreparedStatement ps = this.jdbc.connection.prepareStatement(sql);
			ps.setInt(1, enrollment.getCompany_id());
			ps.setInt(2, vehicle_id);
			ps.setString(3, enrollment.getType());
			ps.setString(4, enrollment.getDate());
			ps.executeUpdate();
			
System.out.println(enrollment.getType().toString());
			if( enrollment.getType().equals("enroll") ) {
				status = "Vehiculo afiliado satisfactoriamente";
			}else if( enrollment.getType().equals("disenroll") ) {
				status = "Vehiculo desafiliado satisfactoriamente";
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}
}
