package HardNightGame.DesanCardGamestatistic.repository;

import HardNightGame.DesanCardGamestatistic.model.GameRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.awt.*;
import java.util.*;
import java.util.List;

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

    @Test
    public void GetTopRecords() {
        // Array
        GameRecord gameRecord1 = AddRecord(1,"aoeui");
        GameRecord gameRecord2 = AddRecord(5,"stnhd");
        GameRecord gameRecord3 = AddRecord(2,"ejukix");
        GameRecord gameRecord4 = AddRecord(4,"bmw");
        GameRecord gameRecord5 = AddRecord(3,"lrcg");

        // Act
        List<GameRecord> gameRecords = new ArrayList<>(gameRecordRepository.GetTopRecords(3));

        // Assert
        assertEquals(gameRecord1,gameRecords.get(0));
        assertEquals(gameRecord3,gameRecords.get(1));
        assertEquals(gameRecord5,gameRecords.get(2));

        List<GameRecord> gameRecords2 = new ArrayList<GameRecord>((Collection<? extends GameRecord>) gameRecordRepository.findAll());
        assertEquals(gameRecord1,gameRecords2.get(0));
        assertEquals(gameRecord2,gameRecords2.get(1));
        assertEquals(gameRecord3,gameRecords2.get(2));
        assertEquals(gameRecord4,gameRecords2.get(3));
        assertEquals(gameRecord5,gameRecords2.get(4));
    }

    private GameRecord AddRecord(Integer gameTime, String name) {
        return gameRecordRepository.save(GameRecord.builder().Name(name).GameTime(gameTime).build());
    }
}
