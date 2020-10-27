package HardNightGame.DesanCardGamestatistic.converter;

import HardNightGame.DesanCardGamestatistic.dto.GameRecord;
import HardNightGame.DesanCardGamestatistic.dto.GameRecordData;
import HardNightGame.DesanCardGamestatistic.dto.GameRecordId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ModelGameRecordToDtoGameRecordConverter implements Converter<HardNightGame.DesanCardGamestatistic.model.GameRecord, GameRecord> {
    private final Converter<HardNightGame.DesanCardGamestatistic.model.GameRecord, GameRecordId> modelToDtoIdConverter;
    private final Converter<HardNightGame.DesanCardGamestatistic.model.GameRecord, GameRecordData> modelToDtoDataConverter;

    public ModelGameRecordToDtoGameRecordConverter(
            Converter<HardNightGame.DesanCardGamestatistic.model.GameRecord, GameRecordId> modelToDtoIdConverter,
            Converter<HardNightGame.DesanCardGamestatistic.model.GameRecord, GameRecordData> modelToDtoDataConverter) {
        this.modelToDtoIdConverter = modelToDtoIdConverter;
        this.modelToDtoDataConverter = modelToDtoDataConverter;
    }

    @Override
    public GameRecord convert(HardNightGame.DesanCardGamestatistic.model.GameRecord source) {
        if (source == null)
            return null;
        return GameRecord.builder()
                .id(modelToDtoIdConverter.convert(source))
                .data(modelToDtoDataConverter.convert(source))
                .build();
    }
}
