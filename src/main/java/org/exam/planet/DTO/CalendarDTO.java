package org.exam.planet.DTO;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.exam.planet.Entity.BaseTimeEntity;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@Builder
@AllArgsConstructor @NoArgsConstructor
public class CalendarDTO {

    private Long dateDateNum;               //벙 번호
    @NotEmpty(message = "생략이 불가능 합니다.")
    private String dateBoardGameTilte;      //벙 대표 보드게임
    @NotEmpty(message = "생략이 불가능 합니다.")
    private String dateBoardGameClass;      //벙 대표 보드게임분류
    @NotEmpty(message = "생략이 불가능 합니다.")
    private String dateTitle;               //벙 제목
    @NotEmpty(message = "생략이 불가능 합니다.")
    private String dateContent;             //벙 내용
    @NotEmpty(message = "생략이 불가능 합니다.")
    private String datePlace;               //벙 장소

    private LocalDateTime dateDate;
    private LocalDateTime dateTime;


    private MemberDTO member;


}
