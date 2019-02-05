package sensor.elastics.demo.serviceTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import sensor.elastics.demo.ElastisensorApplication;
import sensor.elastics.demo.model.Reading;
import sensor.elastics.demo.service.ReadingServiceES;
import tec.units.ri.quantity.Quantities;
import tec.units.ri.unit.Units;

import java.time.Instant;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElastisensorApplication.class)
public class ReadingServiceESTest {

    @Autowired
    private ReadingServiceES readingServiceES;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Before
    public void init(){
        elasticsearchTemplate.deleteIndex(Reading.class);
        elasticsearchTemplate.createIndex(Reading.class);
        elasticsearchTemplate.putMapping(Reading.class);
        elasticsearchTemplate.refresh(Reading.class);
    }

    @Test
    public void testSave(){

        Reading reading = new Reading("123",
                "t-probe",
                Quantities.getQuantity(21, Units.CELSIUS),
                Instant.parse("2018-12-03T10:15:30.00Z")
                );

        Reading testReading = readingServiceES.save(reading);

        assertNotNull(testReading.getId());

        assertEquals(testReading.getSensorName(),reading.getSensorName());
        assertEquals(testReading.getQuantityValue(),reading.getQuantityValue());
        assertEquals(testReading.getQuantityUnit(),reading.getQuantityUnit());
        assertEquals(testReading.getTimestamp(),reading.getTimestamp());
    }

}
