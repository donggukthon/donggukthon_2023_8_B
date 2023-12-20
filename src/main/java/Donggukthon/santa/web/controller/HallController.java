package Donggukthon.santa.web.controller;

import Donggukthon.santa.service.DecorationService;
import Donggukthon.santa.service.MemberService;
import Donggukthon.santa.service.SubmissionService;
import Donggukthon.santa.web.apiResponse.ApiResponse;
import Donggukthon.santa.web.apiResponse.SuccessStatus;
import Donggukthon.santa.web.dto.response.MemberNSubmissionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/hall")
@RestController
public class HallController {

    // service
    @Autowired
    private DecorationService decorationService;
    @Autowired
    private SubmissionService submissionService;
    @Autowired
    private MemberService memberService;


    @GetMapping
    public ApiResponse<List<MemberNSubmissionResponseDTO>> getDonatePeople(){

        // 서비스 호출
        List<MemberNSubmissionResponseDTO> memberNSubmissionResponseDTOList = memberService.getDonatePeopleList();

        return new ApiResponse<>(SuccessStatus.READ_PEOPLE_WHO_DONATE, memberNSubmissionResponseDTOList);
    }
}
