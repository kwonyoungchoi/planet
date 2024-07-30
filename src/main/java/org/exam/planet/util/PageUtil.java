package org.exam.planet.util;

import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

public class PageUtil {
    public static Map<String, Integer> getPaginationInfo(Page<?> page) {
        int totalPages = page.getTotalPages();
        int currentPage = page.getNumber() + 1;
        int stratPage = Math.max(1, currentPage - 4);
        int endPage = Math.min(stratPage + 9, totalPages);

        Map<String, Integer> pageInfo = new HashMap<>();
        pageInfo.put("totalPages", totalPages);
        pageInfo.put("currentPage", currentPage);
        pageInfo.put("startPage", stratPage);
        pageInfo.put("endPage", endPage);

        return pageInfo;
    }
}
