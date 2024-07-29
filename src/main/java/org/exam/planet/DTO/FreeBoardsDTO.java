package org.exam.planet.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter @Getter @ToString
@Builder
@AllArgsConstructor @NoArgsConstructor
public class FreeBoardsDTO {


    private Long freeBoardsNum;             //자유게시판 번호
    @NotEmpty(message = "생략이 불가능 합니다.")
    private String freeBoardsTitle;         //자유게시판 제목
    @NotEmpty(message = "생략이 불가능 합니다.")
    private String freeBoardsContent;       //자유게시판 내용
    @NotBlank(message = "생략이 불가능 합니다.")
    private String freeBoardsClass;

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
    private List<FreeBoardsReplyDTO> freeBoardsReplyDTOList = new ArrayList<>();

}
