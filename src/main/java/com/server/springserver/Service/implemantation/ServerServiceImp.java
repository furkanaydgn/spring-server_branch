package com.server.springserver.Service.implemantation;

import com.server.springserver.Service.ServerService;
import com.server.springserver.enumaration.Status;
import com.server.springserver.model.Server;
import com.server.springserver.repo.ServerRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImp implements ServerService {

    private final ServerRepo serverRepo;

    @Override
    public Server create(Server server) {
        log.info("Seeing new server....{}",server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepo.save(server);
    }

    @Override
    public Server ping(String ipaddress) throws IOException {
        log.info("Pinging  server  IP....{}",ipaddress);
        Server server = serverRepo.findByIpAddress(ipaddress);
        InetAddress address = InetAddress.getByName(ipaddress);
        server.setStatus(address.isReachable(3000) ? Status.SERVER_UP : Status.SERVER_DOWN);
        serverRepo.save(server);

        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching server.....");
        return serverRepo.findAll(PageRequest.of(0,limit)).toList();
    }

    @Override
    public Server get(long id) {
        log.info("Fetching server by Id....{}",id);

        return serverRepo.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Updating server....{}",server.getName());

        return serverRepo.save(server);
    }

    @Override
    public Boolean delete(long id) {
        log.info("Deleting server....{}",id);
        serverRepo.deleteById(id);
        return Boolean.TRUE;


    }

    private String setServerImageUrl(){
       String[] imageNames={"server1.png","server2.png","server3.png","server4.png"};

       return ServletUriComponentsBuilder.fromCurrentContextPath().path("server/image"+imageNames[new Random().nextInt(4)]).toUriString();

    }
}
