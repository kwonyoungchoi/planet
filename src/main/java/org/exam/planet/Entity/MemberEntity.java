package org.exam.planet.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.exam.planet.Constrant.RoleType;


import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
@Table(name = "member")
@Builder
@Entity
@SequenceGenerator(
        name = "member_entity_sql",
        sequenceName = "member_entity_sql",
        initialValue = 1,
        allocationSize = 1
)
public class MemberEntity extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_entity_sql")
    private Long memNum;                    //멤버 번호
    @Column(length = 50, nullable = false)
    private String memId;                   //멤버 아이디
    @Column(length = 1000, nullable = false)
    private String memPwd;                  //멤버 비밀번호
    @Column(length = 50, nullable = false)
    private String memName;                 //멤버 이름
    @Column(length = 50, nullable = false)
    private String memTel;                  //멤버 전화번호
    @Column(length = 200, nullable = false)
    private String memAd1;                   //멤버 주소1
    @Column(nullable = false)
    private Long memZipcode;
    @Column(length = 200, nullable = false)
    private String memAd2;                   //멤버 주소2
    @Column(nullable = false)
    private Long memAge;                    //멤버 년생


    @Enumerated(EnumType.STRING) // 열거형에 선언된 상수를 읽어옴
    private RoleType roleType;


}
