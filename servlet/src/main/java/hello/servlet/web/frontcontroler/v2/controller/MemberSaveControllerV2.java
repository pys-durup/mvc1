package hello.servlet.web.frontcontroler.v2.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroler.MyView;
import hello.servlet.web.frontcontroler.v2.ControllerV2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV2 implements ControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        Integer age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        // Model에 데이터를 보관
        request.setAttribute("member", member);

        // v1 코드
        //String viewPath = "/WEB-INF/views/save-result.jsp";
        //RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        //dispatcher.forward(request, response);
        return new MyView("/WEB-INF/views/save-result.jsp");
    }
}
