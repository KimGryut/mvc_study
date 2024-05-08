package kr.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.board.vo.BoardVO;
import kr.util.DBUtil;

public class BoardDAO {
	// 싱글턴 패턴
	private static BoardDAO instance = new BoardDAO();
	public static BoardDAO getInstance() {
		return instance;
	}
	private BoardDAO() {}
	
	// 글 저장 작업
	public void insert(BoardVO boardVO) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			// 커넥션들로부터 커넥션 할당
			conn = DBUtil.getConnection();
			
			// SQL문 작성
			sql = "INSERT INTO svboard (num, title, name, passwd, email, content, ip) "
					+ "VALUES (svboard_seq.nextval, ?, ?, ?, ?, ?, ?)";
			
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// ? 에 데이터 할당
			pstmt.setString(1, boardVO.getTitle());
			pstmt.setString(2, boardVO.getName());
			pstmt.setString(3, boardVO.getPasswd());
			pstmt.setString(4, boardVO.getEmail());
			pstmt.setString(5, boardVO.getContent());
			pstmt.setString(6, boardVO.getIp());
			
			// SQL문 실행
			pstmt.executeUpdate();
		} catch(Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	// 글의 총 개수 (for 페이지 작업)
	public int getCount() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			// 컬럼 인덱스 : 1
			sql = "SELECT COUNT(*) cnt FROM svboard";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if(rs.next()) {
				// 컬럼 인덱스
				count = rs.getInt(1);
				// count(*)처럼 기호가 있으면 안에 쓰지 않고 알리아스를 명시해주는 것도 번거롭기 때문에 인덱스 사용
			}
		} catch(Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return count;
	}
	
	// 글 목록 작업
	// 여기서 리스트는 java.util.List
	public List<BoardVO> getList(int startRow, int endRow) throws Exception {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    List<BoardVO> list = null;
	    String sql = null;
	    try {
	    	conn = DBUtil.getConnection();
	        
	        sql = "SELECT * FROM (SELECT a.*, ROWNUM AS rnum FROM (SELECT * FROM svboard ORDER BY num DESC) a WHERE ROWNUM <= ?) WHERE rnum >= ?";
	        pstmt = conn.prepareStatement(sql);

	        pstmt.setInt(1, endRow);
	        pstmt.setInt(2, startRow);
	        rs = pstmt.executeQuery();
	        list = new ArrayList<BoardVO>();
	        while (rs.next()) {
	            BoardVO boardVO = new BoardVO();
	            boardVO.setNum(rs.getInt("num"));
	            boardVO.setTitle(rs.getString("title"));
	            boardVO.setName(rs.getString("name"));
	            boardVO.setReg_date(rs.getDate("reg_date"));
	            list.add(boardVO);
	        }
	    } catch (Exception e) {
	        throw new Exception(e);
	    } finally {
	        DBUtil.executeClose(rs, pstmt, conn);
	    }
	    return list;
	}
	
	// 글 상세 작업
	public BoardVO getBoard(int num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO board = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM svboard WHERE num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, num);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				board = new BoardVO();
				board.setNum(rs.getInt("num"));
				board.setTitle(rs.getString("title"));
				board.setName(rs.getString("name"));
				board.setPasswd(rs.getString("passwd"));
				board.setEmail(rs.getString("email"));
				board.setContent(rs.getString("content"));
				board.setIp(rs.getString("ip"));
				board.setReg_date(rs.getDate("reg_date"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원 정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return board;
	}
	
	//글 수정 작업
		public void update(BoardVO boardVO)throws Exception{
			Connection conn = null;
		    PreparedStatement pstmt = null;
		    String sql = null;
		    try {
		    	
		    } catch(Exception e) {
		    	throw new Exception(e);
		    } finally {
		    	DBUtil.executeClose(null, pstmt, conn);
		    }
		}
		
		//글 삭제 작업
		public void delete(int num)throws Exception{
			Connection conn = null;
		    PreparedStatement pstmt = null;
		    String sql = null;
		    try {
		    	
		    } catch(Exception e) {
		    	throw new Exception(e);
		    } finally {
		    	DBUtil.executeClose(null, pstmt, conn);
		    }
		}	
}
