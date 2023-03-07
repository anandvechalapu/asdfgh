
Spring Boot Application:

Controller Class

@RestController
public class GithubController {

    @Autowired
    private GithubService githubService;

    @PostMapping("/configureGithub")
    public ResponseEntity<String> configureGithub(@RequestBody GithubConfig config) {
        if (githubService.validateCredentials(config)) {
            githubService.saveConfig(config);
            return ResponseEntity.ok("GitHub Configuration Saved Successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid Credentials, please try again");
        }
    }
}

Service Class

@Service
public class GithubService {

    @Autowired
    private GithubRepository githubRepository;

    public boolean validateCredentials(GithubConfig config) {
        // validation logic
    }

    public void saveConfig(GithubConfig config) {
        githubRepository.save(config);
    }
}

Repository Class

@Repository
public interface GithubRepository extends JpaRepository<GithubConfig, Long> {
	
}