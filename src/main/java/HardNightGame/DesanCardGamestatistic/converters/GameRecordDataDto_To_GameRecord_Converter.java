package HardNightGame.DesanCardGamestatistic.converters;

import HardNightGame.DesanCardGamestatistic.dtos.GameRecordDataDto;
import HardNightGame.DesanCardGamestatistic.models.GameRecord;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GameRecordDataDto_To_GameRecord_Converter implements Converter<GameRecordDataDto, GameRecord> {
    @Override
    public GameRecord convert(GameRecordDataDto source) {
        if (source == null)
            return null;

        return GameRecord.builder().Name(source.getName()).GameTime(source.getGameTime()).build();
    }
}
