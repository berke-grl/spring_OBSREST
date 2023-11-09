package BilgeAdam.Controller;

import BilgeAdam.Entity.Konu;
import BilgeAdam.Entity.Ogrenci;
import BilgeAdam.Repository.KonuRepository;
import BilgeAdam.Repository.OgrenciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ogrenci")
public class OgrenciController {

    private final OgrenciRepository repository;

    @Autowired
    public OgrenciController(OgrenciRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<Ogrenci>> getAll() {
        try {
            return ResponseEntity.ok(repository.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Ogrenci> getById(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok(repository.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Ogrenci ogrenci) {
        boolean result = repository.save(ogrenci);
        try {
            if (result)
                return ResponseEntity.ok("Kayıt Başarıyla kaydedildi");
            else
                return ResponseEntity.internalServerError().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") long id) {
        boolean result = repository.deleteById(id);
        try {
            if (result)
                return ResponseEntity.ok("Silme başarılı");
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id + "id 'li kayıt bulunamadı");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(id + "id 'li kayıt silme işleminde hata oluştu");
        }
    }
}
