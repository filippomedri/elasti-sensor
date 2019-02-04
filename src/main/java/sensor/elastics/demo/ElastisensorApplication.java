package sensor.elastics.demo;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import sensor.elastics.demo.model.Reading;
import sensor.elastics.demo.service.ReadingService;
import sensor.elastics.demo.service.ReadingServiceES;
import tec.units.ri.quantity.Quantities;
import tec.units.ri.unit.Units;

import java.time.Instant;
import java.util.List;
import java.util.Map;


@SpringBootApplication
public class ElastisensorApplication implements CommandLineRunner{

	@Autowired
	private ElasticsearchOperations es;

	@Autowired
	private ReadingService readingService;

	public static void main(String[] args) {
		SpringApplication.run(ElastisensorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		printElasticSearchInfo();

		readingService.save(new Reading("123","t-probe",
				Quantities.getQuantity(23.02, Units.CELSIUS),
						Instant.parse("2018-12-03T10:15:30.00Z")));
		readingService.save(new Reading("123","t-probe",
				Quantities.getQuantity(23.04, Units.CELSIUS),
				Instant.parse("2018-12-03T10:16:30.00Z")));

		readingService.save(new Reading("456","c-probe",
				Quantities.getQuantity(0.5, Units.AMPERE),
				Instant.parse("2018-12-03T10:15:30.00Z")));
		readingService.save(new Reading("456","c-probe",
				Quantities.getQuantity(0.6, Units.AMPERE),
				Instant.parse("2018-12-03T10:16:30.00Z")));

		List<Reading> tReadings = readingService.findBySensorName("t-probe");

		tReadings.forEach(System.out::println);

	}


	private void printElasticSearchInfo(){

		System.out.println("--ElasticSearch--");
		Client client = es.getClient();
		Map<String,Settings> groups = client.settings().getAsGroups();

		groups.forEach((k,v)->
			System.out.println(k + " = " + v.toString())
		);

		System.out.println("--ElasticSearch--");
	}
}

