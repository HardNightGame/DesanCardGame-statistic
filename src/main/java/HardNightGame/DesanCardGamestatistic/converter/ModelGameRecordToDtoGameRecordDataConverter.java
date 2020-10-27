package HardNightGame.DesanCardGamestatistic.converter;

import HardNightGame.DesanCardGamestatistic.dto.GameRecordData;
import HardNightGame.DesanCardGamestatistic.model.GameRecord;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ModelGameRecordToDtoGameRecordDataConverter implements Converter<GameRecord, HardNightGame.DesanCardGamestatistic.dto.GameRecordData> {
    @Override
    public HardNightGame.DesanCardGamestatistic.dto.GameRecordData convert(GameRecord source) {
        if (source == null)
            return null;
        return GameRecordData.builder()
                .Name(source.getName())
                .GameTime(source.getGameTime())
                .build();
    }
}
