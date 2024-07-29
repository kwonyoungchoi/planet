package org.exam.planet.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "boardImg")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "freeBoardsEntity")
@SequenceGenerator(
        name = "board_img_entity_sql",
        sequenceName = "board_img_entity_sql",
        initialValue = 1,
        allocationSize = 1
)
public class BoardImgEntity extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "board_img_entity_sql")
    private Long boardImgNum;
    private String boardImgName;
    private String oriImgName;
    private String fileUploadFullUrl;
//    private String uploadPath;
    private String imageType;       // 대표 이미지


    @ManyToOne(fetch = FetchType.LAZY)
    private FreeBoardsEntity freeBoardsEntity;


}
