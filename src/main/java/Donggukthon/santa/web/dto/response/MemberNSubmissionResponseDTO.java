package Donggukthon.santa.web.dto.response;

import Donggukthon.santa.domain.Member;
import Donggukthon.santa.domain.Submission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberNSubmissionResponseDTO {

    private SubmissionResponseDTO submissionResponseDTO;
    private MemberResponseDTO memberResponseDTO;
}
