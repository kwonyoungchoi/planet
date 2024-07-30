package org.exam.planet.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.exam.planet.DTO.BoardImgDTO;
import org.exam.planet.Entity.BoardGameInformationEntity;
import org.exam.planet.Entity.BoardImgEntity;
import org.exam.planet.Repository.BoardGameInfomationImgRepository;
import org.exam.planet.Repository.FreeBoardsImgRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardGameInformationFileService {

    private final ModelMapper modelMapper;
    private final BoardGameInfomationImgRepository boardImgRepository;

    // 실직적으로 업로드해서 저장할 폴더
    @Value("${imgLocation2}")
    public String imgLocation2;

    //파일업로드 작업
    //업로드할 파일 이름, 파일의 byte값


    public String makeDir(){
        String path = imgLocation2 + "\\";
        File file = new File(path);
        if(file.exists() == false){
            file.mkdirs();
        }
        return path;
    }

    public void saveBoardImg(MultipartFile[] files, Long boardGameNum) {
        String uploadPath = makeDir(); // 경로 생성


        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue; // 빈 파일은 무시
            }

            String uuid = UUID.randomUUID().toString();
            String newFileName = uuid + "_" + file.getOriginalFilename();
            String fileUploadFullUrl = uploadPath + "/" + newFileName;
            File save = new File(fileUploadFullUrl);

            try {
                file.transferTo(save); // 파일 저장
                if (save.exists()) {
                    log.info("File successfully saved: " + save.getAbsolutePath());
                } else {
                    log.error("File not found after transfer: " + save.getAbsolutePath());
                }
            } catch (IOException e) {
                log.error("Error while saving file: " + e.getMessage(), e);
                // 예외 처리
                continue;
            }
            // 파일 엔티티 생성 및 저장
            BoardImgEntity boardImgEntity = new BoardImgEntity();
            boardImgEntity.setBoardImgName(file.getOriginalFilename());
            boardImgEntity.setFileUploadFullUrl(fileUploadFullUrl);
            boardImgEntity.setOriImgName(newFileName);
            boardImgEntity.setImageType("Y");

            BoardGameInformationEntity boardGameInformationEntity = new BoardGameInformationEntity();
            boardGameInformationEntity.setBoardGameNum(boardGameNum);
            boardImgEntity.setBoardGameInformationEntity(boardGameInformationEntity);

            boardImgRepository.save(boardImgEntity);
        }
    }

    // 서비스는 보통 dto <-> Entity
    public List<BoardImgDTO> imgList(Long boardGameNum){
        // 컨트롤러로 부터 bno를 입력받아 DTOList형태로 반환
        // repository에 다 있음
        List<BoardImgEntity> boardImgList = boardImgRepository.findByBoardGameInformationEntity_BoardGameNumOrderByBoardImgNumAsc(boardGameNum);
        log.info(boardImgList);

        // mapper를 이용해서 entity를 dto로
        List<BoardImgDTO> boardImgDTOList = new ArrayList<>();
        log.info(boardImgDTOList);

        for(int i = 0; i < boardImgList.size(); i ++) {
            BoardImgDTO boardImgDTO = new BoardImgDTO();
            boardImgDTO.setBoardImgName(boardImgList.get(i).getBoardImgName());
            boardImgDTO.setOriImgName(boardImgList.get(i).getOriImgName());
            boardImgDTO.setBoardImgNum(boardImgList.get(i).getBoardImgNum());
            boardImgDTO.setImageType(boardImgList.get(i).getImageType());
            boardImgDTO.setRegDate(boardImgList.get(i).getRegDate());
            boardImgDTO.setModDate(boardImgList.get(i).getModDate());

            boardImgDTOList.add(boardImgDTO);
        }
        log.info("이미지리스트" + boardImgDTOList);
        return boardImgDTOList;
    }

    // 지정한 파일을 삭제하는 메소드
    public boolean deleteImageById(Long boardsImgNum) {
        try {
            boardImgRepository.deleteById(boardsImgNum);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}