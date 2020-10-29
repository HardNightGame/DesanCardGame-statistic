package HardNightGame.DesanCardGamestatistic.controllers;

import HardNightGame.DesanCardGamestatistic.dtos.ErrorDto;
import HardNightGame.DesanCardGamestatistic.dtos.GameRecordDataDto;
import HardNightGame.DesanCardGamestatistic.dtos.GameRecordDto;
import HardNightGame.DesanCardGamestatistic.dtos.GameRecordIdDto;
import HardNightGame.DesanCardGamestatistic.services.GameRecordService;
import HardNightGame.DesanCardGamestatistic.testTools.UniqueRandom;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.annotations.Source;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GameRecordControllerTest {

    @MockBean
    GameRecordService gameRecordService;

    UniqueRandom random = new UniqueRandom();

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    MockMvc mockMvc;

//    @BeforeEach
//    void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup();
//    }

    @Test
    void addGameRecord() throws Exception {
        // Array
        GameRecordDataDto newGameRecordData = GameRecordDataDto.builder().Name("aeoui").GameTime(random.NextUniqueInt()).build();

        GameRecordIdDto expGameRecordId = GameRecordIdDto.builder().build();
        when(gameRecordService.AddGameRecord(newGameRecordData)).thenReturn(expGameRecordId);

        String jsonRequest = objectMapper.writeValueAsString(newGameRecordData);

        ArgumentCaptor<GameRecordDataDto> gameRecordDataDtoArgumentCaptor = ArgumentCaptor.forClass(GameRecordDataDto.class);

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
        verify(gameRecordService, times(1)).AddGameRecord(gameRecordDataDtoArgumentCaptor.capture());
        assertEquals(newGameRecordData, gameRecordDataDtoArgumentCaptor.getValue());
        verifyNoMoreInteractions(gameRecordService);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2", "02"})
    void GetGameRecords_WithCount(String query) throws Exception {
        // Array
        Integer expTopRecords = 2;

        GameRecordDto expGameRecordDto1 = GameRecordDto.builder()
                .id(
                        GameRecordIdDto.builder()
                                .Id(random.NextUniqueInt())
                                .build())
                .data(
                        GameRecordDataDto.builder()
                                .Name("aoeui")
                                .GameTime(random.NextUniqueInt())
                                .build()
                ).build();
        GameRecordDto expGameRecordDto2 = GameRecordDto.builder()
                .id(
                        GameRecordIdDto.builder()
                                .Id(random.NextUniqueInt())
                                .build())
                .data(
                        GameRecordDataDto.builder()
                                .Name("htnsn")
                                .GameTime(random.NextUniqueInt())
                                .build()
                ).build();

        when(gameRecordService.GetTopGameRecords(expTopRecords)).thenReturn(Arrays.asList(new GameRecordDto[]{expGameRecordDto1, expGameRecordDto2}));

        // Act
        MvcResult result = mockMvc.perform(get("/game-records")
                .queryParam("topCount", query))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String assertedJSON = result.getResponse().getContentAsString();

        Collection<GameRecordDto> assertedGameRecordDtoArr = objectMapper.readValue(assertedJSON, Collection.class);

        // Assert
        assertEquals(expTopRecords, assertedGameRecordDtoArr.size());
        verify(gameRecordService, times(1)).GetTopGameRecords(expTopRecords);
        verifyNoMoreInteractions(gameRecordService);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void GetGameRecords_ZeroCount_ReturnAll(String queryValue) throws Exception {
        // Array

        GameRecordDto expGameRecordDto1 = GameRecordDto.builder()
                .id(
                        GameRecordIdDto.builder()
                                .Id(random.NextUniqueInt())
                                .build())
                .data(
                        GameRecordDataDto.builder()
                                .Name("aoeui")
                                .GameTime(random.NextUniqueInt())
                                .build()
                ).build();
        GameRecordDto expGameRecordDto2 = GameRecordDto.builder()
                .id(
                        GameRecordIdDto.builder()
                                .Id(random.NextUniqueInt())
                                .build())
                .data(
                        GameRecordDataDto.builder()
                                .Name("htnsn")
                                .GameTime(random.NextUniqueInt())
                                .build()
                ).build();
        var expArr = Arrays.asList(new GameRecordDto[]{expGameRecordDto1, expGameRecordDto2});

        when(gameRecordService.GetAllGameRecords()).thenReturn(expArr);

        // Act
        MvcResult result = mockMvc.perform(get("/game-records")
                .queryParam("topCount", queryValue))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String assertedJSON = result.getResponse().getContentAsString();

        Collection<GameRecordDto> assertedGameRecordDtoArr = objectMapper.readValue(assertedJSON, Collection.class);

        // Assert
        assertEquals(expArr.size(), assertedGameRecordDtoArr.size());
        verify(gameRecordService, times(1)).GetAllGameRecords();
        verifyNoMoreInteractions(gameRecordService);
    }

    @Test
    void GetGameRecords_NoCount() throws Exception {
        // Array

        GameRecordDto expGameRecordDto1 = GameRecordDto.builder()
                .id(
                        GameRecordIdDto.builder()
                                .Id(random.NextUniqueInt())
                                .build())
                .data(
                        GameRecordDataDto.builder()
                                .Name("aoeui")
                                .GameTime(random.NextUniqueInt())
                                .build()
                ).build();
        GameRecordDto expGameRecordDto2 = GameRecordDto.builder()
                .id(
                        GameRecordIdDto.builder()
                                .Id(random.NextUniqueInt())
                                .build())
                .data(
                        GameRecordDataDto.builder()
                                .Name("htnsn")
                                .GameTime(random.NextUniqueInt())
                                .build()
                ).build();
        var expArr = Arrays.asList(new GameRecordDto[]{expGameRecordDto1, expGameRecordDto2});

        when(gameRecordService.GetAllGameRecords()).thenReturn(expArr);

        // Act
        MvcResult result = mockMvc.perform(get("/game-records"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String assertedJSON = result.getResponse().getContentAsString();

        Collection<GameRecordDto> assertedGameRecordDtoArr = objectMapper.readValue(assertedJSON, Collection.class);

        // Assert
        assertEquals(expArr.size(), assertedGameRecordDtoArr.size());
        verify(gameRecordService, times(1)).GetAllGameRecords();
        verifyNoMoreInteractions(gameRecordService);
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "a", "1e3", "a2", "3e", "0", "00"})
    void GetGameRecords_WrongCount(String queryValue) throws Exception {
        // Array

        // Act
        MvcResult result = mockMvc.perform(
                get("/game-records")
                        .queryParam("topCount", queryValue))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
        String assertedJSON = result.getResponse().getContentAsString();
        ErrorDto assertedErrorDto = objectMapper.readValue(assertedJSON, ErrorDto.class);

        // Assert
        verifyNoMoreInteractions(gameRecordService);
        assertNotNull(assertedErrorDto);
    }
}
