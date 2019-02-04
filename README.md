# elasti-sensor
Spring application to test the implementation of time series data on Elastic Search.

The data are modeled as tuple (id, sensor name, quantity, timestamp).

Quantity represents the measurement in terms of Unity of Measure and Value and it is a javax.measure.Quantity
object, its serialization is provided through a jackson JSONSerializer.

The timestamp is a java.time.instant object.



