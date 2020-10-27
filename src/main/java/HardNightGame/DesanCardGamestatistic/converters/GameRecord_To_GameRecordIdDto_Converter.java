package HardNightGame.DesanCardGamestatistic.converters;

import HardNightGame.DesanCardGamestatistic.dtos.GameRecordIdDto;
import HardNightGame.DesanCardGamestatistic.models.GameRecord;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GameRecord_To_GameRecordIdDto_Converter implements Converter<GameRecord, GameRecordIdDto> {
    @Override
    public GameRecordIdDto convert(GameRecord source) {
        if (source == null)
            return null;
        return GameRecordIdDto.builder().Id(source.getId()).build();
    }
}
