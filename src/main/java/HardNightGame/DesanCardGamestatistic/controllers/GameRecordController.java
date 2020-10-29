package HardNightGame.DesanCardGamestatistic.controllers;

import HardNightGame.DesanCardGamestatistic.dtos.GameRecordDto;
import HardNightGame.DesanCardGamestatistic.dtos.GameRecordDataDto;
import HardNightGame.DesanCardGamestatistic.dtos.GameRecordIdDto;
import HardNightGame.DesanCardGamestatistic.services.GameRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/game-records")
public class GameRecordController {

    private final GameRecordService gameRecordService;

    public GameRecordController(GameRecordService gameRecordService) {
        this.gameRecordService = gameRecordService;
    }

    @PostMapping
    public GameRecordIdDto AddGameRecord(@RequestBody GameRecordDataDto gameRecordData) {
        log.debug("GameRecordController.AddGameRecord GameRecordData:" + gameRecordData);
        return gameRecordService.AddGameRecord(gameRecordData);
    }

    // @Pattern(regexp = "^[1-9]\\d*$")
    @GetMapping()
    public Collection<GameRecordDto> GetGameRecord(
          @RequestParam(required = false) Integer topCount) {
        if (topCount == null) return gameRecordService.GetAllGameRecords();
        return gameRecordService.GetTopGameRecords(topCount);
    }
}
