package cs.dit.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BListService implements BoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. BoardDao 를 생성
		BoardDao dao = new BoardDao();
		
		//2. dao의 해당 메소드를 호출
		ArrayList<BoardDto> dtos = dao.list();
		
		//3. 호출 결과 처리
		
		request.setAttribute("dtos", dtos);
	}

}
