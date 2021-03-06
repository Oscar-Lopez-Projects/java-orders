package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Agent;
import com.lambdaschool.crudyorders.repositories.AgentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service(value = "agentServices")
public class AgentServiceImpl implements AgentService{
    @Autowired
    AgentsRepository agentrepo;

    @Override
    public Agent findByAgentCode(long id) {
        return agentrepo.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Agent " + id + " Not Found"));
    }

    @Override
    public Agent save(Agent agent){
        return agentrepo.save(agent);
    }

}
