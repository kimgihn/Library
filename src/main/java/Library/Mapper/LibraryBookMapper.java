package Library.Mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import Library.DTO.Book;
import Library.DTO.SearchVo;

@Mapper
public interface LibraryBookMapper {
	
	public List<Book> selectList(Map<String,Integer> map);
	
	public Integer DBCount();
	
	public List<Book> searchList(SearchVo searchVo);
	
	public Integer SearchCount(String word);
	
	public void BookMin(Long b_no);
	
	public void BookPlus(Long b_no);
	
	public Integer BookAmount(Long b_no);

}
