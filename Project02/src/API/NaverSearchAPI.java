package API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NaverSearchAPI extends HttpServlet
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException
	{
		String clientId = "wdP7ffYxeSTVF43oYWov";	// 애플리케이션 클라이언트 아이디값";
        String clientSecret = "90MntfvhSr";	// 애플리케이션 클라이언트 시크릿값";
        
        // 네이버에서 반환해주는 결과값을 받기위한 변수
        String returnVal = "";
        
        try
        {
        		// 요청페이지에서 입력한 검색어를 파라미터로 받음
        		String searchTxt = req.getParameter("searchKeyword");
        		int startNum = Integer.parseInt(req.getParameter("startNum"));
        		
            String text = URLEncoder.encode(searchTxt, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/blog?query="+ text + "&start=" + startNum; // json 결과
            //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과
            
            URL url = new URL(apiURL);
            
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            
            if(responseCode==200)
            { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } 
            else 
            {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            
            String inputLine;
            StringBuffer response = new StringBuffer();
            
            while ((inputLine = br.readLine()) != null)
            {
                response.append(inputLine);
            }
            
            br.close();
            System.out.println(response.toString());
            
            returnVal = response.toString();
        } 
        catch (Exception e)
        {
            System.out.println(e);
        }
        
        // 네이버에 반환한 데이터를 화면에 즉시 출력
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().write(returnVal);
	}
}
