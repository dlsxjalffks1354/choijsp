package Mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator
{
	PasswordAuthentication pa;
	
	public SMTPAuthenticator(String id, String pw)
	{
		// 고객의 이메일과 이메일 비밀번호를 받아 인증
		pa = new PasswordAuthentication(id, pw);
	}
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication()
	{
		return pa;
	}
}
