package Library.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Library.DTO.Book;
import Library.DTO.Manager;
import Library.DTO.Member;
import Library.DTO.RegistCommand;
import Library.DTO.RentalBook;
import Library.DTO.RentalVo;
import Library.DTO.SearchIDVo;
import Library.DTO.SearchVo;
import Library.DTO.ShowMemberBookVo;
import Library.Mapper.LibraryBookMapper;
import Library.Mapper.LibraryManagerMapper;
import Library.Mapper.LibraryMemberMapper;
import Library.Mapper.LibraryRentalBookMapper;

@Service
public class LibraryService {
	
	@Autowired
	private LibraryManagerMapper ManagerMapper;
	
	@Autowired
	private LibraryBookMapper BookMapper;
	
	@Autowired
	private LibraryMemberMapper MemberMapper;
	
	@Autowired
	private LibraryRentalBookMapper RentalMapper;
	
	public Integer ManagerInsert(RegistCommand cmd) {
		Manager mgr = new Manager(cmd.getM_name(),cmd.getM_nick(),cmd.getM_id(),cmd.getM_password());
		int res = ManagerMapper.ManagerInsert(mgr);
		return res;
	}
	
	public Manager m_login(String id,String pw) {
		Map<String,String> Login = new HashMap<>();
		Login.put("m_id", id);
		Login.put("m_pw", pw);
		Manager mgr = ManagerMapper.selectLogin(Login);
		return mgr;
	}
	
	public Member n_login(String id, String pw) {
		Map<String,String> Login = new HashMap<>();
		Login.put("n_id", id);
		Login.put("n_pw", pw);
		Member member = MemberMapper.LoginMember(Login);
		return member;
	}
	public Integer RegistBook(Book book) {
		int res = ManagerMapper.RegistBook(book);
		return res;
	}
	
	public List<Book> Book(int startRow, int endRow){
		Map<String,Integer> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		List<Book> bookList = BookMapper.selectList(map);
		return bookList;
	}
	public Book DetailBook(Long b_no) {
		return ManagerMapper.DetailBook(b_no);
	}
	
	public Integer BookCount() {
		return BookMapper.DBCount();
	}
	
	public Integer SearchCount(String word) {
		return BookMapper.SearchCount(word);
	}
	
	public List<Book> searchBook(int startRow,int endRow, String word){
		SearchVo searchVo = new SearchVo();
		searchVo.setWord(word);
		searchVo.setStartRow(startRow);
		searchVo.setEndRow(endRow);
		List<Book> searchList = BookMapper.searchList(searchVo);
		return searchList;
		
	}
	
	public Integer UpdateBook(Book updateBook) {
		int res = ManagerMapper.UpdateBook(updateBook);
		return res;
	}
	
	public Integer DeleteBook(Book book) {
		int res = ManagerMapper.DeleteBook(book);
		return res;
	}
	
	public Integer MemberRegist(RegistCommand cmd) {
		
		Member member = new Member(
				cmd.getN_name(),
				cmd.getN_nick(),
				cmd.getN_id(),
				cmd.getN_password(),
				cmd.getN_phone1(),
				cmd.getN_Email(),
				cmd.getN_ssn1(),
				cmd.getN_ssn2(),
				cmd.getN_phone2(),
				cmd.getN_lend());
		
		if(cmd.getN_profile() != null) {
			member.setN_profile(cmd.getN_profile());
		}
		
		int res = MemberMapper.registMember(member);
		return res;
	}
	
	public List<Member> MemberList(int startRow,int endRow){
		Map<String,Integer> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		List<Member> memberList = ManagerMapper.MemberList(map);
		return memberList;
	}
	
	public Integer MemberCount() {
		return ManagerMapper.MemberCount();
	}
	
	public List<Member> searchMember(String word,int startRow,int endRow){
		SearchVo searchVo = new SearchVo();
		searchVo.setEndRow(endRow);
		searchVo.setStartRow(startRow);
		searchVo.setWord(word);
		
		List<Member> searchMember = ManagerMapper.SearchMemberList(searchVo);
		return searchMember;
	}
	public Integer SearchMemberCount(String word) {
		return ManagerMapper.SearchMemberCount(word);
	}
	
	
	public Integer RentalBook(Long n_no,Long b_no) {
		Map<String,Long> map = new HashMap<>();
		map.put("b_no", b_no);
		map.put("n_no", n_no);
		int res = RentalMapper.Rental(map);
		return res;
	}
	
