package com.ensat.controllers;

import com.ensat.entities.Client;
import com.ensat.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ClientController {

    private ClientService clientService;

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }


    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("clients", clientService.listAllClients());
        System.out.println("Returning rclients:");
        return "clients";
    }


    @RequestMapping("client/{id}")
    public String showClient(@PathVariable Integer id, Model model) {
        model.addAttribute("client", clientService.getClientById(id));
        return "clientshow";
    }


    @RequestMapping("client/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("client", clientService.getClientById(id));
        return "clientform";
    }


    @RequestMapping("client/new")
    public String newClient(Model model) {
        model.addAttribute("client", new Client());
        return "clientform";
    }


    @RequestMapping(value = "client", method = RequestMethod.POST)
    public String saveClient(Client client) {
        clientService.saveClient(client);
        return "redirect:/client/" + client.getId();
    }


    @RequestMapping("client/delete/{id}")
    public String delete(@PathVariable Integer id) {
        clientService.deleteClient(id);
        return "redirect:/clients";
    }

}
