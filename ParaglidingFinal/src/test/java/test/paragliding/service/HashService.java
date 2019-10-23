package test.paragliding.service;

import by.training.paragliding.service.UserService;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.AssertJUnit.assertNull;

public class HashService {
    @Test
    public void hashTets() {
        var argon = UserService.Argon2Hasher.getInstance();
        var hashedList = List.of(argon.hashPassword("VikhorVikhor"),
                argon.hashPassword("IvanovIvanov"),
                argon.hashPassword("KokkotouKokkotou"),
                argon.hashPassword("AllevaAlleva"),
                argon.hashPassword("ManeseManese"),
                argon.hashPassword("ReilingReiling"),
                argon.hashPassword("HearstHearst"),
                argon.hashPassword("StephanianStephanian"),
                argon.hashPassword("PupkinPupkin"));
        assertNull(hashedList);
    }
}
