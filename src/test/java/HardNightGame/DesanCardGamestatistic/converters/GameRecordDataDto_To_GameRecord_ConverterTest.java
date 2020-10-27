package HardNightGame.DesanCardGamestatistic.converters;

import HardNightGame.DesanCardGamestatistic.dtos.GameRecordDataDto;
import HardNightGame.DesanCardGamestatistic.models.GameRecord;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GameRecordDataDto_To_GameRecord_ConverterTest {

    GameRecordDataDto_To_GameRecord_Converter dataDtoToModelConverter = new GameRecordDataDto_To_GameRecord_Converter();

    Random random = new Random();

    @Test
    void convert_Null() {
        assertNull(dataDtoToModelConverter.convert(null));
    }

    @Test
    void convert_Empty() {
        // Array
        GameRecordDataDto gameRecordData = new GameRecordDataDto();

        // Act
        GameRecord assertGameRecord = dataDtoToModelConverter.convert(gameRecordData);

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

        GameRecordDataDto gameRecordData = GameRecordDataDto.builder().Name(expName).GameTime(expGameTime).build();

        // Act

        GameRecord assertGameRecord = dataDtoToModelConverter.convert(gameRecordData);

        // Assert
        assertEquals(expName, assertGameRecord.getName());
        assertEquals(expGameTime, assertGameRecord.getGameTime());
        assertNull(assertGameRecord.getId());
    }
}
