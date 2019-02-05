package sensor.elastics.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.measure.Quantity;
import javax.measure.Unit;
import java.time.Instant;
import java.util.Objects;

/**
 * Class responsible to keep reading from sensor information
 *
 */
@Document(indexName = "sensor", type = "reading")
public class Reading {

    @Id
    private String id;

    private String sensorName;

    private Number quantityValue;

    private Unit quantityUnit;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Instant timestamp;

    public Reading() {
    }

    public Reading(String id, String sensorName, Quantity quantity, Instant timestamp) {
        this.id = id;
        this.sensorName = sensorName;
        this.quantityValue = quantity.getValue();
        this.quantityUnit = quantity.getUnit();
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

    public Number getQuantityValue() {
        return quantityValue;
    }

    public void setQuantityValue(Number quantityValue) {
        this.quantityValue = quantityValue;
    }

    public Unit getQuantityUnit() {
        return quantityUnit;
    }

    public void setQuantityUnit(Unit quantityUnit) {
        this.quantityUnit = quantityUnit;
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
                Objects.equals(quantityValue, reading.quantityValue) &&
                Objects.equals(quantityUnit, reading.quantityUnit) &&
                Objects.equals(timestamp, reading.timestamp);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, sensorName, quantityValue, quantityUnit, timestamp);
    }

    @Override
    public String toString() {
        return "Reading{" +
                "id='" + id + '\'' +
                ", sensorName='" + sensorName + '\'' +
                ", quantityValue=" + quantityValue +
                ", quantityUnit=" + quantityUnit +
                ", timestamp=" + timestamp +
                '}';
    }
}