package com.example.basicmvc;

import com.example.basicmvc.repository.JpaTemplateMemberRepository;
import com.example.basicmvc.repository.MemberRepository;
import com.example.basicmvc.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;

    }

    @Bean
    public MemberRepository memberRepository(){

        return new JpaTemplateMemberRepository(dataSource);
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
}
