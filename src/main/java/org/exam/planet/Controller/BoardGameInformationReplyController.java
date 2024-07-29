package org.exam.planet.Controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.exam.planet.DTO.BoardGameInformationReplyDTO;
import org.exam.planet.DTO.FreeBoardsReplyDTO;
import org.exam.planet.Repository.BoardGameInformationReplyRepository;
import org.exam.planet.Service.BoardGameInformationReplyService;
import org.exam.planet.Service.FreeBoardsReplyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@Log4j2
@RequiredArgsConstructor
public class BoardGameInformationReplyController {

    private final BoardGameInformationReplyService boardGameInformationReplyService;

    @PostMapping("/BoardGameInformationReply/insert")
    public String insertProc(Long boardGameNum, BoardGameInformationReplyDTO boardGameInformationReplyDTO){
        log.info("댓글 등록...");

        boardGameInformationReplyService.insert(boardGameNum, boardGameInformationReplyDTO);

        return "redirect:/freeBoards/read/" + boardGameNum;

    }

    @GetMapping("/BoardGameInformationReply/remove/{freeBoardsReplyNum}")
    public String deleteProc(Long boardGameNum, @PathVariable Long BoardGameInformationReplyNum) {
        log.info("댓글 삭제...");

        boardGameInformationReplyService.delete(BoardGameInformationReplyNum);

        return "redirect:/BoardGameInformationReply/read/" + BoardGameInformationReplyNum;
    }

}