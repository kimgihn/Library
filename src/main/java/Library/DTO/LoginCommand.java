package Library.DTO;

public class LoginCommand {
	
	private String id ;
	private String password;
	
	public LoginCommand() {}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
