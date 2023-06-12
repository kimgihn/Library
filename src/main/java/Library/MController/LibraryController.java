package Library.MController;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import Library.DTO.Book;
import Library.DTO.CookieVo;
import Library.DTO.Manager;
import Library.DTO.Member;
import Library.DTO.RegistCommand;
import Library.DTO.RentalBook;
import Library.Service.LibraryService;

@Controller
public class LibraryController {
	
	@Autowired
	private LibraryService  service;
	
	CookieVo cv = new CookieVo();
	
	private String msg = "";
	private String url = "";
	
	// 비회원 메인메뉴 
	@RequestMapping(path = {"/","/main.do"}, method = RequestMethod.GET)
	//1개의 값으로 mapping할때는 value를 사용, 2개이상의 값으로 매핑시 path ={" ", " ",..} 형식으로 사용
	public String LoginMain(@CookieValue(value ="remember",required = false)Cookie cookie,Model model) {
		
		if(cookie != null) {
			model.addAttribute("remember",cookie.getValue());
			cv.setRememberID(true);
		}
		return "main";
	}
	
	@RequestMapping(value = "/GoToRegist.do",method = RequestMethod.POST)
	public String registPOST(Model model,@RequestParam(value = "m_code",required = false)String m_code) {
		if(m_code.equals("kimminseok")) {
			return "redirect:manager/m_registForm";
		}
		return "redirect:n_registForm.do";
	}
	// 관리자 회원가입
	@RequestMapping(value = "/manager/m_registForm",method = RequestMethod.GET)
	public String m_registFormGET(@ModelAttribute("cmd")RegistCommand cmd) {
		return "manager/m_registForm";
	}
	
	@RequestMapping(value = "/manager/m_registForm",method = RequestMethod.POST)
	public String m_registFormPOST(@ModelAttribute("cmd")RegistCommand cmd,Model model) {
	
		int res = 0;
		
		if(cmd.isCheckPassword()) {
			res = service.ManagerInsert(cmd);
		}else {
			msg = "비밀번호를 다시확인해주세요!!";
			url = "../manager/m_registForm";
		}
		
		if(res > 0) {
			msg = "정상적으로 가입이되었습니다.";
			url = "../";
		}else {
			msg = "정상적으로 가입이 되지않았습니다.";
			url = "../manager/m_registForm";
		}
		model.addAttribute("msg",msg);
		model.addAttribute("url",url);
		return "message";
	}
	
	@RequestMapping(value = "/GoToLogin.do")
	public String loginPOST(@RequestParam(value="saveId",defaultValue = "false")boolean saveId,HttpServletResponse resp,HttpSession session,Model model,@RequestParam Map<String, String> params,@RequestParam(value = "m_code",required = false)String m_code) {
		
		// 쿠키
		cv.setRememberID(saveId);
		if(saveId) {
			Cookie remember = new Cookie("remember",params.get("id"));
			if(cv.isRememberID()) {
				remember.setMaxAge(60*4);
			}else {
				remember.setMaxAge(0);
			}
				resp.addCookie(remember);
		}
		
		// 관리자 로그인
		if(m_code.equals("kimminseok")) {
			Manager mgr = service.m_login(params.get("id"), params.get("pw"));
			if(mgr == null) {
				msg = "관리자 로그인에 실패하였습니다.";
				url = "main.do";
				model.addAttribute("msg",msg);
				model.addAttribute("url",url);
				return "message";
			}else {
				mgr.setR_count(service.RentalBookCount());
				session.setAttribute("mgr", mgr);
				return "manager/m_view";
			}
		}
		// 일반 회원 로그인
		Member member = service.n_login(params.get("id"), params.get("pw"));
		if(member == null) {
			msg = "일반회원 로그인에 실패하였습니다.";
			url = "main.do";
			model.addAttribute("msg",msg);
			model.addAttribute("url",url);
			return "message";
		}else {
			session.setAttribute("member", member);
			return "member/n_view";
		}
	}
	// 아이디 찾기
	@RequestMapping(value = "/searchID.do",method = RequestMethod.GET)
	public String SearchMemberIDGET() {
		return "SearchMemberID";
	}
	@RequestMapping(value = "/searchID.do",method = RequestMethod.POST)
	public String SearchMemberIDPOST(@RequestParam Map<String,String> map,Model model) {
		int n_ssn1 = Integer.parseInt(map.get("n_ssn1"));
		int n_ssn2 = Integer.parseInt(map.get("n_ssn2"));
		Member SearchMember = service.SearchID(map.get("n_name"), n_ssn1, n_ssn2);
		
		if(SearchMember == null) {
			String notFound = "주민등록번호 혹은 이름이 잘못되거나 회원가입을 안하신거 갔습니다. 다시확인해주세요!!";
			model.addAttribute("notFound",notFound);
			return "resultMemberID";
		}
		model.addAttribute("SearchMember",SearchMember);
		return "resultMemberID";
	}
	
