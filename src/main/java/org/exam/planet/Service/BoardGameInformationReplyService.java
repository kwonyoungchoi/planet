package org.exam.planet.Service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.exam.planet.DTO.BoardGameInformationDTO;
import org.exam.planet.DTO.BoardGameInformationReplyDTO;
import org.exam.planet.DTO.FreeBoardsReplyDTO;
import org.exam.planet.Entity.*;
import org.exam.planet.Repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class BoardGameInformationReplyService {

    private final BoardGameInformationReplyRepository boardGameInformationReplyRepository;
    private final ModelMapper modelMapper;
    private final BoardGameInformationRepository boardGameInformationRepository;
    private final MemberRepository memberRepository;
    private final HttpSession httpSession;

    public void delete(Long freeBoardsReplyNum) {

        log.info("해당 데이터 삭제 요청..");

        boardGameInformationReplyRepository.deleteById(freeBoardsReplyNum);

    }

    public void update(BoardGameInformationReplyDTO boardGameInformationReplyDTO) {
        log.info("댓글 데이터 수정 요청...");

        long boardGameInformationReplyNum = boardGameInformationReplyDTO.getBoardGameInformationReplyNum();

        Optional<BoardGameInformationReplyEntity> read = boardGameInformationReplyRepository.findByBoardGameInformationReplyNum(boardGameInformationReplyNum);
        BoardGameInformationReplyEntity boardGameInformationReplyEntity = read.orElseThrow();

        Optional<BoardGameInformationEntity> data = boardGameInformationRepository.findByBoardGameNum(boardGameInformationReplyDTO.getBoardGameNum());
        BoardGameInformationEntity boardGameInformationEntity = data.orElseThrow();

        Optional<MemberEntity> temp = memberRepository.findByMemNum(boardGameInformationReplyDTO.getMemNum());
        MemberEntity memberEntity = temp.orElseThrow();

        BoardGameInformationReplyEntity update = modelMapper.map(boardGameInformationReplyDTO, BoardGameInformationReplyEntity.class);

        update.setBoardGameInformationReplyNum(boardGameInformationReplyEntity.getBoardGameInformationReplyNum());
        update.setBoardGameInformationEntity(boardGameInformationEntity);
        update.setMemberEntity(memberEntity);


        boardGameInformationReplyRepository.save(update);
    }

    public void insert(Long BoardGameInformationReplyNum, BoardGameInformationReplyDTO boardGameInformationReplyDTO) {
        log.info("댓글 등록 요청");

        //연관관계 부모 게시글 정보
        Optional<BoardGameInformationEntity> data = boardGameInformationRepository.findByBoardGameNum(BoardGameInformationReplyNum);
        BoardGameInformationEntity boardGameInformationEntity = data.orElseThrow();

        Long memNum = (Long) httpSession.getAttribute("MemNum");
        Optional<MemberEntity> temp = memberRepository.findByMemNum(memNum);

        if (temp.isEmpty()) {
            // 사용자를 찾을 수 없는 경우에 대한 예외 처리
            throw new RuntimeException("로그인된 사용자를 찾을 수 없습니다.");
        }

        MemberEntity memberEntity = temp.get();

        BoardGameInformationReplyEntity boardGameInformationReplyEntity = modelMapper.map(boardGameInformationReplyDTO, BoardGameInformationReplyEntity.class);
        boardGameInformationReplyEntity.setMemberEntity(memberEntity);
        boardGameInformationReplyEntity.setBoardGameInformationEntity(boardGameInformationEntity);
        log.info(boardGameInformationEntity);
        boardGameInformationReplyRepository.save(boardGameInformationReplyEntity);
    }

    public List<BoardGameInformationReplyDTO> list(Long boardGameNum) {
        log.info("댓글 조회 요청");

        List<BoardGameInformationReplyEntity> boardGameInformationReplyEntities = boardGameInformationReplyRepository.findByBoardGameNum(boardGameNum);

        List<BoardGameInformationReplyDTO> boardGameInformationReplyDTOS = new ArrayList<>();

        for (BoardGameInformationReplyEntity entity : boardGameInformationReplyEntities) {
            BoardGameInformationReplyDTO dto = modelMapper.map(entity, BoardGameInformationReplyDTO.class);

            // Retrieve memName from memberEntity
            MemberEntity memberEntity = entity.getMemberEntity();
            if (memberEntity != null) {
                dto.setMemName(memberEntity.getMemName());
            }

            boardGameInformationReplyDTOS.add(dto);
        }

        return boardGameInformationReplyDTOS;
    }


}
