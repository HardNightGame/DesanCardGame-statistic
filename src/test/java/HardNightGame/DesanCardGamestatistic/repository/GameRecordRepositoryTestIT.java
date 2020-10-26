package HardNightGame.DesanCardGamestatistic.repository;

import HardNightGame.DesanCardGamestatistic.model.GameRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.awt.*;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(showSql = true)
class GameRecordRepositoryTestIT {
    @Autowired
    GameRecordRepository gameRecordRepository;

    Random random = new Random();

    @Test
    public void SaveGameRecord_SetId() {
        // Array
        Integer expGameTime = random.nextInt();
        String expName = "aoeui";

        GameRecord gameRecord = GameRecord.builder().Name(expName).GameTime(expGameTime).build();

        // Act
        GameRecord savedGameRecord = gameRecordRepository.save(gameRecord);

        // Assert
        assertEquals(1, savedGameRecord.getId());
        assertTrue(gameRecordRepository.existsById(1));
    }

    @Test
    public void SaveGameRecord_SaveDataCorrect() {
        // Array
        Integer expGameTime = random.nextInt();
        String expName = "aoeui";

        GameRecord gameRecord = GameRecord.builder().Name(expName).GameTime(expGameTime).build();

        // Act
        GameRecord savedGameRecord = gameRecordRepository.save(gameRecord);

        // Assert
        Optional<GameRecord> assertedGameRecordOptional = gameRecordRepository.findById(savedGameRecord.getId());

        assertTrue(assertedGameRecordOptional.isPresent());

        GameRecord assertedGameRecord = assertedGameRecordOptional.get();
        assertEquals(expGameTime, assertedGameRecord.getGameTime());
        assertEquals(expName, assertedGameRecord.getName());
    }
}