	// 비밀번호 찾기
	@RequestMapping(value = "/searchPW.do",method = RequestMethod.GET)
	public String SearchMemberPWGET() {
		return "SearchMemberPW";
	}
	
	@RequestMapping(value = "/searchPW.do",method = RequestMethod.POST)
	public String SearchMemberPWPOST(@RequestParam Map<String,String> map,Model model) {
		Member SearchMember = service.SearchMemberPW(map);
		
		if(SearchMember == null) {
			String notFound = "아이디 혹은 이름이 잘못된것같습니다. 다시 한번 확인해주세요!!";
			model.addAttribute("notFound",notFound);
		}
		else {
			char pw[] = SearchMember.getN_password().toCharArray();
			int lenPW = SearchMember.getN_password().length();
			for(int i = 3; i < lenPW ; i++) {
				pw[i] = '*';
			}
			String password = String.valueOf(pw);
			model.addAttribute("name",SearchMember.getN_name());
			model.addAttribute("password",password);
		}
		return "resultMemberPW";
	}
	
	//로그아웃
	@RequestMapping(value = "/logout.do",method = RequestMethod.POST)
	public String logoutPOST(HttpSession session,Model model,HttpServletRequest req) {
		session.invalidate();
		msg = "로그아웃 되었습니다.";
		url = req.getContextPath(); // 세션을헤제 하면 현재연결중인 프로젝트까지 같이 사라지기 때문에 /LibraryProject/ 프로젝트 이름을 넣어준다.
		model.addAttribute("msg",msg);
		model.addAttribute("url",url);
		return "message";
	}
	
	// 책등록
	@RequestMapping(value = "/RegistBook.do",method = RequestMethod.GET)
	public String bookRegstGET(@ModelAttribute("book")Book book) {
		return "manager/bookRegistForm";
	}
	
	@RequestMapping(value = "/RegistBook.do",method = RequestMethod.POST)
	public String bookRegstPOST(HttpSession session,@ModelAttribute("book")Book book,Model model,@RequestParam("file")MultipartFile file,HttpServletRequest req) throws IOException {
		
		Manager mgr = (Manager) session.getAttribute("mgr");
		if(mgr == null) {
			return "main";
		}
		
		String upPath = req.getSession().getServletContext().getRealPath("/img");
		String fileName = file.getOriginalFilename();
		String filePath = upPath +File.separator+fileName ;
		
		byte filebyte[] = file.getBytes();
		book.setB_imgName(fileName);
		int res = service.RegistBook(book);
		
		if(res>0) {
			Path path = Paths.get(filePath);
			Files.write(path, filebyte);
			System.out.println("업로드 성공");
			msg = "정상적으로 책을 등록하였습니다.";
			url = "m_main.do";
		}else {
			msg = "책을 등록하지 못했습니다.";
			url = "RegistBook.do";
		}
		model.addAttribute("msg",msg);
		model.addAttribute("url",url);
		
		return "message";
	}
	
	
	// (일반회원, 관리자 , 비회원) 책 목록 보기
	@RequestMapping(value = "/bookList.do",method=RequestMethod.GET)
	public String BookListGET(Model model,@RequestParam(defaultValue = "1")int pageNo,@RequestParam(value = "alt", required = false)String alt) {
		
		int pageSize = 5;
		int totalCount = service.BookCount();
		int pageCount = (int) Math.ceil((double)totalCount/pageSize);
		int pageGroupSize = 5;
		int startPageGroup = ((pageNo-1)/pageGroupSize)*pageGroupSize + 1;
		int endPageGroup = startPageGroup + pageGroupSize -1;
		
		if(endPageGroup > pageCount) {
			endPageGroup = pageCount;
		}
		
		int startRow = (pageNo-1)*pageSize +1;
		int endRow = startRow + pageSize - 1;		
		
		List<Book> bookList = service.Book(startRow, endRow);
		String url = "bookList.do?";
		
		model.addAttribute("url",url);
		model.addAttribute("alt",alt);
		model.addAttribute("bookList",bookList);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("startPageGroup", startPageGroup);
		model.addAttribute("endPageGroup", endPageGroup);
		alt="";
		return "book/bookList";
	}
	// 책 상세정보보기
	@RequestMapping(value = "/bookDetail.do",method=RequestMethod.GET)
	public String BookDetailGET(@RequestParam("b_no")Long b_no,Model model) {
		Book DetailBook = service.DetailBook(b_no);
		model.addAttribute("DetailBook",DetailBook);
		return "book/bookDetail";
	}
	
