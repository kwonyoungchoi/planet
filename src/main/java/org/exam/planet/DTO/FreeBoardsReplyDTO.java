package org.exam.planet.DTO;


import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
@Builder
public class FreeBoardsReplyDTO {


    private Long freeBoardsReplyNum;        //자유게시판 번호
    @NotEmpty(message = "생략이 불가능 합니다.")
    private String freeBoardsReplyContent;  //자유게시판 내용
    private LocalDateTime regDate;
    private LocalDateTime modDate;


    private Long memNum;
    private String memId;
    private String memName;

    private Long freeBoardsNum;



}
