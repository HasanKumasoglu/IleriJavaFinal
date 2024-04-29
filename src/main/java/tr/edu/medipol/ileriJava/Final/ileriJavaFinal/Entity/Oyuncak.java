package tr.edu.medipol.ileriJava.Final.ileriJavaFinal.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Oyuncak {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    private Date alisTarihi;
    private Double alisFiyati;
    private String ad;
    private String tur;
    private Integer desi;
    private String notlar;

}
