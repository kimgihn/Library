package Library.Mapper;


import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import Library.DTO.RentalBook;

@Mapper
public interface LibraryRentalBookMapper {
	
	public Integer Rental(Map<String,Long> map);
	
	public Integer ReturnBook(Map<String,Long> map);
	
	public RentalBook SearchRental(Map<String,Long> map);

}
