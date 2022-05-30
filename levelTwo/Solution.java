import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Solution {
	public static int[] solution(int[] data, int n) {
		HashMap<Integer, Integer> jobOccurence = new HashMap<>();
		Arrays.stream(data).forEachOrdered(item -> {
			if (jobOccurence.get(item) != null) {
				jobOccurence.put(item, jobOccurence.get(item) + 1);
			} else {
				jobOccurence.put(item, 1);
			}
		});

		List<Integer> returnList = new ArrayList<>();
		Arrays.stream(data).forEachOrdered(item -> {
			if (jobOccurence.get(item) <= n) {
				returnList.add(item);
			}
		});

		return returnList.stream().mapToInt(Integer::intValue).toArray();
	}
}
