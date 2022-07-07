package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    서블릿은 HttpServlet을 상속받음 - @WebServlet
    ** urlPatterns로 접속되면 (/hello) 이 서블릿이 실행됨

    ** servlet HTTP 요청이 오면, WAS(서블릿 컨테이너)가 request, response 객체 생성하여 servlet에 던져줌
       따라서 해당 /hello url 호출시 웹브라우저가 HTTP 요청메세지 생성, 이것을 서버에 던진다.
       -> service() 파라미터 request, response 객체를 서버가 만들어서 내려줌
 */
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    
//   Ctrl + O 로 자물쇠(protected - service 메소드 기본 틀 생성)
//   이 서블릿이 호출되면 service()가 호출됨
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service"); // soutm 단축keyword, 이 기본상태에서 /hello url로 접속시 빈화면 확인
                                                    // 빈화면 출력 이유: 응답한 것이 아무것도 없기 때문
                                                    // Console log에서 해당 문구 출력 확인

        /*
            ** org.apache.catalin : 톰캣쪽 라이브러리
            request = org.apache.catalina.connector.RequestFacade@16b6b83a
            response = org.apache.catalina.connector.ResponseFacade@67780efe

            여러가지 WAS 서버들이 (톰캣, 제티 etc) 서블릿 표준스펙을 구현한다. - 구현체 존재
            RequestFacade@16b6b83a - 이런 구현체들이 찍히는 것

            ** Request Header 는 웹브라우저가 보내는 정보

         */
        System.out.println("request = " + request); // soutv 단축키
        System.out.println("response = " + response);

        // 쿼리파라미터(쿼리스트링)
        // url에 값을 넣어보면 console에 그대로 출력 -> username = sy
        String username = request.getParameter("username"); // Ctrl + Alt + V
        System.out.println("username = " + username);

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("hello " + username); // write() : HTTP message body에 DATA 적재


    }
}
