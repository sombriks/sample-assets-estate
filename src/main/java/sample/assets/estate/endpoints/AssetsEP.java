package sample.assets.estate.endpoints;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sample.assets.estate.repositories.AssetStatuses;
import sample.assets.estate.repositories.ChangeReasons;

import java.util.Map;

@Controller
@RequestMapping("assets")
public class AssetsEP {

    private final AssetStatuses assetStatuses;
    private final ChangeReasons changeReasons;

    public AssetsEP(
            AssetStatuses assetStatuses,
            ChangeReasons changeReasons) {
        this.assetStatuses = assetStatuses;
        this.changeReasons = changeReasons;
    }

    @GetMapping("status/options")
    public ModelAndView statusOptions(
            @RequestParam(required = false, defaultValue = "0") Long defaultValue) {
        Map<String, Object> model = Map.of(
                "statuses", assetStatuses.findAll(),
                "defaultValue", defaultValue);
        return new ModelAndView("controls/select-asset-status", model);
    }

    @GetMapping("change-reasons/options")
    public ModelAndView changeReasonsOptions(
            @RequestParam(required = false, defaultValue = "0") Long defaultValue) {
        Map<String, Object> model = Map.of(
                "reasons", changeReasons.findAll(),
                "defaultValue", defaultValue);
        return new ModelAndView("controls/select-asset-change-reason", model);
    }
}
