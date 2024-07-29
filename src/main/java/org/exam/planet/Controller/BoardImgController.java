package org.exam.planet.Controller;

import  lombok.extern.log4j.Log4j2;
import org.exam.planet.Service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@Log4j2
public class BoardImgController {

//    private final FileService fileService;
//
//    public BoardImgController(FileService fileService) {
//        this.fileService = fileService;
//    }
//
//    @GetMapping("/freeBoards/upload")
//    public String imgGet(Model model){
//        String imageUrl = fileService.imgLocation; // 이미지 URL 가져오기
//        model.addAttribute("imageUrl", imageUrl); // 모델에 이미지 URL 추가
//        return "image";
//    }
//
//    @PostMapping("/freeBoards/upload")
//    public String imgPost(@RequestParam("na") String na,
//                          @RequestParam("co") String co,
//                          @RequestParam("file") MultipartFile[] files,
//                          @RequestParam("freeBoardsNum") Long freeBoardsNum) {
//        log.info("Name: " + na);
//        log.info("Description: " + co);
//        log.info("Number of files: " + files.length);
//
//        // 파일 저장 메소드 호출
//        fileService.saveBoardImg(files, freeBoardsNum);
//
//        return "redirect:/freeBoards/upload";
//    }

}
