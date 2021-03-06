package com.fivedotscore.climbscore.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fivedotscore.climbscore.entities.Competition;
import com.fivedotscore.climbscore.services.CompService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class CompetitionDeserializer extends JsonDeserializer<Competition> {

    @Autowired
    CompService compService;

    Logger logger = LoggerFactory.getLogger(CompetitionRoundDeserializer.class);

    @Override
    public Competition deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        Long id = node.asLong();

        return compService.findCompetitionById(id);
    }
}
