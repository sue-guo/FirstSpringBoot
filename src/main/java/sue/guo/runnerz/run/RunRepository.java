package sue.guo.runnerz.run;

import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.AssertFalse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {

    private static final Logger logger = LoggerFactory.getLogger(RunRepository.class.getName());
    private final JdbcClient jdbcClient;

    public RunRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    /**
     *  Get all
     * @return
     */
    public List<Run> getAll(){
        return jdbcClient.sql(" select id, title, started_on as startedOn, completed_on as completeedOn, miles, location from run ").query(Run.class).list();
    }

    /**
     * Get by id
     * @param id
     * @return
     */
    public Optional<Run> getById(Integer id){
        return jdbcClient.sql("select id, title, started_on as startedOn, completed_on as completeedOn, miles, location from run where id = ?").param(1,id).query(Run.class).optional();
    }

    /**
     * create new one
     * @param run
     */
    public void create(Run run){
        var updated = jdbcClient.sql("INSERT INTO run (id, title, started_on, completed_on, miles, location) values (?,?,?,?,?,?)")
                .param(1,run.id())
                .param(2,run.title())
                .param(3,run.startedOn())
                .param(4,run.completeedOn())
                .param(5,run.miles())
                .param(6,run.location().toString())
                .update();
        Assert.state(updated == 1,"Failed to create run.");
    }

    /**
     *  delete by id
     * @param id
     */
    public void delete(Integer id){
        var updated = jdbcClient.sql("delete from run where id = ?").param(1, id).update();

        Assert.state(updated == 1,"Failed to delete run.");
    }


    /**
     *  update a tun
     * @param run
     * @param id
     */
    public void update(Run run, Integer id) {
        var updated = jdbcClient.sql("UPDATE run set title = ?,started_on = ?, completed_on= ?,miles=?,location=? where id = ? ")
                .param(1,run.title()).param(2,run.startedOn()).param(3,run.completeedOn())
                .param(4,run.miles()).param(5, run.location()).param(6,id).update();
        Assert.state(updated == 1,"Failed to update run.");
    }

    public Integer count() {
        Integer count = jdbcClient.sql("select count(*) from run").query(Integer.class).single();
        return count;
    }
}
