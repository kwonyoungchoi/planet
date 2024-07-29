package org.exam.planet.DTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.exam.planet.Entity.BaseTimeEntity;

import java.time.LocalDateTime;

@Setter @Getter @ToString
@Builder
@AllArgsConstructor @NoArgsConstructor
public class SuggestionsDTO {


    private Long suggestionsNum;            //건의사항 번호
    @NotEmpty(message = "생략이 불가능 합니다.")
    private String suggestionsTitle;        //건의사항 제목
    @NotEmpty(message = "생략이 불가능 합니다.")
    private String suggestionsContent;      //건의사항 내용
    @NotEmpty(message = "생략이 불가능 합니다.")
    private String suggestionsPwd;          //건의사항 비밀번호
    private LocalDateTime regDate;
    private LocalDateTime modDate;


}
