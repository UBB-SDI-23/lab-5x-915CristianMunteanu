package com.example.Lab5.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    @Column(nullable = false)
    private String lastName;
    @NonNull
    @Column(nullable = false)
    private String firstName;
    @NonNull
    @Column(nullable = false)
    private String specialization;
    @NonNull
    @Min(value = 10000,message = "Salary not in range")
    @Max(value = 700000,message = "Salary not in range")
    @Column(nullable = false)
    private int salary;

    public Doctor() {

    }
    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    @NonNull
    @Pattern(regexp = "^\\(\\d{3}\\) \\d{3}-\\d{4}$",message = "Phone number is incorrect")
    @Column(nullable = false)
    private String contactNumber;
    @JoinColumn
    @ManyToOne
    private Hospital hospital;
    @JsonIgnore
    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
    private List<DoctorPatient> patientRelation;

    public List<DoctorPatient> getPatientRelation() {
        return patientRelation;
    }

    public void setPatientRelation(List<DoctorPatient> patientRelation) {
        this.patientRelation = patientRelation;
    }

    public Doctor(String lastName, String firstName, String specialization, int salary, String contactNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.specialization = specialization;
        this.salary = salary;
        this.contactNumber = contactNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Hospital))
            return false;
        Doctor d = (Doctor) obj;
        return Objects.equals(this.id, d.id) && Objects.equals(this.firstName, d.firstName) && Objects.equals(this.lastName, d.lastName) && Objects.equals(this.contactNumber, d.contactNumber) && Objects.equals(this.salary, d.salary) && Objects.equals(this.specialization,d.specialization);

    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", specialization='" + specialization + '\'' +
                ", salary=" + salary +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstName, this.lastName, this.contactNumber, this.salary);
    }
}
