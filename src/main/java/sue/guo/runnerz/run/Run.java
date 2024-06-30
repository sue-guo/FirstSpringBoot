package sue.guo.runnerz.run;

import java.time.LocalDateTime;

//record has components as a class with getter and setter
public record Run(
        Integer id,
        String title,
        LocalDateTime startedOn,
        LocalDateTime completeedOn,
        Integer miles,
        Location loaction
) {
}
