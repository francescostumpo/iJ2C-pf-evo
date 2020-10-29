package org.ij2c.evolution.service;

import org.bson.types.ObjectId;
import org.ij2c.evolution.model.Client;
import org.ij2c.evolution.repository.ClientRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ClientService {

    @Inject
    ClientRepository clientRepository;

    public boolean insertClientOnMongo(Client client){

        boolean inserted = clientRepository.createClient(client);
        return inserted;
    }

    public Client getClientOnMongo(String id){
        Client client = clientRepository.findById(new ObjectId(id));
        return client;
    }

}
