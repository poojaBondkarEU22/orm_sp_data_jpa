package com.orm.onetomany.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_course")
@Getter
@Setter
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH,CascadeType.PERSIST})
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;



    @Override
    public String toString() {
        return super.toString();
    }


}
