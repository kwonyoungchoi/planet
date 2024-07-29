package org.exam.planet.Controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.exam.planet.DTO.MemberDTO;
import org.exam.planet.Entity.MemberEntity;
import org.exam.planet.Repository.MemberRepository;
import org.exam.planet.Service.MemberPageService;
import org.exam.planet.Service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@AllArgsConstructor
@Log4j2
public class MemberController {
    private final MemberRepository memberRepository;

    private final MemberService memberService;

    @GetMapping("/member/insert")
    public String insertForm(Model model) {

        log.info("회원가입페이지로 이동");

        SecurityContextHolder.getContext().getAuthentication().getName();

        model.addAttribute("boardDTO", new MemberDTO());

        return "member/insert";
    }

    @PostMapping("/member/insert")
    public String insertProc(@ModelAttribute @Validated MemberDTO memberDTO,
                             BindingResult bindingResult) {

        log.info("회원가입 전송");

        if (bindingResult.hasErrors()) {
            return "member/insert";
        }
        memberService.insert(memberDTO);
        return "redirect:/member/login";
    }


    @GetMapping("/member/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) { //(받고, 보낼변수)

        log.info("회원수정페이지로 이동");


        MemberDTO memberDTO = memberService.read(id);
        model.addAttribute("memberDTO", memberDTO); //"변수명", 보낼값


        return "member/update";
    }

    @PostMapping("/member/update")
    public String updateProc(@Validated MemberDTO memberDTO,
                             BindingResult bindingResult,
                             Model model) {

        log.info("회원수정 전송");

        if (bindingResult.hasErrors()) {
            return "/member/update";
        }
        memberService.update(memberDTO);
        return "redirect:/member/logout";
    }


    @GetMapping("/member/delete/{id}")
    public String deleteProc(@PathVariable Long id) {
        log.info("회원삭제");
        memberService.delete(id);
        return "redirect:/member/list";
    }


    @GetMapping("/member/{id}")
    public String readForm(@PathVariable Long id, Model model) {
        MemberDTO memberDTO = memberService.read(id);
        model.addAttribute("memberDTO", memberDTO);
        return "/member/read";


    }

    @GetMapping("/member/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "regirect:/member/login";
    }

    @GetMapping("/member/list")
    //@PageableDefault = View에서 페이지 정보가 전달되지 않으면 기본 1페이지로 사용
    //검색어 추가(type=대상, seacrch=검색어)
    //@RequestParam은 ? 뒤에 변수로 보낸 값을 처리(value="보낸변수", defaultValue="변수가 없을 때 대체")
    public String listForm(@PageableDefault(page=1) Pageable pageable,
                           @RequestParam(value="type", defaultValue="")String type,
                           @RequestParam(value="search", defaultValue="")String search, Model model) {


        Page<MemberDTO> memberDTOS = memberService.list(pageable, type, search);

        //추가로 페이지정보도 view에 전달(하단에 출력할 정보를 가공)
        Map<String, Integer> pageinfo = MemberPageService.pagination(memberDTOS);
        //addAllAttributes = 여러개의 변수를 한번에 전달할 때
        model.addAllAttributes(pageinfo);
        //서비스 처리(전체조회)
        model.addAttribute("list", memberDTOS);
        return "/member/list";
    }


}
