package ua.com.jdbc.entity;

public class Solution {
    private int problem_id;
    private int cost;

    public int getProblem_id() {
        return problem_id;
    }

    public void setProblem_id(int problem_id) {
        this.problem_id = problem_id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "problem_id=" + problem_id +
                ", cost=" + cost +
                '}';
    }
}
