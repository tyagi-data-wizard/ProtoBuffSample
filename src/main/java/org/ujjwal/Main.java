package org.ujjwal;

import com.google.protobuf.InvalidProtocolBufferException;
import com.ujjwal.proto.Student;
import com.ujjwal.proto.Students;

public class Main {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        System.out.println("Hello world!");

        byte[] dataToSend = sender();
        receiver(dataToSend);

    }

    private static void receiver(byte[] dataFromSender) throws InvalidProtocolBufferException {
        System.out.println("Data received from sender = ");
        for (int i = 0; i < dataFromSender.length; i++) {
            System.out.print(dataFromSender[i]+" ");

        }
        System.out.println();

        //parsing/decoding the data
        Students data = Students.parseFrom(dataFromSender);
        System.out.println("size = "+data.getStudentsList().size());
        System.out.println(data.getStudents(0).getName() + " "+data.getStudents(0).getAge());
        System.out.println(data.getStudents(1).getName() + " "+data.getStudents(1).getAge());


    }
    private static byte[] sender() {
        Student student1  = Student.newBuilder().setAge(21).setName("Ujjwal").build();
        Student student2  = Student.newBuilder().setAge(22).setName("Tyagi").build();

        Students.Builder students = Students.newBuilder().addStudents(student1).addStudents(student2);


        byte[] arrayToSend = students.build().toByteArray();
        for (int i =  0; i < arrayToSend.length; i++) {
            System.out.print(arrayToSend[i]+ " ");
        }
        System.out.println();
        System.out.println("length of arrayToSend = "+arrayToSend.length);
        return arrayToSend;
    }
}

//command to use for compiling the proto files using the protoc /proto compiler
// ./protoc -I=proto  --java_out=src/main/java proto/Student.proto