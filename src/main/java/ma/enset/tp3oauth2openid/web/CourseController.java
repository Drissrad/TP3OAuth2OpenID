package ma.enset.tp3oauth2openid.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/api")
public class CourseController {

    // petite “base” en mémoire pour le TP
    private final List<Map<String, Object>> courses = new CopyOnWriteArrayList<>(
            List.of(
                    new HashMap<>(Map.of("id", 1, "title", "Spring Boot Basics")),
                    new HashMap<>(Map.of("id", 2, "title", "React Introduction"))
            )
    );

    @GetMapping("/courses")
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
    public List<Map<String, Object>> getCourses() {
        return courses;
    }

    @PostMapping("/courses")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Object> addCourse(@RequestBody Map<String, Object> course) {
        int newId = courses.size() + 1;
        Map<String, Object> created = new HashMap<>();
        created.put("id", newId);
        created.put("title", Objects.toString(course.get("title"), "Untitled"));
        courses.add(created);
        return created;
    }

    @GetMapping("/me")
    public Map<String, Object> me(@AuthenticationPrincipal Jwt jwt) {
        return jwt.getClaims();
    }
}
