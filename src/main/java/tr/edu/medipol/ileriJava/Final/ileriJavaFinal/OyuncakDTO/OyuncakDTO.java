package tr.edu.medipol.ileriJava.Final.ileriJavaFinal.OyuncakDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;


@Data
@AllArgsConstructor
public class OyuncakDTO {

    private Long ID;
    private Date alisTarihi;
    private Double alisFiyati;
    private String ad;
    private String tur;
    private Integer desi;
    private String notlar;

}
