package HardNightGame.DesanCardGamestatistic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NewGameRecord {
    private String Name;
    private Integer GameTime;
}
