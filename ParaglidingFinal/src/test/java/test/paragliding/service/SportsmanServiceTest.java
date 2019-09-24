package test.paragliding.service;

import by.training.paragliding.bean.entity.Sportsman;
import by.training.paragliding.service.SportsmanService;
import by.training.paragliding.service.exception.ServiceException;
import com.neovisionaries.i18n.CountryCode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class SportsmanServiceTest {
    private SportsmanService service = new SportsmanService();

    @BeforeClass
    public void init() {
        SportsmanService.setDbUrl("jdbc:mysql://localhost:3306/paragliding_test_db?serverTimezone=UTC"
                + "&useSSL=false&allowPublicKeyRetrieval=true");
    }

    @DataProvider(name = "readByIdProvider")
    public Object[][] createReadByIdTestData() {
        return new Object[][] {
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
                315.0F,
                "");
        assertEquals(expected, actual);
    }

    @Test(description = "ReadByIdNegative", dataProvider = "readByIdProvider")
    public void readByIdNegativeTest(int id) throws ServiceException {
        var actual = service.readById(id);
        assertNull(actual);
    }

}
