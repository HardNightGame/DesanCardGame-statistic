package HardNightGame.DesanCardGamestatistic.service;

import HardNightGame.DesanCardGamestatistic.dto.GameRecordData;
import HardNightGame.DesanCardGamestatistic.dto.GameRecordId;

public interface GameRecordService {
    GameRecordId AddGameRecord(GameRecordData gameRecordData);
}
