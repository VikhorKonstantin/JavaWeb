package by.training.task1oop.service;

import by.training.task1oop.bean.entity.Plane;
import by.training.task1oop.dao.factory.RepositoryFactory;
import by.training.task1oop.dao.repository.Repository;
import by.training.task1oop.specification.FindByIdSpecification;
import by.training.task1oop.specification.FindByNameRegExSpecification;
import by.training.task1oop.specification.FindByNameSpecification;
import by.training.task1oop.specification.FindByPayloadAndSeatingCapacity;
import by.training.task1oop.specification.FindByPayloadRangeSpecification;
import by.training.task1oop.specification.Specification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class FindByService {
    /**
     * specification Map name and specification.
     */
    private final Map<String, Function<String, Specification>>
            specificationMap = new HashMap<>();

    public FindByService() {
        specificationMap.put("ID", this::findById);
        specificationMap.put("NAME_REGEX", this::findByNameRegex);
        specificationMap.put("NAME", this::findByName);
        specificationMap.put("PAYLOAD_SEATING_CAPACITY",
                this::findByPayloadAndSeatingCapacity);
        specificationMap.put("PAYLOAD_RANGE", this::findByPayloadRange);
    }

    public String findBy(final String args) {
        String property = args.substring(0, args.indexOf(' '));
        String params = args.substring(args.indexOf(' '));
        String specificationName = property.toUpperCase();
        Specification specification = specificationMap.
                get(specificationName).apply(params);
        Optional<Specification> optionalSpecification =
                Optional.ofNullable(specification);
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        Repository<Plane> repository = repositoryFactory.getPlaneRepository();
        if (optionalSpecification.isPresent()) {
            List<Plane> planes = repository.query(optionalSpecification.get());
            StringBuilder result = new StringBuilder();
            for (var plane : planes) {
                result.append(plane);
                result.append('\n');
            }
            return result.toString();
        } else {
            return "Wrong name of property to find by";
        }
    }

    private Specification findByName(final String name) {
        return new FindByNameSpecification(name);
    }

    private Specification findById(final String idString) {
       long id = Long.parseLong(idString);
       return new FindByIdSpecification(id);
    }

    private Specification findByNameRegex(final String regex) {
        return new FindByNameRegExSpecification(regex);
    }
    private Specification findByPayloadRange(final String rangeString) {
        int a = Integer.parseInt(
                rangeString.substring(0, rangeString.indexOf(' ')));
        int b = Integer.parseInt(rangeString.substring(
                rangeString.indexOf(' '), rangeString.length()));
        return new FindByPayloadRangeSpecification(a, b);
    }

    private Specification findByPayloadAndSeatingCapacity(final String params) {
        int payload = Integer.parseInt(
                params.substring(0, params.indexOf(' ')));
        int seatingCapacity = Integer.parseInt(params.substring(
                params.indexOf(' '), params.length()));
        return new FindByPayloadAndSeatingCapacity(payload, seatingCapacity);

    }


}
