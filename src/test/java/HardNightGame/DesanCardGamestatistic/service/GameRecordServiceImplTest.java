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

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameRecordServiceImplTest {

    @Mock
    GameRecordRepository gameRecordRepository;

    @Mock
    Converter<GameRecordData, GameRecord> dtoDataToModelConverter;

    @Mock
    Converter<GameRecord, GameRecordId> modelToDtoIdConverter;

    @Mock
    Converter<GameRecord, HardNightGame.DesanCardGamestatistic.dto.GameRecord> modelToDtoConverter;

    GameRecordService gameRecordService;
    Random random = new Random();

    @BeforeEach
    void setUp() {
        gameRecordService = new GameRecordServiceImpl(
                gameRecordRepository, dtoDataToModelConverter, modelToDtoIdConverter, modelToDtoConverter);
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

        when(dtoDataToModelConverter.convert(gameRecordData)).thenReturn(convertedGameRecord);

        GameRecord savedGameRecord = GameRecord.builder().build();
        when(gameRecordRepository.save(convertedGameRecord)).thenReturn(savedGameRecord);

        GameRecordId expGameRecordId = GameRecordId.builder().build();
        when(modelToDtoIdConverter.convert(savedGameRecord)).thenReturn(expGameRecordId);

        // Act
        GameRecordId assertGameRecordId = gameRecordService.AddGameRecord(gameRecordData);

        // Assert
        assertTrue(expGameRecordId == assertGameRecordId);
    }

    @Test
    void getTopGameRecords() {
        // Array
        Integer topCount = 2;

        GameRecord record1 = GameRecord.builder().build();
        GameRecord record2 = GameRecord.builder().build();

        Collection<GameRecord> expGameRecordArr = Arrays.asList(new GameRecord[]{record1, record2});

        when(gameRecordRepository.GetTopRecords(topCount)).thenReturn(expGameRecordArr);

        HardNightGame.DesanCardGamestatistic.dto.GameRecord expGameRecord1 = new HardNightGame.DesanCardGamestatistic.dto.GameRecord();
        HardNightGame.DesanCardGamestatistic.dto.GameRecord expGameRecord2 = new HardNightGame.DesanCardGamestatistic.dto.GameRecord();

        when(modelToDtoConverter.convert(any())).thenAnswer(e -> {
            GameRecord gr = e.getArgument(0);
            if (gr == record1) return expGameRecord1;
            else if (gr == record2) return expGameRecord2;
            else throw new AssertionError("Wrong record");
        });

        // Act
        var assertedGameRecord = gameRecordService.GetTopGameRecords(topCount);
        List<HardNightGame.DesanCardGamestatistic.dto.GameRecord> assertGameRecordList =
                new ArrayList<>(assertedGameRecord);

        // Assert
        assertTrue(expGameRecord1 == assertGameRecordList.get(0));
        assertTrue(expGameRecord2 == assertGameRecordList.get(1));

    }
}
