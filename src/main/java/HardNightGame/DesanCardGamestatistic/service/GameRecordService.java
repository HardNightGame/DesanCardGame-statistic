package HardNightGame.DesanCardGamestatistic.service;

import HardNightGame.DesanCardGamestatistic.dto.GameRecord;
import HardNightGame.DesanCardGamestatistic.dto.GameRecordData;
import HardNightGame.DesanCardGamestatistic.dto.GameRecordId;

import java.util.Collection;

public interface GameRecordService {
    GameRecordId AddGameRecord(GameRecordData gameRecordData);
    Collection<GameRecord> GetTopGameRecords(Integer topRecords);
}
