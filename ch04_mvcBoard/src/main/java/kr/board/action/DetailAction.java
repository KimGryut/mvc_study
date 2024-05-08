package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;

public class DetailAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int num = Integer.parseInt(request.getParameter("num"));
		BoardDAO dao = BoardDAO.getInstance();
		BoardVO board = dao.getBoard(num);
		
		request.setAttribute("num", num);
		request.setAttribute("title", board.getTitle());
		request.setAttribute("name", board.getName());
		request.setAttribute("content", board.getContent());
		request.setAttribute("reg_date", board.getReg_date());
		
		
		return "/WEB-INF/views/detail.jsp";
	}

}
