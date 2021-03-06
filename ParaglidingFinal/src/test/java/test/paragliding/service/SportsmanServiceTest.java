package test.paragliding.service;

import by.training.paragliding.bean.entity.Competition;
import by.training.paragliding.bean.entity.Sportsman;
import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.TransactionFactoryImpl;
import by.training.paragliding.service.ServiceFactory;
import by.training.paragliding.service.SportsmanService;
import by.training.paragliding.service.exception.ServiceException;
import by.training.paragliding.service.transaction.TransactionBasedServiceFactory;
import com.neovisionaries.i18n.CountryCode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class SportsmanServiceTest {
    private SportsmanService service;
    @BeforeClass
    public void init() throws DaoException {
        try(var fact = new TransactionFactoryImpl()) {
            ServiceFactory serviceFactory
                    = new TransactionBasedServiceFactory(fact);
            service = serviceFactory.createSportsmanService();
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


    @Test(description = "readByIdPositive")
    public void readByIdPositiveTest() throws ServiceException {
        var actual = service.readById(8388);
        var expected = new Sportsman(8388,
                "Feraric",
                "Matjaz",
                'M',
                CountryCode.valueOf("SI"),
                315.0F);
        assertEquals(actual, expected);
    }

    @Test(description = "ReadByIdNegative", dataProvider = "readByIdProvider")
    public void readByIdNegativeTest(int id) throws ServiceException{
        assertNull(service.readById(id));
    }

    @DataProvider(name = "findTestDataProvider")
    public Object[][] createFindTestData() throws ServiceException {
        return new Object[][]{
                {SportsmanService.FindByProps.ALL, List.of(new Sportsman(8321,
                                "Gorenc",
                                "Jaka",
                                'M',
                                CountryCode.valueOf("SI"),
                                315.8F),
                        new Sportsman(8388,
                                "Feraric",
                                "Matjaz",
                                'M',
                                CountryCode.valueOf("SI"),
                                315.0F),
                        new Sportsman(
                                8389,
                                "Sluga",
                                "Matjaz",
                                'M',
                                CountryCode.valueOf("SI"),
                                353.1F)), service.size(), 0},
                {SportsmanService.FindByProps.COUNTRY_CODE, List.of(new Sportsman(8321,
                                "Gorenc",
                                "Jaka",
                                'M',
                                CountryCode.valueOf("SI"),
                                315.8F),
                        new Sportsman(8388,
                                "Feraric",
                                "Matjaz",
                                'M',
                                CountryCode.valueOf("SI"),
                                315.0F), new Sportsman(
                                8389,
                                "Sluga",
                                "Matjaz",
                                'M',
                                CountryCode.valueOf("SI"),
                                353.1F)), CountryCode.SI},
                {SportsmanService.FindByProps.APPLICATION, List.of(new Sportsman(8388,
                                "Feraric",
                                "Matjaz",
                                'M',
                                CountryCode.valueOf("SI"),
                                315.0F),
                        new Sportsman(
                                8389,
                                "Sluga",
                                "Matjaz",
                                'M',
                                CountryCode.valueOf("SI"),
                                353.1F)),
                        new Competition(1,
                                "6th FAI European Paragliding"
                                        + " Accuracy Championship",
                                LocalDate.of(2019,9,19),"disc" ,
                                Competition.Status.values()[4],
                                "", 20)},
                {SportsmanService.FindByProps.RATING_RANGE, List.of(new Sportsman(8388,
                                "Feraric",
                                "Matjaz",
                                'M',
                                CountryCode.valueOf("SI"),
                                315.0F),
                        new Sportsman(8321,
                                "Gorenc",
                                "Jaka",
                                'M',
                                CountryCode.valueOf("SI"),
                                315.8F),
                        new Sportsman(
                                8389,
                                "Sluga",
                                "Matjaz",
                                'M',
                                CountryCode.valueOf("SI"),
                                353.1F)),
                        0.0F, 500.0F}
        };
    }

    @Test(description = "FindTest", dataProvider = "findTestDataProvider")
    public void findTest(SportsmanService.FindByProps property,
                         List<Sportsman> expectedList,
                         Object... val) throws ServiceException {
        var actualList = service.find(property, val);
        assertEquals(actualList, expectedList);
    }

}
