package com.fivedotscore.climbscore.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fivedotscore.climbscore.entities.Competition;

import java.io.IOException;

public class CompetitionSerializer extends JsonSerializer<Competition> {

    @Override
    public void serialize(Competition competition, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (competition != null) {
            jsonGenerator.writeString(String.valueOf(competition.getId()));
        } else {
            jsonGenerator.writeString("");
        }
    }
}