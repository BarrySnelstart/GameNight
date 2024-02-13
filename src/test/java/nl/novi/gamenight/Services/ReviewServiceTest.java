package nl.novi.gamenight.Services;

import nl.novi.gamenight.Dto.reviewDto.ReviewInputDto;
import nl.novi.gamenight.Dto.reviewDto.ReviewOutputDto;
import nl.novi.gamenight.Model.Game;
import nl.novi.gamenight.Model.User;
import nl.novi.gamenight.Repository.GameRepository;
import nl.novi.gamenight.Repository.ReviewRepository;
import nl.novi.gamenight.Repository.UserRepository;
import nl.novi.gamenight.security.MyUserDetails;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReviewServiceTest {

    @MockBean
    private ReviewRepository reviewRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private GameRepository gameRepository;

    @Test
    void addReview() {

    }

    @Test
    void getReviewList() {
    }

    @Test
    void getReviewByID() {
    }

    @Test
    void updateReviewByID() {
    }

    @Test
    void deleteReviewByID() {
    }

    @Test
    void deleteUserByID() {
    }

    @Test
    void toDto() {
    }

    @Test
    void toEntity() {
    }
}