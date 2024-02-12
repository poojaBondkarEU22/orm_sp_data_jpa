package com.orm.onetomany.repository;


import com.orm.onetomany.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;



@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
