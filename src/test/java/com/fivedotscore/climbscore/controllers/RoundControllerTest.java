package com.fivedotscore.climbscore.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fivedotscore.climbscore.dtos.CompetitionDTO;
import com.fivedotscore.climbscore.dtos.CompetitionRoundDTO;
import com.fivedotscore.climbscore.entities.Competition;
import com.fivedotscore.climbscore.entities.CompetitionRound;
import com.fivedotscore.climbscore.entities.Gym;
import com.fivedotscore.climbscore.entities.Zone;
import com.fivedotscore.climbscore.exceptions.ObjectNotFoundException;
import com.fivedotscore.climbscore.repositories.CompetitionRepository;
import com.fivedotscore.climbscore.repositories.CompetitionRoundRepository;
import com.fivedotscore.climbscore.repositories.GymRepository;
import com.fivedotscore.climbscore.repositories.ZoneRepository;
import com.fivedotscore.climbscore.services.CompService;
import com.fivedotscore.climbscore.services.RoundService;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class RoundControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @MockBean
    ZoneRepository zoneRepository;

    @MockBean
    CompetitionRoundRepository competitionRoundRepository;

    @MockBean
    CompetitionRepository competitionRepository;

    @Autowired
    RoundService roundService;

    @Autowired
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Rule//(expected = ObjectNotFoundException.class)
    public ExpectedException exception = ExpectedException.none();

    private List<Gym> stubGyms() {
        Gym gym1 = new Gym(23L, "Gym A", null);
        Gym gym2 = new Gym(334L, "Gym B", null);

        return Arrays.asList(gym1, gym2);
    }

    private List<Competition> stubCompetitions() {
        Competition competition1 = new Competition(123L,stubGyms().get(0), "Competition A",null);
        Competition competition2 = new Competition(1334L,stubGyms().get(1), "Competition B", null);

        return Arrays.asList(competition1, competition2);
    }

    private List<CompetitionRound> stubCompetitionRounds() {
        CompetitionRound competitionRound1 = new CompetitionRound(91L, "Heart Burn Prelims", stubCompetitions().get(0), LocalDate.parse("2018-01-02"), null, null, null);
        CompetitionRound competitionRound2 = new CompetitionRound(92L, "Heart Burn Finals", stubCompetitions().get(0), LocalDate.parse("2018-01-03"), null, null, null);
        CompetitionRound competitionRound3 = new CompetitionRound(93L, "Winter Burn Prelims", stubCompetitions().get(1), LocalDate.parse("2018-01-04"), null, null, null);
        CompetitionRound competitionRound4 = new CompetitionRound(94L, "Winter Burn Semis", stubCompetitions().get(1), LocalDate.parse("2018-01-05"), null, null, null);
        CompetitionRound competitionRound5 = new CompetitionRound(95L, "Winter Burn Finals", stubCompetitions().get(1), LocalDate.parse("2018-01-06"), null, null, null);

        return Arrays.asList(competitionRound1, competitionRound2, competitionRound3, competitionRound4, competitionRound5);
    }

    private List<Zone> stubZones() {
        Zone zone1 = new Zone(78L, "The Triforce", stubCompetitionRounds().get(0),null);
        Zone zone2 = new Zone(79L, "The Cave", stubCompetitionRounds().get(0),null);
        Zone zone3 = new Zone(80L, "The Slab", stubCompetitionRounds().get(0),null);
        Zone zone4 = new Zone(81L, "The Triforce", stubCompetitionRounds().get(1),null);
        Zone zone5 = new Zone(82L, "The Dihedral", stubCompetitionRounds().get(1),null);

        return Arrays.asList(zone1, zone2, zone3, zone4, zone5);
    }

    @Test
    public void getAllZones() throws Exception {
        when(zoneRepository.findAll()).thenReturn(stubZones());

        this.mvc.perform(MockMvcRequestBuilders
                .get("/zones/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[{\"id\":78,\"name\":\"The Triforce\",\"compRound\":91,\"routes\":null},{\"id\":79,\"name\":\"The Cave\",\"compRound\":91,\"routes\":null},{\"id\":80,\"name\":\"The Slab\",\"compRound\":91,\"routes\":null},{\"id\":81,\"name\":\"The Triforce\",\"compRound\":92,\"routes\":null},{\"id\":82,\"name\":\"The Dihedral\",\"compRound\":92,\"routes\":null}]"));

        verify(zoneRepository, times(1)).findAll();
    }

    @Test
    public void getAllZonesByRound() throws Exception {
        when(zoneRepository.findZonesByCompRound_Id(91L)).thenReturn(stubZones().subList(0,3));
        when(competitionRoundRepository.existsById(91L)).thenReturn(true);

        this.mvc.perform(MockMvcRequestBuilders
                .get("/zones/round/91"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[{\"id\":78,\"name\":\"The Triforce\",\"compRound\":91,\"routes\":null},{\"id\":79,\"name\":\"The Cave\",\"compRound\":91,\"routes\":null},{\"id\":80,\"name\":\"The Slab\",\"compRound\":91,\"routes\":null}]"));

        verify(zoneRepository, times(1)).findZonesByCompRound_Id(91L);
    }

    @Test
    public void getZoneByIdNotFound() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders
                .get("/zones/223123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(""));
        verify(zoneRepository, times(1)).findById(223123L);
    }

    @Test
    public void getZoneById() throws Exception {
        when(zoneRepository.findById(79L)).thenReturn(Optional.ofNullable(stubZones().get(1)));

        this.mvc.perform(MockMvcRequestBuilders
                .get("/zones/79"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":79,\"name\":\"The Cave\",\"compRound\":91,\"routes\":null}"));
        verify(zoneRepository, times(1)).findById(79L);
    }

//    @Test
//    public void addZone() throws Exception {
//        Zone inputZone = new Zone(null, "Zone A", null);
//        Zone outputZone = new Zone(23L, "Zone A", null);
//
//        ObjectMapper mapper = new ObjectMapper();
//        String requestJson = mapper.writeValueAsString(inputZone);
//        String responseJson = mapper.writeValueAsString(outputZone);
//
//        when(zoneRepository.save(inputZone)).thenReturn(outputZone);
//
//        this.mvc.perform(MockMvcRequestBuilders
//                .post("/zones")
//                .contentType(APPLICATION_JSON_UTF8)
//                .content(requestJson))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.content().string(responseJson));
//        verify(zoneRepository, times(1)).save(any(Zone.class));
//    }
//
//    @Test
//    public void modifyZone() throws Exception {
//        Zone inputZone = stubZones().get(0);
//        Zone outputZone = inputZone;
//        outputZone.setName("different");
//        ObjectMapper mapper = new ObjectMapper();
//        String requestJson = mapper.writeValueAsString(inputZone);
//        String responseJson = mapper.writeValueAsString(outputZone);
//
//        when(zoneRepository.save(inputZone)).thenReturn(outputZone);
//
//        this.mvc.perform(MockMvcRequestBuilders
//                .put("/zones/" + inputZone.getId())
//                .contentType(APPLICATION_JSON_UTF8)
//                .content(requestJson))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string(responseJson));
//        verify(zoneRepository, times(1)).save(any(Zone.class));
//    }


//    @Test
//    public void getAllCompetitions() throws Exception {
//        when(compService.findAllCompetitions()).thenReturn(stubCompetitions());
//
//        this.mvc.perform(MockMvcRequestBuilders
//                .get("/competitions/"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string("[{\"id\":123,\"zoneId\":23,\"name\":\"Competition A\",\"roundIds\":[]},{\"id\":1334,\"zoneId\":334,\"name\":\"Competition B\",\"roundIds\":[]}]"));
//
//        verify(competitionRepository, times(1)).findAll();
//    }
//
//    @Test
//    public void getCompetitionByIdNotFound() throws Exception {
//        when(competitionRepository.findById(223123L)).thenReturn(Optional.ofNullable(null));
//
//        this.mvc.perform(MockMvcRequestBuilders
//                .get("/competitions/223123"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string(""));
//        verify(competitionRepository, times(1)).findById(223123L);
//    }
//
//    @Test
//    public void getCompetitionById() throws Exception {
//        when(competitionRepository.findById(1334L)).thenReturn(Optional.ofNullable(stubCompetitions().get(1)));
//
//        this.mvc.perform(MockMvcRequestBuilders
//                .get("/competitions/1334"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1334,\"zoneId\":334,\"name\":\"Competition B\",\"roundIds\":[]}"));
//        verify(competitionRepository, times(1)).findById(1334L);
//    }
//
//    @Test
//    public void addCompetition() throws Exception {
//        Competition outputCompetition = new Competition(1334L,stubZones().get(1), "Competition B", null);
//        CompetitionDTO outputCompetitionDTO = new CompetitionDTO(outputCompetition);
//
//        Competition repoInputCompetition = new Competition(null,stubZones().get(1), "Competition B", null);
//        when(competitionRepository.save(repoInputCompetition)).thenReturn(outputCompetition);
//
//        Competition inputCompetition = new Competition(null,null, "Competition B", null);
//
//        ObjectMapper mapper = new ObjectMapper();
//        String requestJson = mapper.writeValueAsString(inputCompetition);
//        String responseJson = mapper.writeValueAsString(outputCompetitionDTO);
//
//        when(zoneRepository.findById(334L)).thenReturn(Optional.ofNullable(stubZones().get(1)));
//
//        this.mvc.perform(MockMvcRequestBuilders
//                .post("/competitions")
//                .contentType(APPLICATION_JSON_UTF8)
//                .content(requestJson)
//                .param("zoneId", "334"))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.content().string(responseJson));
//        verify(competitionRepository, times(1)).save(any(Competition.class));
//        verify(zoneRepository, times(1)).findById(334L);
//    }
//
//    @Test
//    public void modifyCompetition() throws Exception {
//        Competition inputCompetition = stubCompetitions().get(0);
//        Competition outputCompetition = inputCompetition;
//
//        outputCompetition.setName("different");
//        CompetitionDTO outputCompetitionDTO = new CompetitionDTO(outputCompetition);
//
//        ObjectMapper mapper = new ObjectMapper();
//        String requestJson = mapper.writeValueAsString(inputCompetition);
//        String responseJson = mapper.writeValueAsString(outputCompetitionDTO);
//
//        when(competitionRepository.save(inputCompetition)).thenReturn(outputCompetition);
//
//        this.mvc.perform(MockMvcRequestBuilders
//                .put("/competitions/"+inputCompetition.getId())
//                .contentType(APPLICATION_JSON_UTF8)
//                .content(requestJson))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string(responseJson));
//        verify(competitionRepository, times(1)).save(any(Competition.class));
//    }
//
//    @Test
//    public void getAllCompetitionRounds() throws Exception {
//        when(compService.findAllCompetitionRounds()).thenReturn(stubCompetitionRounds());
//
//        this.mvc.perform(MockMvcRequestBuilders
//                .get("/competitionRounds/"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string("[{\"id\":91,\"name\":\"Heart Burn Prelims\",\"competitionId\":123,\"date\":[2018,1,2],\"zoneIds\":[],\"climberIds\":[],\"judgeIds\":[]},{\"id\":92,\"name\":\"Heart Burn Finals\",\"competitionId\":123,\"date\":[2018,1,3],\"zoneIds\":[],\"climberIds\":[],\"judgeIds\":[]},{\"id\":93,\"name\":\"Winter Burn Prelims\",\"competitionId\":1334,\"date\":[2018,1,4],\"zoneIds\":[],\"climberIds\":[],\"judgeIds\":[]},{\"id\":94,\"name\":\"Winter Burn Semis\",\"competitionId\":1334,\"date\":[2018,1,5],\"zoneIds\":[],\"climberIds\":[],\"judgeIds\":[]},{\"id\":95,\"name\":\"Winter Burn Finals\",\"competitionId\":1334,\"date\":[2018,1,6],\"zoneIds\":[],\"climberIds\":[],\"judgeIds\":[]}]"));
//
//        verify(competitionRoundRepository, times(1)).findAll();
//    }
//
//    @Test
//    public void getCompetitionRoundByIdNotFound() throws Exception {
//        when(competitionRoundRepository.findById(223123L)).thenReturn(Optional.ofNullable(null));
//
//        this.mvc.perform(MockMvcRequestBuilders
//                .get("/competitionRounds/223123"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string(""));
//        verify(competitionRoundRepository, times(1)).findById(223123L);
//    }
//
//    @Test
//    public void getCompetitionRoundById() throws Exception {
//        when(competitionRoundRepository.findById(91L)).thenReturn(Optional.ofNullable(stubCompetitionRounds().get(1)));
//
//        this.mvc.perform(MockMvcRequestBuilders
//                .get("/competitionRounds/91"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string("{\"id\":92,\"name\":\"Heart Burn Finals\",\"competitionId\":123,\"date\":[2018,1,3],\"zoneIds\":[],\"climberIds\":[],\"judgeIds\":[]}"));
//        verify(competitionRoundRepository, times(1)).findById(91L);
//    }
//
//    @Test
//    public void addCompetitionRound() throws Exception {
//        CompetitionRound outputCompetitionRound = new CompetitionRound(96L,"Midnight Burn Finals", stubCompetitions().get(1), LocalDate.parse("2018-01-06"), null, null, null);
//        CompetitionRoundDTO outputCompetitionRoundDTO = new CompetitionRoundDTO(outputCompetitionRound);
//
//        CompetitionRound repoInputCompetitionRound = new CompetitionRound(null,"Midnight Burn Finals", stubCompetitions().get(1), LocalDate.parse("2018-01-06"), null, null, null);
//        when(competitionRoundRepository.save(repoInputCompetitionRound)).thenReturn(outputCompetitionRound);
//
//        CompetitionRound inputCompetitionRound = new CompetitionRound(null,"Midnight Burn Finals", null, LocalDate.parse("2018-01-06"), null, null, null);
//
//        ObjectMapper mapper = new ObjectMapper();
//        String requestJson = mapper.writeValueAsString(inputCompetitionRound);
//        String responseJson = mapper.writeValueAsString(outputCompetitionRoundDTO);
//
//        when(competitionRepository.findById(1334L)).thenReturn(Optional.ofNullable(stubCompetitions().get(1)));
//
//        this.mvc.perform(MockMvcRequestBuilders
//                .post("/competitionRounds")
//                .contentType(APPLICATION_JSON_UTF8)
//                .content(requestJson)
//                .param("compId", "1334"))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.content().string(responseJson));
//        verify(competitionRoundRepository, times(1)).save(any(CompetitionRound.class));
//        verify(competitionRepository, times(1)).findById(1334L);
//    }
//
//    @Test
//    public void modifyCompetitionRound() throws Exception {
//        CompetitionRound inputCompetitionRound = stubCompetitionRounds().get(0);
//        CompetitionRound outputCompetitionRound = inputCompetitionRound;
//
//        outputCompetitionRound.setName("different");
//        CompetitionRoundDTO outputCompetitionRoundDTO = new CompetitionRoundDTO(outputCompetitionRound);
//        CompetitionRoundDTO inputCompetitionRoundDTO = new CompetitionRoundDTO(inputCompetitionRound);
//
//        ObjectMapper mapper = new ObjectMapper();
//        String requestJson = mapper.writeValueAsString(inputCompetitionRoundDTO);
//        System.out.println(inputCompetitionRoundDTO);
//        String responseJson = mapper.writeValueAsString(outputCompetitionRoundDTO);
//
//        when(competitionRepository.findById(1334L)).thenReturn(Optional.ofNullable(stubCompetitions().get(1)));
//        when(competitionRoundRepository.save(inputCompetitionRound)).thenReturn(outputCompetitionRound);
//
//        this.mvc.perform(MockMvcRequestBuilders
//                .put("/competitionRounds/"+inputCompetitionRound.getId())
//                .contentType(APPLICATION_JSON_UTF8)
//                .content(requestJson))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string(responseJson));
//        verify(competitionRoundRepository, times(1)).save(any(CompetitionRound.class));
//    }
}