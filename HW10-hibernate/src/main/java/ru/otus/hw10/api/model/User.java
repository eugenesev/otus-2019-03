package ru.otus.hw10.api.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressDataSet homeAddress;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
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

    public AddressDataSet getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(AddressDataSet homeAddress) {
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
}
