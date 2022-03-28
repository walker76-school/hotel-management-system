package edu.baylor.ecs.hms.service;

import edu.baylor.ecs.hms.dao.ReviewDAO;
import edu.baylor.ecs.hms.dto.ReviewDTO;
import edu.baylor.ecs.hms.exception.ResourceNotFoundException;
import edu.baylor.ecs.hms.model.reservation.Reservation;
import edu.baylor.ecs.hms.model.review.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class ReviewService implements IService<ReviewDTO> {

    @Autowired
    private ReviewDAO dao;

    @Override
    public ReviewDTO get(Long id) {
        return dao.get(id).map(Review::toDTO).orElse(null);
    }

    @Override
    public Collection<ReviewDTO> getAll() {
        return dao.getAll().stream().map(Review::toDTO).collect(Collectors.toList());
    }

    @Override
    public ReviewDTO save(ReviewDTO reviewDTO) {
        return dao.save(new Review(reviewDTO)).toDTO();
    }

    @Override
    public void update(ReviewDTO reviewDTO) throws Throwable {
        Review review = dao.get(reviewDTO.getId()).orElseThrow((Supplier<Throwable>) () -> new ResourceNotFoundException("review", "id", reviewDTO.getId()));

        review.setStars(reviewDTO.getStars());
        review.setDescription(reviewDTO.getDescription());

        dao.update(review);
    }

    @Override
    public void delete(ReviewDTO reviewDTO) {
        dao.delete(new Review(reviewDTO));
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }
}
