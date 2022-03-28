package edu.baylor.ecs.hms.service;

import edu.baylor.ecs.hms.dao.AmenityDAO;
import edu.baylor.ecs.hms.dao.HotelDAO;
import edu.baylor.ecs.hms.dto.AmenityDTO;
import edu.baylor.ecs.hms.exception.ResourceNotFoundException;
import edu.baylor.ecs.hms.model.amenity.Amenity;
import edu.baylor.ecs.hms.model.amenity.AmenityStatus;
import edu.baylor.ecs.hms.model.hotel.Hotel;
import edu.baylor.ecs.hms.repository.AmenityStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class AmenityService implements IService<AmenityDTO> {

    @Autowired
    AmenityDAO amenityDAO;

    @Autowired
    HotelDAO hotelDAO;

    @Autowired
    AmenityStatusRepository amenityStatusRepository;

    @Override
    public AmenityDTO get(Long id) {
        return amenityDAO.get(id).map(Amenity::toDTO).orElse(null);
    }

    @Override
    public Collection<AmenityDTO> getAll() {
        return amenityDAO.getAll().stream().map(Amenity::toDTO).collect(Collectors.toList());
    }

    @Override
    public AmenityDTO save(AmenityDTO amenityDTO) throws Throwable {


        return amenityDAO.save(amenityFromDTO(amenityDTO)).toDTO();
    }

    @Override
    public void update(AmenityDTO amenityDTO) throws Throwable {
        Optional<Amenity> amenityOptional = amenityDAO.get(amenityDTO.getId());
        if(amenityOptional.isPresent()) {
            AmenityStatus status = amenityStatusRepository.findByName(amenityDTO.getAmenityStatusName()).orElseThrow((Supplier<Throwable>) () -> new ResourceNotFoundException("amenitystatus", "name", amenityDTO.getAmenityStatusName()));

            Amenity amenity = amenityOptional.get();
            amenity.setName(amenityDTO.getName());
            amenity.setDescription(amenityDTO.getDescription());
            amenity.setStatus(status);

            amenityDAO.update(amenity);
        } else {
            throw new ResourceNotFoundException("amenity", "id", amenityDTO.getId());
        }
    }

    @Override
    public void delete(AmenityDTO amenityDTO) throws Throwable {
        amenityDAO.delete(amenityFromDTO(amenityDTO));
    }

    @Override
    public void deleteById(Long id) {
        amenityDAO.deleteById(id);
    }

    public void linkHotelAndAmenity(Long hotelId, Long amenityId) throws Throwable {
        Hotel hotel = hotelDAO.get(hotelId).orElseThrow((Supplier<Throwable>) () -> new ResourceNotFoundException("hotel", "id", hotelId));
        Amenity amenity = amenityDAO.get(amenityId).orElseThrow((Supplier<Throwable>) () -> new ResourceNotFoundException("amenity", "id", amenityId));

        hotel.getAmenities().add(amenity);
        amenity.getHotels().add(hotel);

        hotelDAO.update(hotel);
        amenityDAO.update(amenity);
    }

    private Amenity amenityFromDTO(AmenityDTO dto) throws Throwable {
        AmenityStatus status = amenityStatusRepository.findByName(dto.getAmenityStatusName()).orElseThrow((Supplier<Throwable>) () -> new ResourceNotFoundException("amenitystatus", "name", dto.getAmenityStatusName()));

        Amenity amenity = new Amenity();
        amenity.setStatus(status);
        amenity.setId(dto.getId());
        amenity.setName(dto.getName());
        amenity.setDescription(dto.getDescription());
        return amenity;
    }
}
