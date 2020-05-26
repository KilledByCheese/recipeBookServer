//package de.killedbycheese.recipeBookServer.auth;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import org.mindrot.jbcrypt.BCrypt;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
///**
// * @author ktoff
// *
// */
//public class HashTest {
//
//	public static void main(String[] args) {
//
//		String pw = "password";
//		String salt = BCrypt.gensalt(12);
//		String hashed = BCrypt.hashpw(pw, salt);
//
//		System.out.println("salt\t" + salt);
//		System.out.println("hash\t" + hashed);
//
//		String hashed2 = BCrypt.hashpw("Password", salt);
//		System.out.println("hash\t" + hashed2);
//
//		System.out.println(hashed.length());
//		System.out.println(salt.length());
//
//		if (BCrypt.checkpw("Password", hashed2)) {
//			System.out.println("match");
//		} else {
//			System.out.println("no match");
//		}
//
//		Connection con;
//		String username = "user";
//
//		try {
//			con = DriverManager.getConnection("jdbc:postgresql://192.168.178.29:5432/auth", "pi", "3eg6ste5ne1234");
//			String sql = "SELECT pwhash FROM auth_table WHERE userid = ?";
//			PreparedStatement pstm = con.prepareStatement(sql);
//			pstm.setString(1, username);
//			ResultSet rs = pstm.executeQuery();
//			if (rs.next()==false) {
//				System.out.println("User not found - rs==null");
//			} else {
//				do {
//					String passwordFromDb = rs.getString("pwhash");
//					System.out.println(passwordFromDb);
//				} while(rs.next());
//			}
//			con.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//	}
//
//}
