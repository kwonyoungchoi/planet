package org.exam.planet.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//MVC 모델로 구성된 프로젝트에서 Web에 처리되는 부분을 빈스
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
  // 빈스를 호출하는 어노테이션 value
//  Spring uploadPath = "file:///c:\\product";
//  수정시 시간오래 걸린다. 유지보수 오래걸린다.
  // html $는 변수값을 읽어온다.
  @Value("${uploadPath}")
  String uploadPath; // uploadPath는 application에 저장된 값을 가져와서 저장

  // 자원을 추가 , Registry는 운영체제가 컴퓨터의 하드웨어, 소프트웨어의 모든 정보를 관리
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/images/**") // static에 대응하는 폴더명
        .addResourceLocations(uploadPath);
  }
}

