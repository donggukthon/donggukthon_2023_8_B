package Donggukthon.santa.repository;

import Donggukthon.santa.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m.id FROM Member m WHERE m.email = :email")
    Long findIdByEmail(@Param("email") String email);

    Optional<Member> findMemberByEmail(String email);
    Boolean existsMemberByEmail(String email);
}
