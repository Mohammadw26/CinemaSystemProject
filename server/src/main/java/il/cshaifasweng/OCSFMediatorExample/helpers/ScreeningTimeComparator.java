package il.cshaifasweng.OCSFMediatorExample.helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;


import il.cshaifasweng.OCSFMediatorExample.entities.Screening;

public class ScreeningTimeComparator implements Comparator<Screening> {

	@Override
	public int compare(Screening o1, Screening o2) {
		int i,j;
		String temp = o1.getScreeningDate();
		String temp2 = o2.getScreeningDate();
		i = Integer.parseInt(temp.substring(6, 10));
		j = Integer.parseInt(temp2.substring(6, 10));
		if (i < j)
			return -1;
		else if (i > j)
			return 1;
		i = Integer.parseInt(temp.substring(3, 5));
		j = Integer.parseInt(temp2.substring(3, 5));
		if (i < j)
			return -1;
		else if (i > j)
			return 1;
		i = Integer.parseInt(temp.substring(0, 2));
		j = Integer.parseInt(temp2.substring(0, 2));
		if (i < j)
			return -1;
		else if (i > j)
			return 1;
		temp = o1.getScreeningTime();
		temp2 = o2.getScreeningTime();
		i = Integer.parseInt(temp.substring(0, 2));
		j = Integer.parseInt(temp2.substring(0, 2));
		if (i < j)
			return -1;
		else if (i > j)
			return 1;
		i = Integer.parseInt(temp.substring(3, 5));
		j = Integer.parseInt(temp2.substring(3, 5));
		if (i < j)
			return -1;
		else if (i > j)
			return 1;
		return 0;
	}
	

	public int compare(Screening o1, String temp2) {
		int i,j;
		String temp = o1.getScreeningDate();
		i = Integer.parseInt(temp.substring(6, 10));
		j = Integer.parseInt(temp2.substring(6, 10));
		if (i < j)
			return -1;
		else if (i > j)
			return 1;
		i = Integer.parseInt(temp.substring(3, 5));
		j = Integer.parseInt(temp2.substring(3, 5));
		if (i < j)
			return -1;
		else if (i > j)
			return 1;
		i = Integer.parseInt(temp.substring(0, 2));
		j = Integer.parseInt(temp2.substring(0, 2));
		if (i < j)
			return -1;
		else if (i > j)
			return 1;
		return 0;
	}
	
	public int eligibleTicketRefund (String screeningDate, String screeningTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		screeningDate += " " + screeningTime;
		LocalDateTime timeVar = LocalDateTime.parse(screeningDate , formatter);
		LocalDateTime now = LocalDateTime.now().plusHours(3);
		if (now.isBefore(timeVar)) {
			return 3;
		}
		now = LocalDateTime.now().plusHours(1);
		if (now.isBefore(timeVar)) {
			return 1;
		}
		return 0;
	}
	
	public int eligibleRentRefund (String transactionTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy ',' HH:mm");
		LocalDateTime timeVar = LocalDateTime.parse(transactionTime , formatter);
		LocalDateTime now = LocalDateTime.now().plusHours(1);
		if (now.isBefore(timeVar)) {
			return 1;
		}
		return 0;
	}
}