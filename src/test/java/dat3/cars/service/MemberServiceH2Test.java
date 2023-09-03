package dat3.cars.service;

import dat3.cars.dto.MemberRequest;
import dat3.cars.dto.MemberResponse;
import dat3.cars.entity.Member;
import dat3.cars.repositories.MemberRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

    @AllArgsConstructor
    @DataJpaTest
    class MemberServiceH2Test {

        @Autowired
        MemberRepository memberRepository;
        MemberService memberService;

        Member m1, m2;  //Talk about references in Java for why we don't add the "isInitialize flag"

        @BeforeEach
        void setUp() {
            m1 = memberRepository.save(new Member("user1", "pw1", "email1", "fn1", "ln1",  "street1", "city1", "zip1"));
            m2 = memberRepository.save(new Member("user2", "pw2", "email1", "fn2", "ln2", "street2", "city2", "zip2"));
           /* Member m3 = m1;
            m3.setCity("xxx");
            System.out.println(m1.getCity()); //---> xxxxx*/
            memberService = new MemberService(memberRepository); //Set up memberService with the mock (H2) database
        }

        @Test
        void testGetMembersAllDetails() {
            List<MemberResponse> memberResponses = memberService.getMembers(true);
            assertEquals(2,memberResponses.size(),"Expects 2 members");
            LocalDateTime time = memberResponses.get(0).getCreated();
            assertNotNull(time,"expects dates to be set when FALSE is passed");
            //Todo
        }

        @Test
        void testGetMembersNoDetails() {
            List<MemberResponse> memberResponses = memberService.getMembers(false);
            assertEquals(2,memberResponses.size(),"Expects 2 members");
            LocalDateTime time = memberResponses.get(0).getCreated();
            assertNull(time,"expects dates to be set when FALSE is passed");
            //Todo
        }

        @Test
        void testFindByIdFound() {
            assertThrows(ResponseStatusException.class, () -> memberService.getMemberById("I exist"));
            MemberResponse res = memberService.getMemberById("user1");
            assertEquals("user1",res.getUsername());
            //TODO
        }

        @Test
        void testFindByIdNotFound() {
            assertThrows(ResponseStatusException.class, () -> memberService.getMemberById("404 NOT_FOUND"));
            MemberResponse res = memberService.getMemberById("user1");
            assertEquals("user1",res.getUsername()); //tried with "userr1" - test approved

            //This should test that a ResponseStatus exception is thrown with status= 404 (NOT_FOUND)
            //TODO
        }

        @Test
            /* Remember MemberRequest comes from the API layer, and MemberResponse is returned to the API layer
             * Internally addMember savex a Member entity to the database*/
        void testAddMember_UserDoesNotExist() {
            //Add @AllArgsConstructor to MemberRequest and @Builder to MemberRequest for this to work
            //TODO
        }

        @Test
        void testAddMember_UserDoesExistThrows() {
            //This should test that a ResponseStatus exception is thrown with status= 409 (BAD_REQUEST)
            //TODO
        }

        @Test
        void testEditMemberWithExistingUsername() {
            //TODO
        }

        @Test
        void testEditMemberNON_ExistingUsernameThrows() {
            //This should test that a ResponseStatus exception is thrown with status= 404 (NOT_FOUND)
            //TODO
        }

        @Test
        void testEditMemberChangePrimaryKeyThrows() {
            //Create a MemberRequest from an existing member we can edit
            MemberRequest request = new MemberRequest(m1);
            //TODO
        }
/////////////////////////////////////////////////
        @Test
        void testSetRankingForUser() {

            memberService.setRankingForUser("user1", 10);
            Member m = memberRepository.findById(m1.getUsername()).get();
            assertEquals(10,m.getRanking());

            //TODO why 10?
        }

        @Test
        void testSetRankingForNoExistingUser() {
            assertThrows(ResponseStatusException.class, () -> memberService.setRankingForNoExistingUser("User doesnt exist", 10));
            {
                memberService.setRankingForNoExistingUser("user1", 10);
                //fails when writing "user3" obviously because we don't have any users with that username
                Member m = memberRepository.findById(m1.getUsername()).get();
                assertEquals(10, m.getRanking());

                //TODO
            }


            //TODO get back to that
        }

    @Test
        void testDeleteMember_ThatDontExist() {
            memberService.deleteMemberByUserName("user3");
            Member member = memberRepository.findById(m1.getUsername()).get();
            assertEquals(0, member.getUsername());

            //TODO*/
        }

    }