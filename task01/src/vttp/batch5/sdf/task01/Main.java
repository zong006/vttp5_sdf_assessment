package vttp.batch5.sdf.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

// Use this class as the entry point of your program

public class Main {

	public static void main(String[] args) throws IOException {

		
		String fileName = "day.csv";
		File f = new File(fileName);

		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);

		ArrayList<DataPoint> data = new ArrayList<>();
		DataPoint dataPoint = null;
		String lineRead = "";
		int count = 0;

		while ((lineRead = br.readLine())!=null){
			if (count>0){
				String[] terms = lineRead.split(",");
				// season,mnth,holiday,weekday, weathersit
				dataPoint = new DataPoint();
				dataPoint.setSeason(Integer.parseInt(terms[0]));
				dataPoint.setMnth(Integer.parseInt(terms[1]));
				dataPoint.setHoliday(Integer.parseInt(terms[2]));
				dataPoint.setWeekday(Integer.parseInt(terms[3]));
				dataPoint.setWeathersit(Integer.parseInt(terms[4]));
				dataPoint.setUsers(Integer.parseInt(terms[8]) + Integer.parseInt(terms[9]));
				data.add(dataPoint);
			}
			count += 1;
		}
		Comparator<DataPoint> compare = Comparator.comparing(d -> d.getUsers());
        data.sort(compare.reversed());

		String[] position = {"highest", "second highest", "third highest", "fourth highest", "fifth highest"};
		String[] season = {"spring", "summer", "fall", "winter"};
		String[] day = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
		String[] month = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		String[] weather = {"Clear, Few clouds, Partly cloudy, Partly cloudy", 
							"Mist + Cloudy, Mist + Broken clouds, Mist + Few clouds, Mist", 
							"Light Snow, Light Rain + Thunderstorm + Scattered clouds, Light Rain + Scattered clouds", 
							"Heavy Rain + Ice Pallets + Thunderstorm + Mist, Snow + Fog"};
		String[] holiday = {"not a holiday", "holiday"};


		

		for (int i = 0 ; i < 5 ; i ++){
			String s = "The "+ position[i] + " (position)" +  " recorded number of cyclists was in " + season[data.get(i).getSeason()-1] + " (season)"+
						", on a " + day[data.get(i).getWeekday()-1] + " (day)" +  " in the month of " + month[data.get(i).getMnth()-1] + " (month)" + 
						".\nThere were a total of " + data.get(i).getUsers() + " (total)" + " cyclists. The weather was " + weather[data.get(i).getWeathersit()-1] + " (weather)" 
						+ ".\n" + day[data.get(i).getWeekday()-1] + " (day)" + " was " + holiday[data.get(i).getHoliday()];

			System.out.println(s);
			System.out.println();
		}
        



	}
}
