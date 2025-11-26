package hello.hello_spring;


import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 다른 방법은 각 자바 클래스 상단에 어노테이션 등록 후 컴포넌트 스캔
// 자바 코드로 직접 스프링 빈 등록
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    // DI 1. 필드 주입 : 권장 x // 간편하지만 테스트 힘들고 닫혀있는 구조
//    @Autowired
//    private MemberService memberService;

    // 2. setter // 나중에 누군가 이 메서드를 또 호출해서 바꿀 수 있음

    // 3. constructor + autowired : 가장 권장됨, 불변 객체로 사이드 이펙트 방지
}
