package org.exam.planet.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Log4j2
public class PlanetControllter {

    @GetMapping("/planet/information")
    public String planetinformation() {

        log.info("정보페이지로 이동");

        return "planet/information";
    }

}
