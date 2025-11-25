package sum25.SE196853.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sum25.SE196853.entity.SonyAccounts;

@Repository
public interface SonyAccountsRepository extends JpaRepository<SonyAccounts, Integer> {
    SonyAccounts  findByAccountID(Integer id);
    SonyAccounts findByPhoneAndPassword(String phone, String password);
}
