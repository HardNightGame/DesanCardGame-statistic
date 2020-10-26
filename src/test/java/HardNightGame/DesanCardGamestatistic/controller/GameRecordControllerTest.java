package HardNightGame.DesanCardGamestatistic.controller;

import HardNightGame.DesanCardGamestatistic.dto.GameRecordData;
import HardNightGame.DesanCardGamestatistic.dto.GameRecordId;
import HardNightGame.DesanCardGamestatistic.service.GameRecordService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class GameRecordControllerTest {

    @Mock
    GameRecordService gameRecordService;

    @InjectMocks
    GameRecordController controller;

    ObjectMapper objectMapper = new ObjectMapper();

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void addGameRecord() throws Exception {
        // Array
        GameRecordData newGameRecordData = GameRecordData.builder().build();

        GameRecordId expGameRecordId = GameRecordId.builder().build();
        when(gameRecordService.AddGameRecord(newGameRecordData)).thenReturn(expGameRecordId);

        String jsonRequest = objectMapper.writeValueAsString(newGameRecordData);

        // Act
        MvcResult result = mockMvc.perform(post("/game-records")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk())
                .andReturn();
        String assertedJSON = result.getResponse().getContentAsString();

        GameRecordId assertedGameRecordId = objectMapper.readValue(assertedJSON, GameRecordId.class);

        // Assert

        assertEquals(expGameRecordId, assertedGameRecordId);
    }

}
