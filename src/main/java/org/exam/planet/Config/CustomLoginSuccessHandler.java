    package org.exam.planet.Config;

    import jakarta.servlet.ServletException;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import jakarta.servlet.http.HttpSession;
    import lombok.extern.log4j.Log4j2;
    import org.exam.planet.Entity.MemberEntity;
    import org.exam.planet.Repository.MemberRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
    import org.springframework.stereotype.Component;

    import java.io.IOException;
    import java.util.Optional;

    @Log4j2
    @Component
    public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

        private final MemberRepository memberRepository;

        // 생성자를 통해 MemberRepository 주입
        @Autowired
        public CustomLoginSuccessHandler(MemberRepository memberRepository) {
            this.memberRepository = memberRepository;
        }

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request,
                                            HttpServletResponse response,
                                            Authentication authentication)
                throws IOException, ServletException {
            //로그인을 성공했을 때 개발자가 필요한 정보를 추가
            HttpSession session = request.getSession();
            if (session != null) { //클라이언트로부터 접속했으면
                String memId = authentication.getName(); //로그인한 아이디를 읽어서 저장

                Optional<MemberEntity> memberEntityOptional = memberRepository.findByMemId(memId);

                if (memberEntityOptional.isPresent()) {
                    MemberEntity memberEntity = memberEntityOptional.get();
                    String memName = memberEntity.getMemName();
                    session.setAttribute("memName", memName);
                    String memAdd1 = memberEntity.getMemAd1();
                    session.setAttribute("memAdd1", memAdd1);
                    String memAdd2 = memberEntity.getMemAd2();
                    session.setAttribute("memAdd2", memAdd2);
                    String memTel = memberEntity.getMemTel();
                    session.setAttribute("memTel", memTel);
                    Long memAge = memberEntity.getMemAge();
                    session.setAttribute("memAge", memAge);
                    Long memZipcode = memberEntity.getMemZipcode();
                    session.setAttribute("memZipcode", memZipcode);
                    Long MemNum = memberEntity.getMemNum();
                    session.setAttribute("MemNum", MemNum);
                }


                super.setDefaultTargetUrl("/"); //index로 이동
                //인증성공 정보를 클라이언트에 전달
                super.onAuthenticationSuccess(request, response, authentication);
            }
        }
    }
