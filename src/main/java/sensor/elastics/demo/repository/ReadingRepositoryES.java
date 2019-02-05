package sensor.elastics.demo.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import sensor.elastics.demo.model.Reading;

import java.util.List;

/**
 * Interface to crud operations on elasticsearch
 */
public interface ReadingRepositoryES extends ElasticsearchRepository<Reading,String> {

    List<Reading> findBySensorName(String sensorName);

}
