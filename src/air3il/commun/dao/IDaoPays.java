package air3il.commun.dao;

import java.util.List;

import air3il.commun.dto.DtoPays;

public interface IDaoPays {

    public void inserer(DtoPays pays);

    public void modifier(DtoPays pays);

    public void supprimer(int idPays);

    public DtoPays retrouver(int idPays);

    public List<DtoPays> listerTout();
}
