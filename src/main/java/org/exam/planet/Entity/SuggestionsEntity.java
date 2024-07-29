package org.exam.planet.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter @Getter @ToString
@Builder
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "suggestions")
@SequenceGenerator(
        name = "suggestions_entity_sql",
        sequenceName = "suggestions_entity_sql",
        initialValue = 1,
        allocationSize = 1
)
public class SuggestionsEntity extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "suggestions_entity_sql")
    private Long suggestionsNum;            //건의사항 번호
    @Column(length = 200, nullable = false)
    private String suggestionsTitle;        //건의사항 제목
    @Column(length = 2000, nullable = false)
    private String suggestionsContent;      //건의사항 내용
    @Column(length = 16, nullable = false)
    private String suggestionsPwd;          //건의사항 비밀번호



}
