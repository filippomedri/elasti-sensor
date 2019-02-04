package sensor.elastics.demo.service;

import sensor.elastics.demo.model.Reading;

import java.util.List;

public interface ReadingService {

    Reading save(Reading reading);

    void delete(Reading reading);

    Iterable<Reading> findAll();

    List<Reading> findBySensorName(String sensorName);
}
