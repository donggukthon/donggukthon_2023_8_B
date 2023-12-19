package Donggukthon.santa.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeResponseDTO {

    private Long id;
    private String card_message;

    private List<DecorationResponseDTO> decorationResponseDTOList;

}
