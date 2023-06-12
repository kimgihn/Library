package Library.DTO;

import java.time.LocalDateTime;

public class RegistCommand {
	
	private String m_name;
	private String m_nick;
	private String m_id;
	private String m_password;
	private String m_password2;
	
	
	private String n_name;
	private String n_nick;
	private String n_id;
	private String n_password;
	private String n_password2;
	private String n_phone1;
	private String n_phone2;
	private String n_Email;
	private int n_ssn1;
	private int n_ssn2;
	private String n_profile;
	private String n_lend;	
	
	
	
	public RegistCommand() {}
	
	
	//Manager
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getM_nick() {
		return m_nick;
	}
	public void setM_nick(String m_nick) {
		this.m_nick = m_nick;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getM_password() {
		return m_password;
	}
	public void setM_password(String m_password) {
		this.m_password = m_password;
	}
	public String getM_password2() {
		return m_password2;
	}

	public void setM_password2(String m_password2) {
		this.m_password2 = m_password2;
	}
	
	public Boolean isCheckPassword() {
		return m_password.equals(m_password2);
	}
//-----------------------------------------------------------		
	
	// Member
	public String getN_name() {
		return n_name;
	}

	public void setN_name(String n_name) {
		this.n_name = n_name;
	}

	public String getN_nick() {
		return n_nick;
	}

	public void setN_nick(String n_nick) {
		this.n_nick = n_nick;
	}

	public String getN_id() {
		return n_id;
	}

	public void setN_id(String n_id) {
		this.n_id = n_id;
	}

	public String getN_password() {
		return n_password;
	}

	public void setN_password(String n_password) {
		this.n_password = n_password;
	}

	public String getN_phone1() {
		return n_phone1;
	}

	public void setN_phone1(String n_phone1) {
		this.n_phone1 = n_phone1;
	}

	public String getN_Email() {
		return n_Email;
	}

	public void setN_Email(String n_Email) {
		this.n_Email = n_Email;
	}

	public int getN_ssn1() {
		return n_ssn1;
	}

	public void setN_ssn1(int n_ssn1) {
		this.n_ssn1 = n_ssn1;
	}

	public int getN_ssn2() {
		return n_ssn2;
	}

	public void setN_ssn2(int n_ssn2) {
		this.n_ssn2 = n_ssn2;
	}

	public String getN_phone2() {
		return n_phone2;
	}

	public void setN_phone2(String n_phone2) {
		this.n_phone2 = n_phone2;
	}

	public String getN_password2() {
		return n_password2;
	}

	public void setN_password2(String n_password2) {
		this.n_password2 = n_password2;
	}
	public String getN_profile() {
		return n_profile;
	}
	public void setN_profile(String n_profile) {
		this.n_profile = n_profile;
	}
	public String getN_lend() {
		return n_lend;
	}
	public void setN_lend(String n_lend) {
		this.n_lend = n_lend;
	}
	public Boolean n_isCheckPassword() {
		return n_password.equals(n_password2);
	}
	
	

}
