package org.exam.planet.Repository;


import org.exam.planet.Entity.FreeBoardsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FreeBoardsRepository extends JpaRepository<FreeBoardsEntity, Long> {

    Optional<FreeBoardsEntity> findByFreeBoardsNum(Long freeBoardsNum);

    Page<FreeBoardsEntity> findByFreeBoardsTitleContaining(String search, Pageable pageable);
    Page<FreeBoardsEntity> findByFreeBoardsContentContaining(String search, Pageable pageable);
    Page<FreeBoardsEntity> findByMemberEntityMemNumIn(List<Long> memNums, Pageable pageable);



//    Page<FreeBoardsEntity> findBy(String search, Pageable pageable);
//    Page<FreeBoardsEntity> findByMember_MemId(String search, Pageable pageable);

}
