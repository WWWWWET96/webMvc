package com.example.basicmvc;

import com.example.basicmvc.domain.Member;
import com.example.basicmvc.repository.MemberRepository;
import com.example.basicmvc.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Commit
    public void 회원가입() throws  Exception{
        Member member = new Member();
        member.setName("lee");

        Long saveId = memberService.join(member);

        Member findMember = memberRepository.findById(saveId).get();
        Assertions.assertEquals(member.getName(), findMember.getName());
    }

    @Test
    public void 중복_회원_예외() throws Exception{
        Member member1 = new Member();
        member1.setName("Kim");

        Member member2 = new Member();
        member2.setName("Kim");

        memberService.join(member1);
        IllegalStateException e = Assertions.assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));

        org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다.");
    }
}
