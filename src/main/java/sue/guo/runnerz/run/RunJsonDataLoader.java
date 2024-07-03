package sue.guo.runnerz.run;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

/**
 * Reading data from Json file then insert into database when application init
 */
@Component
public class RunJsonDataLoader implements CommandLineRunner {
    private static final Logger log = Logger.getLogger(RunJsonDataLoader.class.getName());
    private RunRepository runRepository;
    private ObjectMapper objectMapper;
    /**
     * dependency injection
     * @param runRepository
     */
    public RunJsonDataLoader(RunRepository runRepository, ObjectMapper objectMapper) {
        this.runRepository = runRepository;
        this.objectMapper = objectMapper;
    }

    /**
     * Reading data from Json file then insert into database
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        if (runRepository.count() == 0){
            try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/runs.json")){
                Runs allRuns = objectMapper.readValue(inputStream, Runs.class);
                log.info("Loading all runs");
                for (Run run : allRuns.runs()){
                    runRepository.create(run);
                }
            }catch (IOException e){
                System.out.println("RunJsonDataLoader:");
                e.printStackTrace();
            }

        }else {
            log.info("Not loading data from json file");
        }
    }
}
