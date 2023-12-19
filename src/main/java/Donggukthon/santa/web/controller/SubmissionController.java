package Donggukthon.santa.web.controller;

import Donggukthon.santa.config.TokenProvider;
import Donggukthon.santa.service.MemberSevice;
import Donggukthon.santa.service.SubmissionService;
import Donggukthon.santa.web.apiResponse.ApiResponse;
import Donggukthon.santa.web.apiResponse.ErrorStatus;
import Donggukthon.santa.web.apiResponse.SuccessStatus;
import Donggukthon.santa.web.dto.request.SubmissionRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/submission")
@RestController
public class SubmissionController {

    @Autowired
    private final SubmissionService submissionService;
    @Autowired
    private final MemberSevice memberSevice;
    @Autowired
    private final TokenProvider tokenProvider;

    // submission을 제출하는 후원 단계
    @PostMapping("")
    public ApiResponse<Void> submitDonation(
            @RequestHeader("Authorization") String accessToken,
            @RequestBody SubmissionRequestDTO submissionRequestDTO)
    {
        if (!tokenProvider.validateToken(accessToken)) {
            // 토큰이 유효하지 않으면 적절한 응답 반환
            return new ApiResponse<>(ErrorStatus.INVALID_TOKEN); // 에러 응답 구현
        }

        // 유효한 토큰에서 userEmail 추출
        String userEmail = tokenProvider.verifyToken(accessToken);

        Long memberId = memberSevice.findByEmail(userEmail);

        submissionService.donate(submissionRequestDTO, memberId);
        return new ApiResponse<>(SuccessStatus.SUBMIT_DONATE);
    }

}
