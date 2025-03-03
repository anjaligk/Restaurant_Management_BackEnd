package com.dto;
 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDto {
	private String token; 
	private Long userId;
	private String roles;
	
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public AuthResponseDto(String token, Long userId, String roles) {
		super();
		this.token = token;
		this.userId = userId;
		this.roles = roles;
	}
	
	
	public AuthResponseDto() {
		super();
	}

	
	
}