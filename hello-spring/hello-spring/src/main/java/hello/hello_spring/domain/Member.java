package hello.hello_spring.domain;

//일반적인 웹 애플리케이션 계층 구조
//컨트롤러 -> 서비스 -> 리포지토리 -> db
//도메인은 컨트롤러 서비스 리포지토리가 참조함

//컨트롤러 : 웹 MVC의 컨트롤러 역할
//서비스 : 핵심 비즈니스 로직 구현
//리포지토리 : 데이터베이스에 접근, 도메인 객체를 DB에 저장하고 관리
//도메인 : 비즈니스 도메인 객체 ex) : 회원, 주문, 쿠폰 등등 주로 데이터베이스에 저장하고 관리됨


//클래스 의존 관계

//MemberService
//MemberRepository :인터페이스
//MemoryMemberRepository : 구현체

import jakarta.persistence.*;

@Entity // jpa 사용하기위한 매핑
public class Member {
                        // autoincrement에 대한 설정
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
