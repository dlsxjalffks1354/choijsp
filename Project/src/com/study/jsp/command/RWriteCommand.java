package com.study.jsp.command;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.study.jsp.dao.RDao;
import com.study.jsp.dto.RDto;

public class RWriteCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int fileSize= 5*1024*1024;
		String uploadPath = request.getServletContext().getRealPath("/UploadFolder");
		HttpSession session = request.getSession();	
		
		try {
            
            // 파일업로드 
            MultipartRequest multi = new MultipartRequest
                    (request, uploadPath, fileSize, "UTF-8", new DefaultFileRenamePolicy());
            
            RDao dao = RDao.getInstance();
            RDto dto = new RDto();
            String DBfile = "";
 
            // 파일이름 가져오기
            String fileName = "";
            Enumeration<String> names = multi.getFileNames();
            if(names.hasMoreElements())
            {
                String name = names.nextElement();
                String realname = multi.getOriginalFileName(name);
                String sysname = multi.getFilesystemName(name);
                String bId = request.getParameter("bId");
                DBfile = sysname;
                dao.FileDB(realname,sysname, bId);
            }
            
            
            
            dto.setbName(multi.getParameter("bName"));
            dto.setbTitle(multi.getParameter("bTitle"));
            dto.setbContent(multi.getParameter("bContent"));
            dto.setFileName(DBfile);
            dto.setbUser((String) session.getAttribute("id"));
            
        
            dao.write(dto);
            
                       
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("글 작성 오류 : " + e.getMessage());
        }
		
	}
	
}
