package org.exam.planet.util;

import org.exam.planet.Entity.BoardImgEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.net.FileNameMap;
import java.util.List;
import java.util.UUID;

@Component
public class FileUpload {
  // 실직적으로 업로드해서 저장할 폴더
//  @Value("${imgLocation}")
//  public String imgLocation;

  //파일업로드 작업
  //업로드할 파일 이름, 파일의 byte값

  private String uploadPath;

  @Autowired
  public FileUpload(@Value("${imgLocation}") String uploadPath) {
    this.uploadPath = uploadPath;
  }

  public String uploadFile(MultipartFile imgFile) {
    String oriFileName = imgFile.getOriginalFilename();

    String newFileName = ""; // 새로 만든 파일명

    if(oriFileName != null && oriFileName.length() != 0) {
      UUID uuid = UUID.randomUUID();
      String extendsion = oriFileName.substring(
              oriFileName.lastIndexOf(".")
      );
      String saveFileName = uuid.toString() + extendsion;
      String uploadFullUrl = uploadPath + saveFileName;

      try {
        FileOutputStream fos = new FileOutputStream(uploadFullUrl);
        fos.write(imgFile.getBytes());
        fos.close();
        newFileName = saveFileName;
      } catch (Exception e) {
        // 아무것도 처리하지 않음
      }
    }

    return newFileName;

  }

  public void deleteFile(List<BoardImgEntity> fileName) {

    if (fileName != null) {
      //파일명이 주어지면, 해당 파일의 전체 경로를 구성
      String deleteFileName = uploadPath + fileName;

      try {
        File deleteFile = new File(deleteFileName);
        //파일이 존재하면 삭제
        if (deleteFile.exists()) { //파일이 존재하면
          deleteFile.delete();
        }
      }catch (Exception e) {
        // 아무것도 처리하지 않음
      }

    }

  }


}
