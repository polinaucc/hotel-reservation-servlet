package ua.polina.model.service;

import ua.polina.model.dao.DaoFactory;
import ua.polina.model.dao.DescriptionDao;
import ua.polina.model.dto.DescriptionDto;
import ua.polina.model.dto.RequestDto;
import ua.polina.model.entity.Description;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DescriptionService {
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public void saveNewDescription(DescriptionDto descriptionDto){
        try(DescriptionDao descriptionDao = daoFactory.createDescriptionDao()) {
            Description description = formDescription(descriptionDto);

            descriptionDao.create(description);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Description formDescription(DescriptionDto descriptionDto) {
        Description description = new Description();
        description.setRoomType(descriptionDto.getRoomType());
        description.setCountOfPersons(descriptionDto.getCountOfPersons());
        description.setCountOfBeds(descriptionDto.getCountOfBeds());
        description.setCostPerNight(descriptionDto.getCostPerNight());
        return description;
    }

    public Optional<Description> getDescriptionByParameters(RequestDto requestDto){
        try(DescriptionDao descriptionDao = daoFactory.createDescriptionDao()) {
            return descriptionDao.findDescriptionByRoomTypeAndCountOfPersonsAndCountOfBeds(
                    requestDto.getRoomType(),
                    requestDto.getCountOfPerson(),
                    requestDto.getCountOfBeds()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Description> getAllDescriptions(){
        try(DescriptionDao descriptionDao = daoFactory.createDescriptionDao()) {
            return descriptionDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Optional<Description> getDescriptionById(Long id){
        try(DescriptionDao descriptionDao = daoFactory.createDescriptionDao()) {
            return Optional.of(descriptionDao.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
