package Donggukthon.santa.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Decoration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String file;
    private Integer scale;
    private String location; // 위치 기본 값
    private LocalDateTime createdAt;


    @OneToMany(mappedBy = "decoration")
    private List<MemberDecorationRelation> memberDecorationRelations;
}
