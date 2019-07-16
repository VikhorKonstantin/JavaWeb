package test.task1oop.bean.entity;

import by.training.task1oop.bean.entity.TransportPlane;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class TransportPlaneTest {
    @Test(description = "createPlaneTestPositive")
    public void createPlaneTestPositive() {
        TransportPlane expected = new TransportPlane(15151515, 50,
                500, 500, "Boeing-247", 800);
        boolean isCorrect = (expected.getPlaneId()== 15151515)
                && (expected.getSeatingCapacity()==50)
                && (expected.getPayload()==500)
                && (expected.getFuelConsumption()==500)
                && (expected.getName().equals("Boeing-247"))
                && (expected.getCargoHoldAmount()==800);
        assertTrue(isCorrect);
    }
}
