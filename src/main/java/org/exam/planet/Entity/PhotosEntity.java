package org.exam.planet.Entity;


import jakarta.persistence.*;
import lombok.*;

@Setter @Getter @ToString
@Builder
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "photos")
@SequenceGenerator(
        name = "photos_entity_sql",
        sequenceName = "photos_entity_sql",
        initialValue = 1,
        allocationSize = 1
)
public class PhotosEntity extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "photos_entity_sql")
    private Long photosNum;             //사진 번호
    @Column(length = 1000)
    private String photosURL;           //사진 주소

    @ManyToOne(fetch = FetchType.LAZY)
    private MemberEntity member;


}
