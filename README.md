# elasti-sensor

This project attempts the implementation of time series data on 
elastic search by using the jackson library for serialization/deserialization
of java.time.instant and javax.measure.unit.

The project is still wip and not ready to run, it is put here with the purpose of discussion.
Right now it is possible to run the ReadingTest test class.

## Description
This Project loads some record of readings into an elastic search repository. 
The data are modeled as tuple (id, sensor name, quantity, timestamp).

## Libraries used
- Spring Boot
- Spring Data Elastic Search
- Jackson fast xml

## Compilation Command
- `mvn clean install` - Plain maven clean and install

## Prerequisites
Access to elastic search cluster named 'sensor-cluster' on localhost:9300
or other elastic search cluster previous change of configuration parameters on application.properties.


