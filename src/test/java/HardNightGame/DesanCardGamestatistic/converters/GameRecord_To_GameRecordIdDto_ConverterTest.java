package HardNightGame.DesanCardGamestatistic.converters;

import HardNightGame.DesanCardGamestatistic.dtos.GameRecordIdDto;
import HardNightGame.DesanCardGamestatistic.models.GameRecord;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GameRecord_To_GameRecordIdDto_ConverterTest {

    GameRecord_To_GameRecordIdDto_Converter converter = new GameRecord_To_GameRecordIdDto_Converter();

    Random random = new Random();

    @Test
    void convert_Null() {
        assertNull(converter.convert(null));
    }

    @Test
    void convert_Empty() {
        // Array
        GameRecord gameRecord = new GameRecord();

        // Act
        GameRecordIdDto assertGameRecordId = converter.convert(gameRecord);

        // Assert
        assertNull(assertGameRecordId.getId());
    }

    @Test
    void convert() {
        // Array

        Integer expId = random.nextInt();

        GameRecord gameRecord = GameRecord.builder().id(expId).build();

        // Act

        GameRecordIdDto assertGameRecordId = converter.convert(gameRecord);

        // Assert
        assertEquals(expId, assertGameRecordId.getId());
    }
}
