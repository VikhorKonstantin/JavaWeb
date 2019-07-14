package by.training.task1oop.service;

import by.training.task1oop.bean.entity.Plane;
import by.training.task1oop.dao.factory.RepositoryFactory;
import by.training.task1oop.dao.repository.Repository;
import by.training.task1oop.specification.*;

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
    static {
        SPECIFICATION_MAP.put("NAME", new SortByNameSpecification());
        SPECIFICATION_MAP.put("NAME_ID", new SortByNameAndIdSpecification());
        SPECIFICATION_MAP.put("FUEL_CONSUMPTION_REVERSED",
                new SortByFuelConsumptionReversed());

    }

    public String sortBy(String property) {
        String specificationName = property.toUpperCase();
        Specification specification = SPECIFICATION_MAP.get(specificationName);
        Optional<Specification> optionalSpecification =
                Optional.ofNullable(specification);
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        Repository<Plane> repository = repositoryFactory.getPlaneRepository();
        if (optionalSpecification.isPresent()){
            List<Plane> planes = repository.query(optionalSpecification.get());
            StringBuilder result = new StringBuilder();
            for (var plane : planes) {
                result.append(plane);
                result.append('\n');
            }
            return result.toString();
        } else {
            return "Wrong name of property to sort by";
        }
    }
}
