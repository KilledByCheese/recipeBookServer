package de.killedbycheese.recipeBookServer.auth.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import de.killedbycheese.recipeBookServer.auth.util.Role;
import de.killedbycheese.recipeBookServer.auth.exception.EmailAlreadyInUseXception;
import de.killedbycheese.recipeBookServer.auth.exception.UserIdAlreadyInUseXception;
import de.killedbycheese.recipeBookServer.auth.model.RegisterRequest;
import de.killedbycheese.recipeBookServer.auth.util.RecipeUser;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	private static final String SQL_FIND_BY_USERID = "SELECT * FROM auth_table WHERE userid = ?";
	private static final String SQL_CREATE = "INSERT INTO auth_table(userid,pwhash,role,email) VALUES(?,?,?,?)";
	private static final String SQL_COUNT_BY_EMAIL = "SELECT COUNT(*) FROM auth_table WHERE email = ?";
	private static final String SQL_COUNT_BY_USERID = "SELECT COUNT(*) FROM auth_table WHERE userid = ?";
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("getting User with username: " + username);
		RecipeUser recipeUser = jdbcTemplate.queryForObject(SQL_FIND_BY_USERID, userRowMapper, username);
		if (recipeUser == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		ArrayList<GrantedAuthority> authorities = new ArrayList<>();
		GrantedAuthority role = new GrantedAuthority() {
			private static final long serialVersionUID = -1826761595869493786L;

			@Override
			public String getAuthority() {
				return recipeUser.getRole().name();
			}
		};
		authorities.add(role);
		return new User(recipeUser.getUserid(), recipeUser.getPwhash(), authorities);

	}
	
	

	private RowMapper<RecipeUser> userRowMapper = ((rs, rowNum) -> {
		RecipeUser user = new RecipeUser();
		user.setUserid(rs.getString("userid"));
		user.setEmail(rs.getString("email"));
		user.setPwhash(rs.getString("pwhash"));
		user.setRole(Role.valueOf(rs.getString("role")));
		System.out.println("Mapped User: " + user);
		return user;
	});
	
	

	public boolean register(RegisterRequest registerRequest) throws EmailAlreadyInUseXception, UserIdAlreadyInUseXception {
		if (getEmailCount(registerRequest.getEmail()) > 0) {
			throw new EmailAlreadyInUseXception("Email Already in Use!");
		}
		if (getUserIdCount(registerRequest.getUserid()) > 0) {
			throw new UserIdAlreadyInUseXception("Username Already in Use!");
		}
		
		String pwhash = BCrypt.hashpw(registerRequest.getPassword(), BCrypt.gensalt(12));

		try {
			jdbcTemplate.update(SQL_CREATE, registerRequest.getUserid(), pwhash, registerRequest.getRole().name(),
					registerRequest.getEmail());
		} catch (DataAccessException e) {
			return false;
		}

		return true;
	}
	
	

	public Integer getUserIdCount(String userid) {
		return jdbcTemplate.queryForObject(SQL_COUNT_BY_USERID, Integer.class, userid);
	}
	public Integer getEmailCount(String email) {
		return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL, Integer.class, email);
	}

}
