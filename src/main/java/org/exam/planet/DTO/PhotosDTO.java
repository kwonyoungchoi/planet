package org.exam.planet.DTO;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.exam.planet.Entity.BaseTimeEntity;

import java.time.LocalDateTime;

@Setter @Getter @ToString
@Builder
@AllArgsConstructor @NoArgsConstructor
public class PhotosDTO {


    private Long photosNum;             //사진 번호
    @NotEmpty(message = "생략이 불가능 합니다.")
    private String photosURL;           //사진 주소

    private LocalDateTime regDate;
    private LocalDateTime modDate;

    private MemberDTO member;


}
