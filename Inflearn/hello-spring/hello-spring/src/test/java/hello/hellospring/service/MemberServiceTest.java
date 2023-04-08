package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
class MemberServiceTest {

                            //    MemberService memberService = new MemberService();
    MemberService memberService;
    //중복실행된 코드 clear해주기
                            //    MemoryMemberRepository memberRepository = new MemoryMemberRepository(); //new 객체를 다른객체로 만들면 다른인스턴스이기때문에 내용물이 달라질 수 있음
    MemoryMemberRepository memberRepository;
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }
    //테스트는 메소드를 한글로 적어도 무방
    @Test
    void 회원가입() {
//        문법
    //given
    Member member = new Member();
    member.setName("spring");

    //when
    Long saveId = memberService.join(member);

    //then
//        저장된게 repository에 있는게 맞는지 확인하기
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");



        //when
        //member1, member2는 다르지만 이름이 중복되어 예외처리가 발생되야함
        memberService.join(member1);
        //ctrl + art + v
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


        /**        try{
//        memberService.join(member2);
//        fail();
//        }catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
 }

 **/
        //then
    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}