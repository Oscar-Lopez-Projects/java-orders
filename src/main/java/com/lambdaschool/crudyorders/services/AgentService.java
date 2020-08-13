package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Agent;

public interface AgentService {

    Agent findByAgentCode(long id);
    Agent save(Agent agent);

}
