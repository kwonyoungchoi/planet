package org.exam.planet.Repository;


import org.exam.planet.Entity.FreeBoardsEntity;
import org.exam.planet.Entity.FreeBoardsReplyEntity;
import org.exam.planet.Entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FreeBoardsReplyRepository extends JpaRepository<FreeBoardsReplyEntity, Long> {

    Optional<FreeBoardsReplyEntity> findByFreeBoardsReplyNum(Long freeBoardsReplyNum);

    List<FreeBoardsReplyEntity> findByFreeBoardsNum(Long freeBoardsNum);







}
