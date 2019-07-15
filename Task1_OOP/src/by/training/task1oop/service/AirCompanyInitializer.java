package by.training.task1oop.service;

import by.training.task1oop.bean.entity.Plane;
import by.training.task1oop.dao.repository.Repository;
import by.training.task1oop.exception.WrongArgumentsException;


import java.util.List;

final class AirCompanyInitializer {

    /**
     * @param planesParams List of params of plane.
     * @param repository repository to init.
     * @throws WrongArgumentsException if request invalid
     */
    void initAirCompany(final List<String> planesParams,
                              final Repository<Plane> repository)
            throws WrongArgumentsException {
        AddService addService = new AddService();
        for (var paramString : planesParams) {
            addService.addPlane(paramString, repository);
        }
    }
}
