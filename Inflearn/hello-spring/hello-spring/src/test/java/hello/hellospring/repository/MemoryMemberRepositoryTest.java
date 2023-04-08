package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();
// test 코드
//  전체 실행을 할 경우, 에러가 나타남. 동작순서 때문
//  그래서 테스트 코드를 진행하고 난 뒤, 코드를 깔끔하게 지워주는 코드를 작성해야함
//  MemoryMemberRepository만 테스트 하기 때문에 객체도 MemoryMemberRepository로 변경
//  테스트는 서로 의존관계없이 실행해줘야함
    @AfterEach
    public void afterEach(){
    repository.clearStore();    //테스트를 실행하고 난뒤 실행한 코드를 지워줌
    //repository MemoryMemberRepository에 메소드를 작성
        // public void clearStore(){
        //        store.clear();
        //    }
    
    }
    
    @Test
    public void save(){
        Member member = new Member();   //객체
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();    //Optional 반환타입을 꺼낼때 get을 사용
//      Assertions.assertEquals(member, result);  //테스트 member와 result값이 값은지 비교
//        Assertions.assertThat(member).isEqualTo(result);        //옵션 기능 아래 누르면 편리하게 assertThat사용가능
        assertThat(member).isEqualTo(result);


    }
    @Test
    public  void findByName() {
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        // 복사 후, shift + f6 간편하게 이름 바꿀수 있도록
        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        Member result = repository.findByName("Spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);


        Member member2 = new Member();
        member1.setName("Spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
