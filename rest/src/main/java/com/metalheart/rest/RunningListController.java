package com.metalheart.rest;

import com.metalheart.EndPoint;
import com.metalheart.model.rest.response.RunningListViewModel;
import com.metalheart.service.RunningListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
public class RunningListController {

    @Autowired
    private RunningListService runningListService;

    @GetMapping(path = EndPoint.GET_TASK_LIST, produces = APPLICATION_JSON_VALUE)
    public RunningListViewModel getTaskList() {

        return runningListService.getRunningList();
    }

    @PostMapping(
        path = EndPoint.RUNNING_LIST_ARCHIVE,
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE)
    public RunningListViewModel archive() {

        runningListService.archive();

        return runningListService.getRunningList();
    }

    @GetMapping(
        path = EndPoint.RUNNING_LIST_ARCHIVE_NEXT,
        produces = APPLICATION_JSON_VALUE)
    public RunningListViewModel getNextArchive(@RequestParam Integer year, @RequestParam Integer week) {

        return runningListService.getNext(year, week);
    }

    @GetMapping(
        path = EndPoint.RUNNING_LIST_ARCHIVE_PREV,
        produces = APPLICATION_JSON_VALUE)
    public RunningListViewModel getPrevArchive(@RequestParam Integer year, @RequestParam Integer week) {

        return runningListService.getPrev(year, week);
    }

    @DeleteMapping(
        path = EndPoint.RUNNING_LIST_UNDO,
        produces = APPLICATION_JSON_VALUE)
    public RunningListViewModel undo() {
        return runningListService.undo();
    }

    @PostMapping(
        path = EndPoint.RUNNING_LIST_REDO,
        produces = APPLICATION_JSON_VALUE)
    public RunningListViewModel redo() {
        return runningListService.redo();
    }
}