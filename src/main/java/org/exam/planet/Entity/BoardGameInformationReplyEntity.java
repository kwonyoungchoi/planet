package org.exam.planet.Entity;


import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
@Table(name = "board_game_information_reply")
@SequenceGenerator(
        name = "board_game_information_reply_entity_sql",
        sequenceName = "board_game_information_reply_entity_sql",
        initialValue = 1,
        allocationSize = 1
)
public class BoardGameInformationReplyEntity extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_game_information_reply_entity_sql")
    private Long boardGameInformationReplyNum;        //자유게시판 번호

    @Column(length = 500, nullable = false)
    private String boardGameInformationReplyContent;  //자유게시판 내용



    @ManyToOne(fetch = FetchType.LAZY)
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    private BoardGameInformationEntity boardGameInformationEntity;


    private Long boardGameNum;



}
