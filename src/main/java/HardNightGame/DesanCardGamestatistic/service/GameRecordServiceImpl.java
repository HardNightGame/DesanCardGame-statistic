package HardNightGame.DesanCardGamestatistic.service;

import HardNightGame.DesanCardGamestatistic.dto.GameRecordData;
import HardNightGame.DesanCardGamestatistic.dto.GameRecordId;
import HardNightGame.DesanCardGamestatistic.model.GameRecord;
import HardNightGame.DesanCardGamestatistic.repository.GameRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GameRecordServiceImpl implements GameRecordService {
    private final GameRecordRepository gameRecordRepository;
    private final Converter<GameRecord, GameRecordId> modelToDtoIdConverter;
    private final Converter<GameRecordData, GameRecord> dtoDataToModelConverter;
    private final Converter<GameRecord, HardNightGame.DesanCardGamestatistic.dto.GameRecord> modelToDtoConverter;

    public GameRecordServiceImpl(GameRecordRepository gameRecordRepository,
                                 Converter<GameRecordData, GameRecord> dtoDataToModelConverter,
                                 Converter<GameRecord, GameRecordId> modelToDtoIdConverter,
                                 Converter<GameRecord, HardNightGame.DesanCardGamestatistic.dto.GameRecord> modelToDtoConverter) {
        this.gameRecordRepository = gameRecordRepository;
        this.modelToDtoIdConverter = modelToDtoIdConverter;
        this.dtoDataToModelConverter = dtoDataToModelConverter;
        this.modelToDtoConverter = modelToDtoConverter;
    }

    @Override
    public GameRecordId AddGameRecord(GameRecordData gameRecordData) {
        log.debug("GameRecordServiceImpl.AddGameRecord (GameRecordData:" + gameRecordData);

        var model = dtoDataToModelConverter.convert(gameRecordData);

        var savedGameRecord = gameRecordRepository.save(model);

        return modelToDtoIdConverter.convert(savedGameRecord);
    }

    @Override
    public Collection<HardNightGame.DesanCardGamestatistic.dto.GameRecord> GetTopGameRecords(Integer topRecords) {
        var gameRecords = gameRecordRepository.GetTopRecords(topRecords);
        return gameRecords.stream().map(e->modelToDtoConverter.convert(e)).collect(Collectors.toList());
    }
}
