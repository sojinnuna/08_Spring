package org.scoula.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.scoula.controller.Chef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
// @ComponentScan: 해당 패키지에서 @Component를 찾아서 등록해주겠다
@ComponentScan(basePackages = {"org.scoula"})
public class RootConfig {

}
