package HardNightGame.DesanCardGamestatistic.converter;

import HardNightGame.DesanCardGamestatistic.dto.NewGameRecord;
import HardNightGame.DesanCardGamestatistic.model.GameRecord;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DtoNewGameRecordToModelGameRecordTest {

    DtoNewGameRecordToModelGameRecord dtoNewGameRecordToModelGameRecord = new DtoNewGameRecordToModelGameRecord();

    Random random = new Random();

    @Test
    void convert_Null() {
        assertNull(dtoNewGameRecordToModelGameRecord.convert(null));
    }

    @Test
    void convert_Empty() {
        // Array
        NewGameRecord newGameRecord = new NewGameRecord();

        // Act
        GameRecord assertGameRecord = dtoNewGameRecordToModelGameRecord.convert(newGameRecord);

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

        NewGameRecord newGameRecord = NewGameRecord.builder().Name(expName).GameTime(expGameTime).build();

        // Act

        GameRecord assertGameRecord = dtoNewGameRecordToModelGameRecord.convert(newGameRecord);

        // Assert
        assertEquals(expName, assertGameRecord.getName());
        assertEquals(expGameTime, assertGameRecord.getGameTime());
        assertNull(assertGameRecord.getId());
    }
}
