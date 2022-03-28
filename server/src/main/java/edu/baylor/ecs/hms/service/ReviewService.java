package edu.baylor.ecs.hms.service;

import edu.baylor.ecs.hms.dao.HotelDAO;
import edu.baylor.ecs.hms.dao.ReviewDAO;
import edu.baylor.ecs.hms.dto.ReviewDTO;
import edu.baylor.ecs.hms.exception.ResourceNotFoundException;
import edu.baylor.ecs.hms.model.hotel.Hotel;
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

    @Autowired
    private HotelDAO hotelDAO;

    @Override
    public ReviewDTO get(Long id) {
        return dao.get(id).map(Review::toDTO).orElse(null);
    }

    @Override
    public Collection<ReviewDTO> getAll() {
        return dao.getAll().stream().map(Review::toDTO).collect(Collectors.toList());
    }

    @Override
    public ReviewDTO save(ReviewDTO reviewDTO) throws Throwable {
        Hotel hotel = hotelDAO.get(reviewDTO.getHotelId()).orElseThrow((Supplier<Throwable>) () -> new ResourceNotFoundException("hotel", "id", reviewDTO.getHotelId()));

        Review review = new Review(reviewDTO);
        review.setHotel(hotel);
        dao.save(review);

        hotel.getReviews().add(review);
        hotelDAO.update(hotel);

        return review.toDTO();
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
