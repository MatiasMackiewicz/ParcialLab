package utn.telefonica.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.telefonica.app.model.Customer;
import utn.telefonica.app.projections.CustomerCalls;
import utn.telefonica.app.projections.CustomerCallsCant;
import utn.telefonica.app.projections.CustomerPriceLastCall;

import java.util.Date;
import java.util.List;

@Repository
public interface    CustomerRepository extends JpaRepository<Customer,Integer> {

    //List<Costumer> findByName(String name);

    List<Customer> findByFirstName(String firstName);

    @Query("SELECT c FROM Customer c WHERE c.username = :username AND c.password = :password")
    Customer findByUsernameAndPassword(@Param("username")String username, @Param("password") String password);

    @Query("SELECT c.calls from Customer c Where c.id = :id")
    CustomerCalls getTotalCalls(Integer id);

    @Query(value = "Select c.first_name name, count(ca.id_call) cant from customers c join calls ca where c.id_customer = ca.customer_id_customer group by (c.id_customer)",nativeQuery = true)
    List<CustomerCallsCant> getCallCant();


    //pARCIALLLLLLLLLLLLLLLL
    @Query(value = "Select c.first_name name,c.dni dni, ca.total_price price from customers c join calls ca where c.id_customer = ca.customer_id_customer and c.id_customer = ?1 order by ca.date_call desc limit 1", nativeQuery = true)
    CustomerPriceLastCall getPriceLastCall(@Param("id") Integer id);
    //parciaaaaaaaaaaaalllllllllllll

}
