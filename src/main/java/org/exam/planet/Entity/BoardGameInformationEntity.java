package org.exam.planet.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
@Table(name = "board_game_information_entity")
@SequenceGenerator(
        name = "board_game_information_entity_sql",
        sequenceName = "board_game_information_entity_sql",
        initialValue = 1,
        allocationSize = 1
)
public class BoardGameInformationEntity extends BaseTimeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "board_game_information_entity_sql")
    private Long boardGameNum;              //보드게임 번호
    @Column(length = 200, nullable = false)
    private String boardGameTitle;          //보드게임 제목
    @Column(length = 20, nullable = false)
    private String boardGameClass;          //보드게임 분류
    @Column(length = 3000, nullable = false)
    private String boardGameContent;        //보드게임 내용
    @Column(length = 1000, nullable = false)



    @OneToMany(mappedBy = "boardGameInformationEntity", cascade = CascadeType.ALL)
    @Builder.Default
    @ToString.Exclude
    private List<BoardImgEntity> boardImgEntities = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private MemberEntity memberEntity;


}
