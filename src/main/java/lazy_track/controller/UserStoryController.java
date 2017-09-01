package lazy_track.controller;

import lazy_track.model.UserStory;
import lazy_track.service.UserStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserStoryController {
    private UserStoryService userStoryService;

    @Autowired
    @Qualifier(value = "userStoryService")
    public void setUserStoryService(UserStoryService userStoryService) {
        this.userStoryService = userStoryService;
    }

    @RequestMapping(value = "userStories", method = RequestMethod.GET)
    public String listBooks(Model model) {
        model.addAttribute("userStory", new UserStory());
        model.addAttribute("listUserStories", userStoryService.list());
        return "userStories";
    }

    @RequestMapping(value = "/userStory/add", method = RequestMethod.POST)
    public String addUserStory(@ModelAttribute("userStory") UserStory userStory) {
        if (userStory.getId() == 0) {
            userStoryService.add(userStory);
        } else {
            userStoryService.update(userStory);
        }
        return "redirect:/userStory";
    }

    @RequestMapping("/remove/{id}")
    public String removeCompany(@PathVariable("id") int id) {
        userStoryService.remove(id);
        return "redirect:/userStories";
    }

    @RequestMapping("edit/{id}")
    public String editUserStory(@PathVariable("id") int id, Model model) {
        model.addAttribute("userStory", userStoryService.get(id));
        model.addAttribute("listUserStories", userStoryService.list());
        return "userStories";
    }

    @RequestMapping("userStoryData/{id}")
    public String userStoryData(@PathVariable("id") int id, Model model) {
        model.addAttribute("userStory", userStoryService.get(id));
        return "userStoryData";
    }
}
