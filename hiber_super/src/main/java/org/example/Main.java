package org.example;

import org.example.entities.CategoryEntity;
import org.example.utils.HibernateHelper;
import org.hibernate.SessionFactory;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DoCRUD();
        HibernateHelper.shutdown();
    }

    private static void Create(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new category name");
        String name = scanner.nextLine();
        var sessionFactory = HibernateHelper.getSessionFactory();
        sessionFactory.inTransaction(session -> {
            session.persist(new CategoryEntity(name));
        });
    }
    private static void Read(){
        var session = HibernateHelper.getSession();
        try{
            var result = session.createSelectionQuery("from CategoryEntity", CategoryEntity.class)
                    .getResultList();
            result.forEach(System.out::println);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    private static void Update(){
        Scanner scanner = new Scanner(System.in);
        try{
             System.out.println("Enter category`s id");
             int id = Integer.parseInt(scanner.nextLine());
             var session = HibernateHelper.getSession();
             session.beginTransaction();
             var category = session.find(CategoryEntity.class,id);
             if(category != null){
                 System.out.println("Enter new category name");
                 String newName = scanner.nextLine();
                 category.Update(newName);
                 session.merge(category);
                 session.getTransaction().commit();
                 System.out.println("Updated successfully!");
             }
             else{
                 throw new Exception("Category with this id doesn`t exist");
             }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    private static void Delete(){
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.println("Enter category`s id");
            int id = Integer.parseInt(scanner.nextLine());
            var session = HibernateHelper.getSession();
            session.beginTransaction();
            var category = session.find(CategoryEntity.class,id);
            if(category != null){
                session.remove(category);
                session.getTransaction().commit();
                System.out.println("Deleted successfully!");
            }
            else{
                throw new Exception("Category with this id doesn`t exist");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    private static void DoCRUD() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Choose option:\t0 - Leave\t1 - Create new category\t2 - Retrieve records\t3 - Update category\t4 - Delete category");

            int num = Integer.parseInt(scanner.nextLine());

            switch (num) {
                case 0:
                    HibernateHelper.shutdown();
                    return;
                case 1:
                    Create();
                    break;
                case 2:
                    Read();
                    break;
                case 3:
                    Update();
                    break;
                case 4:
                    Delete();
                    break;
                default:
                    HibernateHelper.shutdown();
                    break;
            }
        }
    }
}