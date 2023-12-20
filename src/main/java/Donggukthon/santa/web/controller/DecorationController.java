package Donggukthon.santa.web.controller;

import Donggukthon.santa.config.TokenProvider;
import Donggukthon.santa.service.DecorationService;
import Donggukthon.santa.service.MemberService;
import Donggukthon.santa.web.apiResponse.ApiResponse;
import Donggukthon.santa.web.apiResponse.ErrorStatus;
import Donggukthon.santa.web.apiResponse.SuccessStatus;
import Donggukthon.santa.web.dto.request.MetaDataRequestDTO;
import Donggukthon.santa.web.dto.request.SubmissionRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/decoration")
@RestController
public class DecorationController {

    @Autowired
    private final MemberService memberService;
    @Autowired
    private final DecorationService decorationService;
    @Autowired
    private final TokenProvider tokenProvider;

    @PostMapping("/update")
    public ApiResponse<Void> updateDecoration(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody MetaDataRequestDTO metaDataRequestDTO)
    {

        String token = authorizationHeader.replace("Bearer ", "");

        Long memberId;
        String meta = metaDataRequestDTO.getMetadata();

        try {
            // 유효한 토큰에서 userEmail 추출
            String userEmail = tokenProvider.verifyToken(token);
            memberId = memberService.findByEmail(userEmail);

        } catch (Exception e) {
            return new ApiResponse<>(ErrorStatus.INVALID_TOKEN); // 에러 응답 구현
        }


        decorationService.updateMetaData(memberId, meta);

        return new ApiResponse<>(SuccessStatus.UPDATE_DECORATION);
    }
}
