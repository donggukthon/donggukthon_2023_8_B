package Donggukthon.santa.web.dto.request;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JoinRequestDTO {
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String gender;
    private String phoneNumber;
}
