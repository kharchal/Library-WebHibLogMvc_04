package ua.com.hav.domain;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.hav.dao.Holder;

/**
 * Created by Юля on 17.10.2016.
 */
public class Runner {
    private static Holder<Kind> kinds = new Holder<>(Kind.class);

    private Holder<Pet> petHolder;
    private Holder<Kind> kindHolder;

    public static void main(String[] args) {
        Pet pet = new Pet("jhdjfh", 8);


        System.out.println("done!");
    }

    public void fillInPets(int n) {

            for (int i = 0; i < n; i++) {
                petHolder.add(new Pet("RND", i, kindHolder.find((long) (i % kindHolder.size()) + 1)));
            }

    }


    public void fillInKinds(int n) {
        for (int i = 0 ; i < n ; i++) {
            kindHolder.add(new Kind("Kind " + i));
        }

    }

    public void fillTheBase(int i, int j, Holder<Pet> petBase, Holder<Kind> kindBase) {
        this.kindHolder = kindBase;
        this.petHolder = petBase;
        fillInKinds(j);
        fillInPets(i);
    }
}
