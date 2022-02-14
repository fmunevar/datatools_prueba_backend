package com.proyecto.prueba_datatools;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyRepository {
	
	JDBC jdbc = null;
	Company company;
	
	public CompanyRepository() {
		this.company = new Company();
		this.jdbc = new JDBC();
	}
	
	public List<Company> getCompanies() {
		List<Company> companiesList = new ArrayList<>();
		
		try {
//			String sql = "SELECT * FROM companies";
			String sql = "SELECT \n"
					+ "	c.id,\n"
					+ "    c.id_type,\n"
					+ "    c.id_number,\n"
					+ "    c.name,\n"
					+ "    c.address,\n"
					+ "    c.zone_id,\n"
					+ "    u.location,\n"
					+ "    c.phone\n"
					+ "FROM \n"
					+ "	companies c JOIN (\n"
					+ "        SELECT \n"
					+ "            z1.id,\n"
					+ "            CONCAT(z3.name,\" → \",z2.name,\" → \",z1.name) as \"location\"\n"
					+ "        FROM \n"
					+ "            zones z1 \n"
					+ "            JOIN zones z2 ON z2.id=z1.parent_id\n"
					+ "            JOIN zones z3 ON z3.id=z2.parent_id\n"
					+ "    ) as u ON u.id=c.zone_id";
			Statement st = this.jdbc.connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Company company = new Company();
				company.setId(rs.getInt(1));
				company.setId_type(rs.getString(2));
				company.setId_number(rs.getString(3));
				company.setName(rs.getString(4));
				company.setAddress(rs.getString(5));
				company.setZone_id(rs.getInt(6));
				company.setZone(rs.getString(7));
				company.setPhone(rs.getString(8));
				
				companiesList.add(company);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return companiesList;
		
	}
	
	public int createCompany(Company company) {
		
		int insertedId = 0;
		
		try {
			String sql = "INSERT INTO companies(id_type, id_number, name, address, zone_id, phone) VALUES (?,?,?,?,?,?)";
			PreparedStatement ps = this.jdbc.connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, company.getId_type());
			ps.setString(2, company.getId_number());
			ps.setString(3, company.getName());
			ps.setString(4, company.getAddress());
			ps.setInt(5, company.getZone_id());
			ps.setString(6, company.getPhone());
			ps.executeUpdate();
			
			ResultSet inserted = ps.getGeneratedKeys();
			if (inserted.next()) {
			    insertedId = inserted.getInt(1);
			    System.out.println("Inserted Id: " + insertedId);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
		}
		
		return insertedId;
	}
	
	public Company getCompany(int id) {
		Company company = new Company();
		try {
			String sql = "SELECT * FROM companies WHERE id=?";
			PreparedStatement ps = this.jdbc.connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if( rs.next() ) {
				company.setId(rs.getInt(1));
				company.setId_type(rs.getString(2));
				company.setId_number(rs.getString(3));
				company.setName(rs.getString(4));
				company.setAddress(rs.getString(5));
				company.setZone_id(rs.getInt(6));
				company.setPhone(rs.getString(7));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return company;
	}
	
	public boolean updateCompany(Company company) {
		boolean ok = false;
		try {
			String sql = "UPDATE companies SET id_type=?, id_number=?, name=?, address=?, zone_id=?, phone=? WHERE id=?";
			PreparedStatement ps = this.jdbc.connection.prepareStatement(sql);
			ps.setString(1, company.getId_type());
			ps.setString(2, company.getId_number());
			ps.setString(3, company.getName());
			ps.setString(4, company.getAddress());
			ps.setInt(5, company.getZone_id());
			ps.setString(6, company.getPhone());
			ps.setInt(7, company.getId());
			ps.executeUpdate();
			
			ok = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ok;
	}
	
	public boolean deleteCompany(int id) {
		boolean ok = false;
		try {
			String sql = "DELETE FROM companies WHERE id=?";
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
}
