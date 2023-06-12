package Library.DTO;

import java.time.LocalDateTime;

public class Book {
	
	private String b_code;
	private String b_name;
	private LocalDateTime b_regdate;
	private String b_price;
	private String b_writer;
	private String b_imgName;
	private int b_amount;
	private String b_intro;
	private Long b_no;
	private String b_content;
	
	public Book() {}

	public Book(String b_code, String b_name, LocalDateTime b_regdate, String b_price, String b_writer,int b_amount,String b_intro,String b_content) {
		super();
		this.b_code = b_code;
		this.b_name = b_name;
		this.b_regdate = b_regdate;
		this.b_price = b_price;
		this.b_writer = b_writer;
		this.b_amount = b_amount;
		this.b_intro = b_intro;
		this.b_content = b_content;
	}

	public String getB_code() {
		return b_code;
	}

	public void setB_code(String b_code) {
		this.b_code = b_code;
	}

	public String getB_name() {
		return b_name;
	}

	public void setB_name(String b_name) {
		this.b_name = b_name;
	}

	public LocalDateTime getB_regdate() {
		return b_regdate;
	}

	public void setB_regdate(LocalDateTime b_regdate) {
		this.b_regdate = b_regdate;
	}

	public String getB_price() {
		return b_price;
	}

	public void setB_price(String b_price) {
		this.b_price = b_price;
	}

	public String getB_writer() {
		return b_writer;
	}

	public void setB_writer(String b_writer) {
		this.b_writer = b_writer;
	}

	public String getB_imgName() {
		return b_imgName;
	}

	public void setB_imgName(String b_imgName) {
		this.b_imgName = b_imgName;
	}

	public int getB_amount() {
		return b_amount;
	}

	public void setB_amount(int b_amount) {
		this.b_amount = b_amount;
	}

	public String getB_intro() {
		return b_intro;
	}

	public void setB_intro(String b_intro) {
		this.b_intro = b_intro;
	}

	public Long getB_no() {
		return b_no;
	}

	public void setB_no(Long b_no) {
		this.b_no = b_no;
	}

	public String getB_content() {
		return b_content;
	}

	public void setB_content(String b_content) {
		this.b_content = b_content;
	}
	
	

	

}
