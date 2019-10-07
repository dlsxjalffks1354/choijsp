package Mail;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendMailCtrl extends HttpServlet
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException
	{
		// 한글처리
		req.setCharacterEncoding("UTF-8");
		
		// Contact.jsp에서 입력한 폼값들을 가져온다.
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String pass = req.getParameter("phone");
		String message = req.getParameter("message");
		
		// 정보를 담을 객체
		Properties p = new Properties();
		
		// gmail SMTP
		p.put("mail.smtp.host","gmail-smtp.l.google.com");
		// SMTP 서버에 접속하기위한 정보
		//p.put("mail.smtp.port", "587");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		//p.put("mail.smtp.debug", "true");
		//p.put("mail.smtp.socketFactory.port", "465");
		//p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		//p.put("mail.smtp.socketFactory.fallback", "false");
		p.put("mail.transport.protocol", "smtp");
		
		try 
		{
			// 메일 인증(고객의 메일과 비밀번호를 받아서 인증)
			Authenticator auth = new SMTPAuthenticator(email, pass);
			Session session = Session.getInstance(p, auth);
			
			session.setDebug(true);
			
			// 보내는 사람 이메일 주소
			InternetAddress fromAddr = new InternetAddress();
			fromAddr.setPersonal(name, "UTF-8");
			fromAddr.setAddress(email);
			
			// 메일의 내용을 담을 객체
			Message msg = new MimeMessage(session);
			msg.setFrom(fromAddr);
			System.out.println(msg.getFrom());
			msg.setSubject(MimeUtility.encodeText(name, "UTF-8", "B"));
			msg.setContent(message, "text/html;charset=UTF-8");
			// 관리자 메일(lkb3024@gmail.com)
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("lkb3024@gmail.com"));
			
			// 전송
			Transport.send(msg);
			
			System.out.println("메일전송 성공!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("메일전송이 실패하였습니다.");
		}
	}
}
