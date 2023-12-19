package Donggukthon.santa.web.controller;

import Donggukthon.santa.service.DecorationService;
import Donggukthon.santa.service.MemberSevice;
import Donggukthon.santa.service.SubmissionService;
import Donggukthon.santa.web.apiResponse.ApiResponse;
import Donggukthon.santa.web.apiResponse.SuccessStatus;
import Donggukthon.santa.web.dto.response.CertificationResponseDTO;
import Donggukthon.santa.web.dto.response.MemberNSubmissionResponseDTO;
import Donggukthon.santa.web.dto.response.MemberResponseDTO;
import Donggukthon.santa.web.dto.response.SubmissionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    private MemberSevice memberSevice;


    @GetMapping
    public ApiResponse<List<MemberNSubmissionResponseDTO>> getDonatePeople(){

        // 서비스 호출
        List<MemberNSubmissionResponseDTO> memberNSubmissionResponseDTOList = memberSevice.getDonatePeopleList();

        return new ApiResponse<>(SuccessStatus.READ_PEOPLE_WHO_DONATE, memberNSubmissionResponseDTOList);
    }
}
