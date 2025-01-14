package ch.bbw.cardgame;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ViewController {
    private List<City> leftCityList = new ArrayList<>();
    private List<City> rightCityList = new ArrayList<>();
    private City leftCity = null;
    private City rightCity = null;

    public ViewController() {
        setup();
    }

    private void setup() {
        leftCityList.clear();
        rightCityList.clear();

        leftCityList.add(new City("images/paris.jpg", "Paris", 2.1, 105.4, 330, 20238));
        leftCityList.add(new City("images/london.jpg", "London", 8.87, 1570, 310, 5510));
        leftCityList.add(new City("images/newYork.jpg", "New York", 8.26, 778.2, 541, 10356));
        leftCityList.add(new City("images/losAngeles.avif", "Los Angeles", 9.66, 1300, 335, 7430));
        leftCityList.add(new City("images/zuri.jpg", "Zürich", 0.4, 87.9, 126, 4655));

        rightCityList.add(new City("images/tokyo.jpg", "Tokyo", 14.2, 627.5, 634, 22613));
        rightCityList.add(new City("images/sydney.jpg", "Sydney", 5.45, 12140, 309, 448));
        rightCityList.add(new City("images/rioDeJaneiro.avif", "Rio de Janeiro", 6.21, 1206, 294, 4928));
        rightCityList.add(new City("images/berlin.jpeg", "Berlin", 3.88, 891.1, 125, 4244));
        rightCityList.add(new City("images/Barcelona.webp", "Barcelona", 1.62, 101.3, 154, 15992));
    }

    @GetMapping("/")
    public String redirect() {
        return "redirect:/cardGameView";
    }

    @GetMapping("/cardGameView")
    public String showView(Model model) {
        updateModelAttributes(model);
        return "cardGameForm";
    }

    @PostMapping(value = "/cardGameView", params = {"showButton=showLeft"})
    public String showLeftCard(Model model) {
        leftCity = !leftCityList.isEmpty() ? leftCityList.get(0) : null;
        updateModelAttributes(model);
        return "cardGameForm";
    }

    @PostMapping(value = "/cardGameView", params = {"showButton=showRight"})
    public String showRightCard(Model model) {
        rightCity = !rightCityList.isEmpty() ? rightCityList.get(0) : null;
        updateModelAttributes(model);
        return "cardGameForm";
    }

    @PostMapping(value = "/cardGameView", params = {"moveButton=moveToLeft"})
    public String moveCardToLeft(Model model) {
        if (!rightCityList.isEmpty() && !leftCityList.isEmpty()) {
            leftCityList.add(rightCityList.remove(0)); // Verliererkarte hinzufügen
            leftCityList.add(leftCityList.remove(0)); // Siegerkarte nach hinten
        }
        resetCities();
        updateModelAttributes(model);
        return "cardGameForm";
    }

    @PostMapping(value = "/cardGameView", params = {"moveButton=moveToRight"})
    public String moveCardToRight(Model model) {
        if (!leftCityList.isEmpty() && !rightCityList.isEmpty()) {
            rightCityList.add(leftCityList.remove(0)); // Verliererkarte hinzufügen
            rightCityList.add(rightCityList.remove(0)); // Siegerkarte nach hinten
        }
        resetCities();
        updateModelAttributes(model);
        return "cardGameForm";
    }

    @PostMapping(value = "/cardGameView", params = {"showButton=reset"})
    public String resetView(Model model) {
        setup();
        resetCities();
        updateModelAttributes(model);
        return "cardGameForm";
    }

    private void updateModelAttributes(Model model) {
        model.addAttribute("leftCity", leftCity);
        model.addAttribute("rightCity", rightCity);
        model.addAttribute("numberLeft", leftCityList.size());
        model.addAttribute("numberRight", rightCityList.size());
    }

    private void resetCities() {
        leftCity = null;
        rightCity = null;
    }
}
