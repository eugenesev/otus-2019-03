package ru.otus.hw10.api.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "addresses")
public class HomeAddress {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "street")
    private String street;

    @OneToOne(mappedBy = "homeAddress",
            cascade = CascadeType.ALL)
    private User person;

    public HomeAddress() {
    }

    public HomeAddress(String street) {
        this.street = street;
    }

    public long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public User getPerson() {
        return person;
    }

    public void setPerson(User person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "HomeAddress{" +
                "id=" + id +
                ", street='" + street + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HomeAddress that = (HomeAddress) o;
        return getId() == that.getId() &&
                Objects.equals(getStreet(), that.getStreet()) &&
                Objects.equals(getPerson(), that.getPerson());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStreet(), getPerson());
    }
}
