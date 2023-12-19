package Donggukthon.santa.web.dto.request;

import Donggukthon.santa.domain.Member;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {
    private String email;
    private String password;

    public static LoginRequestDTO toUserLoginDto(Member member){
        LoginRequestDTO loginRequestDto = new LoginRequestDTO();

        loginRequestDto.setEmail(member.getEmail());
        loginRequestDto.setPassword(member.getPassword());

        return loginRequestDto;
    }
}
