package Donggukthon.santa.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDecorationRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime createdAt;
    private String location; // 사용자 설정 위치
    private Integer scale; // 사용자 설정 크기

    @ManyToOne
    @JoinColumn(name = "memberId", insertable = false, updatable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "decorationId", insertable = false, updatable = false)
    private Decoration decoration;

}
