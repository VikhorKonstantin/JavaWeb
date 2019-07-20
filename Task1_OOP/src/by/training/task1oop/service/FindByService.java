package by.training.task1oop.service;

import by.training.task1oop.bean.entity.Plane;
import by.training.task1oop.dao.factory.RepositoryFactory;
import by.training.task1oop.dao.repository.Repository;
import by.training.task1oop.service.exception.ServiceException;
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

public class FindByService {
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
     * specification Map name and specification.
     */
    private final Map<String, ThrowingFunction<String, Specification,
            ServiceException>>
            specificationMap = new HashMap<>();
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
     * @throws ServiceException if request invalid
     */
    public List<Plane> findBy(final String args) throws ServiceException {
        Optional<String> optionalArgs = Optional.ofNullable(args);
        String safeArgs = optionalArgs.orElseThrow(
                () -> new ServiceException(WRONG_PROPERTY_NAME));
        try {
            String property = safeArgs.
                    substring(START_INDEX, safeArgs.indexOf(DELIMITER));

            String params = safeArgs.substring(
                    safeArgs.indexOf(DELIMITER)).trim();
            String specificationName = property.toUpperCase();
            Specification specification = specificationMap.
                    get(specificationName).apply(params);
            Optional<Specification> optionalSpecification =
                    Optional.ofNullable(specification);
            RepositoryFactory repositoryFactory =
                    RepositoryFactory.getInstance();
            Repository<Plane> repository =
                    repositoryFactory.getPlaneRepository();
            return repository.query(
                    optionalSpecification.orElseThrow(
                            () -> new ServiceException(WRONG_PROPERTY_NAME)));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ServiceException(WRONG_PROPERTY_NAME);
        }
    }

    /**
     * @param name name to find by.
     * @return FindByNameSpecification
     * @throws ServiceException if method args wrong
     */
    private Specification findByName(final String name)
            throws ServiceException {
        var safeName = Optional.ofNullable(name).orElseThrow(
                () -> new ServiceException(WRONG_PROPERTY_NAME)
        );
        return new FindByNameSpecification(safeName);
    }

    /**
     * @param idString planeId to find by.
     * @return FindByIdSpecification
     * @throws ServiceException if method args wrong
     */
    private Specification findById(final String idString)
            throws ServiceException {
        try {
            long id = Long.parseLong(idString);
            return new FindByIdSpecification(id);
        } catch (NumberFormatException e) {
            throw new ServiceException(WRONG_PROPERTY_NAME, e);
        }
    }

    /**
     * @param regex to find by.
     * @return FindByNameRegExSpecification
     * @throws ServiceException if method args wrong
     */
    private Specification findByNameRegex(final String regex)
            throws ServiceException {
        var safeRegex = Optional.ofNullable(regex).orElseThrow(
                () -> new ServiceException(WRONG_PROPERTY_NAME)
        );
        return new FindByNameRegExSpecification(safeRegex);
    }

    /**
     * @param rangeString payload range to find by.
     * @return FindByPayloadRangeSpecification
     * @throws ServiceException if method args wrong
     */
    private Specification findByPayloadRange(final String rangeString)
            throws ServiceException {
        try {
            int a = Integer.parseInt(
                    rangeString.substring(START_INDEX,
                            rangeString.indexOf(DELIMITER)));
            int b = Integer.parseInt(rangeString.substring(
                    rangeString.indexOf(DELIMITER)).trim());
            return new FindByPayloadRangeSpecification(a, b);
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new ServiceException(WRONG_PROPERTY_NAME, e);
        }

    }

    /**
     * @param params to find by.
     * @return FindByPayloadAndSeatingCapacity specification
     * @throws ServiceException if method args wrong
     */
    private Specification findByPayloadAndSeatingCapacity(final String params)
            throws ServiceException {
        try {
            int payload = Integer.parseInt(
                    params.substring(START_INDEX, params.indexOf(DELIMITER)));
            int seatingCapacity = Integer.parseInt(params.substring(
                    params.indexOf(DELIMITER)).trim());
            return new FindByPayloadAndSeatingCapacity(payload,
                    seatingCapacity);
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new ServiceException(WRONG_PROPERTY_NAME, e);
        }

    }

    /**
     * @param <T> param type.
     * @param <R> return type.
     * @param <E> exception type.
     */
    @FunctionalInterface
    private interface ThrowingFunction<T, R, E extends Exception> {
        R apply(T t) throws E;
    }
}
