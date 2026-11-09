package com.example.Student.Registry.and.Management;

public class Student {

    private String firstName;
    private String lastName;
    private String email;
    private int Age;
    private int id;

    public Student(String fullName, String email, int Age, int id){
        this.firstName =firstName;
        this.lastName =lastName;
        this.email = email;
        this.Age = Age;
        this.id = id;
    }

    public Student(String fullName, int age, String email, int id) {
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        Age = age;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return Age;
    }

    public int getId() {
        return id;
    }
}
