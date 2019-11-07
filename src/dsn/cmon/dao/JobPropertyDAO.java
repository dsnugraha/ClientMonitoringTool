package dsn.cmon.dao;

import java.util.List;

import dsn.cmon.model.JobProperty;

public interface JobPropertyDAO {
    public void addJobProperty(JobProperty jobProperty);
    public void deleteJobProperty(Long id);
    public void updateJobProperty(JobProperty jobProperty);
    public List<JobProperty> getAllJobProperties();
    public JobProperty getJobPropertyById(Long id);
    public JobProperty getJobPropertyByJobNameStatus(String jobName, int jobStatus);

}
