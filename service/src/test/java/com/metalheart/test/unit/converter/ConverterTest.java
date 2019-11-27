package com.metalheart.test.unit.converter;


import com.metalheart.converter.TaskModelToTaskConverter;
import com.metalheart.converter.TaskModelToTaskRecordConverter;
import com.metalheart.model.TaskModel;
import java.time.ZonedDateTime;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Test;

public class ConverterTest {

    @Test
    public void testTaskModelToTaskConverter() {

        // arrange
        var converter = new TaskModelToTaskConverter();

        var src = new TaskModel();
        src.setId(RandomUtils.nextInt());
        src.setTitle(RandomStringUtils.random(5));

        // act
        var dst = converter.convert(src);

        // assert
        Assert.assertEquals(src.getId(), dst.getId());
        Assert.assertEquals(src.getTitle(), dst.getTitle());
    }

    @Test
    public void testTaskModelToTaskRecordConverter() {

        // arrange
        var converter = new TaskModelToTaskRecordConverter();

        var src = new TaskModel();
        src.setId(RandomUtils.nextInt());
        src.setTitle(RandomStringUtils.random(5));
        src.setCreatedAt(ZonedDateTime.now());

        // act
        var dst = converter.convert(src);

        // assert
        Assert.assertEquals(src.getId(), dst.getId());
        Assert.assertEquals(src.getTitle(), dst.getTitle());
        Assert.assertEquals(src.getCreatedAt().toInstant(), dst.getCreatedAt().toInstant());
    }
}
