package org.exam.planet.Controller;



import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.exam.planet.DTO.FreeBoardsReplyDTO;
import org.exam.planet.Service.FreeBoardsReplyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@Log4j2
@RequiredArgsConstructor
public class FreeBoardsReplyController {

    private final FreeBoardsReplyService freeBoardsReplyService;

    @PostMapping("/freeBoardsReply/insert")
    public String insertProc(Long freeBoardsNum, FreeBoardsReplyDTO freeBoardsReplyDTO){
        log.info("댓글 등록...");

        freeBoardsReplyService.insert(freeBoardsNum, freeBoardsReplyDTO);

        return "redirect:/freeBoards/read/" + freeBoardsNum;

    }

    @GetMapping("/freeBoardsReply/remove/{freeBoardsReplyNum}")
    public String deleteProc(Long freeBoardsNum, @PathVariable Long freeBoardsReplyNum) {
        log.info("댓글 삭제...");

        freeBoardsReplyService.delete(freeBoardsReplyNum);

        return "redirect:/freeBoards/read/" + freeBoardsNum;
    }

}