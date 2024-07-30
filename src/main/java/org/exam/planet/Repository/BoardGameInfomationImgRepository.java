package org.exam.planet.Repository;

import org.exam.planet.Entity.BoardImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardGameInfomationImgRepository extends JpaRepository<BoardImgEntity, Long> {

    List<BoardImgEntity> findByFreeBoardsEntity_FreeBoardsNumOrderByBoardImgNumAsc(Long freeBoardsNum);

    List<BoardImgEntity> findByBoardGameInformationEntity_BoardGameNumOrderByBoardImgNumAsc(Long boardGameNum);


}
