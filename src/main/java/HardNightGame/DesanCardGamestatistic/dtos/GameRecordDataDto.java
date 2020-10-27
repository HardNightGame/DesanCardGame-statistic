package HardNightGame.DesanCardGamestatistic.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GameRecordDataDto {
    private String Name;
    private Integer GameTime;
}
