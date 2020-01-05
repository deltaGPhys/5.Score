package com.fivedotscore.climbscore.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fivedotscore.climbscore.entities.Zone;

import java.io.IOException;

public class ZoneSerializer extends JsonSerializer<Zone> {

    @Override
    public void serialize(Zone zone, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (zone != null) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id",zone.getId());
            jsonGenerator.writeStringField("name",String.valueOf(zone.getName()));
            jsonGenerator.writeNumberField("numRoutes",zone.getRoutes().size());
            jsonGenerator.writeEndObject();
        } else {
            jsonGenerator.writeString("");
        }
    }
}