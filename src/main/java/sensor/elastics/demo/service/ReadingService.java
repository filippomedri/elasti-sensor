package sensor.elastics.demo.service;

import sensor.elastics.demo.model.Reading;

import java.util.List;

/**
 * Reading Service methods contract
 */
public interface ReadingService {


    Reading save(Reading reading);

    void delete(Reading reading);

    Iterable<Reading> findAll();

    List<Reading> findBySensorName(String sensorName);
}
