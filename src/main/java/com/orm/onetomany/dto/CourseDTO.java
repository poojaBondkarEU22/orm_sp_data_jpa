package com.orm.onetomany.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {

    private Long id;
    private String title;
    private String description;
    private Long instructorId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private InstructorDTO instructorDTO;
    private List<ReviewDTO> reviews;

}
