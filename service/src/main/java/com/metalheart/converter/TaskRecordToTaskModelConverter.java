package com.metalheart.converter;

import com.metalheart.converter.mapper.TaskModelToTaskRecordMapper;
import com.metalheart.model.TaskModel;
import com.metalheart.model.jooq.tables.records.TaskRecord;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TaskRecordToTaskModelConverter implements Converter<TaskRecord, TaskModel> {

    @Override
    public TaskModel convert(TaskRecord source) {
        return TaskModelToTaskRecordMapper.INSTANCE.convert(source);
    }
}