import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainClass {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		FileOutputStream fileOutput = new FileOutputStream("Persons.eis");

		List<PersonOuterClass.Person> personsList = new ArrayList<PersonOuterClass.Person>();

		long index = 1000000;

		for (int i = 1; i <= index; i++) {

			PersonOuterClass.Person person = PersonOuterClass.Person.newBuilder().setName("Eis" + i)
					.setSurname("IAIS" + i).setAge(28).build();
			personsList.add(person);
		}

		double totalTime = 0;
		int repeatedTimes = 11;

		for (int j = 0; j <= repeatedTimes; j++) {

			long startTime = System.currentTimeMillis();

			for (int i = 0; i < index; i++) {

				PersonOuterClass.Person person = personsList.get(i);

				person.writeTo(fileOutput);

			}
			long estimatedTime = System.currentTimeMillis() - startTime;

			if (j > 1){
				
				totalTime += estimatedTime;
				System.out.println("Elapsed Time:" + estimatedTime);
			}
		}
		System.out.println("Total:" + totalTime);
		System.out.println("Average:" + totalTime / (repeatedTimes-1));

	}

}
