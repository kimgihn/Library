package Library.Mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import Library.DTO.Book;
import Library.DTO.Manager;
import Library.DTO.Member;
import Library.DTO.RentalBook;
import Library.DTO.SearchVo;
import Library.DTO.ShowMemberBookVo;

@Mapper
public interface LibraryManagerMapper {
	
	public Integer ManagerInsert(Manager mgr);
	
	public Manager selectLogin(Map<String,String> Login);
	
	public Integer RegistBook(Book book);
	
	public Integer UpdateBook(Book book);
	
	public Integer DeleteBook(Book book);
	
	public List<Member> MemberList(Map<String,Integer> map);
	
	public Integer MemberCount();
	
	public List<Member> SearchMemberList(SearchVo searchVo);
	
	public Integer SearchMemberCount(String word);
	
	public List<RentalBook> AllRentalbooks();
	
	public void UpdateNotReturnMembers(Long n_no);
	
	public List<Member> RentalMembers(Map<String,Integer> map);
	
	public Integer RentalMembersCount();
	
	public List<Member> NotReturnMembers();
	
	public RentalBook bookExist(Long b_no);
	
	public Book DetailBook(Long b_no);
	
	public Long RentalBookCount();
	
	public Integer LendMemberCount(Long n_no);
	
	public List<Book> MemberLendDetail(ShowMemberBookVo lendbook);

}
