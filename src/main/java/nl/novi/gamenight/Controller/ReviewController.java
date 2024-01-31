package nl.novi.gamenight.Controller;

import nl.novi.gamenight.Model.Game.Game;
import nl.novi.gamenight.Model.User.User;
import nl.novi.gamenight.Services.ReviewService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("review")
public class ReviewController {
    public final ReviewService reviewService;
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


}
