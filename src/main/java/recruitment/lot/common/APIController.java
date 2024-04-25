package recruitment.lot.common;

import static java.net.URI.create;
import static org.springframework.http.ResponseEntity.created;

import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class APIController {

    protected ResponseEntity<?> createWithPath(Long id, HttpServletRequest request) {
        return created(create(request.getRequestURL().toString() + "/" + id)).build();
    }

}
