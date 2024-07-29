package org.exam.planet.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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


//    private String boardGameFileURL;        //보드게임 사진/동영상 주소


    private LocalDateTime regDate;
    private LocalDateTime modDate;


    private Long boardImgNum;

    private Long memNum;
    @NotBlank(message = "생략이 불가능 합니다.")
    private String memName;
    private String memId;
    private String boardImgName;

    @Builder.Default
    // 이미지 리스트 받는 변수선언
    private List<BoardImgDTO> imgDTOList = new ArrayList<>();

    @Builder.Default
    private List<BoardGameInformationReplyDTO> boardGameInformationReplyDTOList = new ArrayList<>();


}
