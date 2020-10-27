package HardNightGame.DesanCardGamestatistic.converters;

import HardNightGame.DesanCardGamestatistic.dtos.GameRecordDataDto;
import HardNightGame.DesanCardGamestatistic.models.GameRecord;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GameRecord_To_GameRecordDataDto_Converter implements Converter<GameRecord, GameRecordDataDto> {
    @Override
    public GameRecordDataDto convert(GameRecord source) {
        if (source == null)
            return null;
        return GameRecordDataDto.builder()
                .Name(source.getName())
                .GameTime(source.getGameTime())
                .build();
    }
}
