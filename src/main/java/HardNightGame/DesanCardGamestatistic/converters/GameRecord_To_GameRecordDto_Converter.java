package HardNightGame.DesanCardGamestatistic.converters;

import HardNightGame.DesanCardGamestatistic.dtos.GameRecordDto;
import HardNightGame.DesanCardGamestatistic.dtos.GameRecordDataDto;
import HardNightGame.DesanCardGamestatistic.dtos.GameRecordIdDto;
import HardNightGame.DesanCardGamestatistic.models.GameRecord;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GameRecord_To_GameRecordDto_Converter implements Converter<GameRecord, GameRecordDto> {
    private final Converter<GameRecord, GameRecordIdDto> modelToDtoIdConverter;
    private final Converter<GameRecord, GameRecordDataDto> modelToDtoDataConverter;

    public GameRecord_To_GameRecordDto_Converter(
            Converter<GameRecord, GameRecordIdDto> modelToDtoIdConverter,
            Converter<GameRecord, GameRecordDataDto> modelToDtoDataConverter) {
        this.modelToDtoIdConverter = modelToDtoIdConverter;
        this.modelToDtoDataConverter = modelToDtoDataConverter;
    }

    @Override
    public GameRecordDto convert(GameRecord source) {
        if (source == null)
            return null;
        return GameRecordDto.builder()
                .id(modelToDtoIdConverter.convert(source))
                .data(modelToDtoDataConverter.convert(source))
                .build();
    }
}
