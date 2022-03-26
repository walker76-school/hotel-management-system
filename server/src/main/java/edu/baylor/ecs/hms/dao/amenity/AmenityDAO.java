package edu.baylor.ecs.hms.dao.amenity;

import edu.baylor.ecs.hms.dao.DAO;
import edu.baylor.ecs.hms.model.amenity.Amenity;
import edu.baylor.ecs.hms.repository.AmenityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class AmenityDAO implements DAO<Amenity> {

    @Autowired
    AmenityRepository amenityRepository;

    @Override
    public Optional<Amenity> get(Long id) {
        return amenityRepository.findById(id);
    }

    @Override
    public Collection<Amenity> getAll() {
        return amenityRepository.findAll();
    }

    @Override
    public Amenity save(Amenity amenity) {
        return amenityRepository.save(amenity);
    }

    @Override
    public void update(Amenity amenity) {
        amenityRepository.save(amenity);
    }

    @Override
    public void delete(Amenity amenity) {
        amenityRepository.delete(amenity);
    }

    @Override
    public void deleteById(Long id) {
        amenityRepository.deleteById(id);
    }
}
