package br.com.itsimple.smartcities.singleton;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactorySingleton {
    
    private static EntityManagerFactory fabrica;
    private EntityManagerFactorySingleton(){}
    
    public static EntityManagerFactory getInstance(){
        if (fabrica == null){
            fabrica = Persistence
                .createEntityManagerFactory("smartcities");
        }
        return fabrica;
    }
}