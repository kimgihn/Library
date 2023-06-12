package Library.DTO;

import java.time.LocalDateTime;

public class Member {
	
	private Long n_no;
	private String n_name;
	private String n_nick;
	private String n_id;
	private String n_password;
	private LocalDateTime n_regdate;
	private String n_phone1;
	private String n_Email;
	private int n_ssn1;
	private int n_ssn2;
	private String n_profile;
	private String n_lend;
	private String n_phone2;
	
	public Member() {}
	
	

	public Member(String n_name, String n_nick, String n_id, String n_password, String n_phone1, String n_Email,
			int n_ssn1, int n_ssn2,String n_phone2,String n_lend) {
		super();
		this.n_name = n_name;
		this.n_nick = n_nick;
		this.n_id = n_id;
		this.n_password = n_password;
		this.n_phone1 = n_phone1;
		this.n_Email = n_Email;
		this.n_ssn1 = n_ssn1;
		this.n_ssn2 = n_ssn2;
		this.n_phone2 = n_phone2;
		this.n_lend = n_lend;
	}



	public Long getN_no() {
		return n_no;
	}

	public void setN_no(Long n_no) {
		this.n_no = n_no;
	}

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

	
	public LocalDateTime getN_regdate() {
		return n_regdate;
	}

	public void setN_regdate(LocalDateTime n_regdate) {
		this.n_regdate = n_regdate;
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
	public String getN_phone2() {
		return n_phone2;
	}
	public void setN_phone2(String n_phone2) {
		this.n_phone2 = n_phone2;
	}
	
}
