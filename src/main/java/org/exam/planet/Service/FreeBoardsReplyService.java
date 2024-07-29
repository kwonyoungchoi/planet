package org.exam.planet.Service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.exam.planet.DTO.FreeBoardsReplyDTO;
import org.exam.planet.Entity.FreeBoardsEntity;
import org.exam.planet.Entity.FreeBoardsReplyEntity;
import org.exam.planet.Entity.MemberEntity;
import org.exam.planet.Repository.FreeBoardsReplyRepository;
import org.exam.planet.Repository.FreeBoardsRepository;
import org.exam.planet.Repository.MemberRepository;
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
public class FreeBoardsReplyService {

    private final FreeBoardsReplyRepository freeBoardsReplyRepository;
    private final ModelMapper modelMapper;
    private final FreeBoardsRepository freeBoardsRepository;
    private final MemberRepository memberRepository;
    private final HttpSession httpSession;

    public void delete(Long freeBoardsReplyNum) {

        log.info("해당 데이터 삭제 요청..");

        freeBoardsReplyRepository.deleteById(freeBoardsReplyNum);

    }

    public void update(FreeBoardsReplyDTO freeBoardsReplyDTO) {
        log.info("댓글 데이터 수정 요청...");

        long freeBoardsReplyNum = freeBoardsReplyDTO.getFreeBoardsReplyNum();

        Optional<FreeBoardsReplyEntity> read = freeBoardsReplyRepository.findByFreeBoardsReplyNum(freeBoardsReplyNum);
        FreeBoardsReplyEntity freeBoardsReplyEntity = read.orElseThrow();

        Optional<FreeBoardsEntity> data = freeBoardsRepository.findByFreeBoardsNum(freeBoardsReplyDTO.getFreeBoardsNum());
        FreeBoardsEntity freeBoardsEntity = data.orElseThrow();

        Optional<MemberEntity> temp = memberRepository.findByMemNum(freeBoardsReplyDTO.getMemNum());
        MemberEntity memberEntity = temp.orElseThrow();

        FreeBoardsReplyEntity update = modelMapper.map(freeBoardsReplyDTO, FreeBoardsReplyEntity.class);

        update.setFreeBoardsReplyNum(freeBoardsReplyEntity.getFreeBoardsReplyNum());
        update.setFreeBoardsEntity(freeBoardsEntity);
        update.setMemberEntity(memberEntity);


        freeBoardsReplyRepository.save(update);
    }

    public void insert(Long freeBoardsNum, FreeBoardsReplyDTO freeBoardsReplyDTO) {
        log.info("댓글 등록 요청");

        //연관관계 부모 게시글 정보
        Optional<FreeBoardsEntity> data = freeBoardsRepository.findByFreeBoardsNum(freeBoardsNum);
        FreeBoardsEntity freeBoardsEntity = data.orElseThrow();

        Long memNum = (Long) httpSession.getAttribute("MemNum");
        Optional<MemberEntity> temp = memberRepository.findByMemNum(memNum);

        if (temp.isEmpty()) {
            // 사용자를 찾을 수 없는 경우에 대한 예외 처리
            throw new RuntimeException("로그인된 사용자를 찾을 수 없습니다.");
        }

        MemberEntity memberEntity = temp.get();

        FreeBoardsReplyEntity freeBoardsReplyEntity = modelMapper.map(freeBoardsReplyDTO, FreeBoardsReplyEntity.class);
        freeBoardsReplyEntity.setMemberEntity(memberEntity);
        freeBoardsReplyEntity.setFreeBoardsEntity(freeBoardsEntity);
        log.info(freeBoardsEntity);
        freeBoardsReplyRepository.save(freeBoardsReplyEntity);
    }

    public List<FreeBoardsReplyDTO> list(Long freeBoardsNum) {
        log.info("댓글 조회 요청");

        List<FreeBoardsReplyEntity> freeBoardsReplyEntities = freeBoardsReplyRepository.findByFreeBoardsNum(freeBoardsNum);

        List<FreeBoardsReplyDTO> freeBoardsReplyDTOs = new ArrayList<>();

        for (FreeBoardsReplyEntity entity : freeBoardsReplyEntities) {
            FreeBoardsReplyDTO dto = modelMapper.map(entity, FreeBoardsReplyDTO.class);

            // Retrieve memName from memberEntity
            MemberEntity memberEntity = entity.getMemberEntity();
            if (memberEntity != null) {
                dto.setMemName(memberEntity.getMemName());
            }

            freeBoardsReplyDTOs.add(dto);
        }

        return freeBoardsReplyDTOs;
    }


}
