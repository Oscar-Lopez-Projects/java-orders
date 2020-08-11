package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Agent;
import com.lambdaschool.crudyorders.repositories.AgentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "agentService")
public class AgentServiceImpl implements AgentService{
    @Autowired
    AgentsRepository agentrepo;

    @Override
    public Agent save(Agent agent){
        return agentrepo.save(agent);
    }

}
