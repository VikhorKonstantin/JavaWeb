package by.training.task1oop.service;

import by.training.task1oop.bean.entity.Plane;
import by.training.task1oop.dao.factory.RepositoryFactory;
import by.training.task1oop.dao.repository.Repository;
import by.training.task1oop.exception.WrongArgumentsException;
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
    /**
     * wrong property name message.
     */
    private static final String WRONG_PROPERTY_NAME =
            "Wrong name of property to find by";
    /**
     * index of first element.
     */
    private static final int START_INDEX = 0;
    /**
     * params delimiter char.
     */
    private static final char DELIMITER = ' ';
    /**
     * init specification map.
     */
    public FindByService() {
        specificationMap.put("ID", this::findById);
        specificationMap.put("NAME_REGEX", this::findByNameRegex);
        specificationMap.put("NAME", this::findByName);
        specificationMap.put("PAYLOAD_SEATING_CAPACITY",
                this::findByPayloadAndSeatingCapacity);
        specificationMap.put("PAYLOAD_RANGE", this::findByPayloadRange);
    }

    /**
     * @param args arguments of request.
     * @return response.
     * @throws WrongArgumentsException if request invalid
     */
    public String findBy(final String args) throws WrongArgumentsException {
        Optional<String> optionalArgs = Optional.ofNullable(args);
        String safeArgs = optionalArgs.orElseThrow(
                () -> new WrongArgumentsException(WRONG_PROPERTY_NAME));
        String property = safeArgs.
                substring(START_INDEX, safeArgs.indexOf(DELIMITER));
        String params = safeArgs.substring(safeArgs.indexOf(DELIMITER));
        String specificationName = property.toUpperCase();
        Specification specification = specificationMap.
                get(specificationName).apply(params);
        Optional<Specification> optionalSpecification =
                Optional.ofNullable(specification);
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        Repository<Plane> repository = repositoryFactory.getPlaneRepository();
        List<Plane> planes = repository.query(
                        optionalSpecification.
                        orElseThrow(() -> new WrongArgumentsException(
                                WRONG_PROPERTY_NAME)));
        StringBuilder result = new StringBuilder();
        for (var plane : planes) {
            result.append(plane);
            result.append('\n');
        }
        return result.toString();
    }

    /**
     * @param name name to find by.
     * @return FindByNameSpecification
     */
    private Specification findByName(final String name) {
        return new FindByNameSpecification(name);
    }

    /**
     * @param idString planeId to find by.
     * @return FindByIdSpecification
     */
    private Specification findById(final String idString) {
       long id = Long.parseLong(idString);
       return new FindByIdSpecification(id);
    }

    /**
     * @param regex to find by.
     * @return FindByNameRegExSpecification
     */
    private Specification findByNameRegex(final String regex) {
        return new FindByNameRegExSpecification(regex);
    }

    /**
     * @param rangeString payload range to find by.
     * @return FindByPayloadRangeSpecification
     */
    private Specification findByPayloadRange(final String rangeString) {
        int a = Integer.parseInt(
                rangeString.substring(START_INDEX,
                        rangeString.indexOf(DELIMITER)));
        int b = Integer.parseInt(rangeString.substring(
                rangeString.indexOf(DELIMITER)));
        return new FindByPayloadRangeSpecification(a, b);
    }

    /**
     * @param params to find by.
     * @return FindByPayloadAndSeatingCapacity specification
     */
    private Specification findByPayloadAndSeatingCapacity(final String params) {
        int payload = Integer.parseInt(
                params.substring(START_INDEX, params.indexOf(DELIMITER)));
        int seatingCapacity = Integer.parseInt(params.substring(
                params.indexOf(DELIMITER)));
        return new FindByPayloadAndSeatingCapacity(payload, seatingCapacity);
    }
}
