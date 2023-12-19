package Donggukthon.santa.web.controller;

import Donggukthon.santa.config.TokenProvider;
import Donggukthon.santa.service.MemberSevice;
import Donggukthon.santa.web.apiResponse.ApiResponse;
import Donggukthon.santa.web.apiResponse.ErrorStatus;
import Donggukthon.santa.web.apiResponse.SuccessStatus;
import Donggukthon.santa.web.dto.request.SubmissionRequestDTO;
import Donggukthon.santa.web.dto.response.CertificationResponseDTO;
import Donggukthon.santa.web.dto.response.MemberDTO;
import Donggukthon.santa.web.dto.response.MemberResponseDTO;
import Donggukthon.santa.web.dto.response.SubmissionResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.aspectj.runtime.internal.Conversions.longValue;

@RequiredArgsConstructor
@RequestMapping("/certification")
@RestController
public class CertificationController {

    @Autowired
    private MemberSevice memberSevice;
    @Autowired
    private final TokenProvider tokenProvider;

    // 버튼 클릭 시 증명서 보이기
    @GetMapping
    public ApiResponse<CertificationResponseDTO> getCertification(@RequestHeader("Authorization") String authorizationHeader)
    {
        String token = authorizationHeader.replace("Bearer ", "");

        Long memberId;

        try {
            // 유효한 토큰에서 userEmail 추출
            String userEmail = tokenProvider.verifyToken(token);
            memberId = memberSevice.findByEmail(userEmail);

        } catch (Exception e) {
            return new ApiResponse<>(ErrorStatus.INVALID_TOKEN); // 에러 응답 구현
        }

        try {
            CertificationResponseDTO certificationResponseDTO = memberSevice.getDonatePersonCertification(memberId)
                    .orElseThrow(RuntimeException::new);

            return new ApiResponse<>(SuccessStatus.READ_CERTIFICATION, certificationResponseDTO);
        } catch (RuntimeException e) {
            return new ApiResponse<>(ErrorStatus.NON_DONATE);
        }
    }
}
