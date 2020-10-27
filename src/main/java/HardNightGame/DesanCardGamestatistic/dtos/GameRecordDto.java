package HardNightGame.DesanCardGamestatistic.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameRecordDto {
    private GameRecordIdDto id;
    private GameRecordDataDto data;
}
