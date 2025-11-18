package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // 키 값 생성

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // 아이디 값 세팅
        store.put(member.getId(),member); // 저장
        return member;

    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        // 널이여도 감싸서 넘겨줄 수 있음
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // 루프
                .filter(member -> member.getName().equals(name)) // 파라미터로 넘어온 이름과 같은지
                .findAny(); // 찾으면 반환, 끝까지 돌려도 없으면 널 반환

    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // 저장소에 멤버 반환
    }

    public void clearStore(){
        store.clear();
    }
}
