package org.exam.planet.util;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

//View 하단에 페이지버튼을 제어할 값들을 계산처리
//변수는 값만 저장, 배열은 값들을 저장
//Map은 키(변수명),값을 저장
@Component
public class PageUtil {
    //[<<][<]<1][2][3][>][>>]
    //[<<] 첫페이지(1), [>>]마지막페이지
    //[<} 현재페이지(5)의 앞 페이지(4), [>] 현재페이지(5)의 다음페이지(6)
    //[1][2] 이동가능한 페이지 번호, [3]현재페이지 선택불가
    //데이터베이스 0부터 시작
    //화면페이지는 일반적으로 1부터 시작
    public static Map<String, Integer> pagination(Page<?> page) {
        int currentPage =page.getNumber()+1; //현재페이지 번호(데이터베이스를 번호를 화면에 akwrp)
        int totalPage = page.getTotalPages(); //전체 페이지수
        int blockLismit = 10; //사용자마음대로(화면에 출력할 페이지 번호 개수)
        //화면에 출력할 페이지정보를 작성
        Map<String, Integer> pageinfo = new HashMap<>(); //<키의 데이터형, 값의 데이터형>
        //첫페이지[<<] 생략 cause 항상 1
        //시작번호[1]....
        int startPage = Math.max(1, currentPage-blockLismit/2); //1. 중간값 중에서 큰값을 선택해서 사용
        //끝번호.....[10]
        int endPage = Math.min(startPage+blockLismit-1, totalPage);
        //현재페이지의 앞페이지번호[<]
        int prevPage = Math.max(1, currentPage-1);
        //현재페이지의 다음페이지번호[>]
        int nextPage = Math.min(currentPage+1, totalPage);
        //마지막페이지
        int lastPage=totalPage;
        //Controller에 값을 전달
        pageinfo.put("startPage", startPage);       //[1].... , [<<]
        pageinfo.put("endPage", endPage);           //....[10]
        pageinfo.put("prevPage", prevPage);         //[<]
        pageinfo.put("currentPage", currentPage);   //[3]
        pageinfo.put("nextPage", nextPage);         //[>]
        pageinfo.put("lastPage", lastPage);         //[>>]

        return pageinfo;
    }


}
//Application에서 선언된 Bean(변수)을 읽을 때 인식이 안되는 문제
//Bean을 이용할 때는 Service에 작성해서 이용
