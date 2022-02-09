package dto;

public class UserDTO {
	
	private String userId;
	private String name;
	private String password;
	
	public UserDTO() {
		
	}
	
	public UserDTO(String userId, String name, String password) {
		
		this.userId = userId;
		this.name = name;
		this.password = password;
	}
	
	
	public String getUserId() {
		return this.userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
