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
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long memberId;
    private int amount;
    private String cardMessage;
    private LocalDateTime createdAt;
    private boolean isActived;

    @ManyToOne
    @JoinColumn(name = "memberId", insertable = false, updatable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "decorationId", insertable = false, updatable = false)
    private Decoration decoration;
}
