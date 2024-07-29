package org.exam.planet.DTO;


import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.exam.planet.Entity.BoardImgEntity;
import org.exam.planet.Entity.FreeBoardsEntity;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString

public class BoardImgDTO {

    private Long boardImgNum;

    private String boardImgName; // 이미지 파일명

    private String oriImgName;

    private String imageType;    // 대표이미지(0), 서브(1), 상세(2)

    private Long freeBoardsNum;

    private String fileUploadFullUrl;

    private FreeBoardsEntity freeBoardsEntity;

    private LocalDateTime regDate;
    private LocalDateTime modDate;



}
