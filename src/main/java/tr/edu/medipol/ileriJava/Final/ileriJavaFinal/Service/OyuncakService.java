package tr.edu.medipol.ileriJava.Final.ileriJavaFinal.Service;

import tr.edu.medipol.ileriJava.Final.ileriJavaFinal.Entity.Oyuncak;

import java.util.List;

public interface OyuncakService {

    Oyuncak Okuma(Long ID);

    Oyuncak Olusturma(Oyuncak oyuncak);

    List<Oyuncak> Listeleme();

    void Silme(Long ID);

}
