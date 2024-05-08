package kr.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;
import kr.util.PagingUtil;

public class ListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 선택한 페이지 반환
		String pageNum = request.getParameter("pageNum");

		// 코드 한줄이므로 중괄호 생략
		if(pageNum==null) pageNum = "1";
		
		// 인스턴스 생성
		BoardDAO dao = BoardDAO.getInstance();
		
		// 게시글 개수 
		int count = dao.getCount();
		
		// 페이지 작업
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum), count, 10, 10, "list.do");
		
		// 게시글 리스트 생성
		List<BoardVO> list = null;
		
		// 글이 한 개 이상일 때만 리스트 보이게
		if(count>0) {
			list = dao.getList(page.getStartRow(), page.getEndRow());
		} 
		
		// JSP에서 사용할 수 있도록 세팅
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		
		// JSP 경로 반환
		return "/WEB-INF/views/list.jsp";
	}

}
