package org.exam.planet.DTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.exam.planet.Entity.BaseTimeEntity;

import java.time.LocalDateTime;
import java.util.Date;

@Setter @Getter @ToString
@Builder
@AllArgsConstructor @NoArgsConstructor
public class MembershipPayDTO {


    private Long membershipPayNum;      //납부 번호
    @NotEmpty(message = "생략이 불가능 합니다.")
    private Date membershipPayMonth;    //납부 월
    @NotEmpty(message = "생략이 불가능 합니다.")
    private Long membershipPayPrice;    //납부 금액

    private LocalDateTime regDate;
    private LocalDateTime modDate;

    private MemberDTO member;


}
