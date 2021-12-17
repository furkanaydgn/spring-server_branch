package com.server.springserver.Service;

import com.server.springserver.model.Server;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

public interface ServerService {
    Server create(Server server);
    Server ping(String ipaddress) throws IOException;
    Collection<Server>  list(int list);
    Server get(long id);
    Server update(Server server);
    Boolean delete(long id);



}
