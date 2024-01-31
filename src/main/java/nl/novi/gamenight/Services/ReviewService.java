package nl.novi.gamenight.Services;

import nl.novi.gamenight.Repository.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    ReviewRepository reviewRepository;
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }



}
