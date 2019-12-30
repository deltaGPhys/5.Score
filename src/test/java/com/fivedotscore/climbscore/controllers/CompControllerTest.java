package com.fivedotscore.climbscore.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fivedotscore.climbscore.dtos.CompetitionDTO;
import com.fivedotscore.climbscore.entities.Competition;
import com.fivedotscore.climbscore.entities.Gym;
import com.fivedotscore.climbscore.exceptions.ObjectNotFoundException;
import com.fivedotscore.climbscore.repositories.CompetitionRepository;
import com.fivedotscore.climbscore.repositories.GymRepository;
import com.fivedotscore.climbscore.services.CompService;
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
public class CompControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @MockBean
    GymRepository gymRepository;

    @MockBean
    CompetitionRepository competitionRepository;

    @Autowired
    CompService compService;

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
        Gym gym1 = new Gym(23L,"Gym A",null);
        Gym gym2 = new Gym(334L, "Gym B", null);

        return Arrays.asList(gym1,gym2);
    }

    @Test
    public void getAllGyms() throws Exception {
        when(compService.findAllGyms()).thenReturn(stubGyms());

        this.mvc.perform(MockMvcRequestBuilders
                .get("/gyms/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[{\"id\":23,\"name\":\"Gym A\",\"comps\":null},{\"id\":334,\"name\":\"Gym B\",\"comps\":null}]"));

        verify(gymRepository, times(1)).findAll();
    }

    @Test
    public void getGymByIdNotFound() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders
                .get("/gyms/223123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(""));
        verify(gymRepository, times(1)).findById(223123L);
    }

    @Test
    public void getGymById() throws Exception {
        when(gymRepository.findById(332L)).thenReturn(Optional.ofNullable(stubGyms().get(1)));

        this.mvc.perform(MockMvcRequestBuilders
                .get("/gyms/334"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(""));
        verify(gymRepository, times(1)).findById(334L);
    }

    @Test
    public void addGym() throws Exception {
        Gym inputGym = stubGyms().get(0);
        Gym outputGym = inputGym;
        inputGym.setId(null);
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(inputGym);
        String responseJson = mapper.writeValueAsString(outputGym);

        when(gymRepository.save(inputGym)).thenReturn(outputGym);

        this.mvc.perform(MockMvcRequestBuilders
                .post("/gyms")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string(responseJson));
        verify(gymRepository, times(1)).save(any(Gym.class));
    }

    @Test
    public void modifyGym() throws Exception {
        Gym inputGym = stubGyms().get(0);
        Gym outputGym = inputGym;
        outputGym.setName("different");
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(inputGym);
        String responseJson = mapper.writeValueAsString(outputGym);

        when(gymRepository.save(inputGym)).thenReturn(outputGym);

        this.mvc.perform(MockMvcRequestBuilders
                .put("/gyms/"+inputGym.getId())
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(responseJson));
        verify(gymRepository, times(1)).save(any(Gym.class));
    }

    private List<Competition> stubCompetitions() {
        Competition competition1 = new Competition(23L,stubGyms().get(0), "Competition A",null);
        Competition competition2 = new Competition(334L,stubGyms().get(1), "Competition B", null);

        return Arrays.asList(competition1,competition2);
    }

    @Test
    public void getAllCompetitions() throws Exception {
        when(compService.findAllCompetitions()).thenReturn(stubCompetitions());

        this.mvc.perform(MockMvcRequestBuilders
                .get("/competitions/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[{\"id\":23,\"gymId\":23,\"name\":\"Competition A\",\"roundIds\":[]},{\"id\":334,\"gymId\":334,\"name\":\"Competition B\",\"roundIds\":[]}]"));

        verify(competitionRepository, times(1)).findAll();
    }

    @Test
    public void getCompetitionByIdNotFound() throws Exception {
        when(competitionRepository.findById(223123L)).thenReturn(Optional.ofNullable(null));

        this.mvc.perform(MockMvcRequestBuilders
                .get("/competitions/223123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(""));
        verify(competitionRepository, times(1)).findById(223123L);
    }

    @Test
    public void getCompetitionById() throws Exception {
        when(competitionRepository.findById(334L)).thenReturn(Optional.ofNullable(stubCompetitions().get(1)));

        this.mvc.perform(MockMvcRequestBuilders
                .get("/competitions/334"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":334,\"gymId\":334,\"name\":\"Competition B\",\"roundIds\":[]}"));
        verify(competitionRepository, times(1)).findById(334L);
    }

    @Test
    public void addCompetition() throws Exception {
        Competition inputCompetition = stubCompetitions().get(0);
        Competition outputCompetition = inputCompetition;

        inputCompetition.setId(null);
        CompetitionDTO outputCompetitionDTO = new CompetitionDTO(outputCompetition);

        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(inputCompetition);
        String responseJson = mapper.writeValueAsString(outputCompetitionDTO);

        this.mvc.perform(MockMvcRequestBuilders
                .post("/competitions")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string(responseJson));
        verify(competitionRepository, times(1)).save(any(Competition.class));
    }

    @Test
    public void modifyCompetition() throws Exception {
        Competition inputCompetition = stubCompetitions().get(0);
        Competition outputCompetition = inputCompetition;

        outputCompetition.setName("different");
        CompetitionDTO outputCompetitionDTO = new CompetitionDTO(outputCompetition);

        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(inputCompetition);
        String responseJson = mapper.writeValueAsString(outputCompetitionDTO);

        when(competitionRepository.save(inputCompetition)).thenReturn(outputCompetition);

        this.mvc.perform(MockMvcRequestBuilders
                .put("/competitions/"+inputCompetition.getId())
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(responseJson));
        verify(competitionRepository, times(1)).save(any(Competition.class));
    }
}