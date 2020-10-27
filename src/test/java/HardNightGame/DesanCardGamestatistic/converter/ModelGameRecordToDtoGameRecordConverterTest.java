package HardNightGame.DesanCardGamestatistic.converter;

import HardNightGame.DesanCardGamestatistic.dto.GameRecord;
import HardNightGame.DesanCardGamestatistic.dto.GameRecordData;
import HardNightGame.DesanCardGamestatistic.dto.GameRecordId;
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
class ModelGameRecordToDtoGameRecordConverterTest {

    @Mock
    Converter<HardNightGame.DesanCardGamestatistic.model.GameRecord, GameRecordId> modelToDtoIdConverter;
    @Mock
    Converter<HardNightGame.DesanCardGamestatistic.model.GameRecord, GameRecordData> modelToDtoDataConverter;

    ModelGameRecordToDtoGameRecordConverter converter;

    @BeforeEach
    void setUp() {
        converter = new ModelGameRecordToDtoGameRecordConverter(modelToDtoIdConverter, modelToDtoDataConverter);
    }

    @Test
    void convert_Null() {
        assertNull(converter.convert(null));
    }

    @Test
    void convert_Empty() {
        // Array
        var gameRecord = new HardNightGame.DesanCardGamestatistic.model.GameRecord();

        // Act
        GameRecord assertGameRecord = converter.convert(gameRecord);

        // Assert
        assertNull(assertGameRecord.getId());
        assertNull(assertGameRecord.getData());
    }

    @Test
    void convert() {
        // Array
        var gameRecord = new HardNightGame.DesanCardGamestatistic.model.GameRecord();

        GameRecordId expGameRecordId = new GameRecordId();
        GameRecordData expGameRecordData = new GameRecordData();

        when(modelToDtoIdConverter.convert(gameRecord)).thenReturn(expGameRecordId);
        when(modelToDtoDataConverter.convert(gameRecord)).thenReturn(expGameRecordData);

        // Act
        GameRecord assertedGameRecord = converter.convert(gameRecord);

        // Assert
        assertEquals(expGameRecordId, assertedGameRecord.getId());
        assertEquals(expGameRecordData, assertedGameRecord.getData());
    }

}
