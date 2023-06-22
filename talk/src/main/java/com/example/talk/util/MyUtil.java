package com.example.talk.util;

import org.springframework.stereotype.Service;

@Service
public class MyUtil {
	public int getPageCount(int numPerPage, int dataCount) {
		int pageCount = 0;
		pageCount = dataCount / numPerPage;

		if (dataCount % numPerPage != 0) {
			pageCount++;
		}
		return pageCount;
	}

	public String pageIndexList(int currentPage, int totalPage, String listUrl) {
		StringBuffer sb = new StringBuffer();
		int numPerBlock = 5;
		int currentPageSetup;
		int page;


		if (currentPage == 0 || totalPage == 0) return "";

		if (listUrl.indexOf("?") != -1) {
			listUrl += "&";
		} else {
			listUrl += "?";
		}
	
		//1. '이전' 버튼 만들기
		currentPageSetup = (currentPage / numPerBlock) * numPerBlock;
		
		if(currentPage % numPerBlock == 0) {
			currentPageSetup = currentPageSetup-numPerBlock;
		}
		
		if(totalPage > numPerBlock && currentPageSetup > 0) { 
			sb.append("<a href=\"" + listUrl + "pageNum=" + currentPageSetup + "\">◀이전</a>&nbsp;");
		}
	
		//2. 그냥 페이지 이동 버튼 만들기.
		page = currentPageSetup +1;
		while(page<= totalPage && page <= (currentPageSetup + numPerBlock)) {
			if (page == currentPage) {
				sb.append("<font coler=\"red\">" + page + "<font>&nbsp"); //내가 선택한 페이지면 컬러 다르게.
			} else { //내가 선택한 페이지 아니면 링크.
				sb.append("<a href=\"" + listUrl + "pageNum=" + page + "\">" + page + "</a>&nbsp;");
			}
			page++;
		}
		
		//3. '다음▶'버튼 만들기
		if(totalPage - currentPageSetup > numPerBlock) {
			sb.append("<a href=\"" + listUrl + "pageNum=" + currentPageSetup + "\">다음▶</a>&nbsp;");
		}
		
		//4. 버튼 합쳐서 문자열로 리턴
		System.out.println(sb.toString());
		return sb.toString();
	}
}
