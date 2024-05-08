package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;

public class WriteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");

		// 자바빈 (VO) 객체 생성
		BoardVO boardVO = new BoardVO();

		// 객체에 데이터 넣기
		boardVO.setTitle(request.getParameter("title"));
		boardVO.setName(request.getParameter("name"));
		boardVO.setPasswd(request.getParameter("passwd"));
		boardVO.setEmail(request.getParameter("email"));
		boardVO.setContent(request.getParameter("content"));
		boardVO.setIp(request.getRemoteAddr());

		// 인스턴스 생성
		BoardDAO dao = BoardDAO.getInstance();

		// insert 작업 수행
		dao.insert(boardVO);

		// JSP 경로 반환		
		return "/WEB-INF/views/write.jsp";
	}

}