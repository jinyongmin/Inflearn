package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

//테스트 클래스 만드는 단축키: ctrl + Shift + T
public class MemberService {

//    private  final MemberRepository memberRepository = new MemoryMemberRepository();
    private  final MemberRepository memberRepository;
    //new 객체를 다른객체로 만들면 다른인스턴스이기때문에 내용물이 달라질 수 있음
    //같은 인스턴스를 사용해주기 위해 외부에서 넣어주도록 변경 -->Constructor
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member) {
//        같은 이름이 있는 중복 회원x
/**        Optional 반환 권장
       Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m ->{
           throw new IllegalStateException("이미 존재하는 회원입니다.");
        }); **/

//        같은 이름이 있는 중복 회원x
        validateDuplicateMember(member);    //ctrl + alt + shift + T 후 extra method
        memberRepository.save(member);      //중복 회원 검증
        return  member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    //전체 회원 조회
    public List<Member> findMembers() {
    return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
