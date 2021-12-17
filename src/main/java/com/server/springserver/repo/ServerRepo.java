package com.server.springserver.repo;

import com.server.springserver.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepo extends JpaRepository<Server,Long> {

    Server findByIpAddress(String ipaddress);


}
