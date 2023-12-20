package Donggukthon.santa.service;

import Donggukthon.santa.domain.Member;
import Donggukthon.santa.domain.Submission;
import Donggukthon.santa.repository.MemberRepository;
import Donggukthon.santa.repository.SubmissionRepository;
import Donggukthon.santa.web.dto.request.JoinRequestDTO;
import Donggukthon.santa.web.dto.response.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberSevice {

    private final MemberRepository memberRepository;
    private final SubmissionRepository submissionRepository;


    // 명예의 전당 유저들이 조회 되었습니다.
    public List<MemberNSubmissionResponseDTO> getDonatePeopleList() {

        List<Object[]> results = submissionRepository.findActiveSubmissionsWithMembers();
        List<MemberNSubmissionResponseDTO> memberNSubmissionResponseDTOList = new ArrayList<>();

        for (Object[] result : results) {
            Submission submission = (Submission) result[0];
            Member member = (Member) result[1];


            SubmissionResponseDTO submissionResponseDTO = SubmissionResponseDTO.builder()
                    .member_id(submission.getMemberId())
                    .amount(submission.getAmount())
                    .card_message(submission.getCardMessage())
                    .created_at(submission.getCreatedAt())
                    .build();


            MemberResponseDTO memberResponseDTO = MemberResponseDTO.builder()
                    .email(member.getEmail())
                    .name(member.getName())
                    .nickname(member.getNickname())
                    .gender(member.getGender())
                    .phoneNumber(member.getPhoneNumber())
                    .createdAt(member.getCreatedAt())
                    .build();


            memberNSubmissionResponseDTOList.add(new MemberNSubmissionResponseDTO(submissionResponseDTO, memberResponseDTO));
        }
        return memberNSubmissionResponseDTOList;
    }

    public Optional<CertificationResponseDTO> getDonatePersonCertification(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        if (!member.isPresent()) {
            return Optional.empty(); // Member not found
        }

        Optional<Submission> submission = submissionRepository.findByMemberId(id);
        if (!submission.isPresent()) {
            return Optional.empty(); // Submission not found
        }

        SubmissionResponseDTO submissionResponseDTO = SubmissionResponseDTO.builder()
                .member_id(submission.get().getMemberId())
                .amount(submission.get().getAmount())
                .card_message(submission.get().getCardMessage())
                .created_at(submission.get().getCreatedAt())
                .build();

        MemberResponseDTO memberResponseDTO = MemberResponseDTO.builder()
                .email(member.get().getEmail())
                .name(member.get().getName())
                .nickname(member.get().getNickname())
                .gender(member.get().getGender())
                .phoneNumber(member.get().getPhoneNumber())
                .createdAt(member.get().getCreatedAt())
                .build();

        CertificationResponseDTO certificationResponseDTO = CertificationResponseDTO.builder()
                .submissionResponseDTO(submissionResponseDTO)
                .memberResponseDTO(memberResponseDTO)
                .build();

        return Optional.of(certificationResponseDTO);
    }

    public Long findByEmail(String email) {

        return memberRepository.findIdByEmail(email);
    }

    public Member signUp(JoinRequestDTO joinRequestDto){
        boolean memberExists = memberRepository.existsMemberByEmail(joinRequestDto.getEmail());

        if (memberExists) {
            throw new RuntimeException("이미 존재하는 회원입니다.");
        }else{
            Member member = Member.builder()
                    .email(joinRequestDto.getEmail())
                    .password(joinRequestDto.getPassword())
                    .name(joinRequestDto.getName())
                    .createdAt(LocalDateTime.now())
                    .build();
            memberRepository.save(member);
            return member;
        }
    }

    public MemberDTO findUserByEmail(String email){
        Member member = memberRepository.findMemberByEmail(email).orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
        MemberDTO memberDto = MemberDTO.toMemberDto(member);
        return memberDto;
    }



}
