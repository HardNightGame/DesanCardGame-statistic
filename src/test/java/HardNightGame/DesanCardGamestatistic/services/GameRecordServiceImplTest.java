package HardNightGame.DesanCardGamestatistic.services;

import HardNightGame.DesanCardGamestatistic.dtos.GameRecordDataDto;
import HardNightGame.DesanCardGamestatistic.dtos.GameRecordDto;
import HardNightGame.DesanCardGamestatistic.dtos.GameRecordIdDto;
import HardNightGame.DesanCardGamestatistic.models.GameRecord;
import HardNightGame.DesanCardGamestatistic.repositories.GameRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.converter.Converter;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameRecordServiceImplTest {

    @Mock
    GameRecordRepository gameRecordRepository;

    @Mock
    Converter<GameRecordDataDto, GameRecord> dtoDataToModelConverter;

    @Mock
    Converter<GameRecord, GameRecordIdDto> modelToDtoIdConverter;

    @Mock
    Converter<GameRecord, GameRecordDto> modelToDtoConverter;

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
        GameRecordDataDto gameRecordData = GameRecordDataDto.builder()
                .Name(expName)
                .GameTime(expGameTime)
                .build();

        GameRecord convertedGameRecord = GameRecord.builder().build();

        when(dtoDataToModelConverter.convert(gameRecordData)).thenReturn(convertedGameRecord);

        GameRecord savedGameRecord = GameRecord.builder().build();
        when(gameRecordRepository.save(convertedGameRecord)).thenReturn(savedGameRecord);

        GameRecordIdDto expGameRecordId = GameRecordIdDto.builder().build();
        when(modelToDtoIdConverter.convert(savedGameRecord)).thenReturn(expGameRecordId);

        // Act
        GameRecordIdDto assertGameRecordId = gameRecordService.AddGameRecord(gameRecordData);

        // Assert
        assertTrue(expGameRecordId == assertGameRecordId);
    }

    @Test
    void getTopGameRecords_withCount() {
        // Array
        Integer topCount = 2;

        GameRecord record1 = GameRecord.builder().build();
        GameRecord record2 = GameRecord.builder().build();

        Collection<GameRecord> expGameRecordArr = Arrays.asList(new GameRecord[]{record1, record2});

        when(gameRecordRepository.GetTopRecords(topCount)).thenReturn(expGameRecordArr);

        GameRecordDto expGameRecord1 = new GameRecordDto();
        GameRecordDto expGameRecord2 = new GameRecordDto();

        when(modelToDtoConverter.convert(any())).thenAnswer(e -> {
            GameRecord gr = e.getArgument(0);
            if (gr == record1) return expGameRecord1;
            else if (gr == record2) return expGameRecord2;
            else throw new AssertionError("Wrong record");
        });

        // Act
        var assertedGameRecord = gameRecordService.GetTopGameRecords(topCount);
        List<GameRecordDto> assertGameRecordList =
                new ArrayList<>(assertedGameRecord);

        // Assert
        assertTrue(expGameRecord1 == assertGameRecordList.get(0));
        assertTrue(expGameRecord2 == assertGameRecordList.get(1));
        verify(gameRecordRepository, times(1)).GetTopRecords(topCount);
    }

    @Test
    void getAllGameRecords_ReturnAll() {
        // Array

        GameRecord record1 = GameRecord.builder().build();
        GameRecord record2 = GameRecord.builder().build();

        Collection<GameRecord> expGameRecordArr = Arrays.asList(new GameRecord[]{record1, record2});

        when(gameRecordRepository.findAll()).thenReturn(expGameRecordArr);

        GameRecordDto expGameRecord1 = new GameRecordDto();
        GameRecordDto expGameRecord2 = new GameRecordDto();

        when(modelToDtoConverter.convert(any())).thenAnswer(e -> {
            GameRecord gr = e.getArgument(0);
            if (gr == record1) return expGameRecord1;
            else if (gr == record2) return expGameRecord2;
            else throw new AssertionError("Wrong record");
        });

        // Act
        var assertedGameRecord = gameRecordService.GetAllGameRecords();
        List<GameRecordDto> assertGameRecordList =
                new ArrayList<>(assertedGameRecord);

        // Assert
        assertTrue(expGameRecord1 == assertGameRecordList.get(0));
        assertTrue(expGameRecord2 == assertGameRecordList.get(1));
        verify(gameRecordRepository, times(1)).findAll();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void getTopGameRecords_notPositiveCount_ThrowException(Integer notPositiveCount) {
        // Array

        // Act
        assertThrows(IllegalArgumentException.class,
                () -> gameRecordService.GetTopGameRecords(notPositiveCount));

        // Assert
        verifyNoInteractions(gameRecordRepository);
    }
}
