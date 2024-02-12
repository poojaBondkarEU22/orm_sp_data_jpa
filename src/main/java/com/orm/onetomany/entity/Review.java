package com.orm.onetomany.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_review")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "comment")
    private String comment;

}
