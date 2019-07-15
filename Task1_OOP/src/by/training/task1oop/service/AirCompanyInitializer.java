package by.training.task1oop.service;

import by.training.task1oop.bean.entity.Plane;
import by.training.task1oop.dao.repository.Repository;


import java.util.List;

class AirCompanyInitializer {

    /**
     * @param planesParams List of params of plane.
     * @param repository repository to init.
     */
    void initAirCompany(final List<String> planesParams,
                              final Repository<Plane> repository) {
        AddService addService = new AddService();
        for (var paramString : planesParams) {
            addService.addPlane(paramString, repository);
        }
    }
}
