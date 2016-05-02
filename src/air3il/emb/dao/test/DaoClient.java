package air3il.emb.dao.test;

import air3il.commun.dto.DtoClient;
import java.util.ArrayList;
import java.util.List;

import air3il.commun.dao.IDaoClient;
import java.util.Map;

public class DaoClient implements IDaoClient {

    // Champs
    private final ManagerDao managerDao;
    private final Map<Integer, DtoClient> mapClients;

    // Constructeur
    public DaoClient(ManagerDao managerDao) {
        this.managerDao = managerDao;
        mapClients = managerDao.getMapClients();
    }

    // Actions
    @Override
    public DtoClient inserer(DtoClient client) {
        client.setId(managerDao.getNextIdClient());
        mapClients.put(client.getId(), client);
        return client;
    }

    @Override
    public DtoClient ajouter(String nom, String prenom, String tel, String email) {
        DtoClient client = new DtoClient(managerDao.getNextIdClient(), nom, prenom, email, tel);
        return client;
    }

    @Override
    public List<DtoClient> rechercher(String nom, String prenom) {
        List<DtoClient> ListeClient = new ArrayList<>();
        int i = 0;
        for (Map.Entry<Integer, DtoClient> KeyClients : mapClients.entrySet()) {
            if (this.mapClients.get(KeyClients).getNom().equals(nom)
                    && this.mapClients.get(KeyClients).getPrenom().equals(prenom)) {
                ListeClient.add(this.mapClients.get(KeyClients));
            }
        }
        return ListeClient;
    }

    @Override
    public DtoClient modifier(DtoClient client) {
        mapClients.replace(client.getId(), client);
        return client;
    }

    @Override
    public void supprimer(int idClient) {
        mapClients.remove(idClient);
    }

    @Override
    public DtoClient retrouver(int idClient) {
        return mapClients.get(idClient);
    }

    @Override
    public List<DtoClient> listerTout() {
        return new ArrayList<>(mapClients.values());
    }
    
}
