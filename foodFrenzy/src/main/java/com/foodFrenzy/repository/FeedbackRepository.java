package com.foodFrenzy.repository;
import com.foodFrenzy.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
  
}
