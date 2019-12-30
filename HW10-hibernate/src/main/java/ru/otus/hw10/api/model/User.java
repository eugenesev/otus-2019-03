package ru.otus.hw10.api.model;


import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id")
  private long id;

  @Column(name = "name")
  private String name;

  @Column(name = "age")
  private int age;

  public User() {
  }

  public User(long id, String name) {
    this.id = id;
    this.name = name;
  }

  public User(long id, String name, int age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getAge() { return age; }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}
