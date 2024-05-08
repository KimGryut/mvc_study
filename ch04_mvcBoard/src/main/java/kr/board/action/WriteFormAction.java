package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;

public class WriteFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 반환할 데이터가 없으니 경로만 호출하면 됨
		return "/WEB-INF/views/writeForm.jsp";
	}

}
