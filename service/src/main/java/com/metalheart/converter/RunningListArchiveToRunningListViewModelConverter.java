package com.metalheart.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metalheart.model.jpa.RunningListArchive;
import com.metalheart.model.rest.response.RunningListViewModel;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RunningListArchiveToRunningListViewModelConverter
    implements Converter<RunningListArchive, RunningListViewModel> {


    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public RunningListViewModel convert(RunningListArchive source) {
        try {
            return objectMapper.readValue(source.getArchive(), RunningListViewModel.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}