package org.exam.planet.DTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.exam.planet.Entity.BaseTimeEntity;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@Builder
@AllArgsConstructor @NoArgsConstructor
public class NoticeDTO {


    private Long noticeNum;                 //공지사항 번호
    @NotEmpty(message = "생략이 불가능 합니다.")
    private String noticeTitle;             //공지사항 제목
    @NotEmpty(message = "생략이 불가능 합니다.")
    private String noticeContent;           //공지사항 내용
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    private MemberDTO member ;

}
