package sensor.elastics.demo.modelTest;

import org.junit.Test;
import sensor.elastics.demo.model.Reading;
import tec.units.ri.quantity.Quantities;
import tec.units.ri.unit.Units;

import java.time.Instant;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class ReadingTest {

    @Test
    public void readingTest(){

        Reading testReading = new Reading("123",
                "t-probe",
                Quantities.getQuantity(19, Units.CELSIUS),
                Instant.parse("2018-12-03T10:15:30.00Z")
        );

        assertNotNull(testReading.getId());

        assertEquals(testReading.getSensorName(),"t-probe");
        assertEquals(testReading.getQuantityValue(),19);
        assertEquals(testReading.getQuantityUnit(),Units.CELSIUS);

        assertEquals(testReading.getTimestamp(),Instant.parse("2018-12-03T10:15:30.00Z"));

        System.out.println(testReading.toString());

    }
}
