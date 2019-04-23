package com.taim.taimbackendservice.model.basemodels;

import com.taim.taimbackendservice.model.Address;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * Created by tjin on 2017-07-31.
 */
@MappedSuperclass
@Data
public class UserBaseModel extends BaseModel{
    public enum UserType{
        INDIVIDUAL("Individual"),
        COMPANY("Company");

        private String value;

        UserType(String vvalue){
            this.value = vvalue;
        }

        public String getValue() {
            return value;
        }

        public static UserType getUserType(String value){
            for (UserType s : UserType.values()){
                if (s.name().equalsIgnoreCase(value)){
                    return s;
                }
            }
            return null;
        }

    }

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
