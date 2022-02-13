package com.proyecto.prueba_datatools;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserLoginInfoRepository {
	JDBC jdbc = null;
	UserLoginInfo userLogininfo;
	
	public UserLoginInfoRepository() {
		this.userLogininfo = new UserLoginInfo();
		this.jdbc = new JDBC();
	}
	
	public Object login(UserLoginInfo userLoginInfo) {
		int id = 0;
		int user_id = 0;
		String password;
		
		List<AppPermissions> permissions = new ArrayList<>();
		Object perm = new Object();
		Map<String, Object> logged = new HashMap<String, Object>();
		
		int company_id = 0;
		try {
			if( userLoginInfo.isHashed() ) {
				password = userLoginInfo.getPassword();
				System.out.println("Hasheado: "+password);
			}else {
				MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
				messageDigest.reset();
				messageDigest.update(userLoginInfo.getPassword().getBytes("UTF-8"));
				byte[] digest = messageDigest.digest();
				password = Base64.getEncoder().encodeToString(digest);
				System.out.println("NO Hasheado: "+userLoginInfo.getPassword());
				System.out.println("Se Hashea: "+password);
			}
			String sql = "SELECT uli.id, uli.user_id, u.company_id FROM user_login_info uli JOIN users u ON u.id=uli.user_id WHERE username=? AND password=?";
			PreparedStatement ps = this.jdbc.connection.prepareStatement(sql);
			ps.setString(1, userLoginInfo.getUsername());
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);
				user_id = rs.getInt(2);
				company_id = rs.getInt(3);
			}
			
			System.out.println(id+"-"+user_id);
			
			if( id!=0 ) {
				String sql_permissions = "SELECT ap.id,ap.role_id,ap.page FROM app_permissions ap JOIN users u ON u.rol_id=ap.role_id WHERE u.id="+user_id;
				PreparedStatement ps_permissions = this.jdbc.connection.prepareStatement(sql_permissions);
				ResultSet rs_permissions = ps_permissions.executeQuery();
				
				while (rs_permissions.next()) {
					AppPermissions appPermissions = new AppPermissions();
					appPermissions.setId(rs_permissions.getInt(1));
					appPermissions.setRole_id(rs_permissions.getInt(2));
					appPermissions.setPage(rs_permissions.getString(3));
					
					permissions.add(appPermissions); 
				}
//				perm = new Object[2];
				logged.put("Response", "true");
				logged.put("company_id", company_id);
				logged.put("Permission", permissions);
				perm = logged;
//				perm. = permissions;
			}else {
				perm = new Object[1];
				logged.put("Return", "false");
				perm = logged;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return perm;
	}
}
