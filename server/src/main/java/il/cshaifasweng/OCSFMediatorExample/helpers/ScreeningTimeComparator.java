package il.cshaifasweng.OCSFMediatorExample.helpers;


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
}