package by.epam.project.dao.query;

import java.sql.ResultSet;
import java.sql.SQLException;
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

    private Params() {
        super();
    }

    public List<Object[]> params() {
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

    public static interface Mapper<T> {
        Object[] map(T bean);
    }

    public static interface RowMapper<T> {
        T mapRow(ResultSet rs, int rowNum) throws SQLException;
    }

    public static interface QueryMapper {
        String mapQuery();
    }
}
