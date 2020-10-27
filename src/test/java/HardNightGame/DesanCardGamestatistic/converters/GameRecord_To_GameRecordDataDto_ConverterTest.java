package HardNightGame.DesanCardGamestatistic.converters;

import HardNightGame.DesanCardGamestatistic.dtos.GameRecordDataDto;
import HardNightGame.DesanCardGamestatistic.models.GameRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GameRecord_To_GameRecordDataDto_ConverterTest {

    GameRecord_To_GameRecordDataDto_Converter converter;

    Random random = new Random();

    @BeforeEach
    void setUp() {
        converter = new GameRecord_To_GameRecordDataDto_Converter();
    }

    @Test
    void convert_Null() {
        assertNull(converter.convert(null));
    }

    @Test
    void convert_Empty() {
        // Array
        GameRecord gameRecord = new GameRecord();

        // Act
        GameRecordDataDto assertGameRecordDate = converter.convert(gameRecord);

        // Assert
        assertNull(assertGameRecordDate.getName());
        assertNull(assertGameRecordDate.getGameTime());
    }

    @Test
    void convert() {
        // Array
        String expName = "aoeui";
        Integer expGameTime = random.nextInt();

        GameRecord gameRecord = GameRecord.builder().Name(expName).GameTime(expGameTime).build();

        // Act
        GameRecordDataDto assertGameRecordData = converter.convert(gameRecord);

        // Assert
        assertEquals(expName, assertGameRecordData.getName());
        assertEquals(expGameTime, assertGameRecordData.getGameTime());
    }


}
