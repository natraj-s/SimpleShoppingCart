package beans;

public class User {
	private String email = "";
	private String password = "";
	private String emailMessage = "";
	private String pwdMessage = "";
	
	public User() {
		
	}
	
	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailMessage() {
		return emailMessage;
	}
	
	public String getPwdMessage() {
		return pwdMessage;
	}

	public boolean validateEmail() {
		if(!email.matches("\\w+@\\w+\\.\\w+")) {
			emailMessage = "Invalid email address";
			return false;
		}
		
		return true;
	}
	
	public boolean validatePassword() {		
		if(password.length() < 8) {
			pwdMessage = "Password must be atleast 8 characters";
			return false;
		}
		else if(password.matches("\\w*\\s+\\w*")) {
			pwdMessage = "Password cannot contain spaces";
			return false;
		}
		
		return true;
	}

}
