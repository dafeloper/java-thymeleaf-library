package com.developer.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.library.entity.Client;
import com.developer.library.repository.ClientRepository;

@Service
public class ClientService {

	private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void addClient(Long dni, String firstName, String lastName, String address, String phone, boolean subscribed) {

		Client client = new Client();
		client.setDni(dni);
		client.setFirstName(firstName);
		client.setLastName(lastName);
		client.setAddress(address);
		client.setPhone(phone);
		client.setSubscribed(subscribed);

		clientRepository.save(client);
	}
}