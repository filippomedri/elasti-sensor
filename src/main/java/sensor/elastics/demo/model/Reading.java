package sensor.elastics.demo.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import sensor.elastics.demo.serializer.QuantitySerializer;


import javax.measure.Quantity;
import java.time.Instant;
import java.util.Objects;

@Document(indexName = "sensor", type = "readings")
public class Reading{

    @Id
    private String id;

    private String sensorName;

    @JsonSerialize(using = QuantitySerializer.class)
    private Quantity quantity;

    private Instant timestamp;

    public Reading() {
    }

    public Reading(String id, String sensorName, Quantity quantity, Instant timestamp) {
        this.id = id;
        this.sensorName = sensorName;
        this.quantity = quantity;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reading reading = (Reading) o;
        return Objects.equals(id, reading.id) &&
                Objects.equals(sensorName, reading.sensorName) &&
                Objects.equals(quantity, reading.quantity) &&
                Objects.equals(timestamp, reading.timestamp);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, sensorName, quantity, timestamp);
    }

    @Override
    public String toString() {
        return "Reading{" +
                "id='" + id + '\'' +
                ", sensorName='" + sensorName + '\'' +
                ", quantity=" + quantity +
                ", timestamp=" + timestamp +
                '}';
    }

}
