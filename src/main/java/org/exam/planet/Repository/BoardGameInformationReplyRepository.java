package org.exam.planet.Repository;


import org.exam.planet.Entity.BoardGameInformationReplyEntity;
import org.exam.planet.Entity.FreeBoardsReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardGameInformationReplyRepository extends JpaRepository<BoardGameInformationReplyEntity, Long> {

    Optional<BoardGameInformationReplyEntity> findByBoardGameInformationReplyNum(Long boardGameInformationReplyNum);

    List<BoardGameInformationReplyEntity> findByBoardGameNum (Long boardGameNum);







}
