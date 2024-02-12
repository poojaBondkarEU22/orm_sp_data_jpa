package com.orm.onetomany.dto.mapper;

import com.orm.onetomany.dto.ReviewDTO;
import com.orm.onetomany.entity.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    ReviewDTO toDTO (Review review);

    Review toEntity(ReviewDTO reviewDTO);
}
