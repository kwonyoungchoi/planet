package org.exam.planet.DTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.exam.planet.Entity.BaseTimeEntity;

import java.time.LocalDateTime;

@Setter @Getter @ToString
@Builder
@AllArgsConstructor @NoArgsConstructor
public class SuggestionsReplyDTO {


    private Long suggestionsReplyNum;       //건의사항 댓글 번호
    @NotEmpty(message = "생략이 불가능 합니다.")
    private String suggestionReplyContent;  //건의사항 댓글 내용

    private LocalDateTime regDate;
    private LocalDateTime modDate;


    private MemberDTO member;



}
