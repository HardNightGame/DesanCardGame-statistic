package HardNightGame.DesanCardGamestatistic.converter;

import HardNightGame.DesanCardGamestatistic.dto.GameRecordId;
import HardNightGame.DesanCardGamestatistic.model.GameRecord;
import org.springframework.core.convert.converter.Converter;

public class ModelGameRecordToDtoGameRecordIdConverter implements Converter<GameRecord, GameRecordId> {
    @Override
    public GameRecordId convert(GameRecord source) {
        if (source == null)
            return null;
        return GameRecordId.builder().Id(source.getId()).build();
    }
}
