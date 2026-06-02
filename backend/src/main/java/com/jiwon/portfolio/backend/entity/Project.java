package com.jiwon.portfolio.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String techStack;

    private String summary;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String githubUrl;

    private String demoUrl;

    private String imageUrl;

    private String period;

    private String role;

    /** 작을수록 목록 앞쪽 */
    private int sortOrder;

    /** 홈 화면 추천 (최대 3개) */
    private boolean featured;
}
