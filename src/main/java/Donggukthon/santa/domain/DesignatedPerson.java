package Donggukthon.santa.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DesignatedPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long submissionId;
    private Long memberId;
    private String sendMessage;
    private String sendEmail;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "memberId", insertable = false, updatable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "submissionId", insertable = false, updatable = false)
    private Submission submission;

}
