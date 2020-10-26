package HardNightGame.DesanCardGamestatistic.converter;

import HardNightGame.DesanCardGamestatistic.dto.GameRecordData;
import HardNightGame.DesanCardGamestatistic.dto.GameRecordId;
import HardNightGame.DesanCardGamestatistic.model.GameRecord;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ModelGameRecordToDtoGameRecordIdConverterTest {

    ModelGameRecordToDtoGameRecordIdConverter convert = new ModelGameRecordToDtoGameRecordIdConverter();

    Random random = new Random();

    @Test
    void convert_Null() {
        assertNull(convert.convert(null));
    }

    @Test
    void convert_Empty() {
        // Array
        GameRecord gameRecord = new GameRecord();

        // Act
        GameRecordId assertGameRecordId = convert.convert(gameRecord);

        // Assert
        assertNull(assertGameRecordId.getId());
    }

    @Test
    void convert() {
        // Array

        Integer expId = random.nextInt();

        GameRecord gameRecord = GameRecord.builder().id(expId).build();

        // Act

        GameRecordId assertGameRecordId = convert.convert(gameRecord);

        // Assert
        assertEquals(expId, assertGameRecordId.getId());
    }
}
