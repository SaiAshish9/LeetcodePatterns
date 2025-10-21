package greedy.jobSequencingWithDeadlines;

import java.util.*;

public class JobSequencingWithDeadlines {

    static class Job {
        int id, deadline, profit;

        Job(int id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    static int[] jobSequencing(Job[] jobs) {
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        int maxDeadline = 0;
        for (Job job : jobs) {
            maxDeadline = Math.max(maxDeadline, job.deadline);
        }

        int[] slots = new int[maxDeadline + 1];
        Arrays.fill(slots, -1); // -1 means slot is free

        int countJobs = 0, totalProfit = 0;

        for (Job job : jobs) {
            for (int i = job.deadline; i > 0; i--) {
                if (slots[i] == -1) {
                    slots[i] = job.id; // assign job
                    countJobs++;
                    totalProfit += job.profit;
                    break;
                }
            }
        }
        return new int[] { countJobs, totalProfit };
    }

    public static void main(String[] args) {

        Job[] jobs = {
                new Job(1, 4, 20),
                new Job(2, 1, 10),
                new Job(3, 1, 40),
                new Job(4, 1, 30)
        };

        int[] result = jobSequencing(jobs);
        System.out.println("Number of jobs done: " + result[0]); // Output: 2
        System.out.println("Total profit: " + result[1]); // Output: 60
    }
}

// Complexity Analysis
// Time Complexity: O(N log N) due to sorting the jobs based on profit.
// Space Complexity: O(D) where D is the maximum deadline, for the slots array.