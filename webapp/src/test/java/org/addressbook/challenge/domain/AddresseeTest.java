package org.addressbook.challenge.domain;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import org.addressbook.challenge.builder.AddresseeBuilder;
import org.junit.Test;

public class AddresseeTest {
    
    @Test
    public void testEquals() {
        Addressee addresseeOne = AddresseeBuilder.anAddressee().withName("homer").build();
        Addressee addresseeTwo = AddresseeBuilder.anAddressee().withName("homer").build();
        assertThat(addresseeOne, equalTo(addresseeTwo));
        addresseeTwo = AddresseeBuilder.anAddressee().withName("marge").build();
        assertThat(addresseeOne, not(equalTo(addresseeTwo)));
        assertThat(addresseeOne, not(equalTo(null)));        
    }

}
