package Donggukthon.santa.service;

import Donggukthon.santa.domain.Submission;
import Donggukthon.santa.repository.SubmissionRepository;
import Donggukthon.santa.web.dto.request.SubmissionRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final SubmissionRepository submissionRepository;

    // 후원하기
    public void donate(SubmissionRequestDTO submissionRequestDTO, Long id){

        // Submission 객체 생성
        Submission submission = Submission.builder()
                .memberId(id) // 고정된 memberId
                .amount(submissionRequestDTO.getAmount())
                .cardMessage(submissionRequestDTO.getCard_message())
                .createdAt(LocalDateTime.now()) // 현재 시각 설정
                .isActived(true) // 활성화 상태로 설정
                .build();

        // 데이터베이스에 저장
        submissionRepository.save(submission);


    }

}
