package sue.guo.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RunRepository {

    private List<Run> runs = new ArrayList<>();

    List<Run> findAll(){
        return runs;
    }

    //This method will be executed after all dependency injection is done
    @PostConstruct
    private void init(){
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
    }

}
