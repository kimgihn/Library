package Library.DTO;

public class Manager {
	
	private Long m_no;
	private String m_name;
	private String m_nick;
	private String m_id;
	private String m_password;
	private Long r_count;
	
	public Manager() {}
	
	
	public Manager(String m_name, String m_nick, String m_id, String m_password) {
		super();
		this.m_name = m_name;
		this.m_nick = m_nick;
		this.m_id = m_id;
		this.m_password = m_password;
	}

	public Long getM_no() {
		return m_no;
	}
	public void setM_no(Long m_no) {
		this.m_no = m_no;
	}
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
	public Long getR_count() {
		return r_count;
	}
	public void setR_count(Long r_count) {
		this.r_count = r_count;
	}
	

}
