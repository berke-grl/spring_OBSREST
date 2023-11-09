package BilgeAdam.Controller;

import BilgeAdam.Entity.Ders;
import BilgeAdam.Entity.Konu;
import BilgeAdam.Repository.DersRepository;
import BilgeAdam.Repository.KonuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/konu")
public class KonuController {

    private final KonuRepository repository;

    @Autowired
    public KonuController(KonuRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<Konu>> getAll() {
        try {
            return ResponseEntity.ok(repository.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Konu> getById(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok(repository.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Konu konu) {
        boolean result = repository.save(konu);
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
