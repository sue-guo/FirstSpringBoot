package sue.guo.runnerz.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

//handle rest request
@RestController
@RequestMapping("/api/runs")
public class RunController {

//    private List<Run> runs = new ArrayList<>();
    private final RunRepository runRepository;

    public RunController( RunRepository runRepository) {
        this.runRepository = runRepository;
    }


    /**
     * Get all records
     * @return
     */
    @GetMapping("")
    List<Run> findAll(){
        return runRepository.findAll();
    }

    /**
     * Get one record by id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id){
        Optional<Run> run = runRepository.findById(id);
        if(run.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Could not find run with id "+id);
        }

        return run.get();
    }

    /**
     *  Post method to add a new record
     * @param run
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void add(@RequestBody Run run){
        runRepository.save(run);

    }

    /**
     *  Put method for update run
     * @param run
     * @param id
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Run run, @PathVariable Integer id){
        runRepository.update(run,id);
    }

    /**
     *  Delete method to remove a run from list
     * @param id
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id){
        runRepository.delete(id);
    }
}
