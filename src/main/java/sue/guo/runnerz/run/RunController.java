package sue.guo.runnerz.run;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//handle rest request
@RestController
@RequestMapping("/api/runs")
public class RunController {

//    private List<Run> runs = new ArrayList<>();
    private final RunRepository runRepository;

    public RunController( RunRepository runRepository) {
        this.runRepository = runRepository;
    }


    @GetMapping("")
    List<Run> findAll(){
        return runRepository.findAll();
    }

    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id){
        System.out.println("test");
        return runRepository.findById(id);
    }
}
