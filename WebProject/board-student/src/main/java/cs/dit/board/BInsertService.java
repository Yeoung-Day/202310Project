package cs.dit.board;


import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class BInsertService implements BoardService {

	   @Override
	   public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      request.setCharacterEncoding("utf-8");

	      //7. insertForm  뿉 꽌  엯 젰 븳 subject, content, writer 瑜  諛쏆븘    븣留욌뒗 蹂  닔 뿉  엯 젰 븯 꽭 슂.
	      
	      String subject = request.getParameter("subject");
	      String content = request.getParameter("content");
	      String writer = request.getParameter("writer");
	      String filename = null;
	      String dir = null;
	      
	      String contentType = request.getContentType();
	      if(contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
	         dir = request.getServletContext().getRealPath("/uploadfiles");
	      }
	       
	      File f = new File(dir);
	      if(!f.exists()) {
	         f.mkdir();
	      }
	      
	      Collection<Part> parts = request.getParts();
	      
	      for(Part p :parts) {
	         if(p.getHeader("Content-Disposition").contains("filename=")) {
	            if(p.getSize()>0) {
	               filename = p.getSubmittedFileName();
	               String filePath = dir + File.separator + filename;
	               
	               p.write(filePath);
	               
	               p.delete();
	            }
	         }
	      }
	      BoardDto dto = new BoardDto(0, subject, content, writer, null, filename); 
	      BoardDao dao = new BoardDao();
	      dao.insert(dto);

	   }

	}

