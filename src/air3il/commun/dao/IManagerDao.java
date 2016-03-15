package air3il.commun.dao;

public interface IManagerDao {

    public abstract <T> T getDao(Class<T> type);

    public void transactionBegin();

    public void transactionCommit();

    public void transactionRollback();

    public void threadAfterBegin();

    public void threadBeforeEnd();

    public void close();

}
