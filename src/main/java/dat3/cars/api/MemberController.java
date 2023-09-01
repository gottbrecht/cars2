package dat3.cars.api;

import dat3.cars.dto.MemberRequest;
import dat3.cars.dto.MemberResponse;
import dat3.cars.repositories.MemberRepository;
import dat3.cars.service.MemberService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }



    //Security admin only
    @GetMapping
    List<MemberResponse> getMembers(){
        return memberService.getMembers(false);
    }

    //security admin only
    @GetMapping
    @RequestMapping("/simple")
    List<MemberResponse> getMembersSimple() {
        return memberService.getMembersSimple();
    }

    //security admin only
    @GetMapping(path = "/{username}")
    MemberResponse getMembersById(@PathVariable String username) throws Exception {
        return memberService.getMemberById(username);
    }

    //security - Anyone
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    MemberResponse addMember(@RequestBody MemberRequest body) {

        return memberService.addMember(body);
    }

    //security - Admin
    @PutMapping("/{username}")
    void editMember(@RequestBody MemberRequest body, @PathVariable String username) {
        memberService.editMember(body,username);
    }
    //TODO why did we make this change?
    //ResponseEntity<Boolean> editMember(@RequestBody MemberRequest body, @PathVariable String username){
    //return memberService.editMember(body, username);

    //Security admin
    @PatchMapping("/ranking/{username}/{value}")
    void setRankingForUser(@PathVariable String username, @PathVariable int value) {
         memberService.setRankingForUser(username, value);

    }
    //Security admin
    @DeleteMapping("/{username}")
    void deleteMemeberByUsername(@PathVariable String username) {
        memberService.deleteMemberByUserName(username);
    }

  /*  @GetMapping("/bad")
    public List<Member> getMembersBad(){
        return MemberRepository.*/




}
