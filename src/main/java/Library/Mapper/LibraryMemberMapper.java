package Library.Mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import Library.DTO.Book;
import Library.DTO.Member;
import Library.DTO.RentalVo;
import Library.DTO.SearchIDVo;
import Library.DTO.ShowMemberBookVo;

@Mapper
public interface LibraryMemberMapper {
	
	public Integer registMember(Member member);
	
	public Member LoginMember(Map<String,String> Login);
	
	public void LendBook(RentalVo rentalVo);
	
	public List<Book> ShowMemberBook(ShowMemberBookVo lendbook);
	
	public Integer MemberUpdate(Member member);
	
	public Integer ShowMemberBookCount(Long n_no);
	
	public Integer DeleteMember(Long n_no);
	
	public Member SearchID(SearchIDVo searchIDVo);
	
	public Member SearchPW(Map<String,String> map);
	
}
