package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
           회원 가입
         */
    public Long join(Member member){
        // 같은 이름이 잇는 중복 회원x
        // 옵셔널이 멤버를 감싸주기때문에 이런 메서드 동작도 가능함
        //Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m->{
//            throw new IllegalStateException("이미 존재하는 회언입니다.");
//        });

        // 위랑 같은 방식
        validateDuplicateMember(member); // 중복 회원 검즘
        // 리팩토링 단축기 : crtl + alt + m

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m->{
                            throw new IllegalStateException("이미 존재하는 회원입니다");

                        });
    }


    /*
        전체 회원 조회
     */

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
