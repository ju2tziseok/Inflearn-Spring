package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

// 테스트 파일 생성 딸깍 하는 법
// crtl shift t
class MemberServiceTest {
    MemberService memberService ;
    MemoryMemberRepository memberRepository; // 데이터 비우기 메서드를 가져오기 위함


    @BeforeEach
    public void beforeEach(){
        // 멤버 서비스에 생성자를 만들어서 같은 레포지토리를 참조하게 만들어 같은 디비 안에서 테스트 하도록 설계

        memberRepository = new MemoryMemberRepository();
        // 이게 바로 의존성 주입
        memberService = new MemberService(memberRepository);
    }

    @AfterEach // 매번 메서드 실행 후 어떤 동작을 실행시킬수 잇게 해줌
    public void afterEach(){
        memberRepository.clearStore();
    }


    @Test
    void join() {
        // given : 주어진 상황
        Member member = new Member();
        member.setName("hello");

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

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}