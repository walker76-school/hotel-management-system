package edu.baylor.ecs.hms.dao;

import edu.baylor.ecs.hms.dao.DAO;
import edu.baylor.ecs.hms.model.review.Review;
import edu.baylor.ecs.hms.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class ReviewDAO implements DAO<Review> {
    
    @Autowired
    private ReviewRepository repository;
    
    @Override
    public Optional<Review> get(Long id) {
        return repository.findById(id);
    }

    @Override
    public Collection<Review> getAll() {
        return repository.findAll();
    }

    @Override
    public Review save(Review review) {
        return repository.save(review);
    }

    @Override
    public void update(Review review) {
        repository.save(review);
    }

    @Override
    public void delete(Review review) {
        repository.delete(review);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
