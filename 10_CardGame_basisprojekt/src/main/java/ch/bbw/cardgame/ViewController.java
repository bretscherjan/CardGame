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
        City City1 = new City("images/city_img.jpg", "Audi", "Flaschback 300", 50000.00 );
        leftCityList.add(City1);
        City City2 = new City("images/city_img.jpg", "Opel", "Manta SE", 20000.00 );
        leftCityList.add(City2);
        City City3 = new City("images/city_img.jpg", "VW", "Golf GL", 12000.00 );
        leftCityList.add(City3);

        //Objekt direkt erstellen
        rightCityList.add(new City("images/city_img.jpg", "Fiat", "500", 15000.00 ));
        rightCityList.add(new City("images/city_img.jpg", "Alfa Romeo", "Guilietta", 30000.00 ));
        rightCityList.add(new City("images/city_img.jpg", "Lancia", "Delta", 45000.00 ));
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
        model.addAttribute("leftCity", leftCity);
        model.addAttribute("rightCity", rightCity);
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
        model.addAttribute("leftCity", leftCity);
        model.addAttribute("rightCity", rightCity);
        model.addAttribute("numberLeft", leftCityList.size());
        model.addAttribute("numberRight", rightCityList.size());
        return "cardGameForm";
    }

    @PostMapping(value = "/cardGameView", params = {"moveButton=moveToLeft"})
    public String moveCardToLeft(Model model) {
        if(rightCityList.isEmpty()){
            //do nothing
        }else{
            //Verliererkarte hinzufügen
            leftCityList.add(rightCityList.remove(0));
            //Siegerkarte nach hinten schieben
            leftCityList.add(leftCityList.remove(0));
        }
        leftCity = null;
        rightCity = null;
        model.addAttribute("leftCity", leftCity);
        model.addAttribute("rightCity", rightCity);
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
        model.addAttribute("leftCity", leftCity);
        model.addAttribute("rightCity", rightCity);
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
        model.addAttribute("leftCity", leftCity);
        model.addAttribute("rightCity", rightCity);
        model.addAttribute("numberLeft", leftCityList.size());
        model.addAttribute("numberRight", rightCityList.size());
        return "cardGameForm";
    }

    @PostMapping(value = "/cardGameView", params = {"showButton=reset"})
    public String resetView(Model model) {
        setup();
        leftCity = null;
        rightCity = null;
        model.addAttribute("leftCity", leftCity);
        model.addAttribute("rightCity", rightCity);
        model.addAttribute("numberLeft", leftCityList.size());
        model.addAttribute("numberRight", rightCityList.size());
        return "cardGameForm";
    }

}
