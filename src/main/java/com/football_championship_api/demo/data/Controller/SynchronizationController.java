import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/synchronization")
public class SynchronizationController {

    private final SynchronizationService service;

    public SynchronizationController(SynchronizationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> synchronizeData() {
        service.syncAllData();
        return ResponseEntity.ok().build();
    }
}
