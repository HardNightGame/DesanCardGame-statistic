package HardNightGame.DesanCardGamestatistic.service;

import HardNightGame.DesanCardGamestatistic.dto.GameRecordData;
import HardNightGame.DesanCardGamestatistic.dto.GameRecordId;
import HardNightGame.DesanCardGamestatistic.model.GameRecord;
import HardNightGame.DesanCardGamestatistic.repository.GameRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.converter.Converter;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameRecordServiceImplTest {

    @Mock
    GameRecordRepository gameRecordRepository;

    @Mock
    Converter<GameRecordData, GameRecord> dtoToModelConverter;

    @Mock
    Converter<GameRecord, GameRecordId> modelToDtoIdConverter;

    GameRecordService gameRecordService;
    Random random = new Random();

    @BeforeEach
    void setUp() {
        gameRecordService = new GameRecordServiceImpl(
                gameRecordRepository, dtoToModelConverter, modelToDtoIdConverter);
    }


    @Test
    void addGameRecord() {
        // Array
        String expName = "aoeui";
        Integer expGameTime = random.nextInt();
        GameRecordData gameRecordData = GameRecordData.builder()
                .Name(expName)
                .GameTime(expGameTime)
                .build();

        GameRecord convertedGameRecord = GameRecord.builder().build();

        when(dtoToModelConverter.convert(gameRecordData)).thenReturn(convertedGameRecord);

        GameRecord savedGameRecord = GameRecord.builder().build();
        when(gameRecordRepository.save(convertedGameRecord)).thenReturn(savedGameRecord);

        GameRecordId expGameRecordId = GameRecordId.builder().build();
        when(modelToDtoIdConverter.convert(savedGameRecord)).thenReturn(expGameRecordId);

        // Act
        GameRecordId assertGameRecordId = gameRecordService.AddGameRecord(gameRecordData);

        // Assert
        assertTrue(expGameRecordId == assertGameRecordId);
    }

}
