package com.metalheart.test.integration.runninglist;

import com.metalheart.model.rest.request.ChangeTaskPriorityRequest;
import com.metalheart.model.rest.response.TaskViewModel;
import com.metalheart.service.RunningListCommandService;
import com.metalheart.service.RunningListService;
import com.metalheart.service.TagService;
import com.metalheart.service.TaskService;
import com.metalheart.test.integration.BaseIntegrationTest;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TaskReorderWithTagsIntegrationTest extends BaseIntegrationTest {

    public static final String PREFIX = "task_";

    @Autowired
    private TaskService taskService;

    @Autowired
    private TagService tagService;

    @Autowired
    private RunningListService runningListService;

    @Autowired
    private RunningListCommandService runningListCommandService;

    @Test
    public void testOrderFromTopToMiddleWithTag() {

        // arrange
        IntStream.range(0, 5).forEach(i -> runningListCommandService.createTask(getCreateTaskRequest(PREFIX + i)));
        IntStream.range(5, 10).forEach(i -> runningListCommandService.createTask(getCreateTaskRequest(PREFIX + i, "tag1")));

        // act
        tagService.selectTag("tag1");
        taskService.reorderTask(ChangeTaskPriorityRequest.builder().startIndex(0).endIndex(3).build());

        // assert
        List<TaskViewModel> tasks = runningListService.getRunningList().getTasks();
        Assert.assertNotNull(tasks);
        Assert.assertEquals(5, tasks.size());
        Assert.assertEquals(PREFIX + 6, tasks.get(0).getTitle());
        Assert.assertEquals(PREFIX + 7, tasks.get(1).getTitle());
        Assert.assertEquals(PREFIX + 8, tasks.get(2).getTitle());
        Assert.assertEquals(PREFIX + 5, tasks.get(3).getTitle());
        Assert.assertEquals(PREFIX + 9, tasks.get(4).getTitle());
    }

    @Test
    public void testOrderFromTopToMiddleWithotTag() {

        // arrange
        IntStream.range(0, 5).forEach(i -> runningListCommandService.createTask(getCreateTaskRequest(PREFIX + i)));
        IntStream.range(5, 10).forEach(i -> runningListCommandService.createTask(getCreateTaskRequest(PREFIX + i, "tag1")));

        // act
        tagService.selectTag("tag1");
        taskService.reorderTask(ChangeTaskPriorityRequest.builder().startIndex(0).endIndex(3).build());
        tagService.removeSelectedTag("tag1");


        // assert
        List<TaskViewModel> tasks = runningListService.getRunningList().getTasks();
        Assert.assertNotNull(tasks);
        Assert.assertEquals(10, tasks.size());

        Assert.assertEquals(PREFIX + 0, tasks.get(0).getTitle());
        Assert.assertEquals(PREFIX + 1, tasks.get(1).getTitle());
        Assert.assertEquals(PREFIX + 2, tasks.get(2).getTitle());
        Assert.assertEquals(PREFIX + 3, tasks.get(3).getTitle());
        Assert.assertEquals(PREFIX + 4, tasks.get(4).getTitle());

        Assert.assertEquals(PREFIX + 6, tasks.get(5).getTitle());
        Assert.assertEquals(PREFIX + 7, tasks.get(6).getTitle());
        Assert.assertEquals(PREFIX + 8, tasks.get(7).getTitle());
        Assert.assertEquals(PREFIX + 5, tasks.get(8).getTitle());
        Assert.assertEquals(PREFIX + 9, tasks.get(9).getTitle());
    }
}
