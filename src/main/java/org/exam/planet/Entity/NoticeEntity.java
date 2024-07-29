package org.exam.planet.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@Builder
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "notice")
@SequenceGenerator(
        name = "notice_entity_sql",
        sequenceName = "notice_entity_sql",
        initialValue = 1,
        allocationSize = 1
)
public class NoticeEntity extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notice_entity_sql")
    private Long noticeNum;                 //공지사항 번호
    @Column(length = 200, nullable = false)
    private String noticeTitle;             //공지사항 제목
    @Column(length = 1000, nullable = false)
    private String noticeContent;           //공지사항 내용


    @ManyToOne(fetch = FetchType.LAZY)
    private MemberEntity member ;

}
