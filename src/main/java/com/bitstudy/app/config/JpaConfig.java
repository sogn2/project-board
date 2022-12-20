package com.bitstudy.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
/*JPA에서 auditing을 가능하게 하는 어노테이션
* jpa auditing이란 spring data jpa에서 자동으로 값을 넣어주는 기능
*
* */
@EnableJpaAuditing
public class JpaConfig {
    
    //사람이름 정보 넣어주려고 만드는 config 설정
    @Bean
    public AuditorAware<String> auditorAware(){
        return () -> Optional.of("bitstudy");
        //이렇게 하면 앞으로 JPA Auditing 할때마다 사람이름은 이걸로 넣게 된다
        //TODO: 나중에 스프링 시큐리티로 인증기능 붙일때 수정필요
    }
}
