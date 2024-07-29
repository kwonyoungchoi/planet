package org.exam.planet.DTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.exam.planet.Entity.BaseTimeEntity;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
@Builder
public class BoardGameInformationDTO {



    private Long boardGameNum;              //보드게임 번호
    @NotEmpty(message = "생략이 불가능 합니다.")
    private String boardGameTitle;          //보드게임 제목
    @NotEmpty(message = "생략이 불가능 합니다.")
    private String boardGameClass;          //보드게임 분류
    @NotEmpty(message = "생략이 불가능 합니다.")
    private String boardGameContent;        //보드게임 내용
    @NotEmpty(message = "사진을 하나 이상 올려주세요")
    private String boardGameFileURL;        //보드게임 사진/동영상 주소
    private Boolean recommendation;         //추천여부

    private LocalDateTime regDate;
    private LocalDateTime modDate;


    private MemberDTO member;


}
