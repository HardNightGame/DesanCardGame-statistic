package HardNightGame.DesanCardGamestatistic.converter;

import HardNightGame.DesanCardGamestatistic.dto.GameRecordData;
import HardNightGame.DesanCardGamestatistic.dto.GameRecordId;
import HardNightGame.DesanCardGamestatistic.model.GameRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ModelGameRecordToDtoGameRecordDataConverterTest {

    ModelGameRecordToDtoGameRecordDataConverter converter;

    Random random = new Random();

    @BeforeEach
    void setUp() {
        converter = new ModelGameRecordToDtoGameRecordDataConverter();
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
        GameRecordData assertGameRecordDate = converter.convert(gameRecord);

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
        GameRecordData assertGameRecordData = converter.convert(gameRecord);

        // Assert
        assertEquals(expName, assertGameRecordData.getName());
        assertEquals(expGameTime, assertGameRecordData.getGameTime());
    }


}
