package HardNightGame.DesanCardGamestatistic.converter;

import HardNightGame.DesanCardGamestatistic.dto.GameRecordData;
import HardNightGame.DesanCardGamestatistic.model.GameRecord;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DtoGameRecordDataToModelGameRecordTest {

    DtoGameRecordDataToModelGameRecord dtoGameRecordDataToModelGameRecord = new DtoGameRecordDataToModelGameRecord();

    Random random = new Random();

    @Test
    void convert_Null() {
        assertNull(dtoGameRecordDataToModelGameRecord.convert(null));
    }

    @Test
    void convert_Empty() {
        // Array
        GameRecordData gameRecordData = new GameRecordData();

        // Act
        GameRecord assertGameRecord = dtoGameRecordDataToModelGameRecord.convert(gameRecordData);

        // Assert
        assertNull(assertGameRecord.getId());
        assertNull(assertGameRecord.getName());
        assertNull(assertGameRecord.getGameTime());
    }

    @Test
    void convert() {
        // Array
        String expName = "aoeui";
        Integer expGameTime = random.nextInt();

        GameRecordData gameRecordData = GameRecordData.builder().Name(expName).GameTime(expGameTime).build();

        // Act

        GameRecord assertGameRecord = dtoGameRecordDataToModelGameRecord.convert(gameRecordData);

        // Assert
        assertEquals(expName, assertGameRecord.getName());
        assertEquals(expGameTime, assertGameRecord.getGameTime());
        assertNull(assertGameRecord.getId());
    }
}
