package es.atrapandocucarachas.sqlite.model;

/**
 * @author Alejandro Platas Mallo
 * @version 1.00
 * @since 26/5/16
 */

public class Person {
    //<editor-fold desc="PROPERTIES">
    private long id;
    private String name;
    private String surname;
    private int age;
    private String phone;
    private String address;
    //</editor-fold>

    //<editor-fold desc="CONSTRUCTORS">
    public Person(String name, String surname, int age, String phone, String address) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phone = phone;
        this.address = address;
    }

    public Person(long id, String name, String surname, int age, String phone, String address) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phone = phone;
        this.address = address;
    }
    //</editor-fold>

    //<editor-fold desc="GETTERS AND SETTERS">
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    //</editor-fold>

    //<editor-fold desc="TO_STRING">
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
    //</editor-fold>
}
