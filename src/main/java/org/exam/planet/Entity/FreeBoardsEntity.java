package org.exam.planet.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter @Getter
@Builder
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "freeBoards")
@SequenceGenerator(
        name = "free_boards_entity_sql",
        sequenceName = "free_boards_entity_sql",
        initialValue = 1,
        allocationSize = 1
)
public class FreeBoardsEntity extends BaseTimeEntity{


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "free_boards_entity_sql")
    private Long freeBoardsNum;             //자유게시판 번호
    @Column(length = 200, nullable = false)
    private String freeBoardsTitle;         //자유게시판 제목
    @Column(length = 2000, nullable = false)
    private String freeBoardsContent;       //자유게시판 내용
    @Column(length = 200, nullable = false)
    private String freeBoardsClass;         //자유게시판 분류

    @OneToMany(mappedBy = "freeBoardsEntity", cascade = CascadeType.ALL)
    @Builder.Default
    private List<BoardImgEntity> boardImgEntities = new ArrayList<>();

    
    @ManyToOne(fetch = FetchType.LAZY)
    private MemberEntity memberEntity;





}
