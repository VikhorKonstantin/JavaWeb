package test.paragliding.service;

import by.training.paragliding.bean.entity.Competition;
import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.TransactionFactoryImpl;
import by.training.paragliding.service.CompetitionService;
import by.training.paragliding.service.ServiceFactory;
import by.training.paragliding.service.exception.ServiceException;
import by.training.paragliding.service.transaction.TransactionBasedServiceFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class CompetitionServiceTest {
    private static final int NEW_ID = 7;
    private CompetitionService service;
    private Competition competition = new Competition();

    @BeforeClass
    public void init() throws DaoException {
        try {
            ServiceFactory serviceFactory
                    = new TransactionBasedServiceFactory(
                    new TransactionFactoryImpl());
            service = serviceFactory.createCompetitionService();
            var userService = serviceFactory.createUserService();
            var u1 = userService.readById(1);
            competition.setOrganizer(u1);
            competition.setStatus(Competition.Status.ANNOUNCED);
            competition.setDiscipline("PG");
            competition.setParticipationFee(6.5F);
            competition.setName("NAME");
            competition.setDate(LocalDate.parse("2020-12-12"));
            competition.setDescription("DESCRIPTION");
            competition.setId(NEW_ID);
        } catch (DaoException | ServiceException newE) {
            newE.printStackTrace();
        }
    }

    @DataProvider(name = "readByIdProvider")
    public Object[][] createReadByIdTestData() {
        return new Object[][]{
                {-1}, {464}
        };
    }

    @Test(description = "ReadByIdNegative", dataProvider = "readByIdProvider")
    public void readByIdNegativeTest(int id) throws ServiceException {
        assertNull(service.readById(id));
    }

    @DataProvider(name = "AddingDataProvider")
    public Object[][] createAddingData() throws ServiceException, DaoException {
        competition.setName("NEW_NAME");
        return new Object[][]{
                {null, false},
                {competition, true}
        };
    }

    @Test(description = "positiveScenarioTest")
    public void readByIdPositiveScenarioTest() throws ServiceException {
        assertEquals(competition, service.readById(NEW_ID));
    }


    @Test(description = "addCompetitionTest",
            dataProvider = "AddingDataProvider")
    public void addCompetitionTest(final Competition newCompetition,
                                   final boolean expected)
            throws ServiceException {
        assertEquals(service.addCompetition(newCompetition), expected);
    }

    @Test(description = "updateTest",
            dataProvider = "AddingDataProvider")
    public void updateCompetitionTest(final Competition newCompetition,
                                      final boolean expected)
            throws ServiceException {
        assertEquals(service.update(newCompetition), expected);
    }

    @AfterClass
    public void clear() throws ServiceException {
        service.deleteCompetition(competition);
    }


}

