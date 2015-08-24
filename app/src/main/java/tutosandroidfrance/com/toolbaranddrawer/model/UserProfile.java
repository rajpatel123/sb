package tutosandroidfrance.com.toolbaranddrawer.model;

public class UserProfile {



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public String getSn_id() {
		return sn_id;
	}

	public void setSn_id(String sn_id) {
		this.sn_id = sn_id;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	private String name = "";
	private String email="";
	private String img_url="";
	private String sn_id="";
	private String user_type="email";

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	private String dob;
}
