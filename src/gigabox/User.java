package gigabox;

public class User {
	private String ID;
	private String password;
	private String phone;
	private String birth;
	private String email;
	
	public void setID(String id) {
		ID = id;
	}
	public String getID() {
		return ID;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
