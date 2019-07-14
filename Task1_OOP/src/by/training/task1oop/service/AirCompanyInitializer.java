package by.training.task1oop.service;

import by.training.task1oop.bean.entity.Plane;
import by.training.task1oop.dao.repository.Repository;


import java.util.List;

public class AirCompanyInitializer {

    /**
     * @param planesParams list parameters necessary for creating planes.
     * @return AirCompany
     */
    public void initAirCompany(final List<String> planesParams, Repository<Plane> repository) {
        AddService addService = new AddService();
        for (var paramString : planesParams) {
            addService.addPlane(paramString, repository);
        }
    }
}
