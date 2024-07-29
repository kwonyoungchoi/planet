package org.exam.planet.Service;


import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.exam.planet.DTO.BoardImgDTO;
import org.exam.planet.DTO.FreeBoardsDTO;
import org.exam.planet.Entity.BoardImgEntity;
import org.exam.planet.Entity.FreeBoardsEntity;
import org.exam.planet.Entity.MemberEntity;
import org.exam.planet.Repository.BoardImgRepository;
import org.exam.planet.Repository.FreeBoardsRepository;
import org.exam.planet.Repository.MemberRepository;
import org.exam.planet.util.FileUpload;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
//repository, entity, dto를 주입처리
@RequiredArgsConstructor
//데이터베이스 처리를 모아서 처리(일괄처리)
@Transactional
@Log4j2
public class FreeBoardsService {

    //사용할 저장소 지정
    private final FreeBoardsRepository freeBoardsRepository;
    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;
    private final HttpSession httpSession;
    private final FileService fileService;
    private final FileUpload fileUpload;
    private final BoardImgRepository boardImgRepository;

    //view에서 DTO전달->Entity변환 후 데이터베이스에 저장
    public Long insert(@Valid FreeBoardsDTO freeBoardsDTO, MultipartFile[] files) {

        Long memNum = (Long) httpSession.getAttribute("MemNum");

        Optional<MemberEntity> data = memberRepository.findByMemNum(memNum);

        if (data.isEmpty()) {
            // 사용자를 찾을 수 없는 경우에 대한 예외 처리
            throw new RuntimeException("로그인된 사용자를 찾을 수 없습니다.");
        }


        MemberEntity memberEntity = data.get();

        FreeBoardsEntity freeBoardsEntity = modelMapper.map(freeBoardsDTO, FreeBoardsEntity.class);

        freeBoardsEntity.setMemberEntity(memberEntity);

        log.info(freeBoardsEntity);
        log.info(freeBoardsDTO);

        // 게시글 저장
        freeBoardsRepository.save(freeBoardsEntity);

        if (files != null) {
                    fileService.saveBoardImg(files, freeBoardsEntity.getFreeBoardsNum());
        }
        return null;
    }



    //수정
    public void update(FreeBoardsDTO freeBoardsDTO) {
        //수정할 데이터가 존재하는가?
        //수정할 id로 조회
        Optional<FreeBoardsEntity> temp = freeBoardsRepository.findById(freeBoardsDTO.getFreeBoardsNum());

        if(temp.isPresent()) { //존재하면

            FreeBoardsEntity freeBoardsEntity = modelMapper.map(freeBoardsDTO, FreeBoardsEntity.class);
            //사용할 데이터베이스 질의어로 데이터베이스 구동
            //저장확인가능
            freeBoardsRepository.save(freeBoardsEntity);
        }

    }

    public void delete(Long id) {
        FreeBoardsEntity freeBoardsEntity = freeBoardsRepository.findById(id).orElseThrow();
        fileUpload.deleteFile(freeBoardsEntity.getBoardImgEntities());
    }

    //개별조회
    //요청번호 -> 조회해서 -> DTO변환 후 -> View전달
    public FreeBoardsDTO read(Long freeBoardsNum) {
        Optional<FreeBoardsEntity> freeBoardsEntityOptional = freeBoardsRepository.findById(freeBoardsNum);
        if (freeBoardsEntityOptional.isPresent()) {
            FreeBoardsEntity freeBoardsEntity = freeBoardsEntityOptional.get();
            FreeBoardsDTO freeBoardsDTO = modelMapper.map(freeBoardsEntity, FreeBoardsDTO.class);

            List<BoardImgEntity> boardImgEntities = boardImgRepository.findAll();
            List<BoardImgDTO> boardImgDTOList = new ArrayList<>();

            for (BoardImgEntity boardImgEntity : boardImgEntities) {
                BoardImgDTO boardImgDTO = modelMapper.map(boardImgEntity, BoardImgDTO.class);
                boardImgDTOList.add(boardImgDTO);
            }

            // 해당 게시글과 연관된 사용자 정보를 가져와서 DTO에 세팅
            MemberEntity memberEntity = freeBoardsEntity.getMemberEntity();
            if (memberEntity != null) {
                freeBoardsDTO.setMemName(memberEntity.getMemName());
            }




            return freeBoardsDTO;
        }



        return null; // 게시글이 없을 경우 처리
    }

    //전체조회 List, pageable
    public Page<FreeBoardsDTO> list(Pageable page, String type, String search) {
        int currentPage = page.getPageNumber() - 1;
        int pageLimit = 5;

        Pageable pageable = PageRequest.of(currentPage, pageLimit, Sort.by(Sort.Direction.DESC, "freeBoardsNum"));

        Page<FreeBoardsEntity> freeBoardsEntities;

        if (type.equals("i") && search != null) {
            freeBoardsEntities = freeBoardsRepository.findByFreeBoardsTitle(search, pageable);
        } else if (type.equals("n") && search != null) {
            freeBoardsEntities = freeBoardsRepository.findByFreeBoardsContent(search, pageable);
        } else {
            freeBoardsEntities = freeBoardsRepository.findAll(pageable);
        }

        return freeBoardsEntities.map(this::mapToDtoWithUserInfo);
    }


    private FreeBoardsDTO mapToDtoWithUserInfo(FreeBoardsEntity entity) {
        FreeBoardsDTO dto = modelMapper.map(entity, FreeBoardsDTO.class);

        // 게시글 작성자의 정보를 가져와서 DTO에 세팅
        MemberEntity memberEntity = entity.getMemberEntity();
        if (memberEntity != null) {
            dto.setMemNum(memberEntity.getMemNum());
            dto.setMemName(memberEntity.getMemName());
            dto.setMemId(memberEntity.getMemId());
        }

        return dto;
    }

}