	public void BookLend(Long n_no) {
		RentalVo rentalVo = new RentalVo();
		rentalVo.setN_no(n_no);
		rentalVo.setN_lend("책빌림");
		MemberMapper.LendBook(rentalVo);
	}
	
	public void ReturnBook(Long n_no) {
		RentalVo rentalVo = new RentalVo();
		rentalVo.setN_no(n_no);
		rentalVo.setN_lend("안빌림");
		MemberMapper.LendBook(rentalVo);
	}
	
	public Integer BookReturn(Long memberNo,Long bookNo) {
		Map<String,Long> map = new HashMap<>();
		map.put("b_no", bookNo);
		map.put("n_no", memberNo);
		int res = RentalMapper.ReturnBook(map);
		return res;
	}
	
	public RentalBook SearchRental(Long n_no,Long b_no){
		Map<String,Long> map = new HashMap<>();
		map.put("b_no", b_no);
		map.put("n_no", n_no);
		RentalBook SearchRental = RentalMapper.SearchRental(map);
		return SearchRental;
	}
	
	public List<Book> ShowMemberBook(int startRow,int endRow, Long n_no) {
		ShowMemberBookVo lendbook = new ShowMemberBookVo();
		lendbook.setN_no(n_no);
		lendbook.setEndRow(endRow);
		lendbook.setStartRow(startRow);
		return MemberMapper.ShowMemberBook(lendbook);
	}
	
	public Integer MemberUpdate(Member memberUpdate) {
		int res = MemberMapper.MemberUpdate(memberUpdate);
		return res;
	}
	
	public Integer LendBookCount(Long n_no) {
		return MemberMapper.ShowMemberBookCount(n_no);
	}
	
	public void BookPlus(Long b_no) {
		BookMapper.BookPlus(b_no);
	}
	public void BookMin(Long b_no) {
		BookMapper.BookMin(b_no);
	}
	public Integer BookAmount(Long b_no) {
		return BookMapper.BookAmount(b_no);
	}
	
	public List<RentalBook> AllRentalbooks(){
		return ManagerMapper.AllRentalbooks();
	}
	public void updateNotReturn(Long n_no) {
		ManagerMapper.UpdateNotReturnMembers(n_no);
	}
	public Integer RentalMembersCount() {
		return ManagerMapper.RentalMembersCount();
	}
	public List<Member> RentalMembers(int startRow,int endRow){
		Map<String,Integer> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return ManagerMapper.RentalMembers(map);
	}
	public List<Member> NotReturnMembers(){
		return ManagerMapper.NotReturnMembers();
	}
	public Integer DeleteMember(Long n_no) {
		return MemberMapper.DeleteMember(n_no);
	}
	public RentalBook bookExist(Long b_no) {
		return ManagerMapper.bookExist(b_no);
	}
	public Member SearchID(String n_name,int n_ssn1,int n_ssn2) {
		SearchIDVo searchIDVo = new SearchIDVo();
		searchIDVo.setN_name(n_name);
		searchIDVo.setN_ssn1(n_ssn1);
		searchIDVo.setN_ssn2(n_ssn2);
		Member member = MemberMapper.SearchID(searchIDVo);
		return member;
	}
	
	public Member SearchMemberPW(Map<String,String>map){
		Member SearchMember = MemberMapper.SearchPW(map); 
		return SearchMember;
	}
	
	public Long RentalBookCount() {
		return ManagerMapper.RentalBookCount();
	}
	public Integer LendMemberCount(Long n_no) {
		return ManagerMapper.LendMemberCount(n_no);
	}
	public List<Book> MemberLendDetail(int startRow,int endRow,Long n_no) {
		ShowMemberBookVo lendbook = new ShowMemberBookVo();
		lendbook.setEndRow(endRow);
		lendbook.setN_no(n_no);
		lendbook.setStartRow(startRow);
		return ManagerMapper.MemberLendDetail(lendbook);
	}
}
