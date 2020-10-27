package HardNightGame.DesanCardGamestatistic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameRecord {
    private GameRecordId id;
    private GameRecordData data;
}
