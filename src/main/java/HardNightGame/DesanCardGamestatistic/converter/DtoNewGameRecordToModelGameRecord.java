package HardNightGame.DesanCardGamestatistic.converter;

import HardNightGame.DesanCardGamestatistic.dto.NewGameRecord;
import HardNightGame.DesanCardGamestatistic.model.GameRecord;
import org.springframework.core.convert.converter.Converter;

public class DtoNewGameRecordToModelGameRecord implements Converter<NewGameRecord, GameRecord> {
    @Override
    public GameRecord convert(NewGameRecord source) {
        if (source == null)
            return null;

        return GameRecord.builder().Name(source.getName()).GameTime(source.getGameTime()).build();
    }
}
