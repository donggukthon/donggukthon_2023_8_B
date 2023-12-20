package Donggukthon.santa.service;

import Donggukthon.santa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DecorationService {

    private final MemberRepository memberRepository;


    @Transactional
    public void updateMetaData(Long memberId, String meta) {

        memberRepository.updateMetaById(memberId, meta);
    }
}
