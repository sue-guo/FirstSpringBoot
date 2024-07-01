package sue.guo.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {

    private List<Run> runs = new ArrayList<>();

    /**
     * Get all record from list
     * @return
     */
    List<Run> findAll(){
        return runs;
    }

    //This annotation method means it will be executed after all dependency injection is done
    @PostConstruct
    private void init(){
        System.out.println("init runs");
        runs.add(
                new Run(
                        1,
                        "Monday",
                        LocalDateTime.now(),
                        LocalDateTime.now().plus(1, ChronoUnit.HOURS),
                        6,
                        Location.INDOOR
                )
        );
        runs.add(
                new Run(
                        2,
                        "Tuseday",
                        LocalDateTime.now(),
                        LocalDateTime.now().plus(1, ChronoUnit.HOURS),
                        8,
                        Location.OUTDOOR
                )
        );
        System.out.println(runs.size());
    }

    Optional<Run> findById(Integer id) {
        return runs.stream().filter(run -> run.id().equals(id)).findFirst();
    }

    /**
     * add a new run
     * @param run
     */
    void save(Run run) {
        runs.add(run);
    }

    /**
     * Update the value of Run
     * @param run run to update
     * @param id id that going to update
     */
    void update(Run run, Integer id) {
        Optional<Run> existingrun = findById(id);
        if(existingrun.isPresent()){
            runs.set(runs.indexOf(existingrun.get()),run);
        }
    }

    /**
     * Delete run from list
     * @param id
     */
    void delete(Integer id) {
        runs.removeIf(run -> run.id().equals(id));
    }
}
