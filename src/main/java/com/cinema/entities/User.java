package com.cinema.entities;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

 
    @EntityScan
	@Entity
	@Table (name = "users_table")
	public class User implements UserDetails {
        private static final long serialVersionUID = 123456789L;


		@Id 
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "user_id")
		private Integer user_id;

		
		@Column(name = "user_name")
		private String userName;
		
		@Column(name = "password")
		private String password;
		
		
		
//		@Enumerated(EnumType.STRING)
//		private Role role;
		
		@Column(name = "role")
		private String role;
		
		public String getRole() {
			return role;
		}

//		public void setRole(Role role) {
//			this.role = role;
//		}
//

		
		public void setRole(String role) {
			this.role = role;
		}

		public static User fromUserDetails(UserDetails userDetails) {
	        User user = new User();

	        // Copy UserDetails properties to User
	        user.setuserName(userDetails.getUsername());
	        user.setPassword(userDetails.getPassword());
	        user.setRole(userDetails.getAuthorities().toString());
	        // Copy other necessary properties

	        return user;
	    }
		
		//constructor (no user ID is needed to construct user because user id is auto generated using annotation)
		public User( String userName, String password, String role, Integer user_id) {
			this.userName = userName;
			this.password = password;
			this.role = role;
			this.user_id = user_id;
		}

		public User() {
			// TODO Auto-generated constructor stub
		}

		public Integer getUser_id() {
			return user_id;
		}

		public void setUser_id(Integer user_id) { 
			this.user_id = user_id;
		}

		public String getuserName() {
			return userName;
		}

		public void setuserName(String userName) {
			this.userName = userName;
		}

		

		public void setPassword(String password) {
			this.password = password;
		}

		
	
		
		
		
		
	
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			// TODO Auto-generated method stub
			 if (role == null) {
		            return Collections.emptyList();
		        }
			return List.of(new SimpleGrantedAuthority(role));
		}

		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return userName;
		}
		

		

		@Override
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public String getPassword() {
			// TODO Auto-generated method stub
			return password;
		}
		


	
}