	// 책 검색
	@RequestMapping(value = "/search.do",method = RequestMethod.GET)
	public String SearchBookListGET(Model model,@RequestParam(defaultValue = "1")int pageNo,@RequestParam("word")String word) {
		
		int pageSize = 5;
		int totalCount = service.SearchCount(word);
		int pageCount = (int) Math.ceil((double)totalCount/pageSize);
		int pageGroupSize = 5;
		int startPageGroup = ((pageNo-1)/pageGroupSize) * pageGroupSize + 1;
		int endPageGroup = startPageGroup + pageGroupSize - 1;
		
		if(endPageGroup > pageCount) {
			endPageGroup = pageCount;
		}
		
		int startRow = (pageNo-1)*pageSize +1;
		int endRow = startRow + pageSize - 1;
		
		List<Book> searchList = service.searchBook(startRow, endRow, word);
		String url = "search.do?word="+word+"&";
		
		model.addAttribute("url",url);
		model.addAttribute("searchList",searchList);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("startPageGroup", startPageGroup);
		model.addAttribute("endPageGroup", endPageGroup);
		
		return "book/bookList";
	}
	// 책 정보 수정
	@RequestMapping(value = "/updateBook.do",method = RequestMethod.GET)
	public String BookUpdateGET(HttpSession session,@ModelAttribute("updateBook")Book updateBook,Model model,@RequestParam("no")Long no,@RequestParam(value = "bimage2",required = false)String bimage2) {
		Manager mgr = (Manager) session.getAttribute("mgr");
		if(mgr == null) {
			return "main";
		}
		model.addAttribute("no",no);
		model.addAttribute("bimage2",bimage2);
		return "manager/bookUpdate";
	}
	
	@RequestMapping(value = "/updateBook.do",method = RequestMethod.POST)
	public String BookUpdatePOST(HttpServletRequest req,@ModelAttribute("updateBook")Book updateBook,Model model,@RequestParam("bimage")MultipartFile bimage,@RequestParam(value = "bimage2",required = false) String bimage2) throws IOException {
		
		//기존에 있던 파일 지우고
		if (!bimage.isEmpty()) {
			String upPath = req.getSession().getServletContext().getRealPath("/img");
			File file = new File(upPath + File.separator + bimage2);
			if(file.exists()) {
				file.delete();
			}	
		//form 태그로 updateBook 객체에 속성을 넣고 업로드 다시함
			String fileName = bimage.getOriginalFilename();
			String filePath = upPath + File.separator + fileName;
			byte updateFile[] = bimage.getBytes();
			updateBook.setB_imgName(fileName);
			Path path = Paths.get(filePath);
			Files.write(path, updateFile);
		}
		
		int res = service.UpdateBook(updateBook);
		if(res>0) {
			System.out.println("수정 완료!!");
			msg = "수정이 완료되었습니다.";
			url = "bookList.do";
		}else {
			msg = "수정되지 못했습니다.";
			url = "updateBook.do";
		}
		model.addAttribute("msg",msg);
		model.addAttribute("url",url);
		
		return "message";
	}
	
