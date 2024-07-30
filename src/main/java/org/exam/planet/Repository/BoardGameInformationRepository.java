package org.exam.planet.Repository;

import org.exam.planet.Entity.BoardGameInformationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardGameInformationRepository extends JpaRepository<BoardGameInformationEntity, Long> {

    Optional<BoardGameInformationEntity> findByBoardGameNum(Long boardGameNum);

    Page<BoardGameInformationEntity> findByBoardGameTitleContaining(String search, Pageable pageable);
    Page<BoardGameInformationEntity> findByBoardGameContentContaining(String search, Pageable pageable);
    Page<BoardGameInformationEntity> findByMemberEntityMemNumIn(List<Long> memNums, Pageable pageable);


}
