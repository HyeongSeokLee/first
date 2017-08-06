package service.member;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.mail.handlers.message_rfc822;

import service.CommandProcess;

public class EmailChkAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String email = request.getParameter("email");
		String numberCode = ""; // 인증코드
		numberCode = ranNum();

		sendMail(email.toString(), numberCode);
		
		request.setAttribute("numberCode", numberCode);
		request.setAttribute("email", email);
		return "member/emailCheck.jsp";
	}

	// 메일 전송 메소드
	private void sendMail(String email, String numberCode) {
		String host = "smtp.gmail.com";// 호스트 gamil 사용
		String subject = "Daview 메일 인증코드";
		String fromName = "다뷰관리자";
		String from = "otl1202@gmail.com";
		String to = email;
		String content = "인증코드 [" + numberCode + "]";
		try {
			Properties props = new Properties(); // session에 담기 위한 값들 저장하기위해
													// properties 생성
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.tranport.protocol", "smtp"); // 통신 서버 smtp
			props.put("mail.smtp.host", host); //사용할 호스트
			props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.port", "465");// gmil TSL포트 번호 465
			props.put("mail.smtp.user", from); // 보내는사람 정보
			props.put("mail.smtp.auth", "true");

			/**
			 * Session에 props정보를 담고 Authenticator는 보내는이의 계정을 입증 하기 위한 기능
			 */
			Session mailSession = Session.getInstance(props, 
					new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("otl1202", "fhzpt1202");
				}
			});

			/**
			 * Message 메시지는(즉, MimeMessage 클래스는) 보내는 사람, 받는 사람, 제목, 내용과 같이 메일과
			 * 관련된 내용을 지정할 수 있도록 해 준다.
			 * 
			 * InternetAddress 클래스는 이메일 주소를 나타낼 때 사용되는 클래스로서, 
			 * Message 클래스의 수취인과 발신인을 지정할 때 사용된다
			 */
			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(from,MimeUtility.encodeText(fromName,"UTF-8","B")));
			InternetAddress[] addressTo = {new InternetAddress(to)}; //보내는 사람 여러명일때는 배열 사용
			msg.setRecipients(Message.RecipientType.TO, addressTo);
			msg.setSubject(subject); //보낼 메시지의 제목을 설정
			msg.setSentDate(new Date()); //보낼 메시지의 날짜를 설정
			msg.setContent(content,"text/html;charset=euc-kr");//보낼 메시지의 내용을 설정
			
			Transport.send(msg); //메시지를 전송

		} catch (MessagingException e) {e.printStackTrace();} 
		catch (Exception e) {e.printStackTrace();}
	}

	// 인증코드를 생성하기 위한 난수 메소드
	private String ranNum() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 6; i++) {
			int n = (int) (Math.random() * 10);
			sb.append(n);
		}
		return sb.toString();
	}

}
