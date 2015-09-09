package by.epam.project.dao.query;

import java.util.LinkedList;
import java.util.List;

/**
 * Class of list of query parameters.
 * @author Helena.Grouk
 */
public class Params {

    private static final String DATA_OR_MAPPER_IS_NULL_ERROR = "Parameters " +
        "list or mapper should not be null";

    private List<Object[]> paramsList = new LinkedList<>();

    private Params() {}

    public List<Object[]> getParamsList() {
        return paramsList;
    }

    public static <T> Params fill(List<T> beans, Mapper<T> mapper) {
        if (beans == null || mapper == null)
            throw new IllegalArgumentException(DATA_OR_MAPPER_IS_NULL_ERROR);
        Params params = new Params();
        beans.stream().forEach((bean) -> {
            params.paramsList.add(mapper.map(bean));
        });
        return params;
    }

    /**
     * Functional interface of processing entity in query
     * @param <T> type of entity
     */
    public static interface Mapper<T> {
        Object[] map(T bean);
    }


}
