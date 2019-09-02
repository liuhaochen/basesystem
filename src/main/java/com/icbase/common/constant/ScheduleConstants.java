package com.icbase.common.constant;

/**
 * 任务调度通用常量
 * 
 * @author IC-Base
 */
public interface ScheduleConstants
{
    /**
     * 任务调度参数key
     */
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";

    public enum Status
    {
        /**
         * 正常
         */
        NORMAL(0),
        /**
         * 暂停
         */
        PAUSE(1);

        private int value;

        private Status(int value)
        {
            this.value = value;
        }

        public int getValue()
        {
            return value;
        }
    }

}
