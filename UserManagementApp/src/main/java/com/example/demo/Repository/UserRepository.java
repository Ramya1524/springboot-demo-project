package com.example.demo.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
	@Query(value="select u from User u where u.username= :username and u.password= :password")
	public User validateUser(String username, String password);

	@Query("select u from User u where u.id= :id")
	public User getUserById(int id);

	@Query("select u from User u where u.username= :username")
	public User getByUsername(String username);

	
//	@Query(value = "select "
//			+ "u.id as id, u.address as address, u.email as email,"
//			+ " u.phone as phone, u.name as name, u.pan as pan,"
//			+ " u.type as type, u.state as state, u.country as country,"
//			+ " u.dob as dob, l.date as date,"
//			+ " l.duration as duration, l.loantype as loantype, l.amount as amount,"
//			+ " l.interest as interest, l.totalamount as totalamount "
//			+ "from demobank.user u left outer join demobank.loan l on u.id=l.userid", nativeQuery = true)
//	public List<Tuple> getUserLoanList();
	
	
	
}
