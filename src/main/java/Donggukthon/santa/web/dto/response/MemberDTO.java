package Donggukthon.santa.web.dto.response;

import Donggukthon.santa.domain.DesignatedPerson;
import Donggukthon.santa.domain.Member;
import Donggukthon.santa.domain.Submission;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private Long id;
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String gender;
    private String phone_number;
    private LocalDateTime created_at;

    private List<Submission> submissions = new ArrayList<>();
    private List<DesignatedPerson> designatedpersons = new ArrayList<>();

    public static MemberDTO toMemberDto(Member member){
        MemberDTO memberDto = new MemberDTO();

        memberDto.setId(member.getId());
        memberDto.setEmail(member.getEmail());
        memberDto.setPassword(member.getPassword());
        memberDto.setName(member.getName());
        memberDto.setGender(member.getGender());
        memberDto.setPhone_number(member.getPhoneNumber());
        memberDto.setCreated_at(member.getCreatedAt());

        return memberDto;
    }
}