	// 책 삭제
	@RequestMapping(value = "/deleteBook.do",method=RequestMethod.POST)
	public String deletePOST(@RequestParam("b_no")Long b_no,@RequestParam(value = "b_imgName",required = false)String b_imgName,HttpServletRequest req,Model model) {
		// 반납여부 확인
		RentalBook bookExist = service.bookExist(b_no);
		if(bookExist != null) {
			msg = "아직 반납이 안된 책입니다.";
			url = "bookList.do";
			model.addAttribute("msg",msg);
			model.addAttribute("url",url);
			return "message";
		}
		
		String upPath = req.getSession().getServletContext().getRealPath("/img");
		if(b_imgName != null) {
			File file = new File(upPath + File.separator + b_imgName);
			if(file.exists()) {
				file.delete();
			}
		}
		Book book = new Book();
		book.setB_no(b_no);
		int res = service.DeleteBook(book);
		
		if(res >0) {
			msg = "정상적으로 삭제되었습니다.";
			url = "bookList.do";
		}else {
			msg = "삭제가 취소되었습니다.";
			url = "bookList.do";
		}
		model.addAttribute("msg",msg);
		model.addAttribute("url",url);
		return "message";
	}
	// 일반회원가입
	@RequestMapping(value = "/n_registForm.do",method=RequestMethod.GET)
	public String n_registFormGET(@ModelAttribute("cmd")RegistCommand cmd) {
		return "member/n_registForm";
	}
	
	@RequestMapping(value = "/n_registForm.do",method=RequestMethod.POST)
	public String n_registFormPOST(Model model,@ModelAttribute("cmd")RegistCommand cmd,@RequestParam("profile")MultipartFile profile,HttpServletRequest req) throws IOException {
		
		if(profile.isEmpty()) {
			cmd.setN_profile("notProfile.jpeg");
		}
		
		if(!profile.isEmpty()) {
			String upPath = req.getSession().getServletContext().getRealPath("/img");
			String fileName = profile.getOriginalFilename();
			String filePath = upPath+File.separator+fileName;
			cmd.setN_profile(fileName);
			byte profileBytes[] = profile.getBytes();
			Path path = Paths.get(filePath);
			Files.write(path, profileBytes);
		}
		
		if(cmd.n_isCheckPassword()) {
			int res = service.MemberRegist(cmd);
			
			if(res > 0) {
				msg = "정상적으로 가입이 되었습니다.";
				url = "main.do"; 
			}else {
				msg = "정상적으로 가입이 되지않았습니다.";
				url = "n_registForm.do";
			}
		}else {
			msg = "비밀번호를 다시확인해주세요!!";
			url = "n_registForm.do";
		}
		model.addAttribute("msg",msg);
		model.addAttribute("url",url);
		
		return "message";
	}
	// 회원 리스트
	@RequestMapping(value = "/GoToMemberList.do",method = RequestMethod.GET)
	public String MemberListGET(Model model,@RequestParam(defaultValue = "1")int pageNo,HttpSession session){
		
		Manager mgr = (Manager) session.getAttribute("mgr");
		if(mgr == null) {
			return "main";
		}
		int pageSize = 5;
		int totalCount = service.MemberCount();
		int pageCount = (int) Math.ceil((double)totalCount/pageSize);// 페이징할때 double 필수
		int pageGroupSize = 5;
		int startPageGroup = ((pageNo-1)/pageGroupSize)*pageGroupSize + 1;
		int endPageGroup = startPageGroup + pageGroupSize - 1;
		if(endPageGroup > pageCount) {
			endPageGroup = pageCount;
		}
		int startRow = (pageNo-1)*pageSize + 1;
		int endRow = startRow + pageSize - 1;
		List<Member> memberList = service.MemberList(startRow, endRow);
		String url = "GoToMemberList.do?";
		
		model.addAttribute("url",url);
		model.addAttribute("memberList",memberList);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("startPageGroup", startPageGroup);
		model.addAttribute("endPageGroup", endPageGroup);
	
		return "manager/memberList";
	}
	// 회원 검색
	@RequestMapping(value = "/SearchMember.do", method = RequestMethod.GET)
	public String SearchMember(@RequestParam(defaultValue = "1") int pageNo,Model model,@RequestParam("word")String word) {
		
		int pageSize = 5;
		int totalCount = service.SearchMemberCount(word);
		int pageCount = (int) Math.ceil((double)totalCount/pageSize);
		int pageGroupSize = 5;
		int startPageGroup = ((pageNo-1)/pageGroupSize)*pageGroupSize + 1;
		int endPageGroup = startPageGroup + pageGroupSize -1;
		
		if(endPageGroup > pageCount) {
			endPageGroup = pageCount;
		}
		
		int startRow = (pageNo-1)*pageSize +1;
		int endRow = startRow + pageSize -1;
		List<Member> searchMemberList = service.searchMember(word, startRow, endRow);
		String url = "SearchMember.do?word="+ word + "&";
		
		model.addAttribute("url",url);
		model.addAttribute("searchMemberList",searchMemberList);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("startPageGroup", startPageGroup);
		model.addAttribute("endPageGroup", endPageGroup);
		
		return "manager/memberList";
	}
	// 회원 대여 도서정보
	@RequestMapping(value = "GoToMemberRentalBookInfo.do",method=RequestMethod.GET)
	public String MemberRentalBookInfo(@RequestParam("name")String name,Model model,HttpSession session,@RequestParam(defaultValue="1")int pageNo,@RequestParam("n_no")Long n_no,@RequestParam(value = "lend",required = false)String lend) {
		
		Manager mgr = (Manager) session.getAttribute("mgr");
		if(mgr == null) {
			return "main";
		}
		
		int pageSize = 5;
		int totalCount = service.LendMemberCount(n_no);
		int pageCount = (int) Math.ceil((double)totalCount/pageSize);
		int pageGroupSize = 5;
		int startPageGroup = ((pageNo-1)/pageGroupSize)*pageGroupSize +1;
		int endPageGroup = startPageGroup + pageGroupSize -1;
		if(endPageGroup > pageCount) {
			endPageGroup = pageCount;
		}
		int startRow = (pageNo-1)*pageSize +1;
		int endRow = startRow + pageSize -1 ;
		List<Book> MemberLendBook = service.MemberLendDetail(startRow, endRow, n_no);
		String url = "GoToMemberRentalBookInfo.do?";
		
		model.addAttribute("lend",lend);
		model.addAttribute("url",url);
		model.addAttribute("name",name);
		model.addAttribute("MemberLendBook",MemberLendBook);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("startPageGroup", startPageGroup);
		model.addAttribute("endPageGroup", endPageGroup);
		
		
		return "manager/memberLendBook";
	}
	
