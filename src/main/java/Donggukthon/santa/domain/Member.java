package Donggukthon.santa.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String gender;
    private String phoneNumber;
    private LocalDateTime createdAt;

    private String meta;

    @OneToMany(mappedBy = "member")
    private List<Submission> submissions;

    @OneToMany(mappedBy = "member")
    private List<DesignatedPerson> designatedPersons;

    @OneToMany(mappedBy = "member")
    private List<MemberDecorationRelation> memberDecorationRelations;
}
