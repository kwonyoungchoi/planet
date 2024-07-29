package org.exam.planet.Entity;

import jakarta.persistence.*;
import lombok.*;



@Setter @Getter
@Builder
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "suggestions_reply")
@SequenceGenerator(
        name = "suggestion_reply_entity_sql",
        sequenceName = "suggestion_reply_entity_sql",
        initialValue = 1,
        allocationSize = 1
)
public class SuggestionsReplyEntity extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "suggestion_reply_entity_sql")
    private Long suggestionsReplyNum;       //건의사항 댓글 번호
    @Column(length = 500, nullable = false)
    private String suggestionReplyContent;  //건의사항 댓글 내용


    @ManyToOne(fetch = FetchType.LAZY)
    private MemberEntity member;



}