	//도서 대여
	@RequestMapping(value = "/rental.do",method = RequestMethod.POST)
	public String rentalPOST(HttpSession session, @RequestParam("b_no")Long b_no,Model model,@RequestParam("b_name")String b_name) throws UnsupportedEncodingException {
		
		Member member = (Member)session.getAttribute("member");
		if(member == null) {
			return "main";
		}
		Long n_no = member.getN_no();
		
		//랜탈을 했는데 또 같은 책을 랜탈하는 경우
		RentalBook SearchRental = service.SearchRental(n_no, b_no);
		if(SearchRental != null) {
			String alt = "이미 대여하셨습니다."; // 특수문자 공백 이들어가서 인코딩을 해줘야 한다.
			String encodedAlt = URLEncoder.encode(alt, "UTF-8");
			return "redirect:bookList.do?alt="+encodedAlt;
		}
		int bookCount = service.BookAmount(b_no);
		if(bookCount == 0) {
			msg = b_name + "책을 다 빌려갔습니다. 다른책을 대여해주세요!!";
			url = "bookList.do";
			return "message";
		}
		
		int res = service.RentalBook(n_no, b_no); // rental테이블에 추가하는 작업
		if(res > 0) {
			msg = b_name + " 책을 랜탈하셨습니다.";
			service.BookLend(n_no); //member 테이블에 lend속성에 책을 빌렸다고 업데이트하는 작업 
			service.BookMin(b_no);
			url = "bookList.do";
			
		}else {
			msg = "책을 랜탈하는 과정에서 문제가 발생했습니다.";
			url = "bookList.do";
		}
		model.addAttribute("msg",msg);
		model.addAttribute("url",url);
		return "message";
	}
	
	// 도서 반납
	@RequestMapping(value = "/returnBook.do",method = RequestMethod.GET)
	public String returnBookGET(@RequestParam("bookNo")Long bookNo,Model model,HttpSession session) throws UnsupportedEncodingException{
		
		Member member = (Member) session.getAttribute("member");
		if(member == null) {
			return "main";
		}
		Long memberNo = member.getN_no();
		
		//대여하지 않은상태에서 반납을 누른경우
		RentalBook SearchRental = service.SearchRental(memberNo, bookNo);
		if(SearchRental == null) {
			String alt = "책을 대여하시지 않으셨습니다."; // 특수문자 공백 이들어가서 인코딩을 해줘야 한다.
			String encodeAlt = URLEncoder.encode(alt,"UTF-8");
			return "redirect:bookList.do?alt="+encodeAlt;
		}
		 
		int res = service.BookReturn(memberNo, bookNo); 
		
		if(res > 0) {
			service.ReturnBook(memberNo);
			service.BookPlus(bookNo);
			msg = "반납완료!!";
			url = "bookList.do";
		}else {
			msg = "반납에 실패했습니다.";
			url = "bookList.do";
		}
		model.addAttribute("msg",msg);
		model.addAttribute("url",url);
		return "message";
	}
	
