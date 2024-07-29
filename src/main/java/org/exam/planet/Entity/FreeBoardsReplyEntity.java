package org.exam.planet.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
@Table(name = "free_boards_reply")
@SequenceGenerator(
        name = "free_boards_reply_entity_sql",
        sequenceName = "free_boards_reply_entity_sql",
        initialValue = 1,
        allocationSize = 1
)
public class FreeBoardsReplyEntity extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "free_boards_reply_entity_sql")
    private Long freeBoardsReplyNum;        //자유게시판 번호

    @Column(length = 500, nullable = false)
    private String freeBoardsReplyContent;  //자유게시판 내용



    @ManyToOne(fetch = FetchType.LAZY)
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    private FreeBoardsEntity freeBoardsEntity;



    private Long freeBoardsNum;



}
