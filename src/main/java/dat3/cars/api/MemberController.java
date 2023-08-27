package dat3.cars.api;

//import dat3.cars.dto.MemberResquest;
import dat3.cars.dto.MemberRequest;
import dat3.cars.dto.MemberResponse;
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

    //security - Anyone
    @PutMapping("/{username}")
    ResponseEntity<Boolean> editMember(@RequestBody MemberRequest body, @PathVariable String username){
        return memberService.editMember(body, username);
    }

    @PatchMapping("/ranking/{username}/{value}")
    ResponseEntity<Boolean> setRankingForUser(@PathVariable String username, @PathVariable int value) {
        return memberService.setRankingForUser(username, value);

    }

    @DeleteMapping("/{username}")
    ResponseEntity<Boolean> deleteMemeberByUsername(@PathVariable String username) {
        return memberService.deleteMemberByUserName(username);
    }




}
