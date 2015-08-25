import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import com.google.protobuf.ExtensionRegistryLite;

public class Deserialization {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		// DeserializeFromList();
		DeserializeFromFile();
	}

	public static void DeserializeFromList() throws IOException, ClassNotFoundException {

		byte[] bytes;
		List<byte[]> personList = new ArrayList<byte[]>();

		long index = 1000000;

		FileOutputStream fileOutput = new FileOutputStream("Persons.txt");

		for (int i = 1; i <= index; i++) {

			//Use the full hierarchy of class definition
			PersonOuterClass.Person person = PersonOuterClass.Person.newBuilder().setName("Eis" + i)
					.setSurname("IAIS" + i).setAge(28).build();

			personList.add(person.toByteArray());
		}

		double totalTime = 0;
		int repeatedTimes = 11;

		for (int j = 0; j <= repeatedTimes; j++) {

			PersonOuterClass.Person person;

			long startTime = System.currentTimeMillis();

			for (int i = 0; i < index; i++) {

				person = PersonOuterClass.Person.parseFrom(personList.get(i));

				// System.out.println(person.getName());
			}

			long estimatedTime = System.currentTimeMillis() - startTime;

			if (j > 1) {
				totalTime += estimatedTime;
				System.out.println("Elapsed Time:" + estimatedTime);
			}

		}
		System.out.println("Total:" + totalTime);
		System.out.println("Average:" + totalTime / (repeatedTimes - 1));

	}

	@SuppressWarnings("unchecked")
	public static void DeserializeFromFile() throws IOException, ClassNotFoundException {

		byte[] bytes;
		List<byte[]> employeesList = new ArrayList<byte[]>();

		long index = 10000;

		FileOutputStream fileOutput = new FileOutputStream("Persons.eis");

		for (int i = 1; i <= index; i++) {

			PersonOuterClass.Person person = PersonOuterClass.Person.newBuilder().setName("Eis" + i)
					.setSurname("IAIS" + i).setAge(28).build();

			person.writeTo(fileOutput);
		}

		double totalTime = 0;
		int repeatedTimes = 11;

		// File file = new File("Persons.eis");

		// FileInputStream fileInput = new FileInputStream(file);

		// FileInputStream inputt = new FileInputStream("Persons.eis");
		//
		// //byte fileContent[] = new byte[(int) file.length()];
		//
		// //fileInput.read(fileContent);
		//
		// PersonOuterClass.AddressBook personss =
		// PersonOuterClass.AddressBook.parseFrom(inputt);
		//
		// for (int i = 1; i <= index; i++) {
		//
		// //ExtensionRegistryLite extensionRegistry =
		// ExtensionRegistryLite.newInstance();
		// PersonOuterClass.Person person1 =
		// PersonOuterClass.Person.parseFrom(fileContent);
		// //System.out.println(person1.getName());
		//
		// }
		
		File file = new File("Persons.eis");

	     
		FileInputStream fileInput;

		for (int j = 0; j <= repeatedTimes; j++) {

			fileInput = new FileInputStream(file);
			byte fileContent[] = new byte[(int) file.length()];
			//
			fileInput.read(fileContent);

			long startTime = System.currentTimeMillis();

			for (int i = 1; i <= index; i++) {
				PersonOuterClass.Person person = PersonOuterClass.Person.parseFrom(fileContent);

				//System.out.println(person.getName());
				// employees.add(person);

			}
			long estimatedTime = System.currentTimeMillis() - startTime;

			if (j > 1) {
				totalTime += estimatedTime;
				System.out.println("Elapsed Time:" + estimatedTime);
			}

		}
		System.out.println("Total:" + totalTime);
		System.out.println("Average:" + totalTime / (repeatedTimes-1));
	}
}
