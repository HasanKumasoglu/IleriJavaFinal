package tr.edu.medipol.ileriJava.Final.ileriJavaFinal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tr.edu.medipol.ileriJava.Final.ileriJavaFinal.Entity.Oyuncak;
import tr.edu.medipol.ileriJava.Final.ileriJavaFinal.OyuncakDTO.OyuncakDTO;
import tr.edu.medipol.ileriJava.Final.ileriJavaFinal.Service.OyuncakService;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class OyuncakController {

    @Autowired
    OyuncakService oyuncakServ;

    @PostMapping("/ekle")
    public String oyuncakEkle(@RequestBody OyuncakDTO oyuncakDTO) {

        log.info("oyuncakEkle servisi çağrıldı. Parameterler" + oyuncakDTO);

        try {
            log.debug("oyuncakEkle servsinden. oyuncak oluşturuldu.");
            Oyuncak oyuncak = new Oyuncak();
            oyuncak.setAlisTarihi(oyuncakDTO.getAlisTarihi());
            oyuncak.setAlisFiyati(oyuncakDTO.getAlisFiyati());
            oyuncak.setAd(oyuncakDTO.getAd());
            oyuncak.setTur(oyuncakDTO.getTur());
            oyuncak.setDesi(oyuncakDTO.getDesi());
            oyuncak.setNotlar(oyuncakDTO.getNotlar());
            log.debug("oyuncakEkle servisi nesnenin içinde değerler atıldı");
            Oyuncak kayitliOyuncak = oyuncakServ.Olusturma(oyuncak);
            log.debug("oyuncakEkle servisi oyuncakServ üzerinden oyuncak adlı nesneyi kayıt etti");
            return kayitliOyuncak.getAd() + " Adındaki oyuncak nesnesi database'ye kayıt edildi";
        } catch (Exception e) {
            log.error("oyuncakOluştur servisi hata aldı. Parametereis: " + oyuncakDTO + "Hatası: " +e.getMessage());
        }
        return null;
    }

    @GetMapping("/getir")
    public OyuncakDTO oyuncakbul(Long ID) {

        log.info("oyuncakbul servisi çağrıldı. Parameterler" + ID);

        try {

            log.debug("oyuncakbul servsinden. oyuncak aranıyor.");

            Oyuncak oyuncak = oyuncakServ.Okuma(ID);

            log.debug("oyuncakbul servisi oyuncakServ üzerinden oyuncak adlı nesneyi ID'si üzerinden buldu");
            OyuncakDTO oyuncakDTO = new OyuncakDTO(oyuncak.getID(), oyuncak.getAlisTarihi(),oyuncak.getAlisFiyati(),oyuncak. getAd(),oyuncak.getTur(),oyuncak.getDesi(),oyuncak.getNotlar());
            log.debug("Dönüşüm türü OyuncakDTO olduğu için Dönüştürdük");

            return oyuncakDTO;
        }   catch (Exception e) {
            log.error("oyuncakbul servisi hata aldı. Parametereis: " + ID + " Hatası: " +e.getMessage());
        }
        return null;
    }

    @DeleteMapping("/sil")
    public String oyuncakSil(Long ID) {

        log.info("oyuncakSil servisi çağrıldı. Parameterler" + ID);

        try {

            if (ID == null) {
                log.error("oyuncakSil servisi hata aldı. Parametere: " + ID + " Hatası: Geçersiz ID değeri");
                return "Null değer alınamaz";

            } else if (ID <= 0) {

                log.error("oyuncakSil servisi hata aldı. Parametere: " + ID + " Hatası: Geçersiz ID değeri");
                return "Geçersiz ID değeri. ID değeri 0'dan büyük olmalıdır.";

            }

            log.debug("oyuncakSil servisi oyuncakServ üzerinden oyuncak adlı nesneyi ID'si üzerinden buldu ve silme işlemine başladı");
            oyuncakServ.Silme(ID);
            return ID + " No'lu oyuncak nesnesi silindi";

            } catch (Exception e) {
                log.error("oyuncakSil servisi hata aldı. Parametereis: " + ID + " Hatası: " +e.getMessage());
            }


            return null;
    }

    @PutMapping("/guncelle/{ID}")
    public OyuncakDTO oyuncakGuncelleme(@PathVariable Long ID, @RequestBody OyuncakDTO oyuncakDTO) {
        log.info("oyuncakGuncelleme servisi çağrıldı. Parameterleri" + ID + oyuncakDTO);
            try {
                log.debug("oyuncakGuncelleme servsinden. oyuncak aranıyor.");
                Oyuncak oyuncak = oyuncakServ.Okuma(ID);
                log.debug("oyuncakGuncelleme servisi. oyuncakServ kullanarak: Eğer bahis edilden ID varsa değişim işlemine geçiyoruz");
                oyuncak.setAlisTarihi(oyuncakDTO.getAlisTarihi());
                oyuncak.setAlisFiyati(oyuncakDTO.getAlisFiyati());
                oyuncak.setAd(oyuncakDTO.getAd());
                oyuncak.setTur(oyuncakDTO.getTur());
                oyuncak.setDesi(oyuncakDTO.getDesi());
                oyuncak.setNotlar(oyuncakDTO.getNotlar());
                log.debug("oyuncakGuncelleme servisi. oyuncakServ kullanarak: Güncellemeyi kayıt ediyor.");
                oyuncakServ.Olusturma(oyuncak);
                log.debug("oyuncakGuncelleme veri tipi OyuncakDTO olarak göserildiği için Oyuncak nesnesini OyuncakDTO olarak dönüştürdük");
                return new OyuncakDTO(oyuncak.getID(), oyuncak.getAlisTarihi(),oyuncak.getAlisFiyati(),oyuncak. getAd(),oyuncak.getTur(),oyuncak.getDesi(),oyuncak.getNotlar());

            }   catch (Exception e) {
                log.error("oyuncakGuncelleme servisi hata aldı. Parametereleri : " + ID + " " + oyuncakDTO +" Hatası: " +e.getMessage());
            }
        return null;
    }

    @GetMapping("/listele")

   public List<OyuncakDTO> oyuncaklariGetir() {

        log.info("oyuncaklariGetir servisi çağrıldı.");

        try {
            log.debug("oyuncaklariGetir servsinden. oyuncaklar aranıyor.");
            List<Oyuncak> oyuncakList = oyuncakServ.Listeleme();
            log.debug("oyuncaklariGetir servsinde deönüş tipi List<OyuncakDTO> olduğu için bir dönüşüm işlemi yapacız");
            List<OyuncakDTO> tumOyuncaklar = new ArrayList<>();
            log.debug("Ardından tüm kullanıcıların bilgilerini görmek için for döngüsü kullanıyoruz");

            for (Oyuncak o : oyuncakList) {
                log.debug("oyuncaklariGetir servsinde OyuncakDTO olarak nesne oluştuma sebebimiz 1) OyuncakDTO dönüş tipimiz olmamız 2) Direk olarak erişimi engellemek");
                OyuncakDTO oDTO = new OyuncakDTO(o.getID(), o.getAlisTarihi(),o.getAlisFiyati(),o.getAd(),o.getTur(),o.getDesi(),o.getNotlar());

                log.debug("Bütün bilgileri döndükten sorna tumOyuncaklar dizi içine atacğız");

                tumOyuncaklar.add(oDTO);
            }
            return tumOyuncaklar;

        }   catch (Exception e) {
            log.error("oyuncaklariGetir servisi hata aldı. Hatası: " +e.getMessage());
        }
        return null;
    }



}
