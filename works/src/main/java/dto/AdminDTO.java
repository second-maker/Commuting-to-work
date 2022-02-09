package dto;

public class AdminDTO {
	
	private String userId;
	private String password;
	private String name;
	
	public AdminDTO() {
		
	}
	
	public AdminDTO(String userId, String password, String name) {
		
		this.userId = userId;
		this.password = password;
		this.name = name;
		
	}
	
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
	
	
	
	
	
	
}
