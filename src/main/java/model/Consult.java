package model;

import javax.persistence.*;
import java.util.List;

@Entity //pt ca va fi o clasa care are legatura cu baza de date
@Table(name = "consult")
public class Consult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consult_id")
    private Long consultId;

    @Column(name = "date")
    private String date;

    @Column(name = "description")
    private String description;

    @ManyToOne   //relatie bidirectionala
    @JoinColumn(name = "veterinarian_id")
    private Veterinarian veterinarian;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    public Consult() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Veterinarian getVeterinarian() {
        return veterinarian;
    }

    public void setVeterinarian(Veterinarian veterinarian) {
        this.veterinarian = veterinarian;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Consult(String date, String description, Veterinarian veterinarian, Pet pet){
        this.date = date;
        this.description = description;
        this.veterinarian = veterinarian;
        this.pet = pet;

    }

    @Override
    public String toString() {
        return "Consult{" +
                "condultId=" + consultId +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", veterinarian=" + veterinarian +
                ", pet=" + pet +
                '}';
    }
}



