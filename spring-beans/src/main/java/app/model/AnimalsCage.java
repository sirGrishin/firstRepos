
package app.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class AnimalsCage {

    private Animal animal;
    private Timer timer;

    @Autowired
    public AnimalsCage(@Qualifier("dog") Animal animal, Timer time) {
        this.animal = animal;
        this.timer = time;
    }



    public void whatAnimalSay() {
        System.out.println("Say:");
        System.out.println(animal.toString());
        System.out.println("At:");
        System.out.println(timer.getTime());
        System.out.println("________________________");
    }

    public Timer getTimer() {

        return timer;
    }
}