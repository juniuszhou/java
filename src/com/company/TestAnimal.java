/**
 * Code copy from internet to show how to use ?
 */
import java.util.*;

class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public void eat() {
        System.out.println(getName() + " can eat.");
    }

    public String getName(){
        return name;
    }
}


class Cat extends Animal {

    public Cat(String name) {
        super(name);
    }

    public void jump(){
        System.out.println(getName() + " can jump.");
    }
}


class Bird extends Animal {

    public Bird(String name) {
        super(name);
    }

    public void fly(){
        System.out.println(getName() + " can fly.");
    }
}


class Magpie extends Bird {

    public Magpie(String name) {
        super(name);
    }

    public void sing(){
        System.out.println(getName() +
                " can not only eat,but sing");
    }
}



class AnimalTrainer {
    public void act(List<? extends Animal > list) {
        for (Animal animal : list) {
            animal.eat();
        }
    }
}


public class TestAnimal {
    public static void main(String[] args) {
        AnimalTrainer animalTrainer = new AnimalTrainer();
        //Test 1
        List<Animal> animalList = new ArrayList<Animal>();
        animalList.add(new Cat("cat1"));
        animalList.add(new Bird("bird1"));

        animalTrainer.act(animalList);	//可以通过编译

        //Test 2
        List<Cat> catList = new ArrayList<Cat>();
        catList.add(new Cat("cat2"));
        catList.add(new Cat("cat3"));

        animalTrainer.act(catList);		//无法通过编译
    }
}
