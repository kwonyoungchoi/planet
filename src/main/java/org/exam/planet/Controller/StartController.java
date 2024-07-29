package org.exam.planet.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class StartController {

    @GetMapping("/")
    public String start(){
        log.info("메인페이지로 이동");

        SecurityContextHolder.getContext().getAuthentication().getName();

        return "index";
    }

}
