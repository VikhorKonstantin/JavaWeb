package by.training.task1oop.service;

import by.training.task1oop.bean.entity.Plane;
import by.training.task1oop.dao.factory.RepositoryFactory;
import by.training.task1oop.dao.repository.Repository;
import by.training.task1oop.service.exception.ServiceException;
import by.training.task1oop.dao.repository.specification.SortByFuelConsumptionReversed;
import by.training.task1oop.dao.repository.specification.SortByNameAndIdSpecification;
import by.training.task1oop.dao.repository.specification.SortByNameSpecification;
import by.training.task1oop.dao.repository.specification.SortSpecification;
import by.training.task1oop.dao.repository.specification.Specification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SortByService {
    /**
     * specification Map name and specification.
     */
    private static final Map<String, SortSpecification<Plane>>
            SPECIFICATION_MAP = new HashMap<>();
    /**
     * negative scenario message.
     */
    private static final String WRONG_PROPERTY_NAME =
            "Wrong name of property to sort by";
    static {
        SPECIFICATION_MAP.put("NAME", new SortByNameSpecification());
        SPECIFICATION_MAP.put("NAME_ID", new SortByNameAndIdSpecification());
        SPECIFICATION_MAP.put("FUEL_CONSUMPTION_REVERSED",
                new SortByFuelConsumptionReversed());
    }

    /**
     * @param property to sort by.
     * @return response.
     * @throws ServiceException if request invalid
     */
    public List<Plane> sortBy(final String property) throws ServiceException {
        Optional<String> oProperty = Optional.ofNullable(property);
        String specificationName = oProperty.orElseThrow(
                () -> new ServiceException(WRONG_PROPERTY_NAME)).
                toUpperCase();
        Specification specification = SPECIFICATION_MAP.get(specificationName);
        Optional<Specification> optionalSpecification =
                Optional.ofNullable(specification);
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        Repository<Plane> repository = repositoryFactory.getPlaneRepository();
        return repository.query(optionalSpecification.orElseThrow(
                () -> new ServiceException(WRONG_PROPERTY_NAME)));
    }
}
