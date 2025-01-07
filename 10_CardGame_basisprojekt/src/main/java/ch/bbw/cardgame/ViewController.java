package ch.bbw.cardgame;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewController
 *    Kontrolliert Zusammenspiel mit der View
 * @author Peter Rutschmann
 * @date 26.08.2021
 */
@Controller
public class ViewController {
    List<City> leftCityList = new ArrayList<>();
    List<City> rightCityList = new ArrayList<>();
    City leftCity = null;
    City rightCity = null;

    public ViewController() {
        setup();
    }

    private void setup(){
        leftCityList.clear();
        rightCityList.clear();

        //Aufwendiger Style
        City Paris = new City("images/city_img.jpg", "Paris", 2.1, 105.4, 330, 20238);
        leftCityList.add(Paris);
        City London = new City("images/city_img.jpg", "London", 8.87, 1570, 310, 5510);
        leftCityList.add(London);
        City NewYork = new City("images/city_img.jpg", "New York", 8.26, 778.2, 541, 10356);
        leftCityList.add(NewYork);
        City LosAngeles = new City("images/city_img.jpg", "Los Angeles", 9.66, 1300, 335, 7430);
        leftCityList.add(LosAngeles);
        City Zurich = new City("images/city_img.jpg", "Zürich", 0.4, 87.9, 126, 4655);
        leftCityList.add(Zurich);

        City Tokyo = new City("images/city_img.jpg", "Tokyo", 14.2, 627.5, 634, 22613);
        rightCityList.add(Tokyo);
        City Sydney = new City("images/city_img.jpg", "Sydney", 5.45, 12140, 309, 448);
        rightCityList.add(Sydney);
        City RioDeJaneiro = new City("images/city_img.jpg", "Rio de Janeiro", 6.21, 1206, 294, 4928);
        rightCityList.add(RioDeJaneiro);
        City Berlin = new City("images/city_img.jpg", "Berlin", 3.88, 891.1, 125, 4244);
        rightCityList.add(Berlin);
        City Barcelona = new City("images/city_img.jpg", "Barcelona", 1.62, 101.3, 154, 15992);
        rightCityList.add(Barcelona);
    }

    @GetMapping("/")
    public String redirect() {
        City leftCity = null;
        City rightCity = null;
        City middleCity = null;
        return "redirect:/cardGameView";
    }

    @GetMapping("/cardGameView")
    public String showView(Model model) {
        model.addAttribute("leftCar", leftCity);
        model.addAttribute("rightCar", rightCity);
        model.addAttribute("numberLeft", leftCityList.size());
        model.addAttribute("numberRight", rightCityList.size());
        return "cardGameForm";
    }

    @PostMapping(value = "/cardGameView", params = {"showButton=showLeft"})
    public String showLeftCard(Model model) {
        if(leftCityList.isEmpty()){
            leftCity = null;
        }else{
            leftCity = leftCityList.get(0);
        }
        model.addAttribute("leftCar", leftCity);
        model.addAttribute("rightCar", rightCity);
        model.addAttribute("numberLeft", leftCityList.size());
        model.addAttribute("numberRight", rightCityList.size());
        return "cardGameForm";
    }

    @PostMapping(value = "/cardGameView", params = {"moveButton=moveToLeft"})
    public String moveCardToLeft(Model model) {
        if(rightCityList.isEmpty()){

        }else{
            //Verliererkarte hinzufügen
            leftCityList.add(rightCityList.remove(0));
            //Siegerkarte nach hinten schieben
            leftCityList.add(leftCityList.remove(0));
        }
        leftCity = null;
        rightCity = null;
        model.addAttribute("leftCar", leftCity);
        model.addAttribute("rightCar", rightCity);
        model.addAttribute("numberLeft", leftCityList.size());
        model.addAttribute("numberRight", rightCityList.size());
        return "cardGameForm";
    }

    @PostMapping(value = "/cardGameView", params = {"showButton=showRight"})
    public String showRightCard(Model model) {
        if(rightCityList.isEmpty()){
            rightCity = null;
        }else{
            rightCity = rightCityList.get(0);
        }
        model.addAttribute("leftCar", leftCity);
        model.addAttribute("rightCar", rightCity);
        model.addAttribute("numberLeft", leftCityList.size());
        model.addAttribute("numberRight", rightCityList.size());
        return "cardGameForm";
    }

    @PostMapping(value = "/cardGameView", params = {"moveButton=moveToRight"})
    public String moveCardToRight(Model model) {
        if (leftCityList.isEmpty()) {
            //do nothing
        } else {
            //Verliererkarte hinzufügen
            rightCityList.add(leftCityList.remove(0));
            //Siegerkarte nach hinten schieben
            rightCityList.add(rightCityList.remove(0));
        }
        leftCity = null;
        rightCity = null;
        model.addAttribute("leftCar", leftCity);
        model.addAttribute("rightCar", rightCity);
        model.addAttribute("numberLeft", leftCityList.size());
        model.addAttribute("numberRight", rightCityList.size());
        return "cardGameForm";
    }

    @PostMapping(value = "/cardGameView", params = {"showButton=reset"})
    public String resetView(Model model) {
        setup();
        leftCity = null;
        rightCity = null;
        model.addAttribute("leftCar", leftCity);
        model.addAttribute("rightCar", rightCity);
        model.addAttribute("numberLeft", leftCityList.size());
        model.addAttribute("numberRight", rightCityList.size());
        return "cardGameForm";
    }

}
