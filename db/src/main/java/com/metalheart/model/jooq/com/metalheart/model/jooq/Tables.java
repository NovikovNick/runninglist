/*
 * This file is generated by jOOQ.
 */
package com.metalheart.model.jooq;


import com.metalheart.model.jooq.tables.FlywaySchemaHistory;
import com.metalheart.model.jooq.tables.RunningListArchive;
import com.metalheart.model.jooq.tables.Task;
import com.metalheart.model.jooq.tables.WeekWorkLog;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in public
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>public.flyway_schema_history</code>.
     */
    public static final FlywaySchemaHistory FLYWAY_SCHEMA_HISTORY = com.metalheart.model.jooq.tables.FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY;

    /**
     * The table <code>public.running_list_archive</code>.
     */
    public static final RunningListArchive RUNNING_LIST_ARCHIVE = com.metalheart.model.jooq.tables.RunningListArchive.RUNNING_LIST_ARCHIVE;

    /**
     * The table <code>public.task</code>.
     */
    public static final Task TASK = com.metalheart.model.jooq.tables.Task.TASK;

    /**
     * The table <code>public.week_work_log</code>.
     */
    public static final WeekWorkLog WEEK_WORK_LOG = com.metalheart.model.jooq.tables.WeekWorkLog.WEEK_WORK_LOG;
}