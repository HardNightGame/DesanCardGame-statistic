package HardNightGame.DesanCardGamestatistic.converter;

import HardNightGame.DesanCardGamestatistic.dto.GameRecordData;
import HardNightGame.DesanCardGamestatistic.model.GameRecord;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoGameRecordDataToModelGameRecord implements Converter<GameRecordData, GameRecord> {
    @Override
    public GameRecord convert(GameRecordData source) {
        if (source == null)
            return null;

        return GameRecord.builder().Name(source.getName()).GameTime(source.getGameTime()).build();
    }
}
