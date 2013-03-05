package org.addressbook.challenge.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Version;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class AddressBook {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long addressId;
    
    @Version
    private Long version;
    
    @NotEmpty
    private String name;
    
    @OneToMany(fetch=FetchType.EAGER, cascade= {CascadeType.ALL}, mappedBy="addressBook")
    @OrderBy(value="name")
    private List<Addressee> addressees;

    public Long getId() {
        return addressId;
    }

    public Long getVersion() {
        return version;
    }

    public List<Addressee> getAddressees() {
        return addressees;
    }

    public void setAddressees(List<Addressee> addressees) {
        this.addressees = addressees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addAddressee(Addressee addressee) {
        addressee.setAddressBook(this);
        addressees.add(addressee);        
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((addressId == null) ? 0 : addressId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AddressBook other = (AddressBook) obj;
        if (addressId == null) {
            if (other.addressId != null)
                return false;
        } else if (!addressId.equals(other.addressId))
            return false;
        return true;
    }

    public List<Addressee> findUniqueAddessessFrom(AddressBook addressBookTwo) {
        if (addressBookTwo == null) {
            return new ArrayList<Addressee>();
        }
        return new ArrayList<Addressee>(CollectionUtils.disjunction(this.getAddressees(), addressBookTwo.getAddressees()));
    }    
    
}
