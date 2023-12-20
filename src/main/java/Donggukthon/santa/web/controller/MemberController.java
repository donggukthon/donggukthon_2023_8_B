package Donggukthon.santa.web.controller;

import Donggukthon.santa.config.TokenProvider;
import Donggukthon.santa.domain.Member;
import Donggukthon.santa.service.MemberService;
import Donggukthon.santa.service.SubmissionService;
import Donggukthon.santa.web.apiResponse.ApiResponse;
import Donggukthon.santa.web.apiResponse.ErrorStatus;
import Donggukthon.santa.web.apiResponse.SuccessStatus;
import Donggukthon.santa.web.dto.response.MemberDTO;
import Donggukthon.santa.web.dto.request.JoinRequestDTO;
import Donggukthon.santa.web.dto.request.LoginRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberController {

    private final MemberService memberService;
    private final TokenProvider tokenProvider;
    private final SubmissionService submissionService;


    @PostMapping("/join")
    public ResponseEntity<ApiResponse> joinUser(@RequestBody JoinRequestDTO joinRequestDto){

        Member member = memberService.signUp(joinRequestDto);
        if(member != null){
            ApiResponse response = new ApiResponse<>(SuccessStatus.JOIN_USER, member);
            return ResponseEntity.ok(response);
        }else{
            ApiResponse response = new ApiResponse(ErrorStatus.JOIN_USER);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> loginUser(@RequestBody LoginRequestDTO loginRequestDto){

        MemberDTO memberDto = memberService.findUserByEmail(loginRequestDto.getEmail());

        if(memberDto == null){
            ApiResponse response = new ApiResponse<>(ErrorStatus.CANNOT_FIND_EMAIL);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        if(memberDto.getPassword().equals(loginRequestDto.getPassword())){
            String generatedToken = tokenProvider.generateToken(loginRequestDto.getEmail());

            ApiResponse<String> response = new ApiResponse<>(SuccessStatus.LOGIN_USER, generatedToken);
            return ResponseEntity.ok(response);
        }else{
            ApiResponse<String> response = new ApiResponse<>(ErrorStatus.LOGIN_USER);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

    }

    @GetMapping("/info")
    public ApiResponse<MemberDTO> Userinfo(@RequestHeader("Authorization") String authorizationHeader){

        String token = authorizationHeader.replace("Bearer ", "");

        try {
            String userEmail = tokenProvider.verifyToken(token);
            MemberDTO memberDTO = memberService.findUserByEmail(userEmail);

            return new ApiResponse<>(SuccessStatus.USER_INFO, memberDTO);
        } catch (Exception e) {
            return new ApiResponse<>(ErrorStatus.USER_INFO);
        }
    }

}

