package sue.guo.runnerz.run;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//handle rest
@RestController
public class RunController {

    private List<Run> runs = new ArrayList<>();

    @RequestMapping("/")
    String home(){
        return "Hello, RunnerZ";
    }

    @GetMapping("/hello")
    String home1(){
        return "Hello, RunnerZ";
    }
}
