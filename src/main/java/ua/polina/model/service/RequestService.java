package ua.polina.model.service;

import ua.polina.model.dao.DaoFactory;
import ua.polina.model.dao.RequestDao;
import ua.polina.model.dto.RequestDto;
import ua.polina.model.entity.Client;
import ua.polina.model.entity.Description;
import ua.polina.model.entity.Request;
import ua.polina.model.entity.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RequestService {
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public void saveNewRequest(RequestDto requestDto, Client client, Description description){
        try(RequestDao requestDao = daoFactory.createRequestDao()) {
            Request request = new Request();
            request.setClient(client);
            request.setDescription(description);
            request.setCheckInDate(requestDto.getCheckInDate());
            request.setCheckOutDate(requestDto.getCheckOutDate());
            request.setStatus(Status.New_request);
            requestDao.create(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Request> getAllRequests(){
        try(RequestDao requestDao = daoFactory.createRequestDao()) {
           return requestDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Request> getAllRequestsPagination(Integer offset, Integer limit){
        try(RequestDao requestDao = daoFactory.createRequestDao()){
            return requestDao.findAll(offset, limit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Optional<Request> getRequestById(Long id){
        try(RequestDao requestDao = daoFactory.createRequestDao()) {
            return Optional.of(requestDao.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void update(Request request, Status status){
        try(RequestDao requestDao = daoFactory.createRequestDao()) {
            request.setStatus(status);
            requestDao.update(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Request> getRequestsByClient(Client client){
        try(RequestDao requestDao = daoFactory.createRequestDao()) {
            return requestDao.findByClient(client.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Integer numberOfEntries() {
        try (RequestDao requestDao = daoFactory.createRequestDao()) {
            return requestDao.countAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
