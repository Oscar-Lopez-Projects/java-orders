package com.lambda.javaorder.services;

import com.lambda.javaorder.models.Agent;
import com.lambda.javaorder.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "agentService")
public class AgentServiceImpl implements AgentService{
    @Autowired
    AgentRepository agentrepo;

    @Override
    public Agent save(Agent agent){
        return agentrepo.save(agent);
    }

}
