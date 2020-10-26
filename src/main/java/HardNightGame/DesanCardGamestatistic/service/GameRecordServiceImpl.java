package HardNightGame.DesanCardGamestatistic.service;

import HardNightGame.DesanCardGamestatistic.dto.GameRecordData;
import HardNightGame.DesanCardGamestatistic.dto.GameRecordId;
import HardNightGame.DesanCardGamestatistic.model.GameRecord;
import HardNightGame.DesanCardGamestatistic.repository.GameRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class GameRecordServiceImpl implements GameRecordService {
    private final GameRecordRepository gameRecordRepository;
    private final Converter<GameRecord, GameRecordId> modelToDtoIdConverter;
    private final Converter<GameRecordData, GameRecord> dtoDataToModelConverter;

    public GameRecordServiceImpl(GameRecordRepository gameRecordRepository,
                                 Converter<GameRecordData, GameRecord> dtoDataToModelConverter,
                                 Converter<GameRecord, GameRecordId> modelToDtoIdConverter) {
        this.gameRecordRepository = gameRecordRepository;
        this.modelToDtoIdConverter = modelToDtoIdConverter;
        this.dtoDataToModelConverter = dtoDataToModelConverter;
    }

    @Override
    public GameRecordId AddGameRecord(GameRecordData gameRecordData) {
        log.debug("GameRecordServiceImpl.AddGameRecord (GameRecordData:"+gameRecordData);

        var model = dtoDataToModelConverter.convert(gameRecordData);

        var savedGameRecord = gameRecordRepository.save(model);

        return modelToDtoIdConverter.convert(savedGameRecord);
    }
}
