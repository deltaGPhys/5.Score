package com.fivedotscore.climbscore.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fivedotscore.climbscore.entities.CompetitionRound;

import java.io.IOException;

public class CompetitionRoundSerializer extends JsonSerializer<CompetitionRound> {

    @Override
    public void serialize(CompetitionRound competitionRound, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (competitionRound != null) {
            jsonGenerator.writeNumber(competitionRound.getId());
        } else {
            jsonGenerator.writeString("");
        }
    }
}