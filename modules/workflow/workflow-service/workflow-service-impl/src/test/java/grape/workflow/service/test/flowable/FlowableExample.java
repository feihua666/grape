package grape.workflow.service.test.flowable;

import cn.hutool.core.img.ImgUtil;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangwei
 * Created at 2019/12/30 10:10
 */
@Slf4j
@SpringBootTest
public class FlowableExample {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Test
    public void deployTest(){
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("holiday-request.bpmn20.xml")
                .deploy();

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployment.getId())
                .singleResult();
        Assert.assertEquals(processDefinition.getName(),"Holiday Request");
    }
    @Test
    public void startTest(){
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("employee", "employee");
        variables.put("nrOfHolidays", 5);
        variables.put("description", "holiday leave");
        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey("holidayRequest", variables);
    }
    @Test
    public void queryTaskTest(){
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("managers").list();
        System.out.println("You have " + tasks.size() + " tasks:");
        for (int i=0; i<tasks.size(); i++) {
            System.out.println((i+1) + ") " + tasks.get(i).getName());
        }

        Task task = tasks.get(0);
        Map<String, Object> processVariables = taskService.getVariables(task.getId());
        System.out.println(processVariables.get("employee") + " wants " +
                processVariables.get("nrOfHolidays") + " of holidays. Do you approve this?");
    }
    @Test
    public void taskComplete(){
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("managers").list();
        Task task = tasks.get(0);
        Map<String,Object> variables = new HashMap<String, Object>();
        variables.put("approved", true);
        taskService.complete(task.getId(), variables);
    }

}
