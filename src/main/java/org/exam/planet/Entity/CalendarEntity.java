package org.exam.planet.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@Builder
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "calendar")
@SequenceGenerator(
        name = "calnedar_entity_sql",
        sequenceName = "calnedar_entity_sql",
        initialValue = 1,
        allocationSize = 1
)
public class CalendarEntity extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "calnedar_entity_sql")
    private Long dateDateNum;               //벙 번호
    @Column(length = 200, nullable = false)
    private String dateBoardGameTilte;      //벙 대표 보드게임
    @Column(length = 20, nullable = false)
    private String dateBoardGameClass;      //벙 대표 보드게임분류
    @Column(length = 200, nullable = false)
    private String dateTitle;               //벙 제목
    @Column(length = 3000, nullable = false)
    private String dateContent;             //벙 내용
    @Column(length = 200, nullable = false)
    private String datePlace;               //벙 장소



    @ManyToOne(fetch = FetchType.LAZY)
    private MemberEntity member;


}
