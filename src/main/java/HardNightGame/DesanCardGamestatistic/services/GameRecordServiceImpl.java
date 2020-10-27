package HardNightGame.DesanCardGamestatistic.services;

import HardNightGame.DesanCardGamestatistic.dtos.GameRecordDataDto;
import HardNightGame.DesanCardGamestatistic.dtos.GameRecordDto;
import HardNightGame.DesanCardGamestatistic.dtos.GameRecordIdDto;
import HardNightGame.DesanCardGamestatistic.models.GameRecord;
import HardNightGame.DesanCardGamestatistic.repositories.GameRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GameRecordServiceImpl implements GameRecordService {
    private final GameRecordRepository gameRecordRepository;
    private final Converter<GameRecord, GameRecordIdDto> modelToDtoIdConverter;
    private final Converter<GameRecordDataDto, GameRecord> dtoDataToModelConverter;
    private final Converter<GameRecord, GameRecordDto> modelToDtoConverter;

    public GameRecordServiceImpl(GameRecordRepository gameRecordRepository,
                                 Converter<GameRecordDataDto, GameRecord> dtoDataToModelConverter,
                                 Converter<GameRecord, GameRecordIdDto> modelToDtoIdConverter,
                                 Converter<GameRecord, GameRecordDto> modelToDtoConverter) {
        this.gameRecordRepository = gameRecordRepository;
        this.modelToDtoIdConverter = modelToDtoIdConverter;
        this.dtoDataToModelConverter = dtoDataToModelConverter;
        this.modelToDtoConverter = modelToDtoConverter;
    }

    @Override
    public GameRecordIdDto AddGameRecord(GameRecordDataDto gameRecordData) {
        log.debug("GameRecordServiceImpl.AddGameRecord (GameRecordData:" + gameRecordData);

        var model = dtoDataToModelConverter.convert(gameRecordData);

        var savedGameRecord = gameRecordRepository.save(model);

        return modelToDtoIdConverter.convert(savedGameRecord);
    }

    @Override
    public Collection<GameRecordDto> GetTopGameRecords(Integer topRecords) {
        var gameRecords = gameRecordRepository.GetTopRecords(topRecords);
        return gameRecords.stream().map(e->modelToDtoConverter.convert(e)).collect(Collectors.toList());
    }
}
