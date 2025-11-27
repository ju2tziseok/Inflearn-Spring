package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

// 스프링 통합 테스트
// 테스트 파일 생성 딸깍 하는 법
// crtl shift t
@SpringBootTest // 스프링 컨테이너와 같이 테스트
@Transactional // 테스트를 실행하면 트랜잭션을 먼저 실행하고 db 쿼리를 실행한 다음 테스트가 끝나면 롤백을 해줌
class MemberServiceIntegrationTest {
    @Autowired
    MemberService memberService ;

    // config 파일에 빈으로 등록된 jdbc멤버 리포지토리를 가져옴
    @Autowired
    MemberRepository memberRepository;


    @Test
    void join() {
        // given : 주어진 상황
        Member member = new Member();
        member.setName("spring");

        // when : 실행했을때
        Long saveId = memberService.join(member);

        // then : 이런 결과가 나와야 해
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertEquals(member.getName(),findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        // when
        memberService.join(member1);


        // then
        // 이 예외로 터져야 해 try catch구문 귀찮으니까
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        // 에러 메시지를 확인하고 싶으면 변수에 담아서 검증
        Assertions.assertEquals(e.getMessage(),"이미 존재하는 회원입니다");

//        try{
//            memberService.join(member2);
//            fail(); // 중복을 햇는데도 예외가 안 터지면 테스트 실패 시켜야 함
//        }catch(IllegalStateException e){
//            Assertions.assertEquals(e.getMessage(),"이미 존재하는 회원입니다");
//        }


    }

}