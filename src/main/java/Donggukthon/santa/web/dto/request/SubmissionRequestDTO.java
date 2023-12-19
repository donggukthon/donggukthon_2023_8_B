package Donggukthon.santa.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmissionRequestDTO {

    private Integer amount;
    private String card_message;

    @Nullable
    private String send_message;
    @Nullable
    private String send_email;
}
