package org.exam.planet.Repository;

import org.exam.planet.Entity.BoardGameInformationEntity;
import org.exam.planet.Entity.FreeBoardsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardGameInformationRepository extends JpaRepository<BoardGameInformationEntity, Long> {

    Optional<BoardGameInformationEntity> findByBoardGameNum(Long freeBoardsNum);

    Page<BoardGameInformationEntity> findByBoardGameTitle(String search, Pageable pageable);
    Page<BoardGameInformationEntity> findByBoardGameContent(String search, Pageable pageable);


}
