package HardNightGame.DesanCardGamestatistic.converter;

import HardNightGame.DesanCardGamestatistic.dto.GameRecordId;
import HardNightGame.DesanCardGamestatistic.model.GameRecord;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ModelGameRecordToDtoGameRecordIdConverterTest {

    ModelGameRecordToDtoGameRecordIdConverter converter = new ModelGameRecordToDtoGameRecordIdConverter();

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
        GameRecordId assertGameRecordId = converter.convert(gameRecord);

        // Assert
        assertNull(assertGameRecordId.getId());
    }

    @Test
    void convert() {
        // Array

        Integer expId = random.nextInt();

        GameRecord gameRecord = GameRecord.builder().id(expId).build();

        // Act

        GameRecordId assertGameRecordId = converter.convert(gameRecord);

        // Assert
        assertEquals(expId, assertGameRecordId.getId());
    }
}
