package db;

import entity.Student;
import util.Input;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepo implements Repository<Student>{

    private static String PATH = "src/db/student_db.txt";
    private List<Student> students;
    private static StudentRepo singleton;

    public StudentRepo(List<Student> students) {
        this.students = students;
    }

    public static StudentRepo of(){
        if(singleton==null){
            singleton = new StudentRepo(loadData());
        }
        return singleton;
    }

    @SuppressWarnings("unchecked")
    private static List<Student> loadData() {

        try (InputStream is = new FileInputStream(PATH);
             ObjectInputStream objectInputStream = new ObjectInputStream(is);

        ){
            return (List<Student>) objectInputStream.readObject();
        } catch (EOFException e){
            return new ArrayList<>();
        } catch(IOException | ClassNotFoundException e) {
            throw new RuntimeException();
        }

    }

    private void uploadData() {

        try (
                OutputStream os = new FileOutputStream(PATH);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(os);
        ){
            objectOutputStream.writeObject(students);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void create(Student student) {
        students.add(student);
        uploadData();
    }

    @Override
    public void update(Student student) {
        if(students!=null){
            student.setName(Input.inputStr("Enter name : "));
            student.setAge(Input.inputInt("Enter age : "));
            student.setPhoneNumber(Input.inputStr("Enter phone number : "));
        }
        uploadData();
    }

    @Override
    public void delete(Student student) {

    }

    @Override
    public List<Student> read() {
        return null;
    }
}
