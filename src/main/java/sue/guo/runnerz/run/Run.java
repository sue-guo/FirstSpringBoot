package sue.guo.runnerz.run;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

//record has components as a class with getter and setter
public record Run(
        Integer id,
        @NotNull
        String title,
        LocalDateTime startedOn,
        LocalDateTime completeedOn,
        @Positive
        Integer miles,
        Location loaction
) {
    // validation
    public Run{
        if(!completeedOn().isAfter(startedOn())){
            throw new IllegalArgumentException("Started on cannot be after completed on");
        }
    }
}
