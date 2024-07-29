package org.exam.planet.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter @Getter @ToString
@Builder
@Entity
@AllArgsConstructor @NoArgsConstructor
@Table(name = "membershipPay")
@SequenceGenerator(
        name = "membership_pay_entity_sql",
        sequenceName = "membership_pay_entity_sql",
        initialValue = 1,
        allocationSize = 1
)
public class MembershipPayEntity extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "membership_pay_entity_sql")
    private Long membershipPayNum;      //납부 번호
    private Date membershipPayMonth;    //납부 월
    private Long membershipPayPrice;    //납부 금액
    
    @ManyToOne(fetch = FetchType.LAZY)
    private MemberEntity member;


}
