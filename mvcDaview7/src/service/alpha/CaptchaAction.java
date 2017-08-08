package service.alpha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CommandProcess;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

public class CaptchaAction implements CommandProcess {
	public static final String url = "https://www.google.com/recaptcha/api/siteverify";
	public static final String secret = "6Lfk2ysUAAAAAA4rVmmzSoWQITSbUZYUZGWmkHnr";

	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
		request.setAttribute("verify", verify(gRecaptchaResponse));
		return "alpha/_CAPTCHA/pro.jsp";
	}

	
	private boolean verify(String gRecaptchaResponse) throws IOException {
		if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
			return false;
		}

		try {
			URL obj = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

			// request header 추가
			con.setRequestMethod("POST");

			String postParams = "secret=" + secret + "&response=" + gRecaptchaResponse;

			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postParams);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + postParams);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());

			JSONObject json = new JSONObject(response.toString());
			System.out.println(json);
			System.out.println("key값 success의 값은?" + json.getBoolean("success"));

			return json.getBoolean("success");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
