package dat3.cars.service;


import dat3.cars.dto.MemberRequest;
import dat3.cars.dto.MemberResponse;
import dat3.cars.entity.Member;
import dat3.cars.repositories.MemberRepository;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
    MemberRepository memberRepository;

    //dependencyInject
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberResponse> getMembers(boolean includeAll) {
        List<Member> members = memberRepository.findAll();
        List<MemberResponse> memberResponses = new ArrayList<>();
        for (Member m : members) {
            MemberResponse mr = new MemberResponse(m, includeAll);
            memberResponses.add(mr);
        }
        return memberResponses;

    }

    public List<MemberResponse> getMembersSimple() {
        List<Member> member = memberRepository.findAll();
        List<MemberResponse> members = new ArrayList<>();
        for (Member m : member) {
            MemberResponse mr = MemberResponse.builder()
                    .username(m.getUsername())
                    .email(m.getEmail())
                    .build();
            members.add(mr);
        }
        return members;
    }

    public MemberResponse getMemberById(String username) {
        Member member = memberRepository.findById(username).orElseThrow(() -> new ResponseStatusException
                (HttpStatus.NOT_FOUND, "Member not found"));
        return new MemberResponse(member, true);

    }

    public MemberResponse addMember(MemberRequest requestBody) {
        if (memberRepository.existsById(requestBody.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member with this ID already exist");
        }

        //DTO member
        Member newMember = MemberRequest.getMemberEntity(requestBody);
        newMember = memberRepository.save(newMember);

        return new MemberResponse(newMember, true);

    }

    public ResponseEntity<Boolean> editMember(MemberRequest body, String username) {
        Member member = memberRepository.findById(username).orElseThrow(() -> new ResponseStatusException
                (HttpStatus.BAD_REQUEST, "Member with this username exists"));
        if (!body.getUsername().equals(username)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't change username");
        }
        member.setPassword(body.getPassword());
        member.setEmail(body.getEmail());
        member.setFirstName(body.getFirstName());
        member.setLastName(body.getStreet());
        member.setCity(body.getCity());
        member.setZip(body.getZip());
        memberRepository.save(member);
        return ResponseEntity.ok(true);
    }

    public ResponseEntity<Boolean> setRankingForUser(String username, int value) {
        Member member = memberRepository.findById(username).orElseThrow(() -> new ResponseStatusException
                (HttpStatus.BAD_REQUEST, "Members with this username doesn't exist"));
        member.setRanking(value);
        memberRepository.save(member);
        return ResponseEntity.ok(true);
    }

    public ResponseEntity<Boolean> deleteMemberByUserName(String username) {
        Member member = memberRepository.findById(username).orElseThrow(() -> new ResponseStatusException
                (HttpStatus.BAD_REQUEST, "Member w this un doesn't exist"));
        memberRepository.delete(member);
        return ResponseEntity.ok(true);
    }
}
