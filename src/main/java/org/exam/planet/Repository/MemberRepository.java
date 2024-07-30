package org.exam.planet.Repository;

import org.exam.planet.Entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {


    Optional<MemberEntity> findByMemNum(Long memNum);
    Optional<MemberEntity> findByMemId(String memId);
    Optional<MemberEntity> findByMemName(String memName);
    Page<MemberEntity> findByMemId(String search, Pageable pageable);
    Page<MemberEntity> findByMemName(String search, Pageable pageable);
    Page<MemberEntity> findByMemTel(String search, Pageable pageable);
    Page<MemberEntity> findByMemAd1(String search, Pageable pageable);
    Page<MemberEntity> findByMemAge(String search, Pageable pageable);

    List<MemberEntity> findByMemNameContaining(String search);

    // 사용자 이름으로 멤버 엔티티를 가져오는 메서드 정의





}
