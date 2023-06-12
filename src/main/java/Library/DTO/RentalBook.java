package Library.DTO;

import java.time.LocalDateTime;

public class RentalBook {
	
	private Long r_no;
	private Long n_no;
	private Long b_no;
	private LocalDateTime r_regdate;
	
	public RentalBook() {}
	
	public Long getR_no() {
		return r_no;
	}
	public void setR_no(Long r_no) {
		this.r_no = r_no;
	}
	public Long getN_no() {
		return n_no;
	}
	public void setN_no(Long n_no) {
		this.n_no = n_no;
	}
	public Long getB_no() {
		return b_no;
	}
	public void setB_no(Long b_no) {
		this.b_no = b_no;
	}
	public LocalDateTime getR_regdate() {
		return r_regdate;
	}

	public void setR_regdate(LocalDateTime r_regdate) {
		this.r_regdate = r_regdate;
	}
	@Override
	public String toString() {
		return "RentalBook [r_no=" + r_no + ", n_no=" + n_no + ", b_no=" + b_no + ", r_regdate=" + r_regdate + "]";
	}
}
