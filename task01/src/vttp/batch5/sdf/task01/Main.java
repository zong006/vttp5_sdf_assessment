package vttp.batch5.sdf.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import vttp.batch5.sdf.task01.models.BikeEntry;

// Use this class as the entry point of your program

public class Main {

	public static void main(String[] args) throws IOException {

		
		String fileName = "day.csv";
		File f = new File(fileName);

		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);

		ArrayList<BikeEntry> data = new ArrayList<>();
		BikeEntry entry = null;
		String lineRead = "";
		int count = 0;

		while ((lineRead = br.readLine())!=null){
			if (count>0){
				String[] terms = lineRead.split(",");
				entry = new BikeEntry();
				// season,mnth,holiday,weekday, weathersit
				entry.setSeason(Integer.parseInt(terms[0]));
				entry.setMonth(Integer.parseInt(terms[1]));
				entry.setHoliday(Boolean.parseBoolean(terms[2]));
				entry.setWeekday(Integer.parseInt(terms[3]));
				entry.setWeather(Integer.parseInt(terms[4]));
				entry.setCasual(Integer.parseInt(terms[8]));
				entry.setRegistered(Integer.parseInt(terms[9]));
				data.add(entry);
			}
			count += 1;
		}
		Comparator<BikeEntry> compare = Comparator.comparing(d -> d.getCasual()+d.getRegistered());
        data.sort(compare.reversed());

		String[] position = {"highest", "second highest", "third highest", "fourth highest", "fifth highest"};
		String[] season = {"Spring", "Summer", "Fall", "Winter"};
		String[] day = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
		String[] month = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		String[] weather = {"Clear, Few clouds, Partly cloudy, Partly cloudy", 
							"Mist + Cloudy, Mist + Broken clouds, Mist + Few clouds, Mist", 
							"Light Snow, Light Rain + Thunderstorm + Scattered clouds, Light Rain + Scattered clouds", 
							"Heavy Rain + Ice Pallets + Thunderstorm + Mist, Snow + Fog"};
		
		for (int i = 0 ; i < 5 ; i ++){
			String holiday = "";
			if (data.get(i).isHoliday()){
				holiday = "holiday";
			}
			else {
				holiday = "not a holiday";
			}
			int total = data.get(i).getCasual()+data.get(i).getRegistered();

			String s = "The "+ position[i] + " (position)" +  " recorded number of cyclists was in " + season[data.get(i).getSeason()-1] + " (season)"+
						", on a " + day[data.get(i).getWeekday()-1] + " (day)" +  " in the month of " + month[data.get(i).getMonth()-1] + " (month)" + 
						".\nThere were a total of " + total + " (total)" + " cyclists. The weather was " + weather[data.get(i).getWeather()-1] + " (weather)" 
						+ ".\n" + day[data.get(i).getWeekday()-1] + " (day)" + " was " + holiday;

			System.out.println(s);
			System.out.println();
		}
        



	}
}
