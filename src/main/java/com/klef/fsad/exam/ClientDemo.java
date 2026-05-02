package com.klef.fsad.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class ClientDemo 
{
    public static void main(String[] args) 
    {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session session = sf.openSession();

        // Insert records
        Transaction tx = session.beginTransaction();

        Booking b1 = new Booking("Ravi", "2026-05-01", "Confirmed");
        Booking b2 = new Booking("Sneha", "2026-05-02", "Pending");

        session.save(b1);
        session.save(b2);

        tx.commit();

        // Fetch all records using HQL
        session.beginTransaction();

        Query q = session.createQuery("from Booking");
        List<Booking> list = q.list();

        for(Booking b : list)
        {
            System.out.println(b.getId()+" "+b.getName()+" "+b.getDate()+" "+b.getStatus());
        }

        session.getTransaction().commit();

        session.close();
        sf.close();
    }
}