	// 빌린 책 정보 보기
	@RequestMapping(value = "/GotoLendBook.do",method = RequestMethod.GET)
	public String LendBookGet(Model model,HttpSession session,@RequestParam(defaultValue = "1")int pageNo) {
		Member member = (Member) session.getAttribute("member");
		Long n_no = member.getN_no();
		if(member == null) {
			return "main";
		}
		
		int pageSize = 5;
		int totalCount = service.LendBookCount(n_no);
		int pageCount = (int) Math.ceil((double)totalCount/pageSize);
		int pageGroupSize = 5;
		int startPageGroup = ((pageNo - 1)/pageGroupSize)*pageGroupSize + 1;
		int endPageGroup = startPageGroup + pageGroupSize -1;
		if(endPageGroup > pageCount) {
			endPageGroup = pageCount;
		}
		int startRow = (pageNo - 1)*pageSize +1;
		int endRow = startRow + pageSize -1;
		
		List<Book> MemberBookList = service.ShowMemberBook(startRow, endRow, n_no);
		String url = "GotoLendBook.do?";
		
		model.addAttribute("url",url);
		model.addAttribute("MemberBookList",MemberBookList);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("startPageGroup", startPageGroup);
		model.addAttribute("endPageGroup", endPageGroup);
		return "member/LendBook";
	}
	// 내정보 보기
	@RequestMapping(value = "/GotoMyselfInfo.do",method = RequestMethod.GET)
	public String MyInformationGET(Model model,HttpSession session) {
		Member member = (Member) session.getAttribute("member");
		if(member == null) {
			return "main";
		}
		model.addAttribute("member",member);
		return "member/MyInformation";
	}
	
	// 내정보 수정
	@RequestMapping(value = "/memberUpdate.do",method = RequestMethod.GET)
	public String MemberUpdateGET(HttpSession session,@ModelAttribute("memberUpdate")Member memberUpdate) {
		Member member = (Member) session.getAttribute("member");
		if(member == null) {
			return "main";
		}
		return "member/memberUpdate";
	}
	
	@RequestMapping(value = "/memberUpdate.do",method = RequestMethod.POST)
	public String MemberUpdatePOST(HttpServletRequest req,HttpSession session,@ModelAttribute("memberUpdate")Member memberUpdate,Model model,@RequestParam(value = "newProfile") MultipartFile newProfile) throws IOException {
		
		Member member = (Member) session.getAttribute("member");
		memberUpdate.setN_no(member.getN_no());
		
		if(!newProfile.isEmpty()) {
			String upPath = req.getSession().getServletContext().getRealPath("/img");
			
			File file = new File(upPath+File.separator+member.getN_profile());
			if(file.exists()) {
				file.delete();
			}
			String fileName = newProfile.getOriginalFilename();
			String filePath = upPath+File.separator+fileName;
			memberUpdate.setN_profile(fileName);
			
			byte profileByte[] = newProfile.getBytes();
			Path path = Paths.get(filePath);
			Files.write(path, profileByte);
		}
		int res = service.MemberUpdate(memberUpdate);
		if(res >0) {
			session.invalidate();
			msg = "회원 정보 수정이 완료되었습니다. 다시 로그인을 해주세요!!";
			url = req.getContextPath();
		}else {
			msg = "회원 정보가 수정되지않았습니다.";
			url = "memberUpdate.do";
		}
		model.addAttribute("msg",msg);
		model.addAttribute("url",url);
		
		return "message";
	}
	
	// 관리자 메인메뉴
	@RequestMapping(value = "/m_main.do",method = RequestMethod.GET)
	public String m_viewGet(HttpSession session) {
		Manager mgr = (Manager) session.getAttribute("mgr");
		if(mgr == null) {
			return "main";
		}
		mgr.setR_count(service.RentalBookCount());
		session.setAttribute("mgr", mgr);
		return "manager/m_view";
	}
	
