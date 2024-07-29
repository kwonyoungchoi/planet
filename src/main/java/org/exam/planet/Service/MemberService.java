package org.exam.planet.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.exam.planet.Constrant.RoleType;
import org.exam.planet.DTO.MemberDTO;
import org.exam.planet.Entity.MemberEntity;
import org.exam.planet.Repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberService {
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public void insert(MemberDTO memberDTO) {
        //응용은 기존에 회원이 존재하는지 확인해서 가입(findById 기본검색 그외는 Repository 작성)
        //이메일 조회
        log.info(memberDTO.toString());
        Optional<MemberEntity> temp = memberRepository.findByMemId(memberDTO.getMemId());
        if(temp.isPresent()) { //존재하면
            throw new IllegalStateException("이미 가입된 회원입니다."); //예외 발생
        }
        MemberEntity memberEntity = modelMapper.map(memberDTO, MemberEntity.class);
        //비밀번호 암호화 처리(인증처리 작성 후)
        String pass = passwordEncoder.encode(memberDTO.getMemPwd());

        memberEntity.setMemPwd(pass);
        //회원등급
        memberEntity.setRoleType(RoleType.USER); //권한부여를 기본 사용자로 등록
        log.info(memberEntity.toString());
        memberRepository.save(memberEntity);
    }

    public void update(MemberDTO memberDTO) {
        //응용은 기존에 회원이 존재하는지 확인해서 가입(findById 기본검색 그외는 Repository 작성)
        //이메일 조회
        Optional<MemberEntity> temp = memberRepository.findById(memberDTO.getMemNum());
        if(temp.isPresent()) { //존재하면
            MemberEntity memberEntity = modelMapper.map(memberDTO, MemberEntity.class);
            //비밀번호 암호화 처리(인증처리 작성 후)
            memberEntity.setMemPwd(passwordEncoder.encode(memberDTO.getMemPwd()));
            //회원등급(DTO에 없는 내용은 조회 결과의 값으로 채우기)
            memberEntity.setRoleType(memberDTO.getRoleType()); //RoleType.USER); //권한부여를 기본 사용자로 등록
            memberRepository.save(memberEntity);
            log.info(memberEntity);
        }
    }
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }
    public MemberDTO read(Long id) {
        Optional<MemberEntity> temp = memberRepository.findById(id);
        //MemberDTO memberDTO = modelMapper.map(temp, MemberDTO.class);
        //return memberDTO;
        return modelMapper.map(temp, MemberDTO.class);
    }
    public Page<MemberDTO> list(Pageable page, String type, String search) {
        //페이지정보를 읽기위한 정렬
        int currentPage = page.getPageNumber()-1; // 데이터베이스 페이지번호 변경
        int pageLimit=5; //한화면에 출력할 데이터갯수

        //페이지 처리를 위한 정렬
        //PageRequest.of페이지요청(찾을페이지, 가져올 개수, 정렬(정렬형식Desc(내림차순),ASC(오름차순)
        Pageable pageable = PageRequest.of(currentPage, pageLimit, Sort.by(Sort.Direction.DESC, "memNum"));

        Page<MemberEntity> memberEntities;

        //검색을 추가
        // 문자비교는 변수.equals("문자")
        if(type.equals("i") && search != null) { //제목을 선택하고 검색어가 있으면
            memberEntities = memberRepository.findByMemId(search, pageable);
        }else if (type.equals("n") && search != null) { // 내용을 선택하고 검색어가 있으면
            memberEntities = memberRepository.findByMemName(search, pageable);
        }else if (type.equals("t") && search != null) { // 작성자를 선택하고 검색어가 있으면
            memberEntities = memberRepository.findByMemTel(search, pageable);
        }else if (type.equals("a") && search != null) { // 제목+내용을 선택하고 검색어가 있으면
            memberEntities = memberRepository.findByMemAd1(search, pageable);
        }else if (type.equals("g") && search != null) { // 제목+내용을 선택하고 검색어가 있으면
            memberEntities = memberRepository.findByMemAge(search, pageable);
        }else {
            // 전체조회
            memberEntities = memberRepository.findAll(pageable);

        }
        // 변환
        //Arrays.asList : List의 내용을 개별로 읽어서 변환 후 배열로 저장

        //ForEach(data:entity)...
        //entity(entity,page) ->data(임시변수)->data값을 modelMapper로 변환
        Page<MemberDTO> memberDTOS = memberEntities.map(data->modelMapper.map(data, MemberDTO.class));
        return memberDTOS;
    }
}
