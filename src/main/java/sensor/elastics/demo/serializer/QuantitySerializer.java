package sensor.elastics.demo.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import javax.measure.Quantity;
import java.io.IOException;

public class QuantitySerializer<Q extends Quantity<Q>> extends JsonSerializer<Quantity<Q>> {

    @Override
    public void serialize(Quantity<Q> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        try {
            gen.writeNumberField("value", value.getValue().intValue());
            gen.writeStringField("unit", value.getUnit().toString());
        } catch (NullPointerException e) {}
        gen.writeEndObject();
    }

}