	// 회원 메인메뉴
	@RequestMapping(value = "/n_main.do",method = RequestMethod.GET)
	public String n_viewGet(HttpSession session) {
		Member member = (Member) session.getAttribute("member");
		if(member == null) {
			return "main";
		}
		return "member/n_view";
	}
	
	// 회원 대여도서보기
	@RequestMapping(value = "/GoToRentalMemberList.do",method = RequestMethod.GET)
	public String RentalMemberGET(HttpSession session,Model model,@RequestParam(defaultValue = "1")int pageNo) {
		Manager mgr = (Manager) session.getAttribute("mgr");
		if(mgr == null) {
			return "main";
		}
		int pageSize = 5;
		int totalCount = service.RentalMembersCount();
		int pageCount = (int) Math.ceil((double)totalCount/pageSize);
		int pageGroupSize = 5;
		int startPageGroup = ((pageNo - 1)/pageGroupSize)*pageGroupSize + 1;
		int endPageGroup = startPageGroup + pageGroupSize -1;
		if(endPageGroup > pageCount) {
			endPageGroup = pageCount;
		}
		int startRow = (pageNo - 1)*pageSize +1;
		int endRow = startRow + pageSize -1;
		List<Member> rmemberList = service.RentalMembers(startRow, endRow);
		String url = "GoToRentalMemberList.do?";
		
		model.addAttribute("url",url);
		model.addAttribute("rmemberList",rmemberList);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("startPageGroup", startPageGroup);
		model.addAttribute("endPageGroup", endPageGroup);
		return "manager/RentalMember";
	}
	
	// 미반납 회원 리스트
	@RequestMapping(value = "/GoToNotReturnMemberList.do",method = RequestMethod.GET)
	public String NotReturnMemberListGET(Model model,HttpSession session) {
		Manager mgr = (Manager) session.getAttribute("mgr");
		if(mgr == null) {
			return "main";
		}
		List<RentalBook> AllRentalbooks = service.AllRentalbooks();
		for (RentalBook rentals : AllRentalbooks) {
			LocalDateTime currentDate = LocalDateTime.now();
			LocalDateTime dueDate = rentals.getR_regdate().plusDays(1);
//			System.out.println("현재시간 :" + currentDate);
//			System.out.println("10분을 더한시간 :"+dueDate);
//			System.out.println(rentals.getR_regdate());
//			System.out.println(currentDate.isAfter(dueDate));
			if(currentDate.isAfter(dueDate)) {
				Long n_no = rentals.getN_no();
				service.updateNotReturn(n_no);
			}
		}
		List<Member> NotReturnMembers = service.NotReturnMembers();
		model.addAttribute("NotReturnMembers",NotReturnMembers);
		return "manager/NotReturnMemberList";
	}
	
	//회원 탈퇴
	@RequestMapping(value = "/DeleteMember.do",method = RequestMethod.POST)
	public String DeleteMemberPOST(Model model,HttpSession session,HttpServletRequest req) {
		Member member = (Member) session.getAttribute("member");
		List<RentalBook> RentalList = service.AllRentalbooks();
		for(RentalBook rental : RentalList) {
			Long n_no = rental.getN_no();
			if(member.getN_no() == n_no) {
				msg = "대여한 책이 존재합니다.책을 먼저반납해주세요!!";
				url = "bookList.do";
				model.addAttribute("msg",msg);
				model.addAttribute("url",url);
				return "message";
			}
		}
		if(!member.getN_profile().equals("notProfile.jpeg")) {
			String upPath = req.getSession().getServletContext().getRealPath("/img");
			File profile = new File(upPath+File.separator+member.getN_profile());
			if(profile.exists()) {
				profile.delete();
			}
		}
		Long n_no = member.getN_no();
		int res = service.DeleteMember(n_no);
		session.invalidate();
		
		if(res > 0) {
			msg = "지금까지 저의 도서관리 프로그램을 사용해주셔서 감사합니다.";
			url = req.getContextPath();
		}else {
			msg = "회원 탈퇴를 하지 못했습니다.";
			url = "n_main.do";
		}
		model.addAttribute("msg",msg);
		model.addAttribute("url",url);
		return "message";
	}
	
	
}
