package com.example.demo.service.impl;

import com.example.demo.model.dto.RegistCardDto;
import com.example.demo.service.FlowService;
import org.flowable.app.api.AppRepositoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.impl.util.Flowable5Util;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.ActivityInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class FlowServiceImpl implements FlowService {

    private final RuntimeService runtimeService;
    private final TaskService taskService;
    private final RepositoryService repositoryService;

    public FlowServiceImpl(RuntimeService runtimeService, TaskService taskService, RepositoryService repositoryService) {
        this.runtimeService = runtimeService;
        this.taskService = taskService;
        this.repositoryService = repositoryService;
    }


    @Override
    public ProcessInstance initiateFlow(RegistCardDto registCardDto){
        Map<String, Object> variables = new HashMap<>();
        variables.put("cif", registCardDto.getCif());
        variables.put("account", registCardDto.getAccount());
        variables.put("fullname", registCardDto.getFullName());
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("processes/demo2.bpmn20.xml")
                .deploy();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("demo2", variables);
//        Task checkCIC = taskService.newTask(UUID.randomUUID().toString());
//        checkCIC.setName("CheckCIC");
//        checkCIC.setParentTaskId(processInstance.getId());
//        taskService.saveTask(checkCIC);
        return processInstance;
    }

    @Override
    public void complete(String id) {
//        taskService.claim(id, "validator");
        taskService.complete(id);
    }
}
