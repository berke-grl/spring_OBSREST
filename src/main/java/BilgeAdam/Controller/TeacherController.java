package BilgeAdam.Controller;

import BilgeAdam.Entity.Ogretmen;
import BilgeAdam.Repository.OgretmenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final OgretmenRepository repository;

    @Autowired
    public TeacherController(OgretmenRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<Ogretmen>> getAll() {
        try {
            return ResponseEntity.ok(repository.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Ogretmen> getById(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok(repository.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Ogretmen ogretmen) {
        boolean result = repository.save(ogretmen);
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
