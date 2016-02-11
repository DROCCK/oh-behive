package drocck.sp.beesandhoney.business.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Connor on 2/10/2016.
 * A task is an abstract goal to be achieved on a date. It
 * contains information about its current stage, and where
 * the task should occur at.
 */

@Entity
@DiscriminatorColumn(name="TASK_TYPE")
public abstract class Task {

    @Id
    @Column(unique = true)
    private long id;

    @OneToMany
    private List<Yard> yardList;

    @Column
    private String currentStage;

    private final String[] stageList;

    public Task(String[] stages) {
        stageList = stages;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Yard> getYardList() {
        return yardList;
    }

    public void setYardList(List<Yard> yardList) {
        this.yardList = yardList;
    }

    public String[] getStageList() {
        return stageList;
    }

    public String getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(String currentStage) {
        // check that new stage is a valid stage
        if (contains(stageList, currentStage))
            this.currentStage = currentStage;
    }

    /**
     * Check if a string is in a list, ensure that stage
     * can only be set to
     *
     * @param list a array of strings
     * @param value a specific string
     * @return True if value is in list
     */
    private boolean contains(String[] list, String value) {
        boolean returnValue = false;
        for (String s : list)
            if (s == value)
                returnValue = true;
        return returnValue;
    }
}
