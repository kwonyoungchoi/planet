package org.exam.planet.Controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.exam.planet.DTO.*;
import org.exam.planet.Service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
@Log4j2
public class BoardGameInformationController {

    private final BoardGameInformationService boardGameInformationService;
    private final BoardGameInformationReplyService boardGameInformationReplyService;
    private final FileService fileService;




    @GetMapping("/boardGameInformation/insert")
    public String insertForm(Model model) {

        log.info("게시글 등록 페이지로 이동");

        model.addAttribute("boardGameInformationDTO", new BoardGameInformationDTO());

        return "boardGameInformation/insert";
    }

    @PostMapping("/boardGameInformation/insert")
    public String insertProc(@ModelAttribute BoardGameInformationDTO boardGameInformationDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             @RequestParam("file") MultipartFile[] files) {

        log.info("정보글 등록 전송");

        if (bindingResult.hasErrors()) {
            return "boardGameInformation/insert";
        }

        boardGameInformationService.insert(boardGameInformationDTO, files);
        fileService.saveBoardImg(files, boardGameInformationDTO.getBoardGameNum());

        return "redirect:/boardGameInformation/list";
    }



    @GetMapping("/boardGameInformation/update/{freeBoardsNum}")
    public String updateForm(@PathVariable Long boardGameNum, Model model) { //(받고, 보낼변수)
        BoardGameInformationDTO boardGameInformationDTO = boardGameInformationService.read(boardGameNum);
        List<BoardImgDTO> boardImgDTOS = fileService.imgList(boardGameNum);

        boardGameInformationDTO.setImgDTOList(boardImgDTOS);

        model.addAttribute("boardGameInformationDTO", boardGameInformationDTO); //"변수명", 보낼값

        return "boardGameInformation/update";
    }

    @PostMapping("/boardGameInformation/update")
    public String updateProc(@Validated BoardGameInformationDTO boardGameInformationDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             @RequestParam("file") MultipartFile[] files) {
        if (bindingResult.hasErrors()) {
            return "boardGameInformation/update";
        }

        boardGameInformationService.update(boardGameInformationDTO);

        // 파일 업로드를 위한 로직 추가
        if (files != null && files.length > 0) {
            fileService.saveBoardImg(files, boardGameInformationDTO.getBoardGameNum());
        }

        redirectAttributes.addFlashAttribute("result", "updated");
        redirectAttributes.addAttribute("boardGameNum", boardGameInformationDTO.getBoardGameNum());

        return "redirect:/boardGameInformation/list";
    }


    @GetMapping("/boardGameInformation/delete/{id}")
    public String deleteProc(@PathVariable Long id) {
        boardGameInformationService.delete(id);
        return "redirect:/boardGameInformation/list";
    }


    @GetMapping("/boardGameInformation/read/{boardGameNum}")
    public String readForm(@PathVariable Long boardGameNum,
                           Model model) {


        BoardGameInformationDTO boardGameInformationDTO = boardGameInformationService.read(boardGameNum);
        List<BoardGameInformationReplyDTO> boardGameInformationReplyDTOS = boardGameInformationReplyService.list(boardGameNum);
        List<BoardImgDTO> boardImgDTOS = fileService.imgList(boardGameNum);


        boardGameInformationDTO.setImgDTOList(boardImgDTOS);
        boardGameInformationDTO.setBoardGameInformationReplyDTOList(boardGameInformationReplyDTOS);


        log.info("컨트롤러" + boardGameInformationDTO);



        model.addAttribute("boardGameInformationDTO", boardGameInformationDTO);






        return "boardGameInformation/read";


    }


    @GetMapping("/boardGameInformation/list")
    //@PageableDefault = View에서 페이지 정보가 전달되지 않으면 기본 1페이지로 사용
    //검색어 추가(type=대상, seacrch=검색어)
    //@RequestParam은 ? 뒤에 변수로 보낸 값을 처리(value="보낸변수", defaultValue="변수가 없을 때 대체")
    public String listForm(@PageableDefault(page=1) Pageable pageable,
                           @RequestParam(value="type", defaultValue="")String type,
                           @RequestParam(value="search", defaultValue="")String search, Model model) {

        Page<BoardGameInformationDTO> boardGameInformationDTOS = boardGameInformationService.list(pageable, type, search);


        //추가로 페이지정보도 view에 전달(하단에 출력할 정보를 가공)
        Map<String, Integer> pageinfo = MemberPageService.pagination(boardGameInformationDTOS);
        //addAllAttributes = 여러개의 변수를 한번에 전달할 때
        model.addAllAttributes(pageinfo);
        //서비스 처리(전체조회)
        model.addAttribute("list", boardGameInformationDTOS);
        return "boardGameInformation/list";
    }




}
