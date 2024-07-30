package org.exam.planet.Controller;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.exam.planet.DTO.BoardImgDTO;
import org.exam.planet.DTO.FreeBoardsDTO;
import org.exam.planet.DTO.FreeBoardsReplyDTO;
import org.exam.planet.Service.*;
import org.exam.planet.util.ModelUtil;
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


@Controller
@AllArgsConstructor
@Log4j2
public class FreeBoardsController {

    private final FreeBoardsService freeBoardsService;
    private final FreeBoardsReplyService freeBoardsReplyService;
    private final FreeBoardsFileService freeBoardsFileService;




    @GetMapping("/freeBoards/insert")
    public String insertForm(Model model) {

        log.info("게시글 등록 페이지로 이동");

        model.addAttribute("freeBoardsDTO", new FreeBoardsDTO());

        return "freeBoards/insert";
    }

    @PostMapping("/freeBoards/insert")
    public String insertProc(@ModelAttribute FreeBoardsDTO freeBoardsDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             @RequestParam("file") MultipartFile[] files) {

        log.info("게시글 등록 전송");


        if (bindingResult.hasErrors()) {
            return "freeBoards/insert";
        }

        freeBoardsService.insert(freeBoardsDTO, files);

        return "redirect:/freeBoards/list";
    }



    @GetMapping("/freeBoards/update/{freeBoardsNum}")
    public String updateForm(@PathVariable Long freeBoardsNum, Model model) { //(받고, 보낼변수)
        FreeBoardsDTO freeBoardsDTO = freeBoardsService.read(freeBoardsNum);
        List<BoardImgDTO> boardImgDTOS = freeBoardsFileService.imgList(freeBoardsNum);

        freeBoardsDTO.setImgDTOList(boardImgDTOS);

        model.addAttribute("freeBoardsDTO", freeBoardsDTO); //"변수명", 보낼값

        return "freeBoards/update";
    }

    @PostMapping("/freeBoards/update")
    public String updateProc(@Validated FreeBoardsDTO freeBoardsDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             @RequestParam("file") MultipartFile[] files) {
        if (bindingResult.hasErrors()) {
            return "freeBoards/update";
        }

        freeBoardsService.update(freeBoardsDTO);

        // 파일 업로드를 위한 로직 추가
        if (files != null && files.length > 0) {
            freeBoardsFileService.saveBoardImg(files, freeBoardsDTO.getFreeBoardsNum());
        }

        redirectAttributes.addFlashAttribute("result", "updated");
        redirectAttributes.addAttribute("freeBoardNum", freeBoardsDTO.getFreeBoardsNum());

        return "redirect:/freeBoards/list";
    }


    @GetMapping("/freeBoards/delete/{id}")
    public String deleteProc(@PathVariable Long id) {
        freeBoardsService.delete(id);
        return "redirect:/freeBoards/list";
    }


    @GetMapping("/freeBoards/read/{freeBoardsNum}")
    public String readForm(@PathVariable Long freeBoardsNum,
                           Model model) {

        FreeBoardsDTO freeBoardsDTO = freeBoardsService.read(freeBoardsNum);
        List<FreeBoardsReplyDTO> freeBoardsReplyDTOS = freeBoardsReplyService.list(freeBoardsNum);
        List<BoardImgDTO> boardImgDTOS = freeBoardsFileService.imgList(freeBoardsNum);


        freeBoardsDTO.setImgDTOList(boardImgDTOS);
        freeBoardsDTO.setFreeBoardsReplyDTOList(freeBoardsReplyDTOS);


        log.info("컨트롤러" + freeBoardsDTO);



        model.addAttribute("freeBoardsDTO", freeBoardsDTO);





        
        return "freeBoards/read";


    }


    @GetMapping("/freeBoards/list")
    //@PageableDefault = View에서 페이지 정보가 전달되지 않으면 기본 1페이지로 사용
    //검색어 추가(type=대상, seacrch=검색어)
    //@RequestParam은 ? 뒤에 변수로 보낸 값을 처리(value="보낸변수", defaultValue="변수가 없을 때 대체")
    public String listForm(@PageableDefault(page=1) Pageable pageable,
                           @RequestParam(value="type", defaultValue="")String type,
                           @RequestParam(value="search", defaultValue="")String search, Model model) {

        Page<FreeBoardsDTO> freeBoardsDTOS = freeBoardsService.list(pageable, type, search);

        // 페이지 정보
        ModelUtil.addPageAttributes(model, freeBoardsDTOS, type, search);

        return "freeBoards/list";
    }



    @DeleteMapping("/deleteImage/{boardImgNum}")
    public ResponseEntity<?> deleteImage(@PathVariable Long boardImgNum) {
        try {
            // 이미지 삭제 로직 수행
            boolean isDeleted = freeBoardsFileService.deleteImageById(boardImgNum);
            if (isDeleted) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 삭제 실패");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 삭제 중 오류 발생");
        }
    }
}


