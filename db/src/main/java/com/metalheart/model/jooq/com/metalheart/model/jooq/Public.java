/*
 * This file is generated by jOOQ.
 */
package com.metalheart.model.jooq;


import com.metalheart.model.jooq.tables.FlywaySchemaHistory;
import com.metalheart.model.jooq.tables.RunningListArchive;
import com.metalheart.model.jooq.tables.Task;
import com.metalheart.model.jooq.tables.WeekWorkLog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Sequence;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 988851268;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.flyway_schema_history</code>.
     */
    public final FlywaySchemaHistory FLYWAY_SCHEMA_HISTORY = com.metalheart.model.jooq.tables.FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY;

    /**
     * The table <code>public.running_list_archive</code>.
     */
    public final RunningListArchive RUNNING_LIST_ARCHIVE = com.metalheart.model.jooq.tables.RunningListArchive.RUNNING_LIST_ARCHIVE;

    /**
     * The table <code>public.task</code>.
     */
    public final Task TASK = com.metalheart.model.jooq.tables.Task.TASK;

    /**
     * The table <code>public.week_work_log</code>.
     */
    public final WeekWorkLog WEEK_WORK_LOG = com.metalheart.model.jooq.tables.WeekWorkLog.WEEK_WORK_LOG;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Sequence<?>> getSequences() {
        List result = new ArrayList();
        result.addAll(getSequences0());
        return result;
    }

    private final List<Sequence<?>> getSequences0() {
        return Arrays.<Sequence<?>>asList(
            Sequences.TASK_ID_SEQ);
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY,
            RunningListArchive.RUNNING_LIST_ARCHIVE,
            Task.TASK,
            WeekWorkLog.WEEK_WORK_LOG);
    }
}
