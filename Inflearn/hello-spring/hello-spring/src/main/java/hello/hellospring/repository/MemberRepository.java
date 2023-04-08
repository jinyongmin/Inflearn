package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //회원이 저장소에 저장!
    Optional<Member> findById(Long id);     //id에 맞게 찾아오기
    Optional<Member> findByName(String name); //name에 맞게 찾아오기
    List<Member> findAll();     //찾아온 모든 회원리스트를 반환
}
