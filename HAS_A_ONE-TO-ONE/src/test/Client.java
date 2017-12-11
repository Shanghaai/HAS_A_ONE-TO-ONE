package test;

import java.util.Date;

import model.Vote;
import model.Voter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Client {
	public static void main(String[] args) {
	Configuration cfg =new Configuration().configure("resources/Hibernate.cfg.xml");
	SessionFactory sf =cfg.buildSessionFactory();
	Session s  =sf.openSession();
	Transaction t = s.beginTransaction();
		
	Voter v1 = new Voter();
		v1.setVid(15642);
		v1.setVname("JhonSnow");
		v1.setVage(26);
		
		Vote voting =new Vote();
		voting.setPartyName("BJP");
		voting.setCurrDate(new Date());
		voting.setVoter(v1);
		
		Vote voting2 =new Vote();
		voting2.setPartyName("Congress");
		voting2.setCurrDate(new Date());
		voting2.setVoter(v1);// here we are trying to inser a same object(men) with different parties so we will get
		//org.hibernate.NonUniqueObjectException: A different object with the same identifier value was already associated with the session : [model.Vote#15642]

	
		//Now try to associate same voter for two parties
		s.save(v1);
		s.save(voting);
		s.save(voting2);
		s.beginTransaction().commit();
		sf.close();
		System.out.println("voter voted successfully ");
		
}	
}
