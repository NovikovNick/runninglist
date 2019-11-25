/*
 * This file is generated by jOOQ.
 */
package com.metalheart.model.jooq;


import com.metalheart.model.jooq.tables.FlywaySchemaHistory;
import com.metalheart.model.jooq.tables.RunningListArchive;
import com.metalheart.model.jooq.tables.Task;
import com.metalheart.model.jooq.tables.WeekWorkLog;
import com.metalheart.model.jooq.tables.records.FlywaySchemaHistoryRecord;
import com.metalheart.model.jooq.tables.records.RunningListArchiveRecord;
import com.metalheart.model.jooq.tables.records.TaskRecord;
import com.metalheart.model.jooq.tables.records.WeekWorkLogRecord;

import javax.annotation.Generated;

import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>public</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<TaskRecord, Integer> IDENTITY_TASK = Identities0.IDENTITY_TASK;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<FlywaySchemaHistoryRecord> FLYWAY_SCHEMA_HISTORY_PK = UniqueKeys0.FLYWAY_SCHEMA_HISTORY_PK;
    public static final UniqueKey<RunningListArchiveRecord> RUNNING_LIST_ARCHIVE_PKEY = UniqueKeys0.RUNNING_LIST_ARCHIVE_PKEY;
    public static final UniqueKey<TaskRecord> TASK_PKEY = UniqueKeys0.TASK_PKEY;
    public static final UniqueKey<TaskRecord> TASK_TITLE_KEY = UniqueKeys0.TASK_TITLE_KEY;
    public static final UniqueKey<WeekWorkLogRecord> WEEK_WORK_LOG_PKEY = UniqueKeys0.WEEK_WORK_LOG_PKEY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<TaskRecord, Integer> IDENTITY_TASK = Internal.createIdentity(Task.TASK, Task.TASK.ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<FlywaySchemaHistoryRecord> FLYWAY_SCHEMA_HISTORY_PK = Internal.createUniqueKey(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, "flyway_schema_history_pk", FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_RANK);
        public static final UniqueKey<RunningListArchiveRecord> RUNNING_LIST_ARCHIVE_PKEY = Internal.createUniqueKey(RunningListArchive.RUNNING_LIST_ARCHIVE, "running_list_archive_pkey", RunningListArchive.RUNNING_LIST_ARCHIVE.YEAR, RunningListArchive.RUNNING_LIST_ARCHIVE.WEEK);
        public static final UniqueKey<TaskRecord> TASK_PKEY = Internal.createUniqueKey(Task.TASK, "task_pkey", Task.TASK.ID);
        public static final UniqueKey<TaskRecord> TASK_TITLE_KEY = Internal.createUniqueKey(Task.TASK, "task_title_key", Task.TASK.TITLE);
        public static final UniqueKey<WeekWorkLogRecord> WEEK_WORK_LOG_PKEY = Internal.createUniqueKey(WeekWorkLog.WEEK_WORK_LOG, "week_work_log_pkey", WeekWorkLog.WEEK_WORK_LOG.TASK_ID, WeekWorkLog.WEEK_WORK_LOG.DAY_INDEX);
    }
}
