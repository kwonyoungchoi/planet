package org.exam.planet.util;

import org.exam.planet.DTO.BoardGameInformationDTO;
import org.exam.planet.DTO.FreeBoardsDTO;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

import java.util.Map;

public class ModelUtil {
    public static void addPageAttributes(Model model, Page<FreeBoardsDTO> freeBoardsDTOS, String type, String search) {
        Map<String, Integer> pageInfo = PageUtil.getPaginationInfo(freeBoardsDTOS);
        model.addAllAttributes(pageInfo);
        model.addAttribute("list", freeBoardsDTOS);
        model.addAttribute("type", type);
        model.addAttribute("search", search);
    }

    public static void addPageAttributes2(Model model, Page<BoardGameInformationDTO> boardGameInformationDTOS, String type, String search) {
        Map<String, Integer> pageInfo = PageUtil.getPaginationInfo(boardGameInformationDTOS);
        model.addAllAttributes(pageInfo);
        model.addAttribute("list", boardGameInformationDTOS);
        model.addAttribute("type", type);
        model.addAttribute("search", search);
    }

}
