package HardNightGame.DesanCardGamestatistic.services;

import HardNightGame.DesanCardGamestatistic.dtos.GameRecordDto;
import HardNightGame.DesanCardGamestatistic.dtos.GameRecordDataDto;
import HardNightGame.DesanCardGamestatistic.dtos.GameRecordIdDto;

import java.util.Collection;

public interface GameRecordService {
    GameRecordIdDto AddGameRecord(GameRecordDataDto gameRecordData);
    Collection<GameRecordDto> GetTopGameRecords(Integer topRecords) throws IllegalArgumentException;
    Collection<GameRecordDto> GetAllGameRecords();
}
