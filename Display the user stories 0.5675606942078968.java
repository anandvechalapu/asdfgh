
Controller:

@RestController
public class UserStoryController {

    private UserStoryService userStoryService;

    @Autowired
    public UserStoryController(UserStoryService userStoryService) {
        this.userStoryService = userStoryService;
    }

    @GetMapping("/stories")
    public List<UserStory> getUserStories() {
        return userStoryService.getStories();
    }

    @GetMapping("/stories/{id}")
    public UserStory getUserStory(@PathVariable Long id) {
        return userStoryService.getStory(id);
    }

}

Service:

@Service
public class UserStoryService {

    private UserStoryRepository userStoryRepository;

    @Autowired
    public UserStoryService(UserStoryRepository userStoryRepository) {
        this.userStoryRepository = userStoryRepository;
    }

    public List<UserStory> getStories() {
        return userStoryRepository.findAll();
    }

    public UserStory getStory(Long id) {
        return userStoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User story not found with id " + id));
    }
}

Repository:

@Repository
public interface UserStoryRepository extends JpaRepository<UserStory, Long> {

}