package Donggukthon.santa.service;

import Donggukthon.santa.domain.Decoration;
import Donggukthon.santa.domain.Submission;
import Donggukthon.santa.domain.MemberDecorationRelation;
import Donggukthon.santa.repository.DecorationRepository;
import Donggukthon.santa.repository.MemberDecorationRelationRepository;
import Donggukthon.santa.repository.MemberRepository;
import Donggukthon.santa.repository.SubmissionRepository;
import Donggukthon.santa.web.dto.response.DecorationResponseDTO;
import Donggukthon.santa.web.dto.response.TreeResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TreeService {

    private final MemberDecorationRelationRepository memberDecorationRelationRepository;
    private final SubmissionRepository submissionRepository;
    private final MemberRepository memberRepository;
    private final DecorationRepository decorationRepository;


    // 모든 MemberDecorationRelation 엔티티 가져오기
    public List<TreeResponseDTO> getAllTreeDecorations() {

        List<MemberDecorationRelation> allMemberDecorations = memberDecorationRelationRepository.findAll();

        // Member ID 별로 그룹화
        Map<Long, List<MemberDecorationRelation>> groupedDecorations = allMemberDecorations.stream()
                .collect(Collectors.groupingBy(mdr -> mdr.getMember().getId()));

        // 각 Member에 대한 TreeResponseDTO 생성
        return groupedDecorations.entrySet().stream()
                .map(entry -> createTreeResponseDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    private TreeResponseDTO createTreeResponseDTO(Long memberId, List<MemberDecorationRelation> decorations) {
        List<DecorationResponseDTO> decorationResponseDTOs = decorations.stream()
                .map(this::convertToDecorationResponseDTO)
                .collect(Collectors.toList());

        // member_id를 사용하여 해당 Submission의 card_message를 직접 찾는 로직
        String cardMessage = submissionRepository.findByMemberId(memberId)
                .map(Submission::getCardMessage)
                .orElse("No message available");

        return TreeResponseDTO.builder()
                .id(memberId)
                .card_message(cardMessage)
                .decorationResponseDTOList(decorationResponseDTOs)
                .build();
    }


    private DecorationResponseDTO convertToDecorationResponseDTO(MemberDecorationRelation memberDecorationRelation) {
        Decoration decoration = memberDecorationRelation.getDecoration();
        return DecorationResponseDTO.builder()
                .id(memberDecorationRelation.getId())
                .decorationId(decoration.getId())
                .location(decoration.getLocation())
//                .color("Some color") // Populate appropriately
                .scale(decoration.getScale())
                .createdAt(decoration.getCreatedAt().toString())
                .build();
    }
}
