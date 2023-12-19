package Donggukthon.santa.web.dto.response;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DecorationResponseDTO {

    private Long id;
    private Long decorationId;
    private String location;
//    private String color;
    private int scale;
    private int degree;
    private String floor;
    private String createdAt;
}
