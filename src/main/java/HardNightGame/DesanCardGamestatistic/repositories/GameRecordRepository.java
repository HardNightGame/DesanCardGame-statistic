package HardNightGame.DesanCardGamestatistic.repositories;

import HardNightGame.DesanCardGamestatistic.models.GameRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface GameRecordRepository extends CrudRepository<GameRecord, Integer> {

    @Query(value = "SELECT TOP :top * FROM GAME_RECORD ORDER BY GAME_TIME",
           nativeQuery = true)
    Collection<GameRecord> GetTopRecords(@Param("top") Integer topRecords);
}
