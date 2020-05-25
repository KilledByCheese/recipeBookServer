package de.killedbycheese.recipeBookServer.auth.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	private Connection con;
	
	@Value("${db.url}")
	private String dbURL;
	
	@Value("${db.user}")
	private String dbUSER;
	
	@Value("${db.pw}")
	private String dbPW;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		
		try {
			
			con = DriverManager.getConnection(dbURL, dbUSER, dbPW);
//			System.out.println("connected");
			String sql = "SELECT pwhash FROM auth_table WHERE userid = ?";
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, username);
			ResultSet rs = pstm.executeQuery();
			String pwHash = null;
			if(rs.next()==false) {
//				System.out.println("No user found");
				throw new UsernameNotFoundException("User not found with username: " + username);
			} else {
				do {
					pwHash = rs.getString("pwhash");
//					System.out.println("Hash received "+pwHash);
				} while(rs.next());
			}
			User user = new User(username, pwHash, new ArrayList<>());
//			System.out.println("user/pw: " + user.getUsername() + " " + user.getPassword());
			return user;
			
			
		} catch (SQLException e) {
//			e.printStackTrace();
			try {
				if(con != null)
				con.close();
			} catch (SQLException e1) {
				//TODO implement logging
			}
			//TODO implement logging
		} finally {
			try {
				if(con != null)
				con.close();
			} catch (SQLException e1) {
				//TODO implement logging
			}
			//TODO implement logging
//			System.out.println("User returned");
		}
		throw new UsernameNotFoundException("User not found with username: " + username);
//		//TODO connect to datbase get hash for username
//		if ("javainuse".equals(username)) {
//			return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
//					new ArrayList<>());
//		} else {
//			throw new UsernameNotFoundException("User not found with username: " + username);
//		}
	}
	
	
}
