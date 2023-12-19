package Donggukthon.santa.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionResponseDTO {

    private Long member_id;
    private Integer amount;
    @Nullable
    private String card_message;
    private LocalDateTime created_at;
}
