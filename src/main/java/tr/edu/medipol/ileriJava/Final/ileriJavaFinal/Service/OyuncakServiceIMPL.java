package tr.edu.medipol.ileriJava.Final.ileriJavaFinal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.edu.medipol.ileriJava.Final.ileriJavaFinal.Entity.Oyuncak;
import tr.edu.medipol.ileriJava.Final.ileriJavaFinal.Repo.OyuncakRepository;

import java.util.List;

@Service
public class OyuncakServiceIMPL implements OyuncakService{

    @Autowired
    OyuncakRepository oyuncakRepo;

    @Override
    public Oyuncak Okuma(Long ID) { return oyuncakRepo.getReferenceById(ID); }

    @Override
    public Oyuncak Olusturma(Oyuncak oyuncak) { return oyuncakRepo.save(oyuncak); }

    @Override
    public List<Oyuncak> Listeleme() { return oyuncakRepo.findAll(); }

    @Override
    public void Silme(Long ID) { oyuncakRepo.deleteById(ID); }
}
