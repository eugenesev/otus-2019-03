package ru.otus.hw10.api.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "age")
    private int age;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "fk_address_id")
    private HomeAddress homeAddress;

    @OneToMany(mappedBy = "person", cascade = ALL, fetch = LAZY)
    private List<PhoneDataSet> phones = new ArrayList<>();

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public HomeAddress getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(HomeAddress homeAddress) {
        this.homeAddress = homeAddress;
    }

    public List<PhoneDataSet> getPhone() {
        return phones;
    }

    public void setPhone(List<PhoneDataSet> phones) {
        this.phones = phones;
    }

    public void addPhone(PhoneDataSet phone){
        phone.setPerson(this);
        this.phones.add(phone);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address=" + homeAddress +
                ", phone: " + phones +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId() == user.getId() &&
                getAge() == user.getAge() &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getHomeAddress(), user.getHomeAddress()) &&
                phones.containsAll(user.phones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAge(), getHomeAddress(), phones);
    }
}
