package HardNightGame.DesanCardGamestatistic.converters;

import HardNightGame.DesanCardGamestatistic.dtos.GameRecordDto;
import HardNightGame.DesanCardGamestatistic.dtos.GameRecordDataDto;
import HardNightGame.DesanCardGamestatistic.dtos.GameRecordIdDto;
import HardNightGame.DesanCardGamestatistic.models.GameRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.converter.Converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameRecord_To_GameRecordDto_ConverterTest {

    @Mock
    Converter<GameRecord, GameRecordIdDto> modelToDtoIdConverter;
    @Mock
    Converter<GameRecord, GameRecordDataDto> modelToDtoDataConverter;

    GameRecord_To_GameRecordDto_Converter converter;

    @BeforeEach
    void setUp() {
        converter = new GameRecord_To_GameRecordDto_Converter(modelToDtoIdConverter, modelToDtoDataConverter);
    }

    @Test
    void convert_Null() {
        assertNull(converter.convert(null));
    }

    @Test
    void convert_Empty() {
        // Array
        var gameRecord = new GameRecord();

        // Act
        GameRecordDto assertGameRecord = converter.convert(gameRecord);

        // Assert
        assertNull(assertGameRecord.getId());
        assertNull(assertGameRecord.getData());
    }

    @Test
    void convert() {
        // Array
        var gameRecord = new GameRecord();

        GameRecordIdDto expGameRecordId = new GameRecordIdDto();
        GameRecordDataDto expGameRecordData = new GameRecordDataDto();

        when(modelToDtoIdConverter.convert(gameRecord)).thenReturn(expGameRecordId);
        when(modelToDtoDataConverter.convert(gameRecord)).thenReturn(expGameRecordData);

        // Act
        GameRecordDto assertedGameRecord = converter.convert(gameRecord);

        // Assert
        assertEquals(expGameRecordId, assertedGameRecord.getId());
        assertEquals(expGameRecordData, assertedGameRecord.getData());
    }

}
