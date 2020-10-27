package HardNightGame.DesanCardGamestatistic.controllers;

import HardNightGame.DesanCardGamestatistic.dtos.GameRecordDataDto;
import HardNightGame.DesanCardGamestatistic.dtos.GameRecordIdDto;
import HardNightGame.DesanCardGamestatistic.services.GameRecordService;
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
        GameRecordDataDto newGameRecordData = GameRecordDataDto.builder().build();

        GameRecordIdDto expGameRecordId = GameRecordIdDto.builder().build();
        when(gameRecordService.AddGameRecord(newGameRecordData)).thenReturn(expGameRecordId);

        String jsonRequest = objectMapper.writeValueAsString(newGameRecordData);

        // Act
        MvcResult result = mockMvc.perform(post("/game-records")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk())
                .andReturn();
        String assertedJSON = result.getResponse().getContentAsString();

        GameRecordIdDto assertedGameRecordId = objectMapper.readValue(assertedJSON, GameRecordIdDto.class);

        // Assert

        assertEquals(expGameRecordId, assertedGameRecordId);
    }

}
