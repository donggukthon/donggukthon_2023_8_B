package Donggukthon.santa.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificationResponseDTO {

    private SubmissionResponseDTO submissionResponseDTO;
    private MemberResponseDTO memberResponseDTO;

}
