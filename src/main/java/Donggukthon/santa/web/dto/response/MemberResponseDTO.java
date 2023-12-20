package Donggukthon.santa.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class MemberResponseDTO {

    private String email;
    private String name;
    private String nickname;
    private String gender;
    private String phone_number;
    private LocalDateTime created_at;
}
