package com.myweb.dao.jpa.hibernate;

import com.myweb.pojo.Bookstore;
import com.myweb.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryDefinition(domainClass = Bookstore.class, idClass = Integer.class)
public interface BookstoreRepository extends JpaRepository<Bookstore,Integer> {

    public List<Bookstore> findAllByOwnerid(int owerid);

    public int deleteAllByBookidAndOwneridAndStauts(int bookid,int ownerid,int status);

    public List<Bookstore> findAllByBookidAndOwneridAndStauts(int bookid,int ownerid,int status);

    public List<Bookstore> findAllByOwneridAndStauts(int owerid,int status);
}