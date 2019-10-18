package by.training.paragliding.bean.builder;

import by.training.paragliding.bean.entity.Result;
import by.training.paragliding.bean.exception.BeanException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultBuilder implements Builder<Result> {
    /**
     * @param newResultSet resultSet (result of executing sql statements)
     * @return new T object.
     * @throws BeanException if some exceptions where
     *                       thrown while object building.
     */
    @Override
    public Result buildFromResultSet(final ResultSet newResultSet)
            throws BeanException {
        try {
            final var result = new Result();
            var competitionId = newResultSet.getInt("competitionId");
            var civlId = newResultSet.getInt("civl_id");
            var score = newResultSet.getInt("score");
            result.setCompetitionId(competitionId);
            result.setSportsmanId(civlId);
            result.setScore(score);
            return result;
        } catch (SQLException newE) {
            throw new BeanException(EXC_MSG, newE);
        }
    }
}
