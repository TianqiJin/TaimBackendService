package com.taim.taimbackendservice.model.basemodels;

import com.taim.taimbackendservice.model.Address;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * Created by tjin on 2017-07-31.
 */
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
public class UserBaseModel extends BaseModel{

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private String phone;

    @Column
    private String email;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    @Fetch(FetchMode.SUBSELECT)
    private List<Address> address;
}
