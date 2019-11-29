package com.metalheart.test.integration.runninglist;

import com.metalheart.model.jpa.Task;
import com.metalheart.model.rest.request.CreateTaskRequest;
import com.metalheart.model.rest.response.RunningListViewModel;
import com.metalheart.service.DateService;
import com.metalheart.service.RunningListCommandManager;
import com.metalheart.service.RunningListService;
import com.metalheart.service.TaskService;
import com.metalheart.test.integration.BaseIntegrationTest;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.metalheart.model.jpa.TaskStatus.DONE;
import static com.metalheart.model.jpa.TaskStatus.IN_PROGRESS;
import static com.metalheart.model.jpa.TaskStatus.NONE;
import static com.metalheart.model.jpa.TaskStatus.TO_DO;
import static org.junit.Assert.assertEquals;

public class UpdateTaskStatusIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private TaskService taskService;

    @Autowired
    private RunningListCommandManager commandManager;

    @Autowired
    private RunningListService runningListService;

    @MockBean
    private DateService dateService;

    @Test
    public void simpleUpdateStatusTest() {

        // arrange
        CreateTaskRequest createRequest = generateRandomCreateTaskRequest();
        Task createdTask = taskService.createTask(createRequest);

        setDate(this.dateService, 2019, 1, 0);

        // act
        taskService.changeTaskStatus(getChangeStatusRequest(createdTask, 0, IN_PROGRESS));

        // assert
        RunningListViewModel runningList = runningListService.getRunningList();
        List<String> statuses = runningList.getTasks().get(0).getStatus();
        assertEquals(toStingList(IN_PROGRESS, NONE, NONE, NONE, NONE, NONE, NONE), statuses);
    }

    @Test
    public void testUndoCreating() throws Exception {

        // arrange
        CreateTaskRequest createRequest = generateRandomCreateTaskRequest();
        Task createdTask = taskService.createTask(createRequest);

        setDate(this.dateService, 2019, 1, 0);

        // act
        taskService.changeTaskStatus(getChangeStatusRequest(createdTask, 0, DONE));
        commandManager.undo();

        // assert
        RunningListViewModel runningList = runningListService.getRunningList();
        List<String> statuses = runningList.getTasks().get(0).getStatus();
        assertEquals(toStingList(NONE, NONE, NONE, NONE, NONE, NONE, NONE), statuses);
    }

    @Test
    public void testUndoAfterUpdateStatusOperation() throws Exception {

        // arrange
        CreateTaskRequest createRequest = generateRandomCreateTaskRequest();
        Task createdTask = taskService.createTask(createRequest);

        setDate(this.dateService, 2019, 1, 0);

        // act
        taskService.changeTaskStatus(getChangeStatusRequest(createdTask, 0, TO_DO));
        taskService.changeTaskStatus(getChangeStatusRequest(createdTask, 0, DONE));
        commandManager.undo();

        // assert
        RunningListViewModel runningList = runningListService.getRunningList();
        List<String> statuses = runningList.getTasks().get(0).getStatus();
        assertEquals(toStingList(TO_DO, NONE, NONE, NONE, NONE, NONE, NONE), statuses);
    }

    @Test
    public void testRedoCreating() throws Exception {

        // arrange
        CreateTaskRequest createRequest = generateRandomCreateTaskRequest();
        Task createdTask = taskService.createTask(createRequest);

        setDate(this.dateService, 2019, 1, 0);

        // act
        taskService.changeTaskStatus(getChangeStatusRequest(createdTask, 0, DONE));
        commandManager.undo();
        commandManager.redo();

        // assert
        RunningListViewModel runningList = runningListService.getRunningList();
        List<String> statuses = runningList.getTasks().get(0).getStatus();
        assertEquals(toStingList(DONE, DONE, DONE, DONE, DONE, DONE, DONE), statuses);
    }
}