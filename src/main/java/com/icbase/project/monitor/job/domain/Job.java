package com.icbase.project.monitor.job.domain;

import java.io.Serializable;

import com.icbase.framework.web.domain.BaseEntity;

/**
 * 定时任务调度信息 sys_job
 * 
 * @author IC-Base
 */
public class Job extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 任务ID */
    private Long jobId;
    /** 任务名称 */
    private String jobName;
    /** 任务组名 */
    private String jobGroup;
    /** 任务方法 */
    private String methodName;
    /** 方法参数 */
    private String params;
    /** cron执行表达式 */
    private String cronExpression;
    /** 状态（0正常 1暂停） */
    private int status;

    public Long getJobId()
    {
        return jobId;
    }

    public void setJobId(Long jobId)
    {
        this.jobId = jobId;
    }

    public String getJobName()
    {
        return jobName;
    }

    public void setJobName(String jobName)
    {
        this.jobName = jobName;
    }

    public String getJobGroup()
    {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup)
    {
        this.jobGroup = jobGroup;
    }

    public String getMethodName()
    {
        return methodName;
    }

    public void setMethodName(String methodName)
    {
        this.methodName = methodName;
    }

    public String getParams()
    {
        return params;
    }

    public void setParams(String params)
    {
        this.params = params;
    }

    public String getCronExpression()
    {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression)
    {
        this.cronExpression = cronExpression;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "Job [jobId=" + jobId + ", jobName=" + jobName + ", jobGroup=" + jobGroup + ", methodName=" + methodName
                + ", params=" + params + ", cronExpression=" + cronExpression + ", status=" + status + "]";
    }

}
