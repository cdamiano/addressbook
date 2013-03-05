package org.addressbook.challenge.dao;

import java.util.List;

import javax.annotation.Resource;

import org.addressbook.challenge.domain.AddressBook;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AddressBookDaoHibernate implements AddressBookDao {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public List<AddressBook> getAll() {
        Query query = sessionFactory.getCurrentSession().createQuery("from AddressBook");
        return query.list();
    }

    @Transactional(readOnly=false)
    @Override
    public void save(AddressBook addressBook) {
        sessionFactory.getCurrentSession().saveOrUpdate(addressBook);
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public AddressBook get(Long id) {
        return (AddressBook)sessionFactory.getCurrentSession().get(AddressBook.class, id);        
    }

}
