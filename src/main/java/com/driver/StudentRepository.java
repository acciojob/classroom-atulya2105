package com.driver;


import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {

   HashMap<String , Student> studentDb = new HashMap<>();

   HashMap<String , Teacher> teacherDb = new HashMap<>();

   HashMap<String , List<String >> db = new HashMap<>();

   public void addStudent(Student student){
       studentDb.put(student.getName(),student);
   }

   public void addTeacher(Teacher teacher){
       teacherDb.put(teacher.getName(),teacher);
   }

   public void addStudentTeacherPair(String student, String teacher){
       if(studentDb.containsKey(student) && teacherDb.containsKey(teacher)){
           if(db.containsKey(teacher)){
               List<String > ls = db.get(teacher);
               ls.add(student);
               db.put(teacher,ls);
           }else{
               List<String > ls = new ArrayList<>();
               ls.add(student);
               db.put(teacher,ls);
           }
       }
   }

   public Student getStudentByName(String name){
       return studentDb.get(name);
   }

   public Teacher getTeacherByName(String  name){
       return teacherDb.get(name);
   }

   public List<String> getStudentsByTeacherName(String teacherName){
       return db.get(teacherName);
   }

   public List<String> getAllStudents(){
       List<String> ls = new ArrayList<>();
       for(String st:studentDb.keySet()){
           ls.add(st);
       }
       return ls;
   }
   public void deleteTeacherByName(String teacherName){
       teacherDb.remove(teacherName);
       if(db.containsKey(teacherName)){
           List<String> ls = db.get(teacherName);
           for(String s : ls){
               if(studentDb.containsKey(s)){
                   studentDb.remove(s);
               }
           }
           db.remove(teacherName);
       }
   }

   public void deleteAllTeachers(){
       teacherDb = new HashMap<>();
       for(String s : db.keySet()){
           List<String > ls = db.get(s);
           for(String l : ls){
               if(studentDb.containsKey(l)){
                   studentDb.remove(l);
               }
           }


       }
       db = new HashMap<>();
   }
}
