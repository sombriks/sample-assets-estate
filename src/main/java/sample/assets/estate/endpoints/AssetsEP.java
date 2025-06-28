package sample.assets.estate.endpoints;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sample.assets.estate.endpoints.base.BaseEP;
import sample.assets.estate.models.Group;
import sample.assets.estate.repositories.AssetStatuses;
import sample.assets.estate.repositories.ChangeReasons;
import sample.assets.estate.services.AccessService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("assets")
public class AssetsEP extends BaseEP {

    private final AssetStatuses assetStatuses;
    private final ChangeReasons changeReasons;

    public AssetsEP(
            AssetStatuses assetStatuses,
            ChangeReasons changeReasons,
            AccessService accessService) {
        super(accessService, "Admin", "Manager", "Basic");
        this.assetStatuses = assetStatuses;
        this.changeReasons = changeReasons;
    }

    @GetMapping("status/options")
    public ModelAndView statusOptions() {
        Map<String, Object> model = Map.of("statuses", assetStatuses.findAll());
        return new ModelAndView("controls/select-asset-status", model);
    }

    @GetMapping("change-reasons/options")
    public ModelAndView changeReasonsOptions() {
        Map<String, Object> model = Map.of("reasons", changeReasons.findAll());
        return new ModelAndView("controls/select-asset-change-reason", model);
    }
}
