package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// 스프링 컨테이너가 컨트롤러 에너테이션을 확인하면 컨트롤러 객체를 생성해서 담아둠
@Controller
public class MemberController {
    // 여러 빈에서 생성할수 있는데 그때마다 new로 생성하면 매번 새로운 객체를 사용하게 됨
    // 그럴 필요없이 한번 등록해놓고 공유해서 쓸수 있도록 하면 좋음
    private final MemberService memberService;

    // 멤버 서비스를 스프링 컨테이너에 있는 멤버 서비스를 연결 시켜줌
    // 하지만 멤버 서비스도 빈으로 등록 시켜주어야 가능
    // 이게 의존성 주입 : DI
    //@Autowired

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    // 회원 가입
     // 조회할땐 get
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }




    // post는 등록
    // 스프링이 html의 받아온 name을 memberform에다가 넣어줌
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/"; // 메인 화면으로 다시 돌아오기
    }


    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        // 리스트에 가져오고 모델에 담아서 넘기기
        model.addAttribute("members",members);
        return "members/memberList";
    }





}
