package org.exam.planet.DTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.exam.planet.Constrant.RoleType;
import org.exam.planet.Entity.BaseTimeEntity;


import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {

    private Long memNum;                    //멤버 번호
    @NotEmpty(message = "생략이 불가능 합니다.")
    private String memId;                   //멤버 아이디
    @NotEmpty(message = "생략이 불가능 합니다.")
    private String memPwd;                  //멤버 비밀번호
    @NotEmpty(message = "생략이 불가능 합니다.")
    private String memName;                 //멤버 이름
    @NotEmpty(message = "생략이 불가능 합니다.")
    private String memTel;                  //멤버 전화번호
    @NotEmpty(message = "생략이 불가능 합니다.")
    private String memAd1;                   //멤버 주소
    @NotEmpty(message = "생략이 불가능 합니다.")
    private String memAd2;                   //멤버 주소
    @NotNull(message = "생략이 불가능 합니다.")
    private Long memZipcode;
    @NotNull(message = "생략이 불가능 합니다.")
    private Long memAge;                    //멤버 년생

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    private RoleType roleType;


}
