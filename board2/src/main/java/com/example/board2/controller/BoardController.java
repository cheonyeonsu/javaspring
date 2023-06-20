package com.example.board2.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.board2.dto.Board;
import com.example.board2.service.BoardService;
import com.example.board2.util.MyUtil;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	MyUtil myUtil;
	

	@RequestMapping(value="/") //localhost로 접속.  getmapping을 이렇게 하면은 최상단으로 감. 
	public String index() {
		return "index";
	}
	
	//페이지 보여줌
	@RequestMapping(value="/created", method=RequestMethod.GET) //localhost로 접속.  get방식으로 들어왔을 때 아래 메소드 실행시키겠오
	public String created() {
		return "bbs/created";
	}
	
	//게시글 등록
	@RequestMapping(value="/created", method=RequestMethod.POST) 
	public String createdOK(Board board, HttpServletRequest request, Model model) {
		                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
		try {
			int maxNum = boardService.maxNum(); //오류나니깐 trycatch
			
			board.setNum(maxNum + 1); //num컬럼에 들어갈 값을 1 증가시켜준다. 
			board.setIpAddr(request.getRemoteAddr()); //클라이언트의 ip주소를 구해준다. 
			
			boardService.insertData(board);
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "게시글 작성 중 에러가 발생했습니다.");
			return "bbs/created";
		}
		
		return "redirect:/list"; //글 썼으니까 리스트로 넘어감
	}
	
	//리스트 페이지 보여줌(get,post방식 전부 여기로 들어옴~)
	@RequestMapping(value="/list", method= {RequestMethod.GET,RequestMethod.POST}) 
	public String list(Board board, HttpServletRequest request, Model model) {
		
		try {
			String pageNum = request.getParameter("pageNum"); //바뀌는 페이지 번호
			int currentPage = 1; //현재 페이지 번호 (기본값은 1)
			
			if(pageNum !=null)currentPage = Integer.parseInt(pageNum); 
			
			String searchKey = request.getParameter("searchKey"); //검색 키워드
			String searchValue = request.getParameter("searchValue"); //검색어
			
			if(searchValue == null) {
				searchKey = "subject"; //검색 키워드의 디폴트는 subject
				searchValue = ""; //검색어의 디폴트는 빈문자열.
			} else {
				if(request.getMethod().equalsIgnoreCase("GET")) {
					//get방식으로request가 왔다면
					//쿼리파라메터의 값(searchValue)을 디코딩해준다. 
					searchValue = URLDecoder.decode(searchValue, "UTF-8");
				}
			}
			
			//1.전체 게시물의 갯수 가져오기 : 페이징 처리에 필요함. 쿼리문-서비스-다오
			int dataCount = boardService.getDataCount(searchKey, searchValue);
			
			
			//2.페이징 처리(준비 단계)
			int numPerPage = 5; //페이지당 보여줄 데이터의 갯수. 
			int totalPage = myUtil.getPageCount(numPerPage,dataCount);//페이지의 전체 갯수를 구한다. 
			
			if(currentPage > totalPage) currentPage = totalPage; //totalPage보다 크면 안된다. 
			
			int start = (currentPage - 1) * numPerPage +1; //1 6 11 16
			int end = currentPage * numPerPage; // 5 10 15 20
			
			//3.전체 게시물 리스트를 가져오기
			List<Board> lists = boardService.getLists(searchKey, searchValue, start, end);
			
			//4.페이징 처리
			String param = "";
			
			if(searchValue!=null &&! searchValue.equals("")) {
				//검색어가 있다면
				param += "&searchKey=" + searchKey;
				param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8"); //컴퓨터의 언어로 인코딩. 
			}
			
			
			String listUrl = "/list";
			if (!param.equals("")) listUrl += "?" + param;
			
			//버튼이 태그 형태로 들어있음. 
			String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, listUrl);
			
			String articlUrl = "/article?pageNum=" + currentPage;
			
			if(!param.equals("")) {
				articlUrl += "&" + param;
			}
			
			model.addAttribute("lists",lists); //db에서 가져온 전체 게시물			
			model.addAttribute("articlUrl",articlUrl); //상세페이지로 이동하기 위한url			
			model.addAttribute("pageIndexList",pageIndexList); // ◀이전 1 2 3 4 5 다음▶			
			model.addAttribute("dataCount",dataCount); //전체 게시물의 갯수.			
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "게시글 작성 중 에러가 발생했습니다.");
		}
		
		
		return "bbs/list"; //위에 4가지를 리스트 페이지에 넘겨줌. > 리스트페이지에서 사용자에게 넘겨줌
	}
	
	
	@RequestMapping(value="/article", method=RequestMethod.GET) 
	public String article(HttpServletRequest request,Model model) {
		try {
			int num = Integer.parseInt(request.getParameter("num"));
			String pageNum = request.getParameter("pageNum");
			String searchKey = request.getParameter("searchKey");
			String searchValue = request.getParameter("searchValue");
			
			if(searchValue !=null) {
				searchValue = URLDecoder.decode(searchValue, "UTF-8");
			}
			
			//1. 조회수 늘리기
			boardService.updateHitCount(num);
			
			//2. 게시물 데이터 가져오기
			Board board = boardService.getReadData(num);
			
			if(board == null ) {
				// list?pageNum1
				return "redirect:/list?pageNum=" + pageNum;
			}
			
			//게시글의 라인 수를 구한다. 
			int lineSu = board.getContent().split("\n").length; // /랑 |구분 필......
			
			String param = "pageNum=" + pageNum;
			
			
			if(searchValue!=null &&! searchValue.equals("")) {
				//검색어가 있다면
				param += "&searchKey=" + searchKey;
				param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8"); //컴퓨터의 언어로 인코딩. 
			}
			
			model.addAttribute("board",board);
			model.addAttribute("params",param);
			model.addAttribute("lineSu",lineSu);
			model.addAttribute("pageNum",pageNum);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "게시글을 불러오는 중 에러가 발생했습니다.");
		}
		return "bbs/article"; //리턴하는 곳 보고 넘어가서 데이터 넣어주기. 
	}
	
	//게시글 수정 페이지 보여줌
	@RequestMapping(value="/updated", method=RequestMethod.GET) 
	public String updated(HttpServletRequest request, Model model) {
		
		try {
			int num = Integer.parseInt(request.getParameter("num"));
			String pageNum = request.getParameter("pageNum");
			String searchKey = request.getParameter("searchKey");
			String searchValue = request.getParameter("searchValue");
			
			if(searchValue !=null) {
				searchValue = URLDecoder.decode(searchValue,"UTF-8");
			}
			
			Board board = boardService.getReadData(num);
			
			if(board==null) {
				return "redirect:/list?pageNum=" + pageNum;
			}
			
			String param = "pageNum" + pageNum;
			
			if(searchValue!=null &&! searchValue.equals("")) {
				//검색어가 있다면
				param += "&searchKey=" + searchKey;
				param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8"); 
			}
			
			model.addAttribute("board",board);
			model.addAttribute("pageNum",pageNum);
			model.addAttribute("params",param);
			model.addAttribute("searchKey",searchKey);
			model.addAttribute("searchValue",searchValue);
			
		} catch (Exception e) {
			
		}
		
		return "bbs/updated";
	}
	
	//게시글 수정
	@RequestMapping(value="/updated_ok", method=RequestMethod.POST) 
	public String updatedOK(Board board, HttpServletRequest request, Model model) {
		String pageNum = request.getParameter("pageNum");
		String searchKey= request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		String param = "?pageNum="+ pageNum;
		
		try {
			board.setContent(board.getContent().replaceAll("<br/>", "|r|n"));
			boardService.updateData(board);
			
			if(searchValue!=null &&! searchValue.equals("")) {
				//검색어가 있다면
				param += "&searchKey=" + searchKey;
				param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8"); 
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
			try {
				param += "&errorMessage=" + URLEncoder.encode("게시글 수정 중 에러가 발생했습니다.", "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				
				e1.printStackTrace();
			}
		}
		
		
		return "redirect:/list" + param; //redirect:데이터를 끌고가지않음 > get방식으로 가져가야 함. 
	}
	
	@RequestMapping(value="/deleted", method=RequestMethod.GET) //localhost로 접속.  get방식으로 들어왔을 때 아래 메소드 실행시키겠오
	public String deleted(HttpServletRequest request, Model model) {
		String pageNum = request.getParameter("pageNum");
		String searchKey= request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		int num = Integer.parseInt(request.getParameter("num"));
		String param = "?pageNum="+ pageNum;
		
		try {
			boardService.deleteData(num);
			
			if(searchValue!=null &&! searchValue.equals("")) {
				//검색어가 있다면
				param += "&searchKey=" + searchKey;
				param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8"); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		return "redirect:/list" + param;
	}
	
	@RequestMapping(value = "/deleted_ok", method= {RequestMethod.GET})
	public String deleteOK(HttpServletRequest request, Model model) {
		
		String pageNum = request.getParameter("pageNum");
		String searchKey= request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		int num = Integer.parseInt(request.getParameter("num"));
		String param = "?pageNum="+ pageNum;
		
		try {
			boardService.deleteData(num);
			if(searchValue!=null &&! searchValue.equals("")) {
				//검색어가 있다면
				param += "&searchKey=" + searchKey;
				param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8"); 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			try {
				param += "&errorMessage=" + URLEncoder.encode("게시글 삭제 중 에러가 발생했습니다.", "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				
				e1.printStackTrace();
			}
		}
		
		return "redirect:/list"+ param ;
	}
	
	
}
