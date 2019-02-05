package sensor.elastics.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sensor.elastics.demo.model.Reading;
import sensor.elastics.demo.repository.ReadingRepositoryES;

import java.util.List;

/**
 * Reading Service Implementation on ElasticSearch
 */

@Service
public class ReadingServiceES implements ReadingService{


    private ReadingRepositoryES readingRepository;

    @Autowired
    public void setReadingRepository(ReadingRepositoryES readingRepository) {
        this.readingRepository = readingRepository;
    }

    @Override
    public Reading save(Reading reading) {
        return readingRepository.save(reading);
    }

    @Override
    public void delete(Reading reading) {
        readingRepository.delete(reading);

    }

    @Override
    public Iterable<Reading> findAll() {
        return readingRepository.findAll();
    }

    @Override
    public List<Reading> findBySensorName(String sensorName) {
        return readingRepository.findBySensorName(sensorName);
    }
}
