package org.exam.planet.Service;

import lombok.RequiredArgsConstructor;
import org.exam.planet.Entity.MemberEntity;
import org.exam.planet.Repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

//보안인증을 처리하는 클래스
@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {


    private MemberRepository memberRepository;

    @Autowired
    public void CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String memId) {
        //로그인폼에서 아이디와 비밀번호를 입력하고 로그인버튼을 클릭하면
        //입력한 아이디로 회원테이블에 존재여부를 확인
        Optional<MemberEntity> memberEntity = memberRepository.findByMemId(memId);
        if(memberEntity.isPresent()) { //검색한 회원이 존재하면
            //보안인증에 해당하는 아이디, 비밀번호, 권한을 저장해서 보안인증에 전달해주면
            //보안인증이 저장된 내용과 로그인폼에 입력한 내용을 비교해서 로그인처리
            return User.withUsername(memberEntity.get().getMemId())
                    .password(memberEntity.get().getMemPwd())
                    .roles(memberEntity.get().getRoleType().name())
                    .build();
        } else {
            throw new UsernameNotFoundException("알수 없는 아이디 :"+ memId);
        }
    }
}
