package edu.baylor.ecs.hms.service;

import edu.baylor.ecs.hms.dao.AmenityDAO;
import edu.baylor.ecs.hms.dto.AmenityDTO;
import edu.baylor.ecs.hms.model.amenity.Amenity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class AmenityService implements IService<AmenityDTO> {

    @Autowired
    AmenityDAO amenityDAO;

    @Override
    public AmenityDTO get(Long id) {
        return amenityDAO.get(id).map(Amenity::toDTO).orElse(null);
    }

    @Override
    public Collection<AmenityDTO> getAll() {
        return amenityDAO.getAll().stream().map(Amenity::toDTO).collect(Collectors.toList());
    }

    @Override
    public AmenityDTO save(AmenityDTO amenityDTO) {
        return amenityDAO.save(new Amenity(amenityDTO)).toDTO();
    }

    @Override
    public void update(AmenityDTO amenityDTO) {
        amenityDAO.save(new Amenity(amenityDTO));
    }

    @Override
    public void delete(AmenityDTO amenityDTO) {
        amenityDAO.delete(new Amenity(amenityDTO));
    }

    @Override
    public void deleteById(Long id) {
        amenityDAO.deleteById(id);
    }
}
