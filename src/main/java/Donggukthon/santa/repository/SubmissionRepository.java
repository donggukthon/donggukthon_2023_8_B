package Donggukthon.santa.repository;

import Donggukthon.santa.domain.Submission;
import Donggukthon.santa.web.dto.response.MemberNSubmissionResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    @Query("SELECT s FROM Submission s WHERE s.isActived = true")
    List<Submission> findAllActiveSubmissions();

    @Query("SELECT s, m FROM Submission s JOIN Member m ON s.memberId = m.id")
    List<Object[]> findActiveSubmissionsWithMembers();

    @Query("SELECT s FROM Submission s WHERE s.memberId = :id")
    Optional<Submission> findByMemberId(@Param("id") Long id);
}
