package br.com.compus.services;

import java.sql.SQLException;
import java.util.List;

import br.com.compus.dao.ClientDAO;
import br.com.compus.models.Client;

public class ClientExistenceValidator {
  
  public static boolean checkExistingClientForCreate(String cpf) {
    System.out.println("CPF:" + cpf);
    try {
      List<Client> clients = ClientDAO.getInstance().getAll();
      for (Client client: clients) {
        System.out.println(client.getCpf());
        if(client.getCpf().equals(cpf)) {
          return true;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
  
  public static boolean checkExistingClientForEdit(String cpf, int id) {
    try {
      List<Client> clients = ClientDAO.getInstance().getAll();
      for (Client client: clients) {
        if(client.getCpf().equals(cpf) && client.getId() != id) {
          return true;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
}
