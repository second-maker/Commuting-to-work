package dto;

public class WorkDTO {
	
	private String userId;
	private String date;
	private String startTime;
	private String endTime;
	private String breakTime;
	private String endBreakTime;
	
	
	
	public WorkDTO() {
		
	}
	
	public WorkDTO(String userId, String date, String startTime, String endTime, String breakTime, String endBreakTime) {
		
		this.userId = userId;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.breakTime = breakTime;
		this.endBreakTime = endBreakTime;
		
	}
	
	public String getUserId() {
		return this.userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getStartTime() {
		return this.startTime;
	}
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public String getEndTime() {
		return this.endTime;
	}
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public String getBreakTime() {
		return this.breakTime;
	}
	
	public void setBreakTime(String breakTime) {
		this.breakTime = breakTime;
	}
	
	public String getEndBreakTime() {
		return this.endBreakTime;
	}
	
	public void setEndBreakTime(String endBreakTime) {
		this.endBreakTime = endBreakTime;
	}
	
}
