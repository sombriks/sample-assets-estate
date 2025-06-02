package sample.assets.estate.endpoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sample.assets.estate.service.AccessService;

@Controller
@RequestMapping("/")
public class Index {

    private static final Logger LOG = LoggerFactory.getLogger(Index.class);

    private final AccessService accessService;

    public Index(AccessService accessService) {
        this.accessService = accessService;
    }

    @GetMapping
    public ModelAndView index() {
        LOG.info("index page");
        return new ModelAndView("pages/index");
    }

    @GetMapping("menu")
    public ModelAndView menu() {
        LOG.info("menu fragment");
        return new ModelAndView("fragments/nav-bar");
    }
